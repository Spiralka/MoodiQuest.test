package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSuggestedQuest {
    private String userName;
    private String quest;

    public UserSuggestedQuest() {
    }

    public UserSuggestedQuest(String userName, String quest) {
        this.userName = userName;
        this.quest = quest;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getQuest() {
        return quest;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }
}
