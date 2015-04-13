package vn.smartdev.javatrainingpractice.springmvcpractice.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.User;

@Repository
public interface IUserRepository extends CrudRepository<User,String> {
}
