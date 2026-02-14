package tests;

import client.ApiClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.ApiConfig;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import models.Quest;
import models.QuestDTO;
import models.UserSuggestedQuest;
import org.junit.jupiter.api.Test;
import steps.QuestSteps;
import steps.SuggestionSteps;
import utils.RandomDataUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MoodiQuestApiTests {
    private static final int HTTP_OK = 200;
    private static final int HTTP_NOT_FOUND = 404;
    private static final int HTTP_INTERNAL_SERVER_ERROR = 500;
    private static final int HTTP_I_AM_A_TEAPOT = 418;

    private static final ApiClient API_CLIENT = new ApiClient(ApiConfig.load(), new ObjectMapper());
    private static final QuestSteps QUEST_STEPS = new QuestSteps(API_CLIENT);
    private static final SuggestionSteps SUGGESTION_STEPS = new SuggestionSteps(API_CLIENT);

    @Test
    @Description("Validates that random quest endpoint returns 200 and non-empty quest core fields.")
    void getRandomQuest_returns200AndValidQuest_test() {
        Response response = QUEST_STEPS.getRandomQuestResponse();
        Quest quest = QUEST_STEPS.getRandomQuest(response);

        assertThat(response.statusCode()).isEqualTo(HTTP_OK);
        assertThat(quest.getId()).isNotNull();
        assertThat(quest.getShortName()).isNotBlank();
        assertThat(quest.getDescription()).isNotBlank();
    }

    @Test
    @Description("Validates that daily quests endpoint returns 200 with non-empty and schema-valid quest list.")
    void getDailyQuests_returns200AndNonEmptyList_test() {
        Response response = QUEST_STEPS.getDailyQuestsResponse();
        List<Quest> quests = QUEST_STEPS.getDailyQuests(response);

        assertThat(response.statusCode()).isEqualTo(HTTP_OK);
        assertThat(quests).isNotEmpty();
        assertThat(quests).allSatisfy(quest -> {
            assertThat(quest.getId()).isNotNull();
            assertThat(quest.getShortName()).isNotBlank();
            assertThat(quest.getDescription()).isNotBlank();
        });
    }

    @Test
    @Description("Validates that quest by id endpoint returns 200 and echoes the requested quest id.")
    void getQuestById_existingId_returns200AndMatchingId_test() {
        Response randomQuestResponse = QUEST_STEPS.getRandomQuestResponse();
        Quest randomQuest = QUEST_STEPS.getRandomQuest(randomQuestResponse);

        assertThat(randomQuestResponse.statusCode()).isEqualTo(HTTP_OK);
        assertThat(randomQuest.getId()).isNotNull();

        Response response = QUEST_STEPS.getQuestByIdResponse(randomQuest.getId());
        QuestDTO quest = QUEST_STEPS.getQuestById(response);

        assertThat(response.statusCode()).isEqualTo(HTTP_OK);
        assertThat(quest.getId()).isEqualTo(randomQuest.getId());
    }

    @Test
    @Description("Validates current behavior for missing quest id and asserts not-found status.")
    void getQuestById_missingId_assertsCurrentApiBehavior_test() {
        Response response = QUEST_STEPS.getQuestByIdResponse(99_999_999L);

        assertThat(response.statusCode()).isEqualTo(HTTP_NOT_FOUND);
    }

    @Test
    @Description("Validates that suggestMe load endpoint returns 200 and echoes submitted payload fields.")
    void postSuggestMeLoad_returns200AndEchoesPayload_test() {
        UserSuggestedQuest request = new UserSuggestedQuest(
                RandomDataUtils.randomUserName(),
                RandomDataUtils.randomQuestText()
        );

        Response response = SUGGESTION_STEPS.loadSuggestionResponse(request);
        UserSuggestedQuest actual = SUGGESTION_STEPS.loadSuggestion(response);

        assertThat(response.statusCode()).isEqualTo(HTTP_OK);
        assertThat(actual.getUserName()).isEqualTo(request.getUserName());
        assertThat(actual.getQuest()).isEqualTo(request.getQuest());
    }

    @Test
    @Description("Validates that suggestMe all endpoint returns 200 and each item has required non-blank fields.")
    void getSuggestMeAll_returns200AndValidListSchema_test() {
        Response response = SUGGESTION_STEPS.getAllSuggestionsResponse();
        List<UserSuggestedQuest> suggestions = SUGGESTION_STEPS.getAllSuggestions(response);

        assertThat(response.statusCode()).isEqualTo(HTTP_OK);
        assertThat(suggestions).isNotNull();
        assertThat(suggestions).allSatisfy(item -> {
            assertThat(item.getUserName()).isNotBlank();
            assertThat(item.getQuest()).isNotBlank();
        });
    }

    @Test
    @Description("Validates that technical error endpoint returns HTTP 500 as expected in real API behavior.")
    void getQuestsTestError_returns500_test() {
        Response response = QUEST_STEPS.getTestErrorResponse();

        assertThat(response.statusCode()).isEqualTo(HTTP_INTERNAL_SERVER_ERROR);
    }

    @Test
    @Description("Validates that makeCoffee endpoint returns 418 and teapot-related message in response body.")
    void getMakeCoffee_returns418AndTeapotMessage_test() {
        Response response = QUEST_STEPS.getMakeCoffeeResponse();

        assertThat(response.statusCode()).isEqualTo(HTTP_I_AM_A_TEAPOT);
        assertThat(response.asString().toLowerCase()).containsAnyOf("teapot", "чайник");
    }
}
