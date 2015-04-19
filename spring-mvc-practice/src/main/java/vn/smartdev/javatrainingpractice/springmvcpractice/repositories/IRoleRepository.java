package vn.smartdev.javatrainingpractice.springmvcpractice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.Role;

public interface IRoleRepository extends JpaRepository<Role,String> {
    Role findFirstByRoleName(String roleName);
}
