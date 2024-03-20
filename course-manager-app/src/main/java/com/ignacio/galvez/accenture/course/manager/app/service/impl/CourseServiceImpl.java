package com.ignacio.galvez.accenture.course.manager.app.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ignacio.galvez.accenture.course.manager.app.domain.model.Course;
import com.ignacio.galvez.accenture.course.manager.app.domain.repository.CourseRepository;
import com.ignacio.galvez.accenture.course.manager.app.dto.CourseCreatedResponseDTO;
import com.ignacio.galvez.accenture.course.manager.app.dto.CourseCreationRequestDTO;
import com.ignacio.galvez.accenture.course.manager.app.dto.CourseDTO;
import com.ignacio.galvez.accenture.course.manager.app.dto.CourseDeletedResponseDTO;
import com.ignacio.galvez.accenture.course.manager.app.service.CourseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    public static final String ERROR_OCCURRED_WHILE_CREATING_COURSE = "Error occurred while creating course with name {}";
    public static final String COURSE_WITH_NAME_AND_CATEGORY_CREATED_WITH_UUID = "course with name {} and category {} created with UUID {}";
    public static final String CREATING_AND_PERSISTING_COURSE_WITH_NAME_AND_CATEGORY = "creating and persisting course with name {} and category {}";
    @Autowired
    private final CourseRepository courseRepository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public CourseCreatedResponseDTO createCourse(CourseCreationRequestDTO courseCreationRequestDTO) {
        try {

            log.info(CREATING_AND_PERSISTING_COURSE_WITH_NAME_AND_CATEGORY,
                    StringUtils.normalizeSpace(courseCreationRequestDTO.getName()),
                    StringUtils.normalizeSpace(courseCreationRequestDTO.getCategory()));

            Course savedCourse = this.buildAndSaveCourseEntity(courseCreationRequestDTO);

            CourseCreatedResponseDTO courseCreatedResponseDTO = mapResponse(savedCourse);

            log.info(COURSE_WITH_NAME_AND_CATEGORY_CREATED_WITH_UUID,
                    StringUtils.normalizeSpace(courseCreatedResponseDTO.getName()),
                    StringUtils.normalizeSpace(courseCreatedResponseDTO.getCategory())
                    , StringUtils.normalizeSpace(courseCreatedResponseDTO.getUuid().toString()));

            return courseCreatedResponseDTO;

        } catch (Exception e) {

            log.error(ERROR_OCCURRED_WHILE_CREATING_COURSE, StringUtils.normalizeSpace(courseCreationRequestDTO.getName()));
            throw e;
        }

    }

    @Override
    public CourseDeletedResponseDTO deleteCourse(UUID courseId) {
        try {
            Optional<Course> courseOptional = this.courseRepository.findById(courseId);
            if (courseOptional.isEmpty()) {
                log.error("");
                throw new RuntimeException();
            }
            this.courseRepository.delete(courseOptional.get());


            CourseDeletedResponseDTO courseDeletedResponseDTO = CourseDeletedResponseDTO.builder().courseDeletedId(courseId).build();


            log.info("");
            return courseDeletedResponseDTO;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Flux<CourseDTO> findAll() {
        return Flux
                .fromStream(
                        this.courseRepository.findAll()
                                .stream()
                                .map(course -> this.mapper.convertValue(course,CourseDTO.class))
                                .toList().stream());
    }

    @Override
    public CourseDTO findCourseByName(String courseName) {
        try{
            log.info("");
            Optional<Course> courseOptional = this.courseRepository.findByName(courseName);
            if(courseOptional.isEmpty()){
                throw new RuntimeException();
            }
            Course course = courseOptional.get();
            CourseDTO courseDTO =  this.mapper.convertValue(course,CourseDTO.class);
            log.info("");
            return courseDTO;
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    private CourseCreatedResponseDTO mapResponse(Course savedCourse) {
        return CourseCreatedResponseDTO.builder()
                .name(savedCourse.getName())
                .category(savedCourse.getCategory())
                .link(savedCourse.getLink())
                .uuid(savedCourse.getId())
                .build();
    }

    private Course buildAndSaveCourseEntity(CourseCreationRequestDTO courseCreationRequestDTO) {
        Course course = Course.builder().name(courseCreationRequestDTO.getName())
                .category(courseCreationRequestDTO.getCategory())
                .link(courseCreationRequestDTO.getLink())
                .documents(Collections.emptyList()).build();
        return this.courseRepository.save(course);
    }
}