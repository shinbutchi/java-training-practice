package vn.smartdev.javatrainingpractice.springmvcpractice.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

public class ResponseUtil {
    public static BaseResponse<?> getErrorResponse(String msgCode, Object...args) {
        return getResponse(false, msgCode, null, args);
    }


    public static BaseResponse<?> getSuccessReponse(String msgCode, Object...args) {
        return getResponse(true, msgCode, null, args);
    }


    public static BaseResponse<?> getResponse(boolean isSuccess, String msgCode, String body, Object[] args) {
        BaseResponse<String> response = new BaseResponse<String>();
        String successMessage = getMessage(msgCode, args);
        response.setMessageInfo(successMessage);
        response.setSuccess(isSuccess);
        response.setBody(body);
        return response;
    }

    public static BaseResponse<?> getFieldErrorResponse(String field, String msgCode, Object[] args) {
        BaseResponse<String> response = new BaseResponse<String>();

        String errorMessage = getMessage(msgCode, args);
        response.setMessageInfo(errorMessage);
        response.setField(field);
        return response;
    }

    private static String getMessage(String code, Object... args) {
        MessageSource messageSource = getMessageSource();
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, args, locale);

    }

    private static MessageSource getMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }
}
