package vn.smartdev.javatrainingpractice.springmvcpractice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.smartdev.javatrainingpractice.springmvcpractice.dto.UserDTO;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.User;
import vn.smartdev.javatrainingpractice.springmvcpractice.repositories.IUserRepository;
import vn.smartdev.javatrainingpractice.springmvcpractice.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    private IUserRepository iUserRepository;

    @Autowired
    public UserServiceImpl(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }


    @Override
    public User add(UserDTO userDTO) {
        User user = fromUserDTO(userDTO);
        return iUserRepository.save(user);
    }

    @Override
    public void print() {
        System.out.println("XXXX");
        System.out.println("yyy");
    }

    private User fromUserDTO(UserDTO userDTO) {
        User user = new User();
        user.setEmailAddress(userDTO.getEmailAddress());
        user.setPassword(userDTO.getPassword());
        return user;
    }

}
