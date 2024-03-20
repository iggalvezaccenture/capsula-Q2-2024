package com.ignacio.galvez.accenture.course.manager.app.controller;


import com.ignacio.galvez.accenture.course.manager.app.controller.constants.Endpoints;
import com.ignacio.galvez.accenture.course.manager.app.dto.CourseCreatedResponseDTO;
import com.ignacio.galvez.accenture.course.manager.app.dto.CourseCreationRequestDTO;
import com.ignacio.galvez.accenture.course.manager.app.dto.CourseDeletedResponseDTO;
import com.ignacio.galvez.accenture.course.manager.app.service.CourseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(Endpoints.COURSE_PATH)
@AllArgsConstructor
@Slf4j
public class CourseController {

    public static final String COURSE_CREATION_WITH_NAME_AND_CATEGORY_BEGIN = "course creation with name {} and category {} begin";
    public static final String COURSE_WITH_NAME_AND_CATEGORY_SUCCESSFULLY_CREATED = "course with name {} and category {} successfully created";
    private static final String COURSE_DELETE_WITH_ID = "deleting course  with id {} begin";
    private static final String COURSE_ID_SUCCESSFULLY_DELETED = "course with id  {} successfully deleted";
    @Autowired
    private CourseService courseService;
    @PostMapping(Endpoints.COURSE_CREATION)
    public Mono<CourseCreatedResponseDTO> createCourse(@RequestBody CourseCreationRequestDTO courseCreationRequestDTO){
        log.info(COURSE_CREATION_WITH_NAME_AND_CATEGORY_BEGIN, StringUtils.normalizeSpace(courseCreationRequestDTO.getName()),
                StringUtils.normalizeSpace(courseCreationRequestDTO.getCategory()));
        Mono<CourseCreatedResponseDTO> courseCreatedResponse =  Mono.just(courseService.createCourse(courseCreationRequestDTO));
        log.info(COURSE_WITH_NAME_AND_CATEGORY_SUCCESSFULLY_CREATED,StringUtils.normalizeSpace(courseCreationRequestDTO.getName()),
                StringUtils.normalizeSpace(courseCreationRequestDTO.getCategory()));
        return courseCreatedResponse;
    }

    @DeleteMapping(Endpoints.COURSE_DELETING)
    public Mono<CourseDeletedResponseDTO> deleteCourse(@PathVariable(value = "courseId") String courseId){
        log.info(COURSE_DELETE_WITH_ID, StringUtils.normalizeSpace(courseId));
        Mono<CourseDeletedResponseDTO> courseDeletedResponse =  Mono.just(courseService.deleteCourse(courseId));
        log.info(COURSE_ID_SUCCESSFULLY_DELETED,StringUtils.normalizeSpace(courseId));
        return courseDeletedResponse;
    }

}
