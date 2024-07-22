# Stytch Java Library

The Stytch Java library makes it easy to use the Stytch user infrastructure API in Java, Kotlin and Scala applications.

It pairs well with the Stytch [Web SDK](https://www.npmjs.com/package/@stytch/vanilla-js) or your own custom authentication flow.

Check out our Java example app [here](https://github.com/stytchauth/stytch-java-magic-links).

## Install

```
implementation("com.stytch.java:sdk:1.1.0")
```

## Usage

You can find your API credentials in the [Stytch Dashboard](https://stytch.com/dashboard/api-keys).

This client library supports all of Stytch's live products:

**B2C**

- [x] [Email Magic Links](https://stytch.com/docs/api/send-by-email)
- [x] [Embeddable Magic Links](https://stytch.com/docs/api/create-magic-link)
- [x] [OAuth logins](https://stytch.com/docs/api/oauth-google-start)
- [x] [SMS passcodes](https://stytch.com/docs/api/send-otp-by-sms)
- [x] [WhatsApp passcodes](https://stytch.com/docs/api/whatsapp-send)
- [x] [Email passcodes](https://stytch.com/docs/api/send-otp-by-email)
- [x] [Session Management](https://stytch.com/docs/api/session-auth)
- [x] [WebAuthn](https://stytch.com/docs/api/webauthn-register-start)
- [x] [User Management](https://stytch.com/docs/api/create-user)
- [x] [Time-based one-time passcodes (TOTPs)](https://stytch.com/docs/api/totp-create)
- [x] [Crypto wallets](https://stytch.com/docs/api/crypto-wallet-authenticate-start)
- [x] [Passwords](https://stytch.com/docs/api/password-create)

**B2B**

- [x] [Organizations](https://stytch.com/docs/b2b/api/organization-object)
- [x] [Members](https://stytch.com/docs/b2b/api/member-object)
- [x] [Email Magic Links](https://stytch.com/docs/b2b/api/send-login-signup-email)
- [x] [OAuth logins](https://stytch.com/docs/b2b/api/oauth-google-start)
- [x] [Session Management](https://stytch.com/docs/b2b/api/session-object)
- [x] [Single-Sign On](https://stytch.com/docs/b2b/api/sso-authenticate-start)
- [x] [Discovery](https://stytch.com/docs/b2b/api/discovered-organization-object)
- [x] [Passwords](https://stytch.com/docs/b2b/api/passwords-authenticate)

### Example B2C usage

Create an API client:

Kotlin:
```kotlin
import com.stytch.java.consumer.StytchClient
StytchClient.configure(
    projectId = "project-live-c60c0abe-c25a-4472-a9ed-320c6667d317",
    secret = "secret-live-80JASucyk7z_G8Z-7dVwZVGXL5NT_qGAQ2I="
)
```
Java:

```java
import com.stytch.java.consumer.StytchClient;
StytchClient.configure(
        "project-live-c60c0abe-c25a-4472-a9ed-320c6667d317",
        "secret-live-80JASucyk7z_G8Z-7dVwZVGXL5NT_qGAQ2I="
        );
```

Send a magic link by email:

Kotlin:
```kotlin
when (val result = StytchClient.magicLinks.email.loginOrCreate(
    LoginOrCreateRequest(
        email = "email@address.com",
        loginMagicLinkURL = "https://example.com/authenticate",
        signupMagicLinkURL = "https://example.com/authenticate",
    ),
)) {
    is StytchResult.Success -> println(result.value)
    is StytchResult.Error -> println(result.exception)
}
```
Java:
```java
LoginOrCreateRequest request = new LoginOrCreateRequest(
    "email@address.com",
    "https://example.com/authenticate",
    "https://example.com/authenticate",
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null
);
StytchResult<LoginOrCreateResponse> response = StytchClient.magicLinks.getEmail().loginOrCreateCompletable(request).get();
if (response instanceof StytchResult.Error) {
    var exception = ((StytchResult.Error) response).getException();
    System.out.println(exception.getReason());
} else {
    System.out.println(((StytchResult.Success<?>) response).getValue());
}
```

Authenticate the token from the magic link:

Kotlin:
```kotlin
when (val result = StytchClient.magicLinks.authenticate(
    AuthenticateRequest(token = "DOYoip3rvIMMW5lgItikFK-Ak1CfMsgjuiCyI7uuU94=")
)) {
    is StytchResult.Success -> println(result.value)
    is StytchResult.Error -> println(result.exception)
}
```
Java:
```java
AuthenticateRequest request = new AuthenticateRequest("DOYoip3rvIMMW5lgItikFK-Ak1CfMsgjuiCyI7uuU94=");
StytchResult<AuthenticateResponse> response = StytchClient.magicLinks.authenticateCompletable(request).get();
if (response instanceof StytchResult.Error) {
    var exception = ((StytchResult.Error) response).getException();
    System.out.println(exception.getReason());
} else {
    System.out.println(((StytchResult.Success<?>) response).getValue());
}
```

### Example B2B usage

Create an API client:

Kotlin:
```kotlin
import com.stytch.java.b2b.StytchB2BClient
...
StytchB2BClient.configure(
    projectId = "project-live-c60c0abe-c25a-4472-a9ed-320c6667d317",
    secret = "secret-live-80JASucyk7z_G8Z-7dVwZVGXL5NT_qGAQ2I="
)
```
Java:

```java
import com.stytch.java.b2b.StytchB2BClient;
...
        StytchB2BClient.configure(
        "project-live-c60c0abe-c25a-4472-a9ed-320c6667d317",
        "secret-live-80JASucyk7z_G8Z-7dVwZVGXL5NT_qGAQ2I="
        );
```

Create an organization

Kotlin:
```kotlin
when (val result = StytchB2BClient.organizations.create(CreateRequest(
    organizationName = "Acme Co",
    organizationSlug = "acme-co",
    emailAllowedDomains = listOf("acme.co"),
))) {
    is StytchResult.Success -> println(result.value)
    is StytchResult.Error -> println(result.exception)
}
```
Java:
```java
var emailAllowedDomains = new ArrayList<String>();
emailAllowedDomains.add("acme.co");
CreateRequest request = new CreateRequest(
        "Acme Co",
        "acme-co",
        null,
        null,
        null,
        emailAllowedDomains
);
StytchResult<CreateResponse> response = StytchB2BClient.organizations.createCompletable(request).get();
if (response instanceof StytchResult.Error) {
    var exception = ((StytchResult.Error) response).getException();
    System.out.println(exception.getReason());
} else {
    System.out.println(((StytchResult.Success<?>) response).getValue());
}
```

Log the first user into the organization

Kotlin:
```kotlin
when (val result = StytchB2BClient.magicLinks.email.loginOrSignup(LoginOrSignupRequest(
    organizationId = "organization-id-from-create-response-...",
    emailAddress = "admin@acme.co",
    loginRedirectURL = "https://example.com/authenticate",
    signupRedirectURL = "https://example.com/authenticate",
)) {
    is StytchResult.Success -> println(result.value)
    is StytchResult.Error -> println(result.exception)
}
```
Java:
```java
LoginOrSignupRequest request = new LoginOrSignupRequest(
    "organization-id-from-create-response-...",
    "admin@acme.co",
    "https://example.com/authenticate",
    "https://example.com/authenticate"
);
StytchResult<LoginOrSignupResponse> response = StytchB2BClient.magicLinks.getEmail().loginOrSignup(request).get();
if (response instanceof StytchResult.Error) {
    var exception = ((StytchResult.Error) response).getException();
    System.out.println(exception.getReason());
} else {
    System.out.println(((StytchResult.Success<?>) response).getValue());
}
```

## Handling Errors

When possible Stytch returns an error wrapped in a  `StytchResult.Error` class.
Additionally, the error should include a type that can be used to distinguish errors.

Learn more about errors in the [docs](https://stytch.com/docs/api/errors).

## Documentation

See example requests and responses for all the endpoints in the [Stytch API Reference](https://stytch.com/docs/api).

Follow one of the [integration guides](https://stytch.com/docs/guides) or start with one of our [example apps](https://stytch.com/docs/example-apps).

## Support

If you've found a bug, [open an issue](https://github.com/stytchauth/stytch-node/issues/new)!

If you have questions or want help troubleshooting, join us in [Slack](https://stytch.com/docs/resources/support/overview) or email support@stytch.com.

If you've found a security vulnerability, please follow our [responsible disclosure instructions](https://stytch.com/docs/security).

## Development

See [DEVELOPMENT.md](DEVELOPMENT.md)

## Code of Conduct

Everyone interacting in the Stytch project's codebases, issue trackers, chat rooms and mailing lists is expected to follow the [code of conduct](CODE_OF_CONDUCT.md).
