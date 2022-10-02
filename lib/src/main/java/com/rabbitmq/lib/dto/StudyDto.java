package com.rabbitmq.lib.dto;

import java.io.Serializable;

public class StudyDto implements Serializable {
    private String name;
    private String duration;

    public StudyDto() {
    }

    public StudyDto(String name, String duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
