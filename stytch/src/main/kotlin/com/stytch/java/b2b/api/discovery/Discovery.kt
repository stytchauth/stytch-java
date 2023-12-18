package com.stytch.java.b2b.api.discovery

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Moshi
import com.stytch.java.b2b.api.discoveryintermediatesessions.IntermediateSessions
import com.stytch.java.b2b.api.discoveryintermediatesessions.IntermediateSessionsImpl
import com.stytch.java.b2b.api.discoveryorganizations.Organizations
import com.stytch.java.b2b.api.discoveryorganizations.OrganizationsImpl
import com.stytch.java.common.InstantAdapter
import com.stytch.java.http.HttpClient
import kotlinx.coroutines.CoroutineScope

public interface Discovery {
    public val intermediateSessions: IntermediateSessions

    public val organizations: Organizations
}

internal class DiscoveryImpl(private val httpClient: HttpClient, private val coroutineScope: CoroutineScope) : Discovery {
    private val moshi = Moshi.Builder().add(InstantAdapter()).build()

    override val intermediateSessions: IntermediateSessions = IntermediateSessionsImpl(httpClient, coroutineScope)
    override val organizations: Organizations = OrganizationsImpl(httpClient, coroutineScope)
}
