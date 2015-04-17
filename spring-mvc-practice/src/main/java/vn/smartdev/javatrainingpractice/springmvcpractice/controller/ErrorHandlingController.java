package vn.smartdev.javatrainingpractice.springmvcpractice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.smartdev.javatrainingpractice.springmvcpractice.util.BaseResponse;
//import vn.smartdev.javatrainingpractice.springmvcpractice.util.FormErrorResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@ControllerAdvice
public class ErrorHandlingController {

//    @Autowired
//    private MessageSource messageSource;
//
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public ResponseEntity<FormErrorResponse> processValidationError(HttpServletRequest request, HttpServletResponse response,
//                                                                    Object handler, Exception ex) {
////        BindingResult result = ex.getBindingResult();
//        String errorMessage = ex.getMessage();
//        FormErrorResponse error = new FormErrorResponse();
//        return new ResponseEntity<FormErrorResponse>(error, HttpStatus.OK);
//
//    }
//
//    private BaseResponse<?> createErrorResponse(String errorCode, Object[] errorArgs) {
//        BaseResponse errorResponse = new BaseResponse();
//        Locale locale = LocaleContextHolder.getLocale();
//        errorResponse.setMessageInfo(messageSource.getMessage(errorCode, errorArgs, locale));
//        return errorResponse;
//    }

}
