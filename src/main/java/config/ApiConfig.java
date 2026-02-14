package config;

import java.time.Duration;

public record ApiConfig(String baseUrl, String basePath, Duration connectTimeout, Duration readTimeout) {
    private static final String DEFAULT_BASE_URL = "https://moodiquest.online";
    private static final String BASE_URL_ENV = "BASE_URL";
    private static final String DEFAULT_BASE_PATH = "/api";

    public static ApiConfig load() {
        String envBaseUrl = System.getenv(BASE_URL_ENV);
        String resolvedBaseUrl = (envBaseUrl == null || envBaseUrl.isBlank()) ? DEFAULT_BASE_URL : envBaseUrl;
        return new ApiConfig(resolvedBaseUrl, DEFAULT_BASE_PATH, Duration.ofSeconds(10), Duration.ofSeconds(20));
    }
}
