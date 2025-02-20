package com.stytch.java.b2b.models.ssosaml

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.stytch.java.b2b.models.sso.SAMLConnection
import com.stytch.java.b2b.models.sso.SAMLConnectionImplicitRoleAssignment
import com.stytch.java.b2b.models.sso.SAMLGroupImplicitRoleAssignment
import com.stytch.java.common.methodoptions.Authorization

@JsonClass(generateAdapter = false)
public enum class CreateConnectionRequestIdentityProvider {
    @Json(name = "classlink")
    CLASSLINK,

    @Json(name = "cyberark")
    CYBERARK,

    @Json(name = "duo")
    DUO,

    @Json(name = "generic")
    GENERIC,

    @Json(name = "googleworkspace")
    GOOGLEWORKSPACE,

    @Json(name = "jumpcloud")
    JUMPCLOUD,

    @Json(name = "keycloak")
    KEYCLOAK,

    @Json(name = "miniorange")
    MINIORANGE,

    @Json(name = "microsoftentra")
    MICROSOFTENTRA,

    @Json(name = "okta")
    OKTA,

    @Json(name = "onelogin")
    ONELOGIN,

    @Json(name = "pingfederate")
    PINGFEDERATE,

    @Json(name = "rippling")
    RIPPLING,

    @Json(name = "salesforce")
    SALESFORCE,

    @Json(name = "shibboleth")
    SHIBBOLETH,
}

@JsonClass(generateAdapter = false)
public enum class UpdateConnectionRequestIdentityProvider {
    @Json(name = "classlink")
    CLASSLINK,

    @Json(name = "cyberark")
    CYBERARK,

    @Json(name = "duo")
    DUO,

    @Json(name = "generic")
    GENERIC,

    @Json(name = "googleworkspace")
    GOOGLEWORKSPACE,

    @Json(name = "jumpcloud")
    JUMPCLOUD,

    @Json(name = "keycloak")
    KEYCLOAK,

    @Json(name = "miniorange")
    MINIORANGE,

    @Json(name = "microsoftentra")
    MICROSOFTENTRA,

    @Json(name = "okta")
    OKTA,

    @Json(name = "onelogin")
    ONELOGIN,

    @Json(name = "pingfederate")
    PINGFEDERATE,

    @Json(name = "rippling")
    RIPPLING,

    @Json(name = "salesforce")
    SALESFORCE,

    @Json(name = "shibboleth")
    SHIBBOLETH,
}

public data class CreateConnectionRequestOptions
    @JvmOverloads
    constructor(
        /**
         * Optional authorization object.
         * Pass in an active Stytch Member session token or session JWT and the request
         * will be run using that member's permissions.
         */
        val authorization: Authorization? = null,
    ) {
        internal fun addHeaders(headers: Map<String, String> = emptyMap()): Map<String, String> {
            var res = mapOf<String, String>()
            if (authorization != null) {
                res = authorization.addHeaders(res)
            }
            return res + headers
        }
    }

public data class DeleteVerificationCertificateRequestOptions
    @JvmOverloads
    constructor(
        /**
         * Optional authorization object.
         * Pass in an active Stytch Member session token or session JWT and the request
         * will be run using that member's permissions.
         */
        val authorization: Authorization? = null,
    ) {
        internal fun addHeaders(headers: Map<String, String> = emptyMap()): Map<String, String> {
            var res = mapOf<String, String>()
            if (authorization != null) {
                res = authorization.addHeaders(res)
            }
            return res + headers
        }
    }

public data class UpdateByURLRequestOptions
    @JvmOverloads
    constructor(
        /**
         * Optional authorization object.
         * Pass in an active Stytch Member session token or session JWT and the request
         * will be run using that member's permissions.
         */
        val authorization: Authorization? = null,
    ) {
        internal fun addHeaders(headers: Map<String, String> = emptyMap()): Map<String, String> {
            var res = mapOf<String, String>()
            if (authorization != null) {
                res = authorization.addHeaders(res)
            }
            return res + headers
        }
    }

public data class UpdateConnectionRequestOptions
    @JvmOverloads
    constructor(
        /**
         * Optional authorization object.
         * Pass in an active Stytch Member session token or session JWT and the request
         * will be run using that member's permissions.
         */
        val authorization: Authorization? = null,
    ) {
        internal fun addHeaders(headers: Map<String, String> = emptyMap()): Map<String, String> {
            var res = mapOf<String, String>()
            if (authorization != null) {
                res = authorization.addHeaders(res)
            }
            return res + headers
        }
    }

/**
* Request type for `SAML.createConnection`.
*/
@JsonClass(generateAdapter = true)
public data class CreateConnectionRequest
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that identifies a specific Organization. The `organization_id` is critical to perform operations
         * on an Organization, so be sure to preserve this value.
         */
        @Json(name = "organization_id")
        val organizationId: String,
        /**
         * A human-readable display name for the connection.
         */
        @Json(name = "display_name")
        val displayName: String? = null,
        /**
         * Name of the IdP. Enum with possible values: `classlink`, `cyberark`, `duo`, `google-workspace`, `jumpcloud`,
         * `keycloak`, `miniorange`, `microsoft-entra`, `okta`, `onelogin`, `pingfederate`, `rippling`, `salesforce`,
         * `shibboleth`, or `generic`.
         *
         * Specifying a known provider allows Stytch to handle any provider-specific logic.
         */
        @Json(name = "identity_provider")
        val identityProvider: CreateConnectionRequestIdentityProvider? = null,
    )

/**
* Response type for `SAML.createConnection`.
*/
@JsonClass(generateAdapter = true)
public data class CreateConnectionResponse
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that is returned with every API call. This value is important to log for debugging purposes; we
         * may ask for this value to help identify a specific API call when helping you debug an issue.
         */
        @Json(name = "request_id")
        val requestId: String,
        /**
         * The HTTP status code of the response. Stytch follows standard HTTP response status code patterns, e.g. 2XX values
         * equate to success, 3XX values are redirects, 4XX are client errors, and 5XX are server errors.
         */
        @Json(name = "status_code")
        val statusCode: Int,
        /**
         * The `SAML Connection` object affected by this API call. See the
         * [SAML Connection Object](https://stytch.com/docs/b2b/api/saml-connection-object) for complete response field details.
         */
        @Json(name = "connection")
        val connection: SAMLConnection? = null,
    )

/**
* Request type for `SAML.deleteVerificationCertificate`.
*/
@JsonClass(generateAdapter = true)
public data class DeleteVerificationCertificateRequest
    @JvmOverloads
    constructor(
        /**
         * The organization ID that the SAML connection belongs to.
         */
        @Json(name = "organization_id")
        val organizationId: String,
        /**
         * The ID of the SAML connection.
         */
        @Json(name = "connection_id")
        val connectionId: String,
        /**
         * The ID of the certificate to be deleted.
         */
        @Json(name = "certificate_id")
        val certificateId: String,
    )

/**
* Response type for `SAML.deleteVerificationCertificate`.
*/
@JsonClass(generateAdapter = true)
public data class DeleteVerificationCertificateResponse
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that is returned with every API call. This value is important to log for debugging purposes; we
         * may ask for this value to help identify a specific API call when helping you debug an issue.
         */
        @Json(name = "request_id")
        val requestId: String,
        /**
         * The ID of the certificate that was deleted.
         */
        @Json(name = "certificate_id")
        val certificateId: String,
        /**
         * The HTTP status code of the response. Stytch follows standard HTTP response status code patterns, e.g. 2XX values
         * equate to success, 3XX values are redirects, 4XX are client errors, and 5XX are server errors.
         */
        @Json(name = "status_code")
        val statusCode: Int,
    )

/**
* Request type for `SAML.updateByURL`.
*/
@JsonClass(generateAdapter = true)
public data class UpdateByURLRequest
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that identifies a specific Organization. The `organization_id` is critical to perform operations
         * on an Organization, so be sure to preserve this value.
         */
        @Json(name = "organization_id")
        val organizationId: String,
        /**
         * Globally unique UUID that identifies a specific SSO `connection_id` for a Member.
         */
        @Json(name = "connection_id")
        val connectionId: String,
        /**
         * A URL that points to the IdP metadata. This will be provided by the IdP.
         */
        @Json(name = "metadata_url")
        val metadataURL: String,
    )

/**
* Response type for `SAML.updateByURL`.
*/
@JsonClass(generateAdapter = true)
public data class UpdateByURLResponse
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that is returned with every API call. This value is important to log for debugging purposes; we
         * may ask for this value to help identify a specific API call when helping you debug an issue.
         */
        @Json(name = "request_id")
        val requestId: String,
        /**
         * The HTTP status code of the response. Stytch follows standard HTTP response status code patterns, e.g. 2XX values
         * equate to success, 3XX values are redirects, 4XX are client errors, and 5XX are server errors.
         */
        @Json(name = "status_code")
        val statusCode: Int,
        /**
         * The `SAML Connection` object affected by this API call. See the
         * [SAML Connection Object](https://stytch.com/docs/b2b/api/saml-connection-object) for complete response field details.
         */
        @Json(name = "connection")
        val connection: SAMLConnection? = null,
    )

/**
* Request type for `SAML.updateConnection`.
*/
@JsonClass(generateAdapter = true)
public data class UpdateConnectionRequest
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that identifies a specific Organization. The `organization_id` is critical to perform operations
         * on an Organization, so be sure to preserve this value.
         */
        @Json(name = "organization_id")
        val organizationId: String,
        /**
         * Globally unique UUID that identifies a specific SSO `connection_id` for a Member.
         */
        @Json(name = "connection_id")
        val connectionId: String,
        /**
         * A globally unique name for the IdP. This will be provided by the IdP.
         */
        @Json(name = "idp_entity_id")
        val idpEntityId: String? = null,
        /**
         * A human-readable display name for the connection.
         */
        @Json(name = "display_name")
        val displayName: String? = null,
        /**
         * An object that represents the attributes used to identify a Member. This object will map the IdP-defined User
         * attributes to Stytch-specific values. Required attributes: `email` and one of `full_name` or `first_name` and
         * `last_name`.
         */
        @Json(name = "attribute_mapping")
        val attributeMapping: Map<String, Any?>? = emptyMap(),
        /**
         * A certificate that Stytch will use to verify the sign-in assertion sent by the IdP, in
         * [PEM](https://en.wikipedia.org/wiki/Privacy-Enhanced_Mail) format. See our
         * [X509 guide](https://stytch.com/docs/b2b/api/saml-certificates) for more info.
         */
        @Json(name = "x509_certificate")
        val x509Certificate: String? = null,
        /**
         * The URL for which assertions for login requests will be sent. This will be provided by the IdP.
         */
        @Json(name = "idp_sso_url")
        val idpSSOURL: String? = null,
        /**
         * All Members who log in with this SAML connection will implicitly receive the specified Roles. See the
         * [RBAC guide](https://stytch.com/docs/b2b/guides/rbac/role-assignment) for more information about role assignment.
         */
        @Json(name = "saml_connection_implicit_role_assignments")
        val samlConnectionImplicitRoleAssignments: List<SAMLConnectionImplicitRoleAssignment>? = emptyList(),
        /**
         * Defines the names of the SAML groups
         *  that grant specific role assignments. For each group-Role pair, if a Member logs in with this SAML connection and
         *  belongs to the specified SAML group, they will be granted the associated Role. See the
         *  [RBAC guide](https://stytch.com/docs/b2b/guides/rbac/role-assignment) for more information about role assignment.
         * Before adding any group implicit role assignments, you must add a "groups" key to your SAML connection's
         *          `attribute_mapping`. Make sure that your IdP is configured to correctly send the group information.
         */
        @Json(name = "saml_group_implicit_role_assignments")
        val samlGroupImplicitRoleAssignments: List<SAMLGroupImplicitRoleAssignment>? = emptyList(),
        /**
         * An alternative URL to use for the Audience Restriction. This value can be used when you wish to migrate an existing
         * SAML integration to Stytch with zero downtime. Read our
         * [SSO migration guide](https://stytch.com/docs/b2b/guides/migrations/additional-migration-considerations) for more info.
         */
        @Json(name = "alternative_audience_uri")
        val alternativeAudienceUri: String? = null,
        /**
         * Name of the IdP. Enum with possible values: `classlink`, `cyberark`, `duo`, `google-workspace`, `jumpcloud`,
         * `keycloak`, `miniorange`, `microsoft-entra`, `okta`, `onelogin`, `pingfederate`, `rippling`, `salesforce`,
         * `shibboleth`, or `generic`.
         *
         * Specifying a known provider allows Stytch to handle any provider-specific logic.
         */
        @Json(name = "identity_provider")
        val identityProvider: UpdateConnectionRequestIdentityProvider? = null,
        /**
         * A PKCS1 format RSA private key used for signing SAML requests. Only PKCS1 format (starting with "-----BEGIN RSA PRIVATE
         * KEY-----") is supported. When provided, Stytch will generate a new x509 certificate from this key and return it in the
         * signing_certificates array.
         */
        @Json(name = "signing_private_key")
        val signingPrivateKey: String? = null,
    )

/**
* Response type for `SAML.updateConnection`.
*/
@JsonClass(generateAdapter = true)
public data class UpdateConnectionResponse
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that is returned with every API call. This value is important to log for debugging purposes; we
         * may ask for this value to help identify a specific API call when helping you debug an issue.
         */
        @Json(name = "request_id")
        val requestId: String,
        /**
         * The HTTP status code of the response. Stytch follows standard HTTP response status code patterns, e.g. 2XX values
         * equate to success, 3XX values are redirects, 4XX are client errors, and 5XX are server errors.
         */
        @Json(name = "status_code")
        val statusCode: Int,
        /**
         * The `SAML Connection` object affected by this API call. See the
         * [SAML Connection Object](https://stytch.com/docs/b2b/api/saml-connection-object) for complete response field details.
         */
        @Json(name = "connection")
        val connection: SAMLConnection? = null,
    )
