package client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.ApiConfig;
import endpoints.ApiEndpoint;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiClient {
    private final RequestSpecification specification;
    private final ObjectMapper objectMapper;

    public ApiClient(ApiConfig apiConfig, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.specification = new RequestSpecBuilder()
                .setBaseUri(apiConfig.baseUrl())
                .setBasePath(apiConfig.basePath())
                .setContentType("application/json")
                .setAccept("application/json")
                .setConfig(RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", (int) apiConfig.connectTimeout().toMillis())
                        .setParam("http.socket.timeout", (int) apiConfig.readTimeout().toMillis())
                        .setParam("http.connection-manager.timeout", apiConfig.readTimeout().toMillis())))
                .addFilter(new AllureRestAssured())
                .build();
    }

    public Response get(ApiEndpoint endpoint) {
        return given().spec(specification).when().get(endpoint.path()).andReturn();
    }

    public Response get(ApiEndpoint endpoint, Map<String, ?> pathParams) {
        return given().spec(specification).pathParams(pathParams).when().get(endpoint.path()).andReturn();
    }

    public Response post(ApiEndpoint endpoint, Object body) {
        return given().spec(specification).body(body).when().post(endpoint.path()).andReturn();
    }

    public <T> T readBody(Response response, Class<T> type) {
        return response.as(type);
    }

    public <T> T readBody(Response response, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(response.getBody().asString(), typeReference);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to deserialize response", e);
        }
    }
}
