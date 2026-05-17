## Why

The app currently lacks an onboarding entry point for new users. Adding a registration view now enables a complete initial flow and ensures users cannot proceed with invalid or empty credentials.

## What Changes

- Add a new registration screen built with Jetpack Compose in the current UI architecture.
- Include a username input, password input, and a registration button.
- Validate required fields and block submission when username or password is empty.
- Validate password minimum length (6 characters) before allowing navigation.
- Navigate to the main page when all validations pass; do not persist registration data yet.

## Capabilities

### New Capabilities
- `user-registration-ui`: Provide an in-app registration view with client-side field validation and success navigation to the main page.

### Modified Capabilities
- None.

## Impact

- Affected code: Compose UI screen(s), navigation routing, and UI-level validation state handling.
- APIs/dependencies: No new backend or storage dependencies; registration remains local and transient.
- Systems: Updates user entry flow by adding a pre-main-screen registration step.
