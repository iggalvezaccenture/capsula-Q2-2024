package com.ignacio.galvez.accenture.course.manager.app.service.impl;

import com.ignacio.galvez.accenture.course.manager.app.Exception.DatabaseException;
import com.ignacio.galvez.accenture.course.manager.app.Exception.MappingErrorException;
import com.ignacio.galvez.accenture.course.manager.app.Exception.MissingCourseException;
import com.ignacio.galvez.accenture.course.manager.app.domain.model.Course;
import com.ignacio.galvez.accenture.course.manager.app.domain.repository.CourseRepository;
import com.ignacio.galvez.accenture.course.manager.app.dto.response.CourseCreatedResponseDTO;
import com.ignacio.galvez.accenture.course.manager.app.dto.request.CourseCreationRequestDTO;
import com.ignacio.galvez.accenture.course.manager.app.dto.response.CourseDeletedResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {


    @InjectMocks
    private CourseServiceImpl courseService;

    @Mock
    private CourseRepository courseRepository;

    private CourseCreationRequestDTO courseCreationRequestDTO;

    private Course course;

    @BeforeEach
    void setUp() {
        this.courseCreationRequestDTO = Mockito.mock(CourseCreationRequestDTO.class);
        this.course = Mockito.mock(Course.class);
    }

    @Test
    public void givenValidData_WhenCreatingCourse_ThenCreateCourse() throws MappingErrorException, DatabaseException {
        Mockito.when(this.courseRepository.save(Mockito.any())).thenReturn(this.course);
        Mockito.when(this.course.getId()).thenReturn(UUID.randomUUID());
        CourseCreatedResponseDTO courseCreatedResponseDTO = this.courseService.createCourse(this.courseCreationRequestDTO);
        Assertions.assertEquals(course.getId(),courseCreatedResponseDTO.getUuid());
    }


    @Test
    public void givenValidData_WhenDeletingCourse_ThenCreateCourse() throws MissingCourseException, DatabaseException, MappingErrorException {
        UUID id = UUID.randomUUID();
        Mockito.when(this.courseRepository.findById(Mockito.any())).thenReturn(Optional.of(this.course));
        CourseDeletedResponseDTO courseDeletedResponseDTO = this.courseService.deleteCourse(id);
        Assertions.assertEquals(id,courseDeletedResponseDTO.getCourseDeletedId());
    }



}