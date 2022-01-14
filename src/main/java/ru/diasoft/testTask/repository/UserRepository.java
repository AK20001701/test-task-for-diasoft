package ru.diasoft.testTask.repository;

import org.springframework.data.repository.CrudRepository;
import ru.diasoft.testTask.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
