package steps;

import client.ApiClient;
import com.fasterxml.jackson.core.type.TypeReference;
import endpoints.ApiEndpoint;
import io.restassured.response.Response;
import models.UserSuggestedQuest;

import java.util.List;

public class SuggestionSteps {
    private final ApiClient apiClient;

    public SuggestionSteps(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Response loadSuggestionResponse(UserSuggestedQuest request) {
        return apiClient.post(ApiEndpoint.SUGGEST_LOAD, request);
    }

    public UserSuggestedQuest loadSuggestion(Response response) {
        return apiClient.readBody(response, UserSuggestedQuest.class);
    }

    public Response getAllSuggestionsResponse() {
        return apiClient.get(ApiEndpoint.SUGGEST_ALL);
    }

    public List<UserSuggestedQuest> getAllSuggestions(Response response) {
        return apiClient.readBody(response, new TypeReference<>() {
        });
    }
}
