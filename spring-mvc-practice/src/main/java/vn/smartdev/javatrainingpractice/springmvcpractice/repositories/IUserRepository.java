package vn.smartdev.javatrainingpractice.springmvcpractice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.User;


public interface IUserRepository extends JpaRepository<User,String> {
}
