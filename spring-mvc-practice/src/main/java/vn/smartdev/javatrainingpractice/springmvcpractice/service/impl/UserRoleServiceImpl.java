package vn.smartdev.javatrainingpractice.springmvcpractice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.Role;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.User;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.UserRole;
import vn.smartdev.javatrainingpractice.springmvcpractice.repositories.IRoleRepository;
import vn.smartdev.javatrainingpractice.springmvcpractice.repositories.IUserRoleRepository;
import vn.smartdev.javatrainingpractice.springmvcpractice.service.IUserRoleService;

import java.util.List;

@Service
public class UserRoleServiceImpl implements IUserRoleService {

    private IUserRoleRepository userRoleRepository;
    private IRoleRepository roleRepository;
    private final String _ROLEUSER = "ROLE_USER";

    @Autowired
    public UserRoleServiceImpl(IUserRoleRepository userRoleRepository, IRoleRepository roleRepository) {
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
    }

    public UserRole add(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    public UserRole setRoleForUser(User user) {
        Role role = roleRepository.findFirstByRoleName(_ROLEUSER);
        UserRole userRole = new UserRole(user, role);
        return add(userRole);
    }

    public List<UserRole> getUserRoleByUser(User user) {
        return userRoleRepository.findByUserByUserId(user);
    }
}
