package config;

public final class ApiConfig {
    private static final String DEFAULT_BASE_URL = "https://moodiquest.online";
    private static final String BASE_PATH = "/api";

    private ApiConfig() {
    }

    public static String baseUrl() {
        String fromEnv = System.getenv("BASE_URL");
        if (fromEnv == null || fromEnv.isBlank()) {
            return DEFAULT_BASE_URL;
        }
        return fromEnv;
    }

    public static String basePath() {
        return BASE_PATH;
    }
}
