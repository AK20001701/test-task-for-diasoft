package ru.diasoft.testTask.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.diasoft.testTask.data.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<List<User>> findByFirstName(String firstName);

    Optional<List<User>> findByLastName(String lastName);

}
