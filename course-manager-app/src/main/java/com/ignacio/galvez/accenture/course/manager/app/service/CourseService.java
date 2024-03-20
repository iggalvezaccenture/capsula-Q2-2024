package com.ignacio.galvez.accenture.course.manager.app.service;

import com.ignacio.galvez.accenture.course.manager.app.Exception.MissingCourseException;
import com.ignacio.galvez.accenture.course.manager.app.Exception.UnexpectedBehaviorException;
import com.ignacio.galvez.accenture.course.manager.app.dto.CourseCreatedResponseDTO;
import com.ignacio.galvez.accenture.course.manager.app.dto.CourseCreationRequestDTO;
import com.ignacio.galvez.accenture.course.manager.app.dto.CourseDTO;
import com.ignacio.galvez.accenture.course.manager.app.dto.CourseDeletedResponseDTO;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface CourseService {

    CourseCreatedResponseDTO createCourse(CourseCreationRequestDTO courseCreationRequestDTO) throws UnexpectedBehaviorException;

    CourseDeletedResponseDTO deleteCourse(UUID courseId) throws MissingCourseException;

    Flux<CourseDTO> findAll();

    CourseDTO findCourseByName(String courseName);
}
