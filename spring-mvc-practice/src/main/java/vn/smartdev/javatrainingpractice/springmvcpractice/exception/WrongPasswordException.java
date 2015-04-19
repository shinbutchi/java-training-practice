package vn.smartdev.javatrainingpractice.springmvcpractice.exception;

public class WrongPasswordException extends RuntimeException {

    public WrongPasswordException(String message) {
        super(message);
    }
}
