package client;

import config.ApiConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiClient {
    private final RequestSpecification requestSpecification;

    public ApiClient() {
        this.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(ApiConfig.baseUrl())
                .setBasePath(ApiConfig.basePath())
                .setContentType("application/json")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    public Response get(String path) {
        return given()
                .spec(requestSpecification)
                .when()
                .get(path)
                .andReturn();
    }
}
