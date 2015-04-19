package vn.smartdev.javatrainingpractice.springmvcpractice.dao;

import vn.smartdev.javatrainingpractice.springmvcpractice.entities.UserAttempts;

public interface UserDetailsDao {
    void updateFailAttempts(String username);
    void resetFailAttempts(String username);
    UserAttempts getUserAttempts(String username);
}
