package br.com.trainer.trainerapi.exception;

public class NotAuthorizedException extends Exception{
    public NotAuthorizedException(String message) {
        super(message);
    }
}
