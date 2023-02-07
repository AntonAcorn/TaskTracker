package ru.acorn.taskTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.acorn.taskTracker.entity.Project;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository <Project, Long> {
    List<Project> findAllByNameStartsWith(String startAt);
    List<Project> findAllByNameEndsWith(String endAt);
    List<Project> findAllByOrderByPriority();
    List<Project> findAllByOrderByStartTimeOfProject();
}
