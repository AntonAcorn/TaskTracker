package ru.acorn.taskTracker.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.acorn.taskTracker.dto.ProjectDTO;
import ru.acorn.taskTracker.entity.Project;
import ru.acorn.taskTracker.repository.ProjectRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static ru.acorn.taskTracker.entity.enums.ProjectStatus.Active;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {
    @Mock
    private ProjectRepository projectRepository;
    @InjectMocks
    private ProjectService projectService;

    @Test
    void createProject() {
        var project1 = getOneMockProject();
        projectService.createProject(project1);
        Mockito.verify(projectRepository).save(project1);
    }

    @Test
    void viewProjectById() {
        var project1 = getOneMockProject();
        Long id = 1L;
        Mockito.when(projectRepository.findById(id)).thenReturn(Optional.ofNullable(project1));
        projectRepository.findById(id);
        Mockito.verify(projectRepository).findById(id);
    }

    @Test
    void viewAllProjects() {
        var listOfProjects = getProjects();
        Mockito.when(projectRepository.findAll()).thenReturn(listOfProjects);
        var result = projectService.viewAllProjects();
        assertNotNull(result);
        assertEquals(listOfProjects, result);
    }

    @Test
    void deleteProjectById() {
        var id = 1L;
        projectService.deleteProjectById(id);
        Mockito.verify(projectRepository).deleteById(id);
    }

    private List<Project> getProjects(){
        Project project1 = Project.builder()
                .id(1L)
                .projectName("Temp")
                .startTimeOfProject(LocalDateTime.now())
                .completionDate(null)
                .status(Active)
                .priority(10)
                .build();

        Project project2 = Project.builder()
                .id(2L)
                .projectName("Temp2")
                .startTimeOfProject(LocalDateTime.now())
                .completionDate(null)
                .status(Active)
                .priority(5)
                .build();
        return List.of(project1, project2);
    }

    private Project getOneMockProject(){
        return Project.builder()
                .id(1L)
                .projectName("Temp")
                .startTimeOfProject(LocalDateTime.now())
                .completionDate(null)
                .status(Active)
                .priority(10)
                .build();
    }
}