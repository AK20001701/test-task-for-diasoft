package ru.diasoft.testTask.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.diasoft.testTask.data.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
