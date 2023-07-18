package com.stytch.kotlin.http

import com.stytch.kotlin.common.StytchResult
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.slot
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.IOException
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
internal class HttpClientTest {
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    @MockK
    private lateinit var mockOkhttpClient: OkHttpClient
    private lateinit var httpClient: HttpClient

    @Before
    fun before() {
        Dispatchers.setMain(mainThreadSurrogate)
        MockKAnnotations.init(this, true, true)
        httpClient = spyk(HttpClient(
            baseUrl = "http://something",
            projectId = "project-id",
            secret = "secret",
            client = mockOkhttpClient
        ))
    }

    @Test
    fun `buildUrl correctly builds params`() {
        val noParams = httpClient.buildUrl("/my-path")
        assert(noParams.toString() == "http://something/my-path")

        val oneParam = httpClient.buildUrl("/my-path", mapOf("key1" to "value1"))
        assert(oneParam.toString() == "http://something/my-path?key1=value1")

        val multiParams = httpClient.buildUrl("/my-path", mapOf("key1" to "value1", "key2" to "value2"))
        assert(multiParams.toString() == "http://something/my-path?key1=value1&key2=value2")
    }

    @Test
    fun `mapResponseToClass returns as expected`() {
        val mockEmptyBodyResponse: Response = mockk {
            every { body } returns null
        }
        assert(httpClient.mapResponseToClass(mockEmptyBodyResponse, Any::class.java) == null)

        val mockExceptionalResponse: Response = mockk {
            every { body } returns mockk {
                every { source() } throws IOException()
            }
        }
        assert(httpClient.mapResponseToClass(mockExceptionalResponse, Any::class.java) == null)

        val mockUnmappableResponse: Response = mockk {
            every { body } returns mockk {
                every { source() } returns mockk(relaxed = true, relaxUnitFun = true)
            }
        }
        assert(httpClient.mapResponseToClass(mockUnmappableResponse, Any::class.java) == null)
    }

    @Test
    fun `makeRequest is nonblocking`() = runTest {
        val mockRequest: Request = mockk(relaxed = true, relaxUnitFun = true)
        val slot = slot<Callback>()
        val mockCall: Call = mockk {
            every { enqueue(capture(slot)) } answers {
                slot.captured.onFailure(mockk(), IOException())
            }
        }
        every { mockOkhttpClient.newCall(mockRequest) } returns mockCall
        try {
            httpClient.makeRequest(mockRequest, Any::class.java)
        } catch (_: IOException) {}
        verify { mockCall.enqueue(any()) }
    }

    @Test(expected = IOException::class)
    fun `makeRequest throws on failures`() = runTest {
        val mockRequest: Request = mockk(relaxed = true, relaxUnitFun = true)
        val slot = slot<Callback>()
        val mockCall: Call = mockk {
            every { enqueue(capture(slot)) } answers {
                slot.captured.onFailure(mockk(), IOException())
            }
        }
        every { mockOkhttpClient.newCall(mockRequest) } returns mockCall
        httpClient.makeRequest(mockRequest, Any::class.java)
    }

    @Test
    fun `makeRequest returns StytchResult_Error for unsuccessful requests`() = runTest {
        val mockRequest: Request = mockk(relaxed = true, relaxUnitFun = true)
        val slot = slot<Callback>()
        val mockCall: Call = mockk {
            every { enqueue(capture(slot)) } answers {
                slot.captured.onResponse(mockk(), mockk {
                    every { isSuccessful } returns false
                    every { body } returns null
                    every { close() } just runs
                })
            }
        }
        every { mockOkhttpClient.newCall(mockRequest) } returns mockCall
        val result = try {
            httpClient.makeRequest(mockRequest, StytchResult::class.java)
        } catch (_: IOException) {}
        assert(result is StytchResult.Error)
    }

    @Test
    fun `makeRequest returns StytchResult_Error for successful requests that are unmappable`() = runTest {
        val mockRequest: Request = mockk(relaxed = true, relaxUnitFun = true)
        val slot = slot<Callback>()
        val mockCall: Call = mockk {
            every { enqueue(capture(slot)) } answers {
                slot.captured.onResponse(mockk(), mockk {
                    every { isSuccessful } returns true
                    every { body } returns null
                    every { close() } just runs
                })
            }
        }
        every { mockOkhttpClient.newCall(mockRequest) } returns mockCall
        val result = httpClient.makeRequest(mockRequest, Any::class.java)
        assert(result is StytchResult.Error)
    }

    @Test
    fun `makeRequest returns StytchResult_Success for successful requests that are mappable`() = runTest {
        val mockRequest: Request = mockk(relaxed = true, relaxUnitFun = true)
        val mockResponse = Response.Builder().apply {
            request(mockRequest)
            protocol(Protocol.HTTP_2)
            code(200)
            message("")
            body("{}".toResponseBody("application/json".toMediaType()))
        }.build()
        val slot = slot<Callback>()
        val mockCall: Call = mockk {
            every { enqueue(capture(slot)) } answers {
                slot.captured.onResponse(mockk(), mockResponse)
            }
        }
        every { mockOkhttpClient.newCall(mockRequest) } returns mockCall
        val result = httpClient.makeRequest(mockRequest, Any::class.java)
        assert(result is StytchResult.Success)
    }
}