package vn.smartdev.javatrainingpractice.springmvcpractice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;
import vn.smartdev.javatrainingpractice.springmvcpractice.exception.WrongPasswordException;
import vn.smartdev.javatrainingpractice.springmvcpractice.exception.WrongUsernameException;
import vn.smartdev.javatrainingpractice.springmvcpractice.service.IUserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    IUserService userService;
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        super.onAuthenticationFailure(request, response, exception);
        if (exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
            String username = request.getParameter("username");
//            if(userService.isExistedUsername(username)) {
//                throw new BadCredentialsException("error");
//            }
//            else {
//                throw new WrongUsernameException("error");
//            }
        }

        if(exception.getClass().equals(WrongUsernameException.class)) {
            String username = request.getParameter("username");
        }
    }


}
