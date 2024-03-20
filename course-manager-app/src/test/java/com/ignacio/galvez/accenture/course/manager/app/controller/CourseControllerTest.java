package com.ignacio.galvez.accenture.course.manager.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ignacio.galvez.accenture.course.manager.app.controller.constants.Endpoints;
import com.ignacio.galvez.accenture.course.manager.app.dto.CourseCreatedResponseDTO;
import com.ignacio.galvez.accenture.course.manager.app.dto.CourseCreationRequestDTO;
import com.ignacio.galvez.accenture.course.manager.app.dto.CourseDeletedResponseDTO;
import com.ignacio.galvez.accenture.course.manager.app.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {CourseController.class})
@ContextConfiguration(classes = {CourseController.class})
class CourseControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private CourseService courseService;

    private CourseCreationRequestDTO courseCreationRequestDTO;
    @BeforeEach
    void setUp() {

    }

    @Test
    public void givenValidData_WhenCreatingCourse_ThenReturnResponse() throws Exception {
        Mockito.when(courseService.createCourse(createValidCourseCreationRequest()))
                .thenReturn(this.createValidCourseCreationResponseDTO());

        mvc.perform(MockMvcRequestBuilders
                        .post(Endpoints.COURSE_PATH + Endpoints.COURSE_CREATION)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(this.createValidCourseCreationResponseDTO())))
                .andExpect(status().isOk())
                .andDo(print());

    }


    @Test
    public void givenValidData_WhenDeletingCourse_ThenReturnResponse() throws Exception {
        Mockito.when(courseService.deleteCourse(Mockito.any(UUID.class)))
                .thenReturn(this.createValidCourseDeleteResponseDTO());

        mvc.perform(MockMvcRequestBuilders
                        .delete(Endpoints.COURSE_PATH + Endpoints.COURSE_DELETING.replace("{courseId}",UUID.randomUUID().toString()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(this.createValidCourseDeleteResponseDTO())))
                .andExpect(status().isOk())
                .andDo(print());

    }

    private CourseDeletedResponseDTO createValidCourseDeleteResponseDTO() {
        return CourseDeletedResponseDTO.builder()
                .courseDeletedId(UUID.randomUUID()).build();
    }

    private CourseCreationRequestDTO createValidCourseCreationRequest(){
        return CourseCreationRequestDTO.builder()
                .name("OOP")
                .category("programming")
                .link("/oop")
                .build();

    }

    private CourseCreatedResponseDTO createValidCourseCreationResponseDTO() {
        return CourseCreatedResponseDTO.builder().uuid(
                UUID.randomUUID())
                        .name("OOP")
                        .category("programming")
                        .link("/oop")
                        .build();

    }



}