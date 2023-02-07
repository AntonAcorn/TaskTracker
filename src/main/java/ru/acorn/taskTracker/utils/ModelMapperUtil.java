package ru.acorn.taskTracker.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.acorn.taskTracker.dto.ProjectDTO;
import ru.acorn.taskTracker.entity.Project;

@Component
public class ModelMapperUtil {
    private ModelMapper modelMapper;

    public ModelMapperUtil() {
        this.modelMapper = new ModelMapper();
    }

    public Project projectDTOConvertToProject(ProjectDTO projectDTO){
        return modelMapper.map(projectDTO, Project.class);
    }

    public ProjectDTO projectConvertToProjectDTO(Project project){
        return modelMapper.map(project, ProjectDTO.class);
    }
}
