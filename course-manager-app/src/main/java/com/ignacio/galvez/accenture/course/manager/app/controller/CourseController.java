package com.ignacio.galvez.accenture.course.manager.app.controller;


import com.ignacio.galvez.accenture.course.manager.app.controller.constants.Endpoints;
import com.ignacio.galvez.accenture.course.manager.app.dto.CourseCreatedResponseDTO;
import com.ignacio.galvez.accenture.course.manager.app.dto.CourseCreationRequestDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(Endpoints.COURSE_PATH)
public class CourseController {

    @PostMapping
    public Mono<CourseCreatedResponseDTO> createCourse(@RequestBody CourseCreationRequestDTO courseCreationRequestDTO)
}
