package vn.smartdev.javatrainingpractice.springmvcpractice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.User;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.UserRole;

import java.util.List;

public interface IUserRoleRepository extends JpaRepository<UserRole, String> {
    List<UserRole> findByUserByUserId(User user);
}
