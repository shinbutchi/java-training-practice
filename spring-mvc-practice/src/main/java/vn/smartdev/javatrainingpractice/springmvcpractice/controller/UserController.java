package vn.smartdev.javatrainingpractice.springmvcpractice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import vn.smartdev.javatrainingpractice.springmvcpractice.dto.UserDTO;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.User;
import vn.smartdev.javatrainingpractice.springmvcpractice.exception.InvalidRequestException;
import vn.smartdev.javatrainingpractice.springmvcpractice.service.IUserRoleService;
import vn.smartdev.javatrainingpractice.springmvcpractice.service.IUserService;
import vn.smartdev.javatrainingpractice.springmvcpractice.util.BaseResponse;
import vn.smartdev.javatrainingpractice.springmvcpractice.util.ResponseUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRoleService userRoleService;

    public UserController(){

    }

    public UserController(IUserService userService, IUserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String defaultPage(Model model) {
        model.addAttribute("title", "Login Form");
        model.addAttribute("message", "Default page!");
        return "hello";

    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout,
                        HttpServletRequest request,
                        Model model) {
        if (error != null) {
//            throw new WrongUsernameException("error");
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addAttribute("username", userDetail.getUsername());
        }
        return "403";

    }

    @RequestMapping(value = "/edit-profile", method = RequestMethod.GET)
    public String showProfile(ModelMap model) throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        UserDTO userDTO = createUserDTO(user);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonObject = objectMapper.writeValueAsString(userDTO);
        model.addAttribute("user",jsonObject);
        return "profile";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String showSignUpPage(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "signup";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<?> update(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
        User user = userService.findByUsername(userDTO.getUsername());
        List<FieldError> fieldErrors = result.getFieldErrors();
        for(FieldError fieldError : fieldErrors) {
            if(fieldError.getField().equals("emailAddress")) {
                String code = fieldError.getCode();
                if(code.equals("ValidEmail")) {
                    if(!user.getEmailAddress().equals(userDTO.getEmailAddress())) {
                        return ResponseUtil.getErrorResponse(code);
                    }
                }
                else {
                    return ResponseUtil.getErrorResponse(code);
                }
            }
        }
        userService.update(userDTO);
        BaseResponse<?> baseResponse = ResponseUtil.getSuccessReponse("update.user.success");
        return baseResponse;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<?> add(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
        if(result.hasErrors()) {
            throw new InvalidRequestException("error",result);
        }
        User user = userService.add(userDTO);
        userRoleService.setRoleForUser(user);
        return ResponseUtil.getSuccessReponse("success");
    }

    @RequestMapping(value = "/list-user", method = RequestMethod.GET)
    public String showListUserPage() {
        return "listUser";
    }

    @RequestMapping(value = "/list-user/{pageNumber}", method = RequestMethod.GET)
    @ResponseBody
    public Page<User> showListUser(@PathVariable Integer pageNumber) {
        Page<User> page = userService.findAllUsers(pageNumber);
        return page;
    }

    private UserDTO createUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmailAddress(user.getEmailAddress());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setCountry(user.getCountry());
        userDTO.setCity(user.getCity());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }
}
