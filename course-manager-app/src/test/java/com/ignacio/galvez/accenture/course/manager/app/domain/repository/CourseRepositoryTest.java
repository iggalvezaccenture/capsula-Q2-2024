package com.ignacio.galvez.accenture.course.manager.app.domain.repository;

import com.ignacio.galvez.accenture.course.manager.app.application.CourseManagerAppApplication;
import com.ignacio.galvez.accenture.course.manager.app.domain.model.Course;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;


@DataJpaTest
@ContextConfiguration(classes = {CourseManagerAppApplication.class})
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void givenCourse_WhenCreatingIt_thenReturnSavedCourse(){
        Course course = new Course();

        Course savedCourse = this.courseRepository.save(course);
        Assertions.assertNotNull(savedCourse);
    }
}