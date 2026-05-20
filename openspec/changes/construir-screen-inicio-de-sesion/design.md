## Context

The Android app currently does not provide a dedicated authentication entry screen. The new `login-screen` capability introduces a clear first-run sign-in experience with field validation, async submission state, and success/failure outcomes. Implementation should align with the existing single-module app architecture and Kotlin/Android UI patterns already used in `:app`.

## Goals / Non-Goals

**Goals:**
- Provide a focused login UI with username/email and password inputs.
- Define deterministic UI behavior for idle, loading, validation error, and authentication failure states.
- Trigger sign-in through an app authentication abstraction and navigate forward on success.
- Keep implementation testable in unit/UI layers.

**Non-Goals:**
- User registration, password reset, and social sign-in providers.
- Backend/auth protocol redesign.
- Session management redesign beyond what is needed to complete initial login navigation.

## Decisions

- Use a dedicated login screen composable as the app's authentication entry point, with state owned by a ViewModel.
  - Rationale: keeps UI declarative and side-effect free while centralizing validation and submission behavior.
  - Alternative considered: local composable state only; rejected because async submission/error handling is harder to test and coordinate with navigation.
- Model UI state as explicit fields (`input`, `validationError`, `isLoading`, `authError`, `isSuccess`).
  - Rationale: avoids ambiguous state transitions and enables straightforward rendering rules.
  - Alternative considered: single free-form message/error state; rejected because it conflates validation and authentication failures.
- Validate inputs before repository call (non-empty password, acceptable email/username format).
  - Rationale: immediate feedback reduces unnecessary auth calls and improves UX.
  - Alternative considered: server-only validation; rejected because it delays user feedback.
- Navigate only after successful authentication signal from ViewModel.
  - Rationale: keeps navigation deterministic and prevents premature transitions.

## Risks / Trade-offs

- [Risk] Validation rules could diverge from backend expectations -> Mitigation: keep validation minimal/client-safe and rely on backend as source of truth for final auth acceptance.
- [Risk] UI state complexity grows as auth features expand -> Mitigation: use a clear state model now so additional states (e.g., lockout) can be added without refactoring screen flow.
- [Trade-off] ViewModel-based state introduces more structure than local state -> Mitigation: pays off with improved testability and predictable async handling.

## Migration Plan

- Add the login screen and ViewModel behind the default launch flow.
- Wire login submission to existing or stubbed authentication repository interface.
- Route successful login to current post-auth/home destination.
- If rollout issues occur, fallback by restoring previous start destination while keeping login code isolated.

## Open Questions

- Should username-only login be accepted in addition to email at launch, or only email format?
- What exact post-login destination should be treated as canonical home in current app navigation?
- Should authentication failures expose backend message text directly or map to user-friendly generic errors?
