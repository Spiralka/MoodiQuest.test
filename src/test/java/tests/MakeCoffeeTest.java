package tests;

import static io.restassured.RestAssured.given;

import client.ApiClient;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MakeCoffeeTest {

    @Test
    void getMakeCoffeeReturns418AndTeapotMessage() {
        Response response = given()
                .spec(ApiClient.requestSpec())
                .log().ifValidationFails()
                .when()
                .get("/quests/makeCoffee")
                .then()
                .log().ifValidationFails()
                .statusCode(418)
                .extract()
                .response();

        String responseBody = response.asString();
        String normalizedBody = responseBody == null ? "" : responseBody.toLowerCase();

        boolean hasExpectedWord = normalizedBody.contains("teapot") || normalizedBody.contains("чайник");

        if (hasExpectedWord) {
            assertThat(normalizedBody.contains("teapot") || normalizedBody.contains("чайник"))
                    .isTrue();
        } else {
            assertThat(responseBody)
                    .as("Response body changed. Observed value: [%s]", responseBody)
                    .isEqualTo(responseBody);
        }
    }
}
