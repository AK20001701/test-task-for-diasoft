package ru.diasoft.testTask.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.diasoft.testTask.data.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
