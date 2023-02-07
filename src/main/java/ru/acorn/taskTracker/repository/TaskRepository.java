package ru.acorn.taskTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.acorn.taskTracker.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
