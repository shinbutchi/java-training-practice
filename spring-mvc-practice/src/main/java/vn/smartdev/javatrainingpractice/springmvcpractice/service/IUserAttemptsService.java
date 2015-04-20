package vn.smartdev.javatrainingpractice.springmvcpractice.service;

import vn.smartdev.javatrainingpractice.springmvcpractice.entities.UserAttempts;

public interface IUserAttemptsService {
    void updateFailAttempts(String username);
    UserAttempts resetFailAttempts(String username);
    UserAttempts addUserAttempts(String username);
    UserAttempts updateAttemptsForUser(UserAttempts userAttempts);
    UserAttempts findByUsername(String username);
}
