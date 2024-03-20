package com.ignacio.galvez.accenture.course.manager.app.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseCreationRequestDTO {

    private String name;
    private String category;
    private String link;

}