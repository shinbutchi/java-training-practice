package vn.smartdev.javatrainingpractice.springmvcpractice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.UserRole;

public interface IUserRoleRepository extends JpaRepository<UserRole, String> {
}
