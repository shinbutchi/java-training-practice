package vn.smartdev.javatrainingpractice.springmvcpractice.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@ControllerAdvice
public class ErrorHandlingController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    protected ResponseEntity<ErrorResource> handleInvalidRequest(RuntimeException ex, WebRequest request) {
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
