package vn.smartdev.javatrainingpractice.springmvcpractice.service;

import org.springframework.stereotype.Component;
import vn.smartdev.javatrainingpractice.springmvcpractice.dto.UserDTO;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.User;

@Component
public interface IUserService {
    User add(UserDTO userDTO);

    void print();
}
