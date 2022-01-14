package ru.diasoft.testTask.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.diasoft.testTask.data.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByPhoneNumber(String phoneNumber);

    List<User> findByFirstName(String firstName);

    List<User> findByLastName(String lastName);

}
