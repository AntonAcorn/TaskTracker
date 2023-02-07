package ru.acorn.taskTracker.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.acorn.taskTracker.dto.ProjectDTO;
import ru.acorn.taskTracker.entity.Project;

@Component
public class ModelMapperUtil {
    private ModelMapper modelMapper;

    private Project projectDTOConvertToProject(ProjectDTO projectDTO){
        return modelMapper.map(projectDTO, Project.class);
    }

    private ProjectDTO projectConvertToProjectDTO(Project project){
        return modelMapper.map(project, ProjectDTO.class);
    }
}
