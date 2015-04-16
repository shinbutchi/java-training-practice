package vn.smartdev.javatrainingpractice.springmvcpractice.service;

import vn.smartdev.javatrainingpractice.springmvcpractice.dto.UserDTO;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.User;

public interface IUserService {
    User add(UserDTO userDTO);

    void print();
}
