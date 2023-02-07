package ru.acorn.taskTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.acorn.taskTracker.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository <Project, Long> {
}
