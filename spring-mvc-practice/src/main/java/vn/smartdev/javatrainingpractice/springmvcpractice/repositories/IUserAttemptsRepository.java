package vn.smartdev.javatrainingpractice.springmvcpractice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.UserAttempts;

public interface IUserAttemptsRepository extends JpaRepository<UserAttempts,Integer> {
    UserAttempts findFirstByUsername(String username);
}
