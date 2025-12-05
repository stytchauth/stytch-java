package com.stytch.java.common

public sealed class StytchException(
    public open val reason: Any?,
) : Exception() {
    /**
     * Critical exception wrapper
     * @property reason provides a Throwable with information on what went wrong.
     * @property response provides the JSON response that was unable to be mapped
     */
    public data class Critical(
        override val reason: Throwable,
        val response: String? = null,
    ) : StytchException(reason)

    /**
     * Response exception wrapper
     * @property reason provides a StytchErrorResponse object with a reason as to what went wrong.
     */
    public data class Response(
        override val reason: ErrorResponse,
    ) : StytchException(reason)
}
