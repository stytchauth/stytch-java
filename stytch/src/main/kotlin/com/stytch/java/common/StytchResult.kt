package com.stytch.java.common

public sealed class StytchResult<out T> {
    /**
     * Data class that can hold a successful response from a Stytch endpoint
     * @property value is the value of the response
     */
    public data class Success<out T>(val value: T) : StytchResult<T>()

    /**
     * Data class that can hold a StytchException
     * @property exception provides information about what went wrong during an API call
     */
    public data class Error(val exception: StytchException) : StytchResult<Nothing>()
}
