package endpoints;

public enum ApiEndpoint {
    RANDOM_QUEST("/quests/random"),
    DAILY_QUESTS("/quests/daily"),
    QUEST_BY_ID("/quests/{id}"),
    SUGGEST_LOAD("/suggestMe/load"),
    SUGGEST_ALL("/suggestMe/all"),
    TEST_ERROR("/quests/testError"),
    MAKE_COFFEE("/quests/makeCoffee");

    private final String path;

    ApiEndpoint(String path) {
        this.path = path;
    }

    public String path() {
        return path;
    }
}
