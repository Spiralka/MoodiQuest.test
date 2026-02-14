package client;

import config.TestConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public final class ApiClient {
    private ApiClient() {
    }

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(TestConfig.baseUrl())
                .setContentType("application/json")
                .build();
    }
}
