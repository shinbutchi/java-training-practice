package vn.smartdev.javatrainingpractice.springmvcpractice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Service;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.UserAttempts;
import vn.smartdev.javatrainingpractice.springmvcpractice.repositories.IUserAttemptsRepository;
import vn.smartdev.javatrainingpractice.springmvcpractice.service.IUserAttemptsService;
import vn.smartdev.javatrainingpractice.springmvcpractice.service.IUserService;

@Service
public class UserAttemptsServiceImpl implements IUserAttemptsService {
    private static final int MAX_ATTEMPTS = 3;

    private IUserAttemptsRepository userAttemptsRepository;

    private IUserService userService;

    @Autowired
    public UserAttemptsServiceImpl(IUserAttemptsRepository userAttemptsRepository, IUserService userService) {
        this.userAttemptsRepository = userAttemptsRepository;
        this.userService = userService;
    }

    public void updateFailAttempts(String username) {
        UserAttempts userAttempts = findByUsername(username);
        if(userAttempts == null) {
            if(userService.isExistedUsername(username)) {
                addUserAttempts(username);
            }
        }
        else {
            if(userService.isExistedUsername(username)) {
                updateAttemptsForUser(userAttempts);
            }
            if(userAttempts.getAttempts() + 1 >= MAX_ATTEMPTS) {
                userService.lockUser(username);
                throw new LockedException("User Account is locked!");
            }
        }
    }

    public UserAttempts resetFailAttempts(String username) {
        UserAttempts userAttempts = userAttemptsRepository.findFirstByUsername(username);
        userAttempts.setAttempts(0);
        return userAttemptsRepository.save(userAttempts);
    }


    public UserAttempts addUserAttempts(String username) {
        UserAttempts userAttempts = new UserAttempts();
        userAttempts.setUsername(username);
        userAttempts.setAttempts(0);
        return userAttemptsRepository.save(userAttempts);
    }

    public UserAttempts updateAttemptsForUser(UserAttempts userAttempts) {
        Integer attempts = userAttempts.getAttempts();
        userAttempts.setAttempts(attempts + 1);
        return userAttemptsRepository.save(userAttempts);
    }

    public UserAttempts findByUsername(String username) {
        return userAttemptsRepository.findFirstByUsername(username);
    }
}
