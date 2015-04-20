package vn.smartdev.javatrainingpractice.springmvcpractice.service;

import org.springframework.data.domain.Page;
import vn.smartdev.javatrainingpractice.springmvcpractice.dto.UserDTO;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.User;

public interface IUserService {
    User add(UserDTO userDTO);
    boolean isExistedEmailAddress(String emailAddress);
    boolean isExistedUsername(String username);
    User findByUsername(String username);
    User update(UserDTO userDTO);
    Page<User> findAllUsers(Integer pageNumber);
    User lockUser(String username);
}
