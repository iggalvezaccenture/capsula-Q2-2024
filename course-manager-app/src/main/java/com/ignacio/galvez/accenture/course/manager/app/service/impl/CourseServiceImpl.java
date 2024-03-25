package com.ignacio.galvez.accenture.course.manager.app.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ignacio.galvez.accenture.course.manager.app.Exception.DatabaseException;
import com.ignacio.galvez.accenture.course.manager.app.Exception.MappingErrorException;
import com.ignacio.galvez.accenture.course.manager.app.Exception.MissingCourseException;
import com.ignacio.galvez.accenture.course.manager.app.domain.model.Course;
import com.ignacio.galvez.accenture.course.manager.app.domain.repository.CourseRepository;
import com.ignacio.galvez.accenture.course.manager.app.dto.response.CourseCreatedResponseDTO;
import com.ignacio.galvez.accenture.course.manager.app.dto.request.CourseCreationRequestDTO;
import com.ignacio.galvez.accenture.course.manager.app.dto.CourseDTO;
import com.ignacio.galvez.accenture.course.manager.app.dto.response.CourseDeletedResponseDTO;
import com.ignacio.galvez.accenture.course.manager.app.service.CourseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

import java.util.Collections;
import java.util.UUID;

/**
 * @author ignacio.galvez
 */
@Slf4j
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    public static final String ERROR_OCCURRED_WHILE_CREATING_COURSE = "Error occurred while creating course with name {}";
    public static final String COURSE_WITH_NAME_AND_CATEGORY_CREATED_WITH_UUID = "course with name {} and category {} created with UUID {}";
    public static final String CREATING_AND_PERSISTING_COURSE_WITH_NAME_AND_CATEGORY = "creating and persisting course with name {} and category {}";
    public static final String AN_UNEXPECTED_EXCEPTION_OCCURRED_WITH_MESSAGE = "An unexpected Exception occurred with message {}";
    @Autowired
    private final CourseRepository courseRepository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public CourseCreatedResponseDTO createCourse(CourseCreationRequestDTO courseCreationRequestDTO) throws DatabaseException, MappingErrorException {

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

    }


    @Override
    public CourseDeletedResponseDTO deleteCourse(UUID courseId) throws MissingCourseException, DatabaseException, MappingErrorException {
        log.info("");
        Course course = this.courseRepository.findById(courseId).orElseThrow(MissingCourseException::new);
        doDelete(course);
        CourseDeletedResponseDTO courseDeletedResponseDTO = mapCourseDeletedResponseDTO(courseId);
        log.info("");
        return courseDeletedResponseDTO;
    }

    private static CourseDeletedResponseDTO mapCourseDeletedResponseDTO(UUID courseId) throws MappingErrorException {
        try {

            return CourseDeletedResponseDTO
                    .builder()
                    .courseDeletedId(courseId)
                    .build();
        } catch (Exception e) {
            throw new MappingErrorException();
        }
    }

    private void doDelete(Course course) throws DatabaseException {
        try {
            this.courseRepository.delete(course);
        } catch (Exception e) {
            throw new DatabaseException();
        }
    }

    @Override
    public Flux<CourseDTO> findAll() {
        return Flux
                .fromStream(
                        this.courseRepository.findAll()
                                .stream()
                                .map(course -> this.mapper.convertValue(course, CourseDTO.class))
                                .toList().stream()).doOnError((e) -> log.error("Error obtaining courses list"));
    }

    @Override
    public CourseDTO findCourseByName(String courseName) throws MissingCourseException, MappingErrorException {

            log.info("");
            Course course = this.courseRepository.findByName(courseName).orElseThrow(MissingCourseException::new);

        CourseDTO courseDTO = mapCourseDTO(course);
        log.info("");
            return courseDTO;

    }

    private CourseDTO mapCourseDTO(Course course) throws MappingErrorException {
        try {
            return this.mapper.convertValue(course, CourseDTO.class);
        } catch (Exception e){
            throw new MappingErrorException();
        }

    }

    private CourseCreatedResponseDTO mapResponse(Course savedCourse) throws MappingErrorException {
        try {
            return CourseCreatedResponseDTO.builder()
                    .name(savedCourse.getName())
                    .category(savedCourse.getCategory())
                    .link(savedCourse.getLink())
                    .uuid(savedCourse.getId())
                    .build();
        }catch (Exception e){
            throw  new MappingErrorException();
        }
    }

    private Course buildAndSaveCourseEntity(CourseCreationRequestDTO courseCreationRequestDTO) throws DatabaseException {
        try {
            Course course = Course.builder().name(courseCreationRequestDTO.getName())
                    .category(courseCreationRequestDTO.getCategory())
                    .link(courseCreationRequestDTO.getLink())
                    .documents(Collections.emptyList()).build();
            return this.courseRepository.save(course);
        } catch (Exception e) {
            throw new DatabaseException();
        }
    }
}
