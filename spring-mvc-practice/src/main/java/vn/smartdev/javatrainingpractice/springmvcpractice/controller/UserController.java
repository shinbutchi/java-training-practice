package vn.smartdev.javatrainingpractice.springmvcpractice.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.smartdev.javatrainingpractice.springmvcpractice.dto.UserDTO;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.User;
import vn.smartdev.javatrainingpractice.springmvcpractice.service.IUserService;

@Controller
public class UserController {

    private IUserService userService;

    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String defaultPage(Model model) {

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
    public String showSignUpPage() {
        return "signup";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public UserDTO add(@RequestBody UserDTO userDTO) {
        userService.print();
        User newUser = userService.add(userDTO);
        return createUserDTO(newUser);
    }

    private UserDTO createUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmailAddress(user.getEmailAddress());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
}
