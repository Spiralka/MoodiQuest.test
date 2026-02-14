package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestDTO {
    private Long id;
    private String shortName;
    private String description;
    private Integer progressNumber;

    public QuestDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getProgressNumber() {
        return progressNumber;
    }

    public void setProgressNumber(Integer progressNumber) {
        this.progressNumber = progressNumber;
    }
}
