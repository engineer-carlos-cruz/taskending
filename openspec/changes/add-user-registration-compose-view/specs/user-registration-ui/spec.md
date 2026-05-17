## ADDED Requirements

### Requirement: Registration form fields are required
The system SHALL provide a registration screen with a username input, a password input, and a registration button. The system SHALL treat username and password as required fields.

#### Scenario: Username is empty on submit
- **WHEN** the user taps the registration button with an empty username
- **THEN** the system SHALL keep the user on the registration screen and show a validation error for the username field

#### Scenario: Password is empty on submit
- **WHEN** the user taps the registration button with an empty password
- **THEN** the system SHALL keep the user on the registration screen and show a validation error for the password field

### Requirement: Password must meet minimum length
The system SHALL require the password to have at least 6 characters before registration can succeed.

#### Scenario: Password is shorter than minimum
- **WHEN** the user taps the registration button with a password length less than 6 characters
- **THEN** the system SHALL keep the user on the registration screen and show a validation error indicating the minimum length requirement

### Requirement: Successful validation navigates to main page
The system SHALL navigate the user to the main page only when username is not empty, password is not empty, and password length is at least 6 characters.

#### Scenario: All fields are valid
- **WHEN** the user taps the registration button with non-empty username and password of at least 6 characters
- **THEN** the system SHALL navigate to the main page

#### Scenario: Registration data is not persisted
- **WHEN** the user completes registration and reaches the main page
- **THEN** the system SHALL not store the submitted registration data in persistent storage
