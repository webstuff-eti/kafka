package com.tibaestiago.apikafka.model;

public class AbstractModel{

    private String title;
    private String description;

    public AbstractModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public AbstractModel(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AbstractModel{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
