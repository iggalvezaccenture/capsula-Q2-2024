package com.ignacio.galvez.accenture.course.manager.app.Exception;

public class MissingCourseException extends Exception implements ErrorMessage{
    @Override
    public String printError() {
        return "course not found";
    }

}
