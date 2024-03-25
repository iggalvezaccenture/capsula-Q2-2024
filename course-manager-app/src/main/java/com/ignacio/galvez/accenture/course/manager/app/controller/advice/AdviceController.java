package com.ignacio.galvez.accenture.course.manager.app.controller.advice;

import com.ignacio.galvez.accenture.course.manager.app.Exception.DatabaseException;
import com.ignacio.galvez.accenture.course.manager.app.Exception.MappingErrorException;
import com.ignacio.galvez.accenture.course.manager.app.Exception.MissingCourseException;
import com.ignacio.galvez.accenture.course.manager.app.controller.CourseController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice(basePackageClasses = {CourseController.class})
public class AdviceController {

    @ExceptionHandler(value = MissingCourseException.class)
    public Mono<String> handleMissingCourseException(MissingCourseException missingCourseException){
        return Mono.just(missingCourseException.printError());
    }


    @ExceptionHandler(value = MissingCourseException.class)
    public Mono<String> handleMappingErrorException(MappingErrorException mappingErrorException){
        return Mono.just(mappingErrorException.printError());
    }

    @ExceptionHandler(value = MissingCourseException.class)
    public Mono<String> handleMappingErrorException(DatabaseException databaseException){
        return Mono.just(databaseException.printError());
    }

}
