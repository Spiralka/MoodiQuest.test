# Task 0001 — Bootstrap MoodiQuest API test framework (Java 21 + Gradle)

## Objective
Create a minimal but production-style API test framework from scratch for MoodiQuest.
Implement exactly 8 API tests.

## Inputs
- OpenAPI contract: `openapi/moodiquest-openapi.json`
- Default base URL: use `servers[0].url` from OpenAPI (https://moodiquest.online)
- Allow override via env var `BASE_URL`.

## Constraints
- Do NOT use Spring or DI frameworks.
- Use JUnit 5.
- Use REST Assured.
- Use AssertJ.
- Use Jackson for DTO mapping.
- Add Allure reporting.

## Required package structure
Create packages:
- `config` (baseUrl/env/timeouts/config loader)
- `client` (RestAssured RequestSpecification, filters/logging, base setup)
- `models` (DTOs aligned with OpenAPI schemas)
- `steps` (API actions returning DTOs / response wrappers)
- `tests` (JUnit5 tests)
- `utils` (helpers: random strings, etc.)

## IMPORTANT: Real status codes
OpenAPI may be inaccurate for status codes:
- GET `/quests/testError` MUST assert HTTP 500.
- GET `/quests/makeCoffee` MUST assert HTTP 418.
  If the API behavior differs, update tests to match real behavior and document the mismatch in README.

## Tests (8 total)
1) GET `/quests/random`
    - expect 200
    - response body maps to `Quest`
    - assert: `id` not null, `shortName` not blank, `description` not blank

2) GET `/quests/daily`
    - expect 200
    - response is array of `Quest`
    - assert: size >= 1 (if always 3, assert == 3)

3) GET `/quests/{id}` using `id` from test #1
    - expect 200
    - response maps to `QuestDTO`
    - assert returned `id` equals requested

4) GET `/quests/{id}` with a non-existing id (e.g. 99999999)
    - assert the actual behavior (prefer 404; if API returns 200 with empty/other behavior, assert that)
    - document behavior in README

5) POST `/suggestMe/load`
    - send `UserSuggestedQuest` with random userName + quest text
    - expect 200
    - response maps to `UserSuggestedQuest`
    - assert response echoes sent fields

6) GET `/suggestMe/all`
    - expect 200
    - response is array of `UserSuggestedQuest`
    - assert array schema; optionally assert it contains the item created in #5 (if API is consistent)

7) GET `/quests/testError`
    - expect 500

8) GET `/quests/makeCoffee`
    - expect 418

## Logging & Allure
- Configure Allure for JUnit5.
- Attach request/response details to Allure at least on failures (preferred).
- Keep logs readable (no megabytes of spam).

## Running
Update README with:
- `./gradlew test`
- how to set BASE_URL
- how to generate/view Allure report (if tasks are added)

## Acceptance criteria
- `./gradlew test` passes on clean checkout.
- Exactly 8 tests exist and are readable and stable.
- Clear separation: config/client/models/steps/tests/utils.
- No secrets committed.
