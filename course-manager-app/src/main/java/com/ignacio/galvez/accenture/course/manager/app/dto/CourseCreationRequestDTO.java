package com.ignacio.galvez.accenture.course.manager.app.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CourseCreationRequestDTO {

    private String name;
    private String category;
    private String link;

    private List<String> documents;
}
