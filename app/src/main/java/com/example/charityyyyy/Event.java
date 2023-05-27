package com.example.charityyyyy;

public class Event {

    private String name;
    private String date;
    private String description;
    private String requirements;

    public Event(String name, String date, String description, String requirements) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.requirements = requirements;
    }
    //getters
    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getRequirements() {
        return requirements;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }
}
