## Why

The app currently opens without a dedicated sign-in entry point, which blocks authentication-driven flows and makes first-time usage unclear. Adding a clear login screen now enables secure access patterns and establishes a foundation for user-specific features.

## What Changes

- Add a new login screen as the app's initial authentication entry UI.
- Define required UI states for sign-in: idle, loading, validation error, and authentication failure.
- Add basic input validation behavior for email/username and password fields before submission.
- Add sign-in action behavior and post-success navigation to the app's main experience.

## Capabilities

### New Capabilities
- `login-screen`: Provides the user-facing sign-in screen, field validation feedback, sign-in submission flow, and success/failure handling.

### Modified Capabilities
None.

## Impact

- Affected code: Android UI layer in `app/src/main/java/com/ccruz/taskending/` and related navigation/viewmodel classes.
- APIs/systems: Authentication client or repository used by sign-in flow.
- Dependencies: No new third-party dependency expected; existing AndroidX/Compose stack should cover implementation.
