package vn.smartdev.javatrainingpractice.springmvcpractice.service;

import vn.smartdev.javatrainingpractice.springmvcpractice.entities.User;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.UserRole;

public interface IUserRoleService {
    UserRole add(UserRole userRole);
    UserRole setRoleForUser(User user);
}
