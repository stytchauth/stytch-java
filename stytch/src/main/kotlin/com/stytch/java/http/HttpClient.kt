package com.stytch.java.http

import com.squareup.moshi.Moshi
import com.stytch.java.common.ErrorResponse
import com.stytch.java.common.InstantAdapter
import com.stytch.java.common.SDK_NAME
import com.stytch.java.common.StytchException
import com.stytch.java.common.StytchResult
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Credentials
import okhttp3.Headers
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

private const val ONE_HUNDRED_TWENTY = 120L

private fun createHttpClient(
    projectId: String,
    secret: String,
): OkHttpClient {
    val credentials = Credentials.basic(username = projectId, password = secret)
    return OkHttpClient.Builder()
        .readTimeout(ONE_HUNDRED_TWENTY, TimeUnit.SECONDS)
        .writeTimeout(ONE_HUNDRED_TWENTY, TimeUnit.SECONDS)
        .connectTimeout(ONE_HUNDRED_TWENTY, TimeUnit.SECONDS)
        .addNetworkInterceptor {
            it.proceed(
                it.request()
                    .newBuilder()
                    // OkHttp is adding a charset to the content-type which is rejected by the API
                    // see: https://github.com/square/okhttp/issues/3081
                    .header("Content-Type", "application/json")
                    .header("User-Agent", SDK_NAME)
                    .header("Authorization", credentials)
                    .build(),
            )
        }
        .build()
}

internal class HttpClient(
    private val baseUrl: String,
    projectId: String,
    secret: String,
    private val client: OkHttpClient = createHttpClient(projectId, secret),
) {
    private val moshi = Moshi.Builder().add(InstantAdapter()).build()

    internal fun buildUrl(
        path: String,
        params: Map<String, Any> = emptyMap(),
    ): HttpUrl =
        "$baseUrl$path".toHttpUrl().newBuilder().apply {
            params.forEach { (key, value) ->
                addQueryParameter(key, value.toString())
            }
        }.build()

    internal inline fun <reified T> mapResponseToClass(
        response: Response,
        clazz: Class<T>,
    ): T? =
        try {
            response.body?.let {
                moshi.adapter(clazz).fromJson(it.source())
            }
        } catch (_: Exception) {
            null
        }

    internal suspend inline fun <reified T> makeRequest(
        request: Request,
        clazz: Class<T>,
    ): StytchResult<T> =
        suspendCancellableCoroutine { cont ->
            client.newCall(request).enqueue(
                object : Callback {
                    override fun onFailure(
                        call: Call,
                        e: IOException,
                    ) {
                        if (cont.isCancelled) return
                        cont.resumeWithException(e)
                    }

                    override fun onResponse(
                        call: Call,
                        response: Response,
                    ) {
                        cont.resume(
                            response.use {
                                if (!response.isSuccessful) {
                                    return@use StytchResult.Error(
                                        mapResponseToClass(response, ErrorResponse::class.java)?.let {
                                            StytchException.Response(it)
                                        } ?: StytchException.Critical(
                                            reason = IllegalStateException("Unable to map error data"),
                                            response = response.body?.source()?.readUtf8(),
                                        ),
                                    )
                                }
                                return@use mapResponseToClass(response, clazz)?.let {
                                    StytchResult.Success(it)
                                } ?: StytchResult.Error(
                                    StytchException.Critical(
                                        reason = IllegalStateException("Unable to map response data"),
                                        response = response.body?.source()?.readUtf8(),
                                    ),
                                )
                            },
                        )
                    }
                },
            )
        }

    suspend inline fun <reified T> get(
        path: String,
        params: Map<String, Any> = emptyMap(),
        headers: Map<String, String> = emptyMap(),
    ): StytchResult<T> {
        val request =
            Request.Builder()
                .url(buildUrl(path, params))
                .headers(Headers.of(headers))
                .build()
        return try {
            makeRequest(request, T::class.java)
        } catch (e: Exception) {
            StytchResult.Error(StytchException.Critical(e))
        }
    }

    suspend inline fun <reified T> post(
        path: String,
        json: String,
        mediaType: MediaType = "application/json".toMediaType(),
        headers: Map<String, String> = emptyMap(),
    ): StytchResult<T> {
        val request =
            Request.Builder()
                .url(buildUrl(path))
                .post(json.toRequestBody(mediaType))
                .headers(Headers.of(headers))
                .build()
        return try {
            makeRequest(request, T::class.java)
        } catch (e: Exception) {
            StytchResult.Error(StytchException.Critical(e))
        }
    }

    suspend inline fun <reified T> put(
        path: String,
        json: String,
        mediaType: MediaType = "application/json".toMediaType(),
        headers: Map<String, String> = emptyMap(),
    ): StytchResult<T> {
        val request =
            Request.Builder()
                .url(buildUrl(path))
                .put(json.toRequestBody(mediaType))
                .headers(Headers.of(headers))
                .build()
        return try {
            makeRequest(request, T::class.java)
        } catch (e: Exception) {
            StytchResult.Error(StytchException.Critical(e))
        }
    }

    suspend inline fun <reified T> delete(
        path: String
        headers: Map<String, String> = emptyMap(),
    ): StytchResult<T> {
        val request =
            Request.Builder()
                .url(buildUrl(path, emptyMap()))
                .delete()
                .headers(Headers.of(headers))
                .build()
        return try {
            makeRequest(request, T::class.java)
        } catch (e: Exception) {
            StytchResult.Error(StytchException.Critical(e))
        }
    }
}
