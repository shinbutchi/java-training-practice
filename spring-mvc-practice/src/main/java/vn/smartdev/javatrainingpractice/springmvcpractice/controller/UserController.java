package vn.smartdev.javatrainingpractice.springmvcpractice.controller;

import ch.qos.logback.classic.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import vn.smartdev.javatrainingpractice.springmvcpractice.dto.UserDTO;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.User;
import vn.smartdev.javatrainingpractice.springmvcpractice.service.IUserService;
import vn.smartdev.javatrainingpractice.springmvcpractice.util.BaseResponse;
import vn.smartdev.javatrainingpractice.springmvcpractice.util.ResponseUtil;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    private static Logger _log;

    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String defaultPage(Model model) {
//        _log.debug("abcd");
        model.addAttribute("title", "Login Form");
        model.addAttribute("message", "Default page!");
        return "hello";

    }


    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model) {

        model.addAttribute("title", "Login Form");
        model.addAttribute("message", "Admin page!");
        return "admin";

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout,
                        Model model) {

//        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addAttribute("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addAttribute("msg", "You've been logged out successfully.");
        }

        return "login";

    }



    //for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accesssDenied(Model model) {

        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addAttribute("username", userDetail.getUsername());
        }
        return "403";

    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String showProfile() {
        return "profile";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String showSignUpPage(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "signup";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public List<BaseResponse> add(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
        User user = userService.add(userDTO);
        BaseResponse<?> response;
        List<BaseResponse> baseResponses = new ArrayList<BaseResponse>();
        if(result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            for(FieldError fieldError : fieldErrors) {
                Object[] arguments = fieldError.getArguments();
                String code = fieldError.getCode();
                String field = fieldError.getField();
                response = ResponseUtil.getFieldErrorResponse(field, code, arguments);
                baseResponses.add(response);
            }
        }
        else {
            response= ResponseUtil.getSuccessReponse("success");
            baseResponses.add(response);
        }

        return baseResponses;
    }

    private UserDTO createUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmailAddress(user.getEmailAddress());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
}
