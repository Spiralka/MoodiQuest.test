package config;

public final class TestConfig {
    private static final String DEFAULT_BASE_URL = "https://moodiquest.online";

    private TestConfig() {
    }

    public static String baseUrl() {
        String fromEnv = System.getenv("BASE_URL");
        if (fromEnv == null || fromEnv.isBlank()) {
            return DEFAULT_BASE_URL;
        }
        return fromEnv;
    }
}
