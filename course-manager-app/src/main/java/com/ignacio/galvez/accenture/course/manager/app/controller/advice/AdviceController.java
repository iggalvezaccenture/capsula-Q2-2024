package com.ignacio.galvez.accenture.course.manager.app.controller.advice;

import com.ignacio.galvez.accenture.course.manager.app.Exception.MissingCourseException;
import com.ignacio.galvez.accenture.course.manager.app.controller.CourseController;
import com.ignacio.galvez.accenture.course.manager.app.service.impl.CourseServiceImpl;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice(basePackageClasses = {CourseController.class})
public class AdviceController {

    @ExceptionHandler(value = MissingCourseException.class)
    public Mono<String> handleMissingCourseException(MissingCourseException missingCourseException){
        return Mono.just(missingCourseException.printError());
    }
}
