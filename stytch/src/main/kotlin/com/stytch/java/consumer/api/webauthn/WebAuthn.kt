package com.stytch.java.consumer.api.webauthn

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.stytch.java.common.InstantAdapter
import com.stytch.java.common.StytchResult
import com.stytch.java.consumer.models.webauthn.AuthenticateRequest
import com.stytch.java.consumer.models.webauthn.AuthenticateResponse
import com.stytch.java.consumer.models.webauthn.AuthenticateStartRequest
import com.stytch.java.consumer.models.webauthn.AuthenticateStartResponse
import com.stytch.java.consumer.models.webauthn.ListCredentialsRequest
import com.stytch.java.consumer.models.webauthn.ListCredentialsResponse
import com.stytch.java.consumer.models.webauthn.RegisterRequest
import com.stytch.java.consumer.models.webauthn.RegisterResponse
import com.stytch.java.consumer.models.webauthn.RegisterStartRequest
import com.stytch.java.consumer.models.webauthn.RegisterStartResponse
import com.stytch.java.consumer.models.webauthn.UpdateRequest
import com.stytch.java.consumer.models.webauthn.UpdateResponse
import com.stytch.java.http.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.future.asCompletableFuture
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.CompletableFuture

public interface WebAuthn {
    /**
     * Initiate the process of creating a new Passkey or WebAuthn registration.
     *
     * To optimize for Passkeys, set the `return_passkey_credential_options` field to `true`.
     *
     * After calling this endpoint, the browser will need to call
     * [navigator.credentials.create()](https://www.w3.org/TR/webauthn-2/#sctn-createCredential) with the data from
     * [public_key_credential_creation_options](https://w3c.github.io/webauthn/#dictionary-makecredentialoptions) passed to
     * the [navigator.credentials.create()](https://www.w3.org/TR/webauthn-2/#sctn-createCredential) request via the public
     * key argument. We recommend using the `create()` wrapper provided by the webauthn-json library.
     *
     * If you are not using the [webauthn-json](https://github.com/github/webauthn-json) library, the
     * `public_key_credential_creation_options` will need to be converted to a suitable public key by unmarshalling the JSON,
     * base64 decoding the user ID field, and converting user ID and the challenge fields into an array buffer.
     */
    public suspend fun registerStart(data: RegisterStartRequest): StytchResult<RegisterStartResponse>

    /**
     * Initiate the process of creating a new Passkey or WebAuthn registration.
     *
     * To optimize for Passkeys, set the `return_passkey_credential_options` field to `true`.
     *
     * After calling this endpoint, the browser will need to call
     * [navigator.credentials.create()](https://www.w3.org/TR/webauthn-2/#sctn-createCredential) with the data from
     * [public_key_credential_creation_options](https://w3c.github.io/webauthn/#dictionary-makecredentialoptions) passed to
     * the [navigator.credentials.create()](https://www.w3.org/TR/webauthn-2/#sctn-createCredential) request via the public
     * key argument. We recommend using the `create()` wrapper provided by the webauthn-json library.
     *
     * If you are not using the [webauthn-json](https://github.com/github/webauthn-json) library, the
     * `public_key_credential_creation_options` will need to be converted to a suitable public key by unmarshalling the JSON,
     * base64 decoding the user ID field, and converting user ID and the challenge fields into an array buffer.
     */
    public fun registerStart(
        data: RegisterStartRequest,
        callback: (StytchResult<RegisterStartResponse>) -> Unit,
    )

    /**
     * Initiate the process of creating a new Passkey or WebAuthn registration.
     *
     * To optimize for Passkeys, set the `return_passkey_credential_options` field to `true`.
     *
     * After calling this endpoint, the browser will need to call
     * [navigator.credentials.create()](https://www.w3.org/TR/webauthn-2/#sctn-createCredential) with the data from
     * [public_key_credential_creation_options](https://w3c.github.io/webauthn/#dictionary-makecredentialoptions) passed to
     * the [navigator.credentials.create()](https://www.w3.org/TR/webauthn-2/#sctn-createCredential) request via the public
     * key argument. We recommend using the `create()` wrapper provided by the webauthn-json library.
     *
     * If you are not using the [webauthn-json](https://github.com/github/webauthn-json) library, the
     * `public_key_credential_creation_options` will need to be converted to a suitable public key by unmarshalling the JSON,
     * base64 decoding the user ID field, and converting user ID and the challenge fields into an array buffer.
     */
    public fun registerStartCompletable(data: RegisterStartRequest): CompletableFuture<StytchResult<RegisterStartResponse>>

    /**
     * Complete the creation of a WebAuthn registration by passing the response from the
     * [navigator.credentials.create()](https://www.w3.org/TR/webauthn-2/#sctn-createCredential) request to this endpoint as
     * the `public_key_credential` parameter.
     *
     * If the [webauthn-json](https://github.com/github/webauthn-json) library's `create()` method was used, the response can
     * be passed directly to the [register endpoint](https://stytch.com/docs/api/webauthn-register). If not, some fields (the
     * client data and the attestation object) from the
     * [navigator.credentials.create()](https://www.w3.org/TR/webauthn-2/#sctn-createCredential) response will need to be
     * converted from array buffers to strings and marshalled into JSON.
     */
    public suspend fun register(data: RegisterRequest): StytchResult<RegisterResponse>

    /**
     * Complete the creation of a WebAuthn registration by passing the response from the
     * [navigator.credentials.create()](https://www.w3.org/TR/webauthn-2/#sctn-createCredential) request to this endpoint as
     * the `public_key_credential` parameter.
     *
     * If the [webauthn-json](https://github.com/github/webauthn-json) library's `create()` method was used, the response can
     * be passed directly to the [register endpoint](https://stytch.com/docs/api/webauthn-register). If not, some fields (the
     * client data and the attestation object) from the
     * [navigator.credentials.create()](https://www.w3.org/TR/webauthn-2/#sctn-createCredential) response will need to be
     * converted from array buffers to strings and marshalled into JSON.
     */
    public fun register(
        data: RegisterRequest,
        callback: (StytchResult<RegisterResponse>) -> Unit,
    )

    /**
     * Complete the creation of a WebAuthn registration by passing the response from the
     * [navigator.credentials.create()](https://www.w3.org/TR/webauthn-2/#sctn-createCredential) request to this endpoint as
     * the `public_key_credential` parameter.
     *
     * If the [webauthn-json](https://github.com/github/webauthn-json) library's `create()` method was used, the response can
     * be passed directly to the [register endpoint](https://stytch.com/docs/api/webauthn-register). If not, some fields (the
     * client data and the attestation object) from the
     * [navigator.credentials.create()](https://www.w3.org/TR/webauthn-2/#sctn-createCredential) response will need to be
     * converted from array buffers to strings and marshalled into JSON.
     */
    public fun registerCompletable(data: RegisterRequest): CompletableFuture<StytchResult<RegisterResponse>>

    /**
     * Initiate the authentication of a Passkey or WebAuthn registration.
     *
     * To optimize for Passkeys, set the `return_passkey_credential_options` field to `true`.
     *
     * After calling this endpoint, the browser will need to call
     * [navigator.credentials.get()](https://www.w3.org/TR/webauthn-2/#sctn-getAssertion) with the data from
     * `public_key_credential_request_options` passed to the
     * [navigator.credentials.get()](https://www.w3.org/TR/webauthn-2/#sctn-getAssertion) request via the public key argument.
     * We recommend using the `get()` wrapper provided by the webauthn-json library.
     *
     * If you are not using the [webauthn-json](https://github.com/github/webauthn-json) library, `the
     * public_key_credential_request_options` will need to be converted to a suitable public key by unmarshalling the JSON and
     * converting some the fields to array buffers.
     */
    public suspend fun authenticateStart(data: AuthenticateStartRequest): StytchResult<AuthenticateStartResponse>

    /**
     * Initiate the authentication of a Passkey or WebAuthn registration.
     *
     * To optimize for Passkeys, set the `return_passkey_credential_options` field to `true`.
     *
     * After calling this endpoint, the browser will need to call
     * [navigator.credentials.get()](https://www.w3.org/TR/webauthn-2/#sctn-getAssertion) with the data from
     * `public_key_credential_request_options` passed to the
     * [navigator.credentials.get()](https://www.w3.org/TR/webauthn-2/#sctn-getAssertion) request via the public key argument.
     * We recommend using the `get()` wrapper provided by the webauthn-json library.
     *
     * If you are not using the [webauthn-json](https://github.com/github/webauthn-json) library, `the
     * public_key_credential_request_options` will need to be converted to a suitable public key by unmarshalling the JSON and
     * converting some the fields to array buffers.
     */
    public fun authenticateStart(
        data: AuthenticateStartRequest,
        callback: (StytchResult<AuthenticateStartResponse>) -> Unit,
    )

    /**
     * Initiate the authentication of a Passkey or WebAuthn registration.
     *
     * To optimize for Passkeys, set the `return_passkey_credential_options` field to `true`.
     *
     * After calling this endpoint, the browser will need to call
     * [navigator.credentials.get()](https://www.w3.org/TR/webauthn-2/#sctn-getAssertion) with the data from
     * `public_key_credential_request_options` passed to the
     * [navigator.credentials.get()](https://www.w3.org/TR/webauthn-2/#sctn-getAssertion) request via the public key argument.
     * We recommend using the `get()` wrapper provided by the webauthn-json library.
     *
     * If you are not using the [webauthn-json](https://github.com/github/webauthn-json) library, `the
     * public_key_credential_request_options` will need to be converted to a suitable public key by unmarshalling the JSON and
     * converting some the fields to array buffers.
     */
    public fun authenticateStartCompletable(data: AuthenticateStartRequest): CompletableFuture<StytchResult<AuthenticateStartResponse>>

    /**
     * Complete the authentication of a Passkey or WebAuthn registration by passing the response from the
     * [navigator.credentials.get()](https://www.w3.org/TR/webauthn-2/#sctn-getAssertion) request to the authenticate
     * endpoint.
     *
     * If the [webauthn-json](https://github.com/github/webauthn-json) library's `get()` method was used, the response can be
     * passed directly to the [authenticate endpoint](https://stytch.com/docs/api/webauthn-authenticate). If not some fields
     * from the [navigator.credentials.get()](https://www.w3.org/TR/webauthn-2/#sctn-getAssertion) response will need to be
     * converted from array buffers to strings and marshalled into JSON.
     */
    public suspend fun authenticate(data: AuthenticateRequest): StytchResult<AuthenticateResponse>

    /**
     * Complete the authentication of a Passkey or WebAuthn registration by passing the response from the
     * [navigator.credentials.get()](https://www.w3.org/TR/webauthn-2/#sctn-getAssertion) request to the authenticate
     * endpoint.
     *
     * If the [webauthn-json](https://github.com/github/webauthn-json) library's `get()` method was used, the response can be
     * passed directly to the [authenticate endpoint](https://stytch.com/docs/api/webauthn-authenticate). If not some fields
     * from the [navigator.credentials.get()](https://www.w3.org/TR/webauthn-2/#sctn-getAssertion) response will need to be
     * converted from array buffers to strings and marshalled into JSON.
     */
    public fun authenticate(
        data: AuthenticateRequest,
        callback: (StytchResult<AuthenticateResponse>) -> Unit,
    )

    /**
     * Complete the authentication of a Passkey or WebAuthn registration by passing the response from the
     * [navigator.credentials.get()](https://www.w3.org/TR/webauthn-2/#sctn-getAssertion) request to the authenticate
     * endpoint.
     *
     * If the [webauthn-json](https://github.com/github/webauthn-json) library's `get()` method was used, the response can be
     * passed directly to the [authenticate endpoint](https://stytch.com/docs/api/webauthn-authenticate). If not some fields
     * from the [navigator.credentials.get()](https://www.w3.org/TR/webauthn-2/#sctn-getAssertion) response will need to be
     * converted from array buffers to strings and marshalled into JSON.
     */
    public fun authenticateCompletable(data: AuthenticateRequest): CompletableFuture<StytchResult<AuthenticateResponse>>

    /**
     * Updates a Passkey or WebAuthn registration.
     */
    public suspend fun update(data: UpdateRequest): StytchResult<UpdateResponse>

    /**
     * Updates a Passkey or WebAuthn registration.
     */
    public fun update(
        data: UpdateRequest,
        callback: (StytchResult<UpdateResponse>) -> Unit,
    )

    /**
     * Updates a Passkey or WebAuthn registration.
     */
    public fun updateCompletable(data: UpdateRequest): CompletableFuture<StytchResult<UpdateResponse>>

    /**
     * List the public key credentials of the WebAuthn Registrations or Passkeys registered to a specific User.
     */
    public suspend fun listCredentials(data: ListCredentialsRequest): StytchResult<ListCredentialsResponse>

    /**
     * List the public key credentials of the WebAuthn Registrations or Passkeys registered to a specific User.
     */
    public fun listCredentials(
        data: ListCredentialsRequest,
        callback: (StytchResult<ListCredentialsResponse>) -> Unit,
    )

    /**
     * List the public key credentials of the WebAuthn Registrations or Passkeys registered to a specific User.
     */
    public fun listCredentialsCompletable(data: ListCredentialsRequest): CompletableFuture<StytchResult<ListCredentialsResponse>>
}

internal class WebAuthnImpl(
    private val httpClient: HttpClient,
    private val coroutineScope: CoroutineScope,
) : WebAuthn {
    private val moshi = Moshi.Builder().add(InstantAdapter()).build()

    override suspend fun registerStart(data: RegisterStartRequest): StytchResult<RegisterStartResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(RegisterStartRequest::class.java).toJson(data)
            httpClient.post("/v1/webauthn/register/start", asJson, headers)
        }

    override fun registerStart(
        data: RegisterStartRequest,
        callback: (StytchResult<RegisterStartResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(registerStart(data))
        }
    }

    override fun registerStartCompletable(data: RegisterStartRequest): CompletableFuture<StytchResult<RegisterStartResponse>> =
        coroutineScope.async {
            registerStart(data)
        }.asCompletableFuture()

    override suspend fun register(data: RegisterRequest): StytchResult<RegisterResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(RegisterRequest::class.java).toJson(data)
            httpClient.post("/v1/webauthn/register", asJson, headers)
        }

    override fun register(
        data: RegisterRequest,
        callback: (StytchResult<RegisterResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(register(data))
        }
    }

    override fun registerCompletable(data: RegisterRequest): CompletableFuture<StytchResult<RegisterResponse>> =
        coroutineScope.async {
            register(data)
        }.asCompletableFuture()

    override suspend fun authenticateStart(data: AuthenticateStartRequest): StytchResult<AuthenticateStartResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(AuthenticateStartRequest::class.java).toJson(data)
            httpClient.post("/v1/webauthn/authenticate/start", asJson, headers)
        }

    override fun authenticateStart(
        data: AuthenticateStartRequest,
        callback: (StytchResult<AuthenticateStartResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(authenticateStart(data))
        }
    }

    override fun authenticateStartCompletable(data: AuthenticateStartRequest): CompletableFuture<StytchResult<AuthenticateStartResponse>> =
        coroutineScope.async {
            authenticateStart(data)
        }.asCompletableFuture()

    override suspend fun authenticate(data: AuthenticateRequest): StytchResult<AuthenticateResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(AuthenticateRequest::class.java).toJson(data)
            httpClient.post("/v1/webauthn/authenticate", asJson, headers)
        }

    override fun authenticate(
        data: AuthenticateRequest,
        callback: (StytchResult<AuthenticateResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(authenticate(data))
        }
    }

    override fun authenticateCompletable(data: AuthenticateRequest): CompletableFuture<StytchResult<AuthenticateResponse>> =
        coroutineScope.async {
            authenticate(data)
        }.asCompletableFuture()

    override suspend fun update(data: UpdateRequest): StytchResult<UpdateResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(UpdateRequest::class.java).toJson(data)
            httpClient.put("/v1/webauthn/${data.webauthnRegistrationId}", asJson, headers)
        }

    override fun update(
        data: UpdateRequest,
        callback: (StytchResult<UpdateResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(update(data))
        }
    }

    override fun updateCompletable(data: UpdateRequest): CompletableFuture<StytchResult<UpdateResponse>> =
        coroutineScope.async {
            update(data)
        }.asCompletableFuture()

    override suspend fun listCredentials(data: ListCredentialsRequest): StytchResult<ListCredentialsResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(ListCredentialsRequest::class.java).toJson(data)
            val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
            val adapter: JsonAdapter<Map<String, Any>> = moshi.adapter(type)
            val asMap = adapter.fromJson(asJson) ?: emptyMap()
            httpClient.get("/v1/webauthn/credentials/${data.userId}/${data.domain}", asMap, headers)
        }

    override fun listCredentials(
        data: ListCredentialsRequest,
        callback: (StytchResult<ListCredentialsResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(listCredentials(data))
        }
    }

    override fun listCredentialsCompletable(data: ListCredentialsRequest): CompletableFuture<StytchResult<ListCredentialsResponse>> =
        coroutineScope.async {
            listCredentials(data)
        }.asCompletableFuture()
}
