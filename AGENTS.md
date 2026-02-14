# Agent Instructions (Codex)

Goal: generate a clean, minimal Java API test framework for MoodiQuest from scratch.

## Tech stack
- Java 21 (Gradle toolchain)
- Gradle
- JUnit 5
- REST Assured for HTTP
- AssertJ for assertions
- Jackson for JSON mapping
- Allure for reporting

## Repository inputs
- API contract (OpenAPI): `openapi/moodiquest-openapi.json`
- Task spec: `tasks/0001-bootstrap-framework.md`

## Project conventions
- Keep it minimal and readable.
- Layered structure:
    - `config` (baseUrl/env/timeouts)
    - `client` (RestAssured spec, filters, common setup)
    - `models` (DTOs)
    - `steps` (high-level API actions returning DTOs)
    - `tests` (JUnit tests)
    - `utils` (helpers)
- No Spring, no DI frameworks.
- Avoid static mutable global state.
- Prefer composition over inheritance.

## Quality gates
Before finishing:
- Ensure `./gradlew test` passes.
- Implement exactly 8 tests as described in the task file.
- Add minimal README instructions for running tests and Allure.

## Secrets
- Never hardcode tokens/passwords.
- Base URL must be configurable via env var `BASE_URL`.
  Default from OpenAPI server URL.

## Deliverables
- Working Gradle project with `src/main/java` and `src/test/java`
- 8 API tests for MoodiQuest
- Allure configured (results folder, useful attachments)
- Clear code structure
