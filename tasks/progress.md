# Progress — MoodiQuest API tests (agent worklog)

## Done
- [x] Framework bootstrap (Java 21 + Gradle + JUnit5 + REST Assured + AssertJ + Jackson + Allure)
- [x] Test 1: GET /quests/random
- [x] Test 2: GET /quests/daily
- [x] Test 3: GET /quests/{id} (existing)
- [x] Test 4: GET /quests/{id} (missing)
- [x] Test 5: POST /suggestMe/load
- [x] Test 6: GET /suggestMe/all
- [x] Test 7: GET /quests/testError (500)
- [x] Test 8: GET /quests/makeCoffee (418)

## Notes / Decisions
- Base URL defaults to `https://moodiquest.online` and is overridable via `BASE_URL`.
- Base path is fixed to `/api`, so all calls are sent to `/api/...`.
- Endpoint paths are centralized in `ApiEndpoint` enum; tests and steps do not use raw endpoint strings.
- Timeouts are configured in API client to avoid hangs.
- Allure REST Assured filter is enabled for request/response diagnostics.

## Observed behavior vs OpenAPI
- `GET /quests/testError`: API is validated as HTTP **500** (task-mandated real behavior).
- `GET /quests/makeCoffee`: API is validated as HTTP **418** and response text contains `teapot` or `чайник`.
- `GET /quests/{id}` for missing id is asserted as **404** in the test suite; if environment behavior differs, update this note with observed status.

## How to run
- `./gradlew test`
- `BASE_URL=https://moodiquest.online ./gradlew test`

## Next step
- Add Allure report viewing instructions with CI artifact publishing.
