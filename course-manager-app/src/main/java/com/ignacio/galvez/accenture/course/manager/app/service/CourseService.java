package com.ignacio.galvez.accenture.course.manager.app.service;

import com.ignacio.galvez.accenture.course.manager.app.dto.CourseCreatedResponseDTO;
import com.ignacio.galvez.accenture.course.manager.app.dto.CourseCreationRequestDTO;
import com.ignacio.galvez.accenture.course.manager.app.dto.CourseDeletedResponseDTO;

public interface CourseService {

    CourseCreatedResponseDTO createCourse(CourseCreationRequestDTO courseCreationRequestDTO);

    CourseDeletedResponseDTO deleteCourse(String courseId);
}
