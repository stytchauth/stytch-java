package com.stytch.kotlin.consumer.api.users

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.stytch.kotlin.common.StytchResult
import com.stytch.kotlin.consumer.models.users.CreateRequest
import com.stytch.kotlin.consumer.models.users.CreateResponse
import com.stytch.kotlin.consumer.models.users.DeleteBiometricRegistrationRequest
import com.stytch.kotlin.consumer.models.users.DeleteBiometricRegistrationResponse
import com.stytch.kotlin.consumer.models.users.DeleteCryptoWalletRequest
import com.stytch.kotlin.consumer.models.users.DeleteCryptoWalletResponse
import com.stytch.kotlin.consumer.models.users.DeleteEmailRequest
import com.stytch.kotlin.consumer.models.users.DeleteEmailResponse
import com.stytch.kotlin.consumer.models.users.DeleteOAuthRegistrationRequest
import com.stytch.kotlin.consumer.models.users.DeleteOAuthRegistrationResponse
import com.stytch.kotlin.consumer.models.users.DeletePasswordRequest
import com.stytch.kotlin.consumer.models.users.DeletePasswordResponse
import com.stytch.kotlin.consumer.models.users.DeletePhoneNumberRequest
import com.stytch.kotlin.consumer.models.users.DeletePhoneNumberResponse
import com.stytch.kotlin.consumer.models.users.DeleteRequest
import com.stytch.kotlin.consumer.models.users.DeleteResponse
import com.stytch.kotlin.consumer.models.users.DeleteTOTPRequest
import com.stytch.kotlin.consumer.models.users.DeleteTOTPResponse
import com.stytch.kotlin.consumer.models.users.DeleteWebAuthnRegistrationRequest
import com.stytch.kotlin.consumer.models.users.DeleteWebAuthnRegistrationResponse
import com.stytch.kotlin.consumer.models.users.GetRequest
import com.stytch.kotlin.consumer.models.users.GetResponse
import com.stytch.kotlin.consumer.models.users.SearchRequest
import com.stytch.kotlin.consumer.models.users.SearchResponse
import com.stytch.kotlin.consumer.models.users.UpdateRequest
import com.stytch.kotlin.consumer.models.users.UpdateResponse
import com.stytch.kotlin.http.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors

public interface Users {
    /**
     * Add a User to Stytch. A `user_id` is returned in the response that can then be used to perform other operations within
     * Stytch. An `email` or a `phone_number` is required.
     */
    public suspend fun create(data: CreateRequest): StytchResult<CreateResponse>

    /**
     * Add a User to Stytch. A `user_id` is returned in the response that can then be used to perform other operations within
     * Stytch. An `email` or a `phone_number` is required.
     */
    public fun create(data: CreateRequest, callback: (StytchResult<CreateResponse>) -> Unit)

    /**
     * Add a User to Stytch. A `user_id` is returned in the response that can then be used to perform other operations within
     * Stytch. An `email` or a `phone_number` is required.
     */
    public fun createCompletable(data: CreateRequest): CompletableFuture<StytchResult<CreateResponse>>

    /**
     * Get information about a specific User.
     */
    public suspend fun get(data: GetRequest): StytchResult<GetResponse>

    /**
     * Get information about a specific User.
     */
    public fun get(data: GetRequest, callback: (StytchResult<GetResponse>) -> Unit)

    /**
     * Get information about a specific User.
     */
    public fun getCompletable(data: GetRequest): CompletableFuture<StytchResult<GetResponse>>

    /**
     * Search for Users within your Stytch Project. Submit an empty `query` in the request to return all Users.
     */
    public suspend fun search(data: SearchRequest): StytchResult<SearchResponse>

    /**
     * Search for Users within your Stytch Project. Submit an empty `query` in the request to return all Users.
     */
    public fun search(data: SearchRequest, callback: (StytchResult<SearchResponse>) -> Unit)

    /**
     * Search for Users within your Stytch Project. Submit an empty `query` in the request to return all Users.
     */
    public fun searchCompletable(data: SearchRequest): CompletableFuture<StytchResult<SearchResponse>>

    /**
     * Update a User's attributes.
     *
     * **Note:** In order to add a new email address or phone number to an existing User object, pass the new email address or
     * phone number into the respective `/send` endpoint for the authentication method of your choice. If you specify the
     * existing User's `user_id` while calling the `/send` endpoint, the new email address or phone number will be added to
     * the existing User object upon successful authentication. We require this process to guard against an account takeover
     * vulnerability.
     */
    public suspend fun update(data: UpdateRequest): StytchResult<UpdateResponse>

    /**
     * Update a User's attributes.
     *
     * **Note:** In order to add a new email address or phone number to an existing User object, pass the new email address or
     * phone number into the respective `/send` endpoint for the authentication method of your choice. If you specify the
     * existing User's `user_id` while calling the `/send` endpoint, the new email address or phone number will be added to
     * the existing User object upon successful authentication. We require this process to guard against an account takeover
     * vulnerability.
     */
    public fun update(data: UpdateRequest, callback: (StytchResult<UpdateResponse>) -> Unit)

    /**
     * Update a User's attributes.
     *
     * **Note:** In order to add a new email address or phone number to an existing User object, pass the new email address or
     * phone number into the respective `/send` endpoint for the authentication method of your choice. If you specify the
     * existing User's `user_id` while calling the `/send` endpoint, the new email address or phone number will be added to
     * the existing User object upon successful authentication. We require this process to guard against an account takeover
     * vulnerability.
     */
    public fun updateCompletable(data: UpdateRequest): CompletableFuture<StytchResult<UpdateResponse>>

    /**
     * Delete a User from Stytch.
     */
    public suspend fun delete(data: DeleteRequest): StytchResult<DeleteResponse>

    /**
     * Delete a User from Stytch.
     */
    public fun delete(data: DeleteRequest, callback: (StytchResult<DeleteResponse>) -> Unit)

    /**
     * Delete a User from Stytch.
     */
    public fun deleteCompletable(data: DeleteRequest): CompletableFuture<StytchResult<DeleteResponse>>

    /**
     * Delete an email from a User.
     */
    public suspend fun deleteEmail(data: DeleteEmailRequest): StytchResult<DeleteEmailResponse>

    /**
     * Delete an email from a User.
     */
    public fun deleteEmail(data: DeleteEmailRequest, callback: (StytchResult<DeleteEmailResponse>) -> Unit)

    /**
     * Delete an email from a User.
     */
    public fun deleteEmailCompletable(data: DeleteEmailRequest): CompletableFuture<StytchResult<DeleteEmailResponse>>

    /**
     * Delete a phone number from a User.
     */
    public suspend fun deletePhoneNumber(data: DeletePhoneNumberRequest): StytchResult<DeletePhoneNumberResponse>

    /**
     * Delete a phone number from a User.
     */
    public fun deletePhoneNumber(data: DeletePhoneNumberRequest, callback: (StytchResult<DeletePhoneNumberResponse>) -> Unit)

    /**
     * Delete a phone number from a User.
     */
    public fun deletePhoneNumberCompletable(data: DeletePhoneNumberRequest): CompletableFuture<StytchResult<DeletePhoneNumberResponse>>

    /**
     * Delete a WebAuthn registration from a User.
     */
    public suspend fun deleteWebAuthnRegistration(data: DeleteWebAuthnRegistrationRequest): StytchResult<DeleteWebAuthnRegistrationResponse>

    /**
     * Delete a WebAuthn registration from a User.
     */
    public fun deleteWebAuthnRegistration(data: DeleteWebAuthnRegistrationRequest, callback: (StytchResult<DeleteWebAuthnRegistrationResponse>) -> Unit)

    /**
     * Delete a WebAuthn registration from a User.
     */
    public fun deleteWebAuthnRegistrationCompletable(data: DeleteWebAuthnRegistrationRequest): CompletableFuture<StytchResult<DeleteWebAuthnRegistrationResponse>>

    /**
     * Delete a biometric registration from a User.
     */
    public suspend fun deleteBiometricRegistration(data: DeleteBiometricRegistrationRequest): StytchResult<DeleteBiometricRegistrationResponse>

    /**
     * Delete a biometric registration from a User.
     */
    public fun deleteBiometricRegistration(data: DeleteBiometricRegistrationRequest, callback: (StytchResult<DeleteBiometricRegistrationResponse>) -> Unit)

    /**
     * Delete a biometric registration from a User.
     */
    public fun deleteBiometricRegistrationCompletable(data: DeleteBiometricRegistrationRequest): CompletableFuture<StytchResult<DeleteBiometricRegistrationResponse>>

    /**
     * Delete a TOTP from a User.
     */
    public suspend fun deleteTOTP(data: DeleteTOTPRequest): StytchResult<DeleteTOTPResponse>

    /**
     * Delete a TOTP from a User.
     */
    public fun deleteTOTP(data: DeleteTOTPRequest, callback: (StytchResult<DeleteTOTPResponse>) -> Unit)

    /**
     * Delete a TOTP from a User.
     */
    public fun deleteTOTPCompletable(data: DeleteTOTPRequest): CompletableFuture<StytchResult<DeleteTOTPResponse>>

    /**
     * Delete a crypto wallet from a User.
     */
    public suspend fun deleteCryptoWallet(data: DeleteCryptoWalletRequest): StytchResult<DeleteCryptoWalletResponse>

    /**
     * Delete a crypto wallet from a User.
     */
    public fun deleteCryptoWallet(data: DeleteCryptoWalletRequest, callback: (StytchResult<DeleteCryptoWalletResponse>) -> Unit)

    /**
     * Delete a crypto wallet from a User.
     */
    public fun deleteCryptoWalletCompletable(data: DeleteCryptoWalletRequest): CompletableFuture<StytchResult<DeleteCryptoWalletResponse>>

    /**
     * Delete a password from a User.
     */
    public suspend fun deletePassword(data: DeletePasswordRequest): StytchResult<DeletePasswordResponse>

    /**
     * Delete a password from a User.
     */
    public fun deletePassword(data: DeletePasswordRequest, callback: (StytchResult<DeletePasswordResponse>) -> Unit)

    /**
     * Delete a password from a User.
     */
    public fun deletePasswordCompletable(data: DeletePasswordRequest): CompletableFuture<StytchResult<DeletePasswordResponse>>

    /**
     * Delete an OAuth registration from a User.
     */
    public suspend fun deleteOAuthRegistration(data: DeleteOAuthRegistrationRequest): StytchResult<DeleteOAuthRegistrationResponse>

    /**
     * Delete an OAuth registration from a User.
     */
    public fun deleteOAuthRegistration(data: DeleteOAuthRegistrationRequest, callback: (StytchResult<DeleteOAuthRegistrationResponse>) -> Unit)

    /**
     * Delete an OAuth registration from a User.
     */
    public fun deleteOAuthRegistrationCompletable(data: DeleteOAuthRegistrationRequest): CompletableFuture<StytchResult<DeleteOAuthRegistrationResponse>>
}

internal class UsersImpl(
    private val httpClient: HttpClient,
    private val coroutineScope: CoroutineScope,
) : Users {

    private val moshi = Moshi.Builder().build()

    override suspend fun create(data: CreateRequest): StytchResult<CreateResponse> = withContext(Dispatchers.IO) {
        val asJson = moshi.adapter(CreateRequest::class.java).toJson(data)
        httpClient.post("/v1/users", asJson)
    }

    override fun create(data: CreateRequest, callback: (StytchResult<CreateResponse>) -> Unit) {
        coroutineScope.launch {
            callback(create(data))
        }
    }

    override fun createCompletable(data: CreateRequest): CompletableFuture<StytchResult<CreateResponse>> {
        val executor = Executors.newFixedThreadPool(1)
        return CompletableFuture.supplyAsync({
            val asJson = moshi.adapter(CreateRequest::class.java).toJson(data)
            httpClient.post("/v1/users", asJson)
        }, executor)
    }
    override suspend fun get(data: GetRequest): StytchResult<GetResponse> = withContext(Dispatchers.IO) {
        val asJson = moshi.adapter(GetRequest::class.java).toJson(data)
        val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
        val adapter: JsonAdapter<Map<String, Any>> = moshi.adapter(type)
        val asMap = adapter.fromJson(asJson) ?: emptyMap()
        httpClient.get("/v1/users/${data.userId}", asMap)
    }

    override fun get(data: GetRequest, callback: (StytchResult<GetResponse>) -> Unit) {
        coroutineScope.launch {
            callback(get(data))
        }
    }

    override fun getCompletable(data: GetRequest): CompletableFuture<StytchResult<GetResponse>> {
        val executor = Executors.newFixedThreadPool(1)
        return CompletableFuture.supplyAsync({
            val asJson = moshi.adapter(GetRequest::class.java).toJson(data)
            val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
            val adapter: JsonAdapter<Map<String, Any>> = moshi.adapter(type)
            val asMap = adapter.fromJson(asJson) ?: emptyMap()
            httpClient.get("/v1/users/${data.userId}", asMap)
        }, executor)
    }
    override suspend fun search(data: SearchRequest): StytchResult<SearchResponse> = withContext(Dispatchers.IO) {
        val asJson = moshi.adapter(SearchRequest::class.java).toJson(data)
        httpClient.post("/v1/users/search", asJson)
    }

    override fun search(data: SearchRequest, callback: (StytchResult<SearchResponse>) -> Unit) {
        coroutineScope.launch {
            callback(search(data))
        }
    }

    override fun searchCompletable(data: SearchRequest): CompletableFuture<StytchResult<SearchResponse>> {
        val executor = Executors.newFixedThreadPool(1)
        return CompletableFuture.supplyAsync({
            val asJson = moshi.adapter(SearchRequest::class.java).toJson(data)
            httpClient.post("/v1/users/search", asJson)
        }, executor)
    }
    override suspend fun update(data: UpdateRequest): StytchResult<UpdateResponse> = withContext(Dispatchers.IO) {
        val asJson = moshi.adapter(UpdateRequest::class.java).toJson(data)
        httpClient.put("/v1/users/${data.userId}", asJson)
    }

    override fun update(data: UpdateRequest, callback: (StytchResult<UpdateResponse>) -> Unit) {
        coroutineScope.launch {
            callback(update(data))
        }
    }

    override fun updateCompletable(data: UpdateRequest): CompletableFuture<StytchResult<UpdateResponse>> {
        val executor = Executors.newFixedThreadPool(1)
        return CompletableFuture.supplyAsync({
            val asJson = moshi.adapter(UpdateRequest::class.java).toJson(data)
            httpClient.put("/v1/users/${data.userId}", asJson)
        }, executor)
    }
    override suspend fun delete(data: DeleteRequest): StytchResult<DeleteResponse> = withContext(Dispatchers.IO) {
        val asJson = moshi.adapter(DeleteRequest::class.java).toJson(data)
        val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
        val adapter: JsonAdapter<Map<String, Any>> = moshi.adapter(type)
        val asMap = adapter.fromJson(asJson) ?: emptyMap()
        httpClient.delete("/v1/users/${data.userId}", asMap)
    }

    override fun delete(data: DeleteRequest, callback: (StytchResult<DeleteResponse>) -> Unit) {
        coroutineScope.launch {
            callback(delete(data))
        }
    }

    override fun deleteCompletable(data: DeleteRequest): CompletableFuture<StytchResult<DeleteResponse>> {
        val executor = Executors.newFixedThreadPool(1)
        return CompletableFuture.supplyAsync({
            val asJson = moshi.adapter(DeleteRequest::class.java).toJson(data)
            val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
            val adapter: JsonAdapter<Map<String, Any>> = moshi.adapter(type)
            val asMap = adapter.fromJson(asJson) ?: emptyMap()
            httpClient.delete("/v1/users/${data.userId}", asMap)
        }, executor)
    }
    override suspend fun deleteEmail(data: DeleteEmailRequest): StytchResult<DeleteEmailResponse> = withContext(Dispatchers.IO) {
        val asJson = moshi.adapter(DeleteEmailRequest::class.java).toJson(data)
        val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
        val adapter: JsonAdapter<Map<String, Any>> = moshi.adapter(type)
        val asMap = adapter.fromJson(asJson) ?: emptyMap()
        httpClient.delete("/v1/users/emails/${data.emailId}", asMap)
    }

    override fun deleteEmail(data: DeleteEmailRequest, callback: (StytchResult<DeleteEmailResponse>) -> Unit) {
        coroutineScope.launch {
            callback(deleteEmail(data))
        }
    }

    override fun deleteEmailCompletable(data: DeleteEmailRequest): CompletableFuture<StytchResult<DeleteEmailResponse>> {
        val executor = Executors.newFixedThreadPool(1)
        return CompletableFuture.supplyAsync({
            val asJson = moshi.adapter(DeleteEmailRequest::class.java).toJson(data)
            val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
            val adapter: JsonAdapter<Map<String, Any>> = moshi.adapter(type)
            val asMap = adapter.fromJson(asJson) ?: emptyMap()
            httpClient.delete("/v1/users/emails/${data.emailId}", asMap)
        }, executor)
    }
    override suspend fun deletePhoneNumber(data: DeletePhoneNumberRequest): StytchResult<DeletePhoneNumberResponse> = withContext(Dispatchers.IO) {
        val asJson = moshi.adapter(DeletePhoneNumberRequest::class.java).toJson(data)
        val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
        val adapter: JsonAdapter<Map<String, Any>> = moshi.adapter(type)
        val asMap = adapter.fromJson(asJson) ?: emptyMap()
        httpClient.delete("/v1/users/phone_numbers/${data.phoneId}", asMap)
    }

    override fun deletePhoneNumber(data: DeletePhoneNumberRequest, callback: (StytchResult<DeletePhoneNumberResponse>) -> Unit) {
        coroutineScope.launch {
            callback(deletePhoneNumber(data))
        }
    }

    override fun deletePhoneNumberCompletable(data: DeletePhoneNumberRequest): CompletableFuture<StytchResult<DeletePhoneNumberResponse>> {
        val executor = Executors.newFixedThreadPool(1)
        return CompletableFuture.supplyAsync({
            val asJson = moshi.adapter(DeletePhoneNumberRequest::class.java).toJson(data)
            val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
            val adapter: JsonAdapter<Map<String, Any>> = moshi.adapter(type)
            val asMap = adapter.fromJson(asJson) ?: emptyMap()
            httpClient.delete("/v1/users/phone_numbers/${data.phoneId}", asMap)
        }, executor)
    }
    override suspend fun deleteWebAuthnRegistration(data: DeleteWebAuthnRegistrationRequest): StytchResult<DeleteWebAuthnRegistrationResponse> = withContext(Dispatchers.IO) {
        val asJson = moshi.adapter(DeleteWebAuthnRegistrationRequest::class.java).toJson(data)
        val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
        val adapter: JsonAdapter<Map<String, Any>> = moshi.adapter(type)
        val asMap = adapter.fromJson(asJson) ?: emptyMap()
        httpClient.delete("/v1/users/webauthn_registrations/${data.webauthnRegistrationId}", asMap)
    }

    override fun deleteWebAuthnRegistration(data: DeleteWebAuthnRegistrationRequest, callback: (StytchResult<DeleteWebAuthnRegistrationResponse>) -> Unit) {
        coroutineScope.launch {
            callback(deleteWebAuthnRegistration(data))
        }
    }

    override fun deleteWebAuthnRegistrationCompletable(data: DeleteWebAuthnRegistrationRequest): CompletableFuture<StytchResult<DeleteWebAuthnRegistrationResponse>> {
        val executor = Executors.newFixedThreadPool(1)
        return CompletableFuture.supplyAsync({
            val asJson = moshi.adapter(DeleteWebAuthnRegistrationRequest::class.java).toJson(data)
            val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
            val adapter: JsonAdapter<Map<String, Any>> = moshi.adapter(type)
            val asMap = adapter.fromJson(asJson) ?: emptyMap()
            httpClient.delete("/v1/users/webauthn_registrations/${data.webauthnRegistrationId}", asMap)
        }, executor)
    }
    override suspend fun deleteBiometricRegistration(data: DeleteBiometricRegistrationRequest): StytchResult<DeleteBiometricRegistrationResponse> = withContext(Dispatchers.IO) {
        val asJson = moshi.adapter(DeleteBiometricRegistrationRequest::class.java).toJson(data)
        val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
        val adapter: JsonAdapter<Map<String, Any>> = moshi.adapter(type)
        val asMap = adapter.fromJson(asJson) ?: emptyMap()
        httpClient.delete("/v1/users/biometric_registrations/${data.biometricRegistrationId}", asMap)
    }

    override fun deleteBiometricRegistration(data: DeleteBiometricRegistrationRequest, callback: (StytchResult<DeleteBiometricRegistrationResponse>) -> Unit) {
        coroutineScope.launch {
            callback(deleteBiometricRegistration(data))
        }
    }

    override fun deleteBiometricRegistrationCompletable(data: DeleteBiometricRegistrationRequest): CompletableFuture<StytchResult<DeleteBiometricRegistrationResponse>> {
        val executor = Executors.newFixedThreadPool(1)
        return CompletableFuture.supplyAsync({
            val asJson = moshi.adapter(DeleteBiometricRegistrationRequest::class.java).toJson(data)
            val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
            val adapter: JsonAdapter<Map<String, Any>> = moshi.adapter(type)
            val asMap = adapter.fromJson(asJson) ?: emptyMap()
            httpClient.delete("/v1/users/biometric_registrations/${data.biometricRegistrationId}", asMap)
        }, executor)
    }
    override suspend fun deleteTOTP(data: DeleteTOTPRequest): StytchResult<DeleteTOTPResponse> = withContext(Dispatchers.IO) {
        val asJson = moshi.adapter(DeleteTOTPRequest::class.java).toJson(data)
        val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
        val adapter: JsonAdapter<Map<String, Any>> = moshi.adapter(type)
        val asMap = adapter.fromJson(asJson) ?: emptyMap()
        httpClient.delete("/v1/users/totps/${data.totpId}", asMap)
    }

    override fun deleteTOTP(data: DeleteTOTPRequest, callback: (StytchResult<DeleteTOTPResponse>) -> Unit) {
        coroutineScope.launch {
            callback(deleteTOTP(data))
        }
    }

    override fun deleteTOTPCompletable(data: DeleteTOTPRequest): CompletableFuture<StytchResult<DeleteTOTPResponse>> {
        val executor = Executors.newFixedThreadPool(1)
        return CompletableFuture.supplyAsync({
            val asJson = moshi.adapter(DeleteTOTPRequest::class.java).toJson(data)
            val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
            val adapter: JsonAdapter<Map<String, Any>> = moshi.adapter(type)
            val asMap = adapter.fromJson(asJson) ?: emptyMap()
            httpClient.delete("/v1/users/totps/${data.totpId}", asMap)
        }, executor)
    }
    override suspend fun deleteCryptoWallet(data: DeleteCryptoWalletRequest): StytchResult<DeleteCryptoWalletResponse> = withContext(Dispatchers.IO) {
        val asJson = moshi.adapter(DeleteCryptoWalletRequest::class.java).toJson(data)
        val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
        val adapter: JsonAdapter<Map<String, Any>> = moshi.adapter(type)
        val asMap = adapter.fromJson(asJson) ?: emptyMap()
        httpClient.delete("/v1/users/crypto_wallets/${data.cryptoWalletId}", asMap)
    }

    override fun deleteCryptoWallet(data: DeleteCryptoWalletRequest, callback: (StytchResult<DeleteCryptoWalletResponse>) -> Unit) {
        coroutineScope.launch {
            callback(deleteCryptoWallet(data))
        }
    }

    override fun deleteCryptoWalletCompletable(data: DeleteCryptoWalletRequest): CompletableFuture<StytchResult<DeleteCryptoWalletResponse>> {
        val executor = Executors.newFixedThreadPool(1)
        return CompletableFuture.supplyAsync({
            val asJson = moshi.adapter(DeleteCryptoWalletRequest::class.java).toJson(data)
            val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
            val adapter: JsonAdapter<Map<String, Any>> = moshi.adapter(type)
            val asMap = adapter.fromJson(asJson) ?: emptyMap()
            httpClient.delete("/v1/users/crypto_wallets/${data.cryptoWalletId}", asMap)
        }, executor)
    }
    override suspend fun deletePassword(data: DeletePasswordRequest): StytchResult<DeletePasswordResponse> = withContext(Dispatchers.IO) {
        val asJson = moshi.adapter(DeletePasswordRequest::class.java).toJson(data)
        val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
        val adapter: JsonAdapter<Map<String, Any>> = moshi.adapter(type)
        val asMap = adapter.fromJson(asJson) ?: emptyMap()
        httpClient.delete("/v1/users/passwords/${data.passwordId}", asMap)
    }

    override fun deletePassword(data: DeletePasswordRequest, callback: (StytchResult<DeletePasswordResponse>) -> Unit) {
        coroutineScope.launch {
            callback(deletePassword(data))
        }
    }

    override fun deletePasswordCompletable(data: DeletePasswordRequest): CompletableFuture<StytchResult<DeletePasswordResponse>> {
        val executor = Executors.newFixedThreadPool(1)
        return CompletableFuture.supplyAsync({
            val asJson = moshi.adapter(DeletePasswordRequest::class.java).toJson(data)
            val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
            val adapter: JsonAdapter<Map<String, Any>> = moshi.adapter(type)
            val asMap = adapter.fromJson(asJson) ?: emptyMap()
            httpClient.delete("/v1/users/passwords/${data.passwordId}", asMap)
        }, executor)
    }
    override suspend fun deleteOAuthRegistration(data: DeleteOAuthRegistrationRequest): StytchResult<DeleteOAuthRegistrationResponse> = withContext(Dispatchers.IO) {
        val asJson = moshi.adapter(DeleteOAuthRegistrationRequest::class.java).toJson(data)
        val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
        val adapter: JsonAdapter<Map<String, Any>> = moshi.adapter(type)
        val asMap = adapter.fromJson(asJson) ?: emptyMap()
        httpClient.delete("/v1/users/oauth/${data.oauthUserRegistrationId}", asMap)
    }

    override fun deleteOAuthRegistration(data: DeleteOAuthRegistrationRequest, callback: (StytchResult<DeleteOAuthRegistrationResponse>) -> Unit) {
        coroutineScope.launch {
            callback(deleteOAuthRegistration(data))
        }
    }

    override fun deleteOAuthRegistrationCompletable(data: DeleteOAuthRegistrationRequest): CompletableFuture<StytchResult<DeleteOAuthRegistrationResponse>> {
        val executor = Executors.newFixedThreadPool(1)
        return CompletableFuture.supplyAsync({
            val asJson = moshi.adapter(DeleteOAuthRegistrationRequest::class.java).toJson(data)
            val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
            val adapter: JsonAdapter<Map<String, Any>> = moshi.adapter(type)
            val asMap = adapter.fromJson(asJson) ?: emptyMap()
            httpClient.delete("/v1/users/oauth/${data.oauthUserRegistrationId}", asMap)
        }, executor)
    }
}
