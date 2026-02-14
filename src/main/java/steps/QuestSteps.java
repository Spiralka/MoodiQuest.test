package steps;

import client.ApiClient;
import com.fasterxml.jackson.core.type.TypeReference;
import endpoints.ApiEndpoint;
import io.restassured.response.Response;
import models.Quest;
import models.QuestDTO;

import java.util.List;
import java.util.Map;

public class QuestSteps {
    private final ApiClient apiClient;

    public QuestSteps(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Response getRandomQuestResponse() {
        return apiClient.get(ApiEndpoint.RANDOM_QUEST);
    }

    public Quest getRandomQuest(Response response) {
        return apiClient.readBody(response, Quest.class);
    }

    public Response getDailyQuestsResponse() {
        return apiClient.get(ApiEndpoint.DAILY_QUESTS);
    }

    public List<Quest> getDailyQuests(Response response) {
        return apiClient.readBody(response, new TypeReference<>() {
        });
    }

    public Response getQuestByIdResponse(long questId) {
        return apiClient.get(ApiEndpoint.QUEST_BY_ID, Map.of("id", questId));
    }

    public QuestDTO getQuestById(Response response) {
        return apiClient.readBody(response, QuestDTO.class);
    }

    public Response getTestErrorResponse() {
        return apiClient.get(ApiEndpoint.TEST_ERROR);
    }

    public Response getMakeCoffeeResponse() {
        return apiClient.get(ApiEndpoint.MAKE_COFFEE);
    }
}
