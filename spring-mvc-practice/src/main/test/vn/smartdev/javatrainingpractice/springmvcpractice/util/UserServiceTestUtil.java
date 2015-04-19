package vn.smartdev.javatrainingpractice.springmvcpractice.util;

import org.springframework.beans.factory.annotation.Autowired;
import vn.smartdev.javatrainingpractice.springmvcpractice.dto.UserDTO;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.Role;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.User;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.UserRole;
import vn.smartdev.javatrainingpractice.springmvcpractice.service.IUserService;

public class UserServiceTestUtil {
    public static final String _USERNAME = "usernameTest";
    public static final String _EMAILADDRESS = "test@test.com";
    public static final String _PASSWORD = "PaSSw0rd";
    public static final String _RETYPEPASSWORD = "PaSSw0rd";
    public static final String _FIRSTNAME = "FirstnameTest";
    public static final String _LASTNAME = "LastnameTest";
    public static final String _COUNTRY = "Italy";
    public static final String _CITY = "Milan";
    public static final String _PHONENUMBER = "0905290591";

    public static UserDTO preparedUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(_USERNAME);
        userDTO.setPassword(_PASSWORD);
        userDTO.setRetypePassword(_RETYPEPASSWORD);
        userDTO.setEmailAddress(_EMAILADDRESS);
        userDTO.setFirstName(_FIRSTNAME);
        userDTO.setLastName(_LASTNAME);
        userDTO.setCountry(_COUNTRY);
        userDTO.setCity(_CITY);
        userDTO.setPhoneNumber(_PHONENUMBER);

        return userDTO;
    }

    public static User preparedUser() {
        User user = new User();
        user.setEmailAddress(_EMAILADDRESS);
        user.setUsername(_USERNAME);
        return user;
    }

    public static Role preparedRole() {
        Role role = new Role();
        role.setRoleName("ROLE_USER");
        return role;
    }

    public static UserRole prepareUserRole() {
        UserRole userRole = new UserRole();
        User user = preparedUser();
        Role role = preparedRole();
        userRole.setRoleByRoleId(role);
        userRole.setUserByUserId(user);

        return userRole;
    }


    public static User fromUserDTO(UserDTO userDTO) {
        User user = new User();
        user.setEmailAddress(userDTO.getEmailAddress());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setCountry(userDTO.getCountry());
        user.setCity(userDTO.getCity());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return user;
    }


}
