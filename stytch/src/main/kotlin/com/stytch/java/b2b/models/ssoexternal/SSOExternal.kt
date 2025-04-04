package com.stytch.java.b2b.models.ssoexternal

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.stytch.java.b2b.models.sso.Connection
import com.stytch.java.b2b.models.sso.ConnectionImplicitRoleAssignment
import com.stytch.java.b2b.models.sso.GroupImplicitRoleAssignment
import com.stytch.java.b2b.models.sso.SAMLConnectionImplicitRoleAssignment
import com.stytch.java.b2b.models.sso.SAMLGroupImplicitRoleAssignment
import com.stytch.java.common.methodoptions.Authorization

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
* Request type for `External.createConnection`.
*/
@JsonClass(generateAdapter = true)
public data class CreateConnectionRequest
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that identifies a specific Organization. The `organization_id` is critical to perform operations
         * on an Organization, so be sure to preserve this value. You may also use the organization_slug here as a convenience.
         */
        @Json(name = "organization_id")
        val organizationId: String,
        /**
         * Globally unique UUID that identifies a different Organization within your Project.
         */
        @Json(name = "external_organization_id")
        val externalOrganizationId: String,
        /**
         * Globally unique UUID that identifies a specific SSO connection configured for a different Organization in your Project.
         */
        @Json(name = "external_connection_id")
        val externalConnectionId: String,
        /**
         * A human-readable display name for the connection.
         */
        @Json(name = "display_name")
        val displayName: String? = null,
        @Json(name = "connection_implicit_role_assignments")
        val connectionImplicitRoleAssignments: List<SAMLConnectionImplicitRoleAssignment>? = emptyList(),
        @Json(name = "group_implicit_role_assignments")
        val groupImplicitRoleAssignments: List<SAMLGroupImplicitRoleAssignment>? = emptyList(),
    )

/**
* Response type for `External.createConnection`.
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
         * The `External Connection` object affected by this API call. See the
         * [External Connection Object](https://stytch.com/docs/b2b/api/external-connection-object) for complete response field
         * details.
         */
        @Json(name = "connection")
        val connection: Connection? = null,
    )

/**
* Request type for `External.updateConnection`.
*/
@JsonClass(generateAdapter = true)
public data class UpdateConnectionRequest
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that identifies a specific Organization. The `organization_id` is critical to perform operations
         * on an Organization, so be sure to preserve this value. You may also use the organization_slug here as a convenience.
         */
        @Json(name = "organization_id")
        val organizationId: String,
        /**
         * Globally unique UUID that identifies a specific External SSO Connection.
         */
        @Json(name = "connection_id")
        val connectionId: String,
        /**
         * A human-readable display name for the connection.
         */
        @Json(name = "display_name")
        val displayName: String? = null,
        /**
         * All Members who log in with this External connection will implicitly receive the specified Roles. See the
         * [RBAC guide](https://stytch.com/docs/b2b/guides/rbac/role-assignment) for more information about role assignment.
         * Implicit role assignments are not supported for External connections if the underlying SSO connection is an OIDC
         * connection.
         */
        @Json(name = "external_connection_implicit_role_assignments")
        val externalConnectionImplicitRoleAssignments: List<ConnectionImplicitRoleAssignment>? = emptyList(),
        /**
         * Defines the names of the groups
         *  that grant specific role assignments. For each group-Role pair, if a Member logs in with this external connection and
         *  belongs to the specified group, they will be granted the associated Role. See the
         *  [RBAC guide](https://stytch.com/docs/b2b/guides/rbac/role-assignment) for more information about role assignment.
         * Before adding any group implicit role assignments to an external connection, you must add a "groups" key to the
         * underlying SAML connection's
         *          `attribute_mapping`. Make sure that the SAML connection IdP is configured to correctly send the group
         * information. Implicit role assignments are not supported
         *          for External connections if the underlying SSO connection is an OIDC connection.
         */
        @Json(name = "external_group_implicit_role_assignments")
        val externalGroupImplicitRoleAssignments: List<GroupImplicitRoleAssignment>? = emptyList(),
    )

/**
* Response type for `External.updateConnection`.
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
         * The `External Connection` object affected by this API call. See the
         * [External Connection Object](https://stytch.com/docs/b2b/api/external-connection-object) for complete response field
         * details.
         */
        @Json(name = "connection")
        val connection: Connection? = null,
    )
