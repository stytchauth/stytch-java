# Development

Thanks for contributing to Stytch's Java library! If you run into trouble, find us in [Slack].

## Setup

1. Clone this repo.
2. Make your changes
3. Test your changes in the `workbench` app

To test your changes locally in YOUR application:
1. TEMPORARILY comment out the `signing` config block in `stytch/build.gradle.kts`
2. Publish to your local maven repository: `./gradlew publishToMavenLocal`
3. Add `mavenLocal()` to the top of your application's build.gradle(.kts) `repositories` block 
4. Refresh your gradle dependencies: `./gradlew --refresh-dependencies` 

## Issues and Pull Requests

Please file issues in this repo. We don't have an issue template yet, but for now, say whatever you think is important!

If you have non-trivial changes you'd like us to incorporate, please open an issue first so we can discuss the changes before starting on a pull request. (It's fine to start with the PR for a typo or simple bug.) If we think the changes align with the direction of the project, we'll either ask you to open the PR or assign someone on the Stytch team to make the changes.

When you're ready for someone to look at your issue or PR, assign `@stytchauth/client-libraries` (GitHub should do this automatically). If we don't acknowledge it within one business day, please escalate it by tagging `@stytchauth/engineering` in a comment or letting us know in [Slack].

[Slack]: https://join.slack.com/t/stytch/shared_invite/zt-nil4wo92-jApJ9Cl32cJbEd9esKkvyg
