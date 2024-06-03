package com.stytch.java.b2b.api.scim

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Moshi
import com.stytch.java.b2b.api.scimconnection.Connection
import com.stytch.java.b2b.api.scimconnection.ConnectionImpl
import com.stytch.java.common.InstantAdapter
import com.stytch.java.http.HttpClient
import kotlinx.coroutines.CoroutineScope

public interface SCIM {
    public val connection: Connection
}

internal class SCIMImpl(
    private val httpClient: HttpClient,
    private val coroutineScope: CoroutineScope,
) : SCIM {
    private val moshi = Moshi.Builder().add(InstantAdapter()).build()

    override val connection: Connection = ConnectionImpl(httpClient, coroutineScope)
}
