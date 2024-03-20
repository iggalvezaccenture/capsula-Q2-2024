package com.ignacio.galvez.accenture.course.manager.app.Exception;

public class UnexpectedBehaviorException extends Exception implements ErrorMessage {
    @Override
    public String printError() {
        return null;
    }
}
