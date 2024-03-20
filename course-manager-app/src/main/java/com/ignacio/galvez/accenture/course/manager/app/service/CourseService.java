package com.ignacio.galvez.accenture.course.manager.app.service;

import com.ignacio.galvez.accenture.course.manager.app.dto.CourseCreatedResponseDTO;
import com.ignacio.galvez.accenture.course.manager.app.dto.CourseCreationRequestDTO;
import com.ignacio.galvez.accenture.course.manager.app.dto.CourseDTO;
import com.ignacio.galvez.accenture.course.manager.app.dto.CourseDeletedResponseDTO;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface CourseService {

    CourseCreatedResponseDTO createCourse(CourseCreationRequestDTO courseCreationRequestDTO);

    CourseDeletedResponseDTO deleteCourse(UUID courseId);

    Flux<CourseDTO> findAll();

    CourseDTO findCourseByName(String courseName);
}
