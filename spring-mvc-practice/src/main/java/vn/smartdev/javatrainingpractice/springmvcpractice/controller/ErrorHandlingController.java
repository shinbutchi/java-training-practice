package vn.smartdev.javatrainingpractice.springmvcpractice.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import vn.smartdev.javatrainingpractice.springmvcpractice.binding.ErrorResource;
import vn.smartdev.javatrainingpractice.springmvcpractice.binding.FieldErrorResource;
import vn.smartdev.javatrainingpractice.springmvcpractice.exception.InvalidRequestException;
import vn.smartdev.javatrainingpractice.springmvcpractice.exception.WrongPasswordException;
import vn.smartdev.javatrainingpractice.springmvcpractice.exception.WrongUsernameException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@ControllerAdvice
public class ErrorHandlingController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResource> handleInvalidRequest(RuntimeException ex, WebRequest request) {
        InvalidRequestException ire = (InvalidRequestException) ex;

        List<FieldErrorResource> fieldErrorResources = new ArrayList<FieldErrorResource>();
        List<FieldError> fieldErrors = ire.getErrors().getFieldErrors();
        if(fieldErrors.size() == 0){
            Errors errors = ire.getErrors();
            fieldErrorResources.add(createdFieldErrorResourceByErrors(errors));
        }
        else {
            for (FieldError fieldError : fieldErrors) {
                fieldErrorResources.add(createdFieldErrorResourceByFieldErrors(fieldError));
            }
        }

        ErrorResource error = new ErrorResource("InvalidRequest", ire.getMessage());
        error.setFieldErrors(fieldErrorResources);

        return new ResponseEntity<ErrorResource>(error, HttpStatus.OK);
    }

    @ExceptionHandler(WrongUsernameException.class)
    public ResponseEntity<ErrorResource> handleWrongUsernameException(RuntimeException ex, WebRequest request) {
        WrongUsernameException wue = (WrongUsernameException) ex;
        List<FieldErrorResource> fieldErrorResources = new ArrayList<FieldErrorResource>();
        fieldErrorResources.add(createdFieldErrorResourceForWrongUsernameException());

        ErrorResource error = new ErrorResource("WrongUsername", wue.getMessage());
        error.setFieldErrors(fieldErrorResources);

        return new ResponseEntity<ErrorResource>(error, HttpStatus.OK);

    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<ErrorResource> handleWrongPasswordException(RuntimeException ex, WebRequest request) {
        WrongPasswordException wpe = (WrongPasswordException) ex;
        List<FieldErrorResource> fieldErrorResources = new ArrayList<FieldErrorResource>();
        fieldErrorResources.add(createdFieldErrorResourceForWrongPasswordException());

        ErrorResource error = new ErrorResource("WrongPassword", wpe.getMessage());
        error.setFieldErrors(fieldErrorResources);

        return new ResponseEntity<ErrorResource>(error, HttpStatus.OK);

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResource> handleBadCredentialsException(RuntimeException ex, WebRequest request) {
        BadCredentialsException bce = (BadCredentialsException) ex;
        List<FieldErrorResource> fieldErrorResources = new ArrayList<FieldErrorResource>();
        ErrorResource error = new ErrorResource("BadCredential", bce.getMessage());
        error.setFieldErrors(fieldErrorResources);
        return new ResponseEntity<ErrorResource>(error, HttpStatus.OK);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResource> handleUsernameNotFoundException(RuntimeException ex, WebRequest request) {
        UsernameNotFoundException bce = (UsernameNotFoundException) ex;
        List<FieldErrorResource> fieldErrorResources = new ArrayList<FieldErrorResource>();
        ErrorResource error = new ErrorResource("BadCredential", bce.getMessage());
        error.setFieldErrors(fieldErrorResources);
        return new ResponseEntity<ErrorResource>(error, HttpStatus.OK);
    }


    private String getMessage(String code, Object... args) {
        MessageSource messageSource = getMessageSource();
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, args, locale);

    }

    private MessageSource getMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    private FieldErrorResource createdFieldErrorResourceForWrongUsernameException() {
        FieldErrorResource fieldErrorResource = new FieldErrorResource();
        fieldErrorResource.setMessage(getMessage("wrong.username", new Object()));
        return fieldErrorResource;
    }

    private FieldErrorResource createdFieldErrorResourceForWrongPasswordException() {
        FieldErrorResource fieldErrorResource = new FieldErrorResource();
        fieldErrorResource.setMessage(getMessage("wrong.password", new Object()));
        return fieldErrorResource;
    }

    private FieldErrorResource createdFieldErrorResourceByErrors(Errors errors) {
        FieldErrorResource fieldErrorResource = new FieldErrorResource();
        fieldErrorResource.setCode(errors.getGlobalError().getCode());
        fieldErrorResource.setMessage(getMessage(errors.getGlobalError().getCode(), new Object()));
        return fieldErrorResource;
    }

    private FieldErrorResource createdFieldErrorResourceByFieldErrors(FieldError fieldError) {
        FieldErrorResource fieldErrorResource = new FieldErrorResource();
        fieldErrorResource.setResource(fieldError.getObjectName());
        fieldErrorResource.setField(fieldError.getField());
        fieldErrorResource.setCode(fieldError.getCode());
        fieldErrorResource.setMessage(getMessage(fieldError.getCode(), fieldError.getField()));
        return fieldErrorResource;
    }

}
