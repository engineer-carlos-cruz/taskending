## 1. Registration Screen Structure

- [ ] 1.1 Add a `RegistrationScreen` composable with username field, password field, and submit button
- [ ] 1.2 Integrate the registration route into current Compose navigation and make it the entry point before the main page

## 2. Validation Behavior

- [ ] 2.1 Implement empty-field validation for username and password when the button is pressed
- [ ] 2.2 Implement password minimum-length validation (6+ characters) and show a clear field error message

## 3. Navigation on Success

- [ ] 3.1 Gate navigation so the app only moves to the main page when all validations pass
- [ ] 3.2 Ensure no registration data is persisted locally or remotely in this version

## 4. Verification

- [ ] 4.1 Verify manually that empty username blocks submit with error feedback
- [ ] 4.2 Verify manually that empty/short password blocks submit with error feedback
- [ ] 4.3 Verify manually that valid username + password (>=6) navigates to the main page
