## 1. Authentication Entry Flow

- [x] 1.1 Identify the current app start destination and route unauthenticated users to the new login screen.
- [x] 1.2 Add or update navigation actions so successful login transitions to the authenticated home destination.

## 2. Login Screen UI

- [x] 2.1 Implement the login screen composable with username/email and password inputs plus a sign-in button.
- [x] 2.2 Implement UI rendering for idle, loading, validation error, and authentication failure states.

## 3. Login State and Validation

- [x] 3.1 Create a ViewModel state model for input values, validation errors, loading, auth error, and success signals.
- [x] 3.2 Implement client-side validation rules and block submission when inputs are invalid.

## 4. Authentication Integration

- [x] 4.1 Connect sign-in submission to the authentication repository/client and map result states.
- [x] 4.2 Prevent duplicate submissions while a sign-in request is active and reset state after completion.

## 5. Verification

- [x] 5.1 Add or update unit tests for ViewModel validation and sign-in state transitions.
- [x] 5.2 Add or update UI tests (or equivalent) for login screen behavior, failure feedback, and success navigation.
