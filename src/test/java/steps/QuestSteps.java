package steps;

import client.ApiClient;
import io.restassured.response.Response;

public class QuestSteps {
    private final ApiClient apiClient;

    public QuestSteps() {
        this(new ApiClient());
    }

    public QuestSteps(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Response getMakeCoffee() {
        return apiClient.get("/quests/makeCoffee");
    }
}
