package com.ignacio.galvez.accenture.course.manager.app.domain.repository;

import com.ignacio.galvez.accenture.course.manager.app.application.CourseManagerAppApplication;
import com.ignacio.galvez.accenture.course.manager.app.domain.model.Course;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
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
    @DirtiesContext
    public void givenCourse_WhenCreatingIt_thenReturnSavedCourse() {
        Course course = Course.builder().build();
        Course savedCourse = this.courseRepository.save(course);
        Assertions.assertNotNull(savedCourse);
        Assertions.assertNotNull(savedCourse.getId());
        Assertions.assertEquals(course, savedCourse);
    }

    @Test
    @DirtiesContext
    public void givenTwoCoursesWithSameId_WhenDeletingOneOfThem_ThenDecreaseRepositoryCountByOne() {
        Course course = Course.builder().build();
        Course anotherCourse = Course.builder().build();
        Course savedCourse = this.courseRepository.save(course);
        Course anotherSavedCourse = this.courseRepository.save(anotherCourse);
        Assertions.assertNotEquals(savedCourse,anotherSavedCourse);
        Assertions.assertEquals(2,this.courseRepository.count());
        this.courseRepository.delete(savedCourse);
        Assertions.assertEquals(1,this.courseRepository.count());
    }


}
