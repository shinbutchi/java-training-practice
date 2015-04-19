package vn.smartdev.javatrainingpractice.springmvcpractice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.smartdev.javatrainingpractice.springmvcpractice.dto.UserDTO;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.User;
import vn.smartdev.javatrainingpractice.springmvcpractice.repositories.IUserRepository;
import vn.smartdev.javatrainingpractice.springmvcpractice.service.IUserService;

import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements IUserService {
    private static final int PAGE_SIZE = 5;

    private IUserRepository iUserRepository;

    @Autowired
    public UserServiceImpl(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }


    public User add(UserDTO userDTO) {
        User user = fromUserDTO(userDTO);
        return iUserRepository.save(user);
    }

    public boolean isExistedEmailAddress(String emailAddress) {
        return iUserRepository.findFirstByEmailAddress(emailAddress) != null;
    }

    public boolean isExistedUsername(String username) {
        return iUserRepository.findFirstByUsername(username) != null;
    }

    public User findByUsername(String username) {
        return iUserRepository.findFirstByUsername(username);
    }

    public User update(UserDTO userDTO) {
        User user = findByUsername(userDTO.getUsername());
        user = fromUpdatedUserDTO(userDTO, user);
        return iUserRepository.save(user);
    }

    public Page<User> findAllUsers(Integer pageNumber) {
        PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "lastModifiedDate");
        return iUserRepository.findAll(request);
    }

    private User fromUpdatedUserDTO(UserDTO userDTO, User user) {
        user.setEmailAddress(userDTO.getEmailAddress());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setCountry(userDTO.getCountry());
        user.setCity(userDTO.getCity());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return user;
    }


    private User fromUserDTO(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmailAddress(userDTO.getEmailAddress());
        try {
            String encryptPassword = encryptPasswordToMD5(userDTO.getPassword());
            user.setPassword(encryptPassword);
        }catch (NoSuchAlgorithmException ignored) {

        }
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setCountry(userDTO.getCountry());
        user.setCity(userDTO.getCity());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEnabled(true);
        return user;
    }

    private String encryptPasswordToMD5(String password) throws NoSuchAlgorithmException {
        PasswordEncoder encoder = new Md5PasswordEncoder();
        return encoder.encodePassword(password, null);
    }


}
