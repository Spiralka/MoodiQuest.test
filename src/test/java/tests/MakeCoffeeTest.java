package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import steps.QuestSteps;

import static org.assertj.core.api.Assertions.assertThat;

class MakeCoffeeTest {
    private final QuestSteps questSteps = new QuestSteps();

    @Test
    void getMakeCoffeeReturns418AndTeapotMessage() {
        Response response = questSteps.getMakeCoffee();

        assertThat(response.statusCode()).isEqualTo(418);
        assertThat(response.asString().toLowerCase())
                .containsAnyOf("teapot", "чайник");
    }
}
