package com.ignacio.galvez.accenture.course.manager.app.service;

import com.ignacio.galvez.accenture.course.manager.app.Exception.DatabaseException;
import com.ignacio.galvez.accenture.course.manager.app.Exception.MappingErrorException;
import com.ignacio.galvez.accenture.course.manager.app.Exception.MissingCourseException;
import com.ignacio.galvez.accenture.course.manager.app.dto.response.CourseCreatedResponseDTO;
import com.ignacio.galvez.accenture.course.manager.app.dto.request.CourseCreationRequestDTO;
import com.ignacio.galvez.accenture.course.manager.app.dto.CourseDTO;
import com.ignacio.galvez.accenture.course.manager.app.dto.response.CourseDeletedResponseDTO;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface CourseService {

    CourseCreatedResponseDTO createCourse(CourseCreationRequestDTO courseCreationRequestDTO) throws  DatabaseException, MappingErrorException;

    CourseDeletedResponseDTO deleteCourse(UUID courseId) throws MissingCourseException, DatabaseException, MappingErrorException;

    Flux<CourseDTO> findAll();

    CourseDTO findCourseByName(String courseName) throws MissingCourseException, MappingErrorException;
}
