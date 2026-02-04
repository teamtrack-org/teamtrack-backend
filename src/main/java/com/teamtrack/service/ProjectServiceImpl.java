package com.teamtrack.service;

import com.teamtrack.dto.ProjectRequestDto;
import com.teamtrack.dto.ProjectResponseDto;
import com.teamtrack.entity.Project;
import com.teamtrack.exception.ResourceNotFoundException;
import com.teamtrack.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public ProjectResponseDto createProject(ProjectRequestDto dto) {
        // DTO → Entity
        Project project = new Project();
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());

        // Save
        Project savedProject = projectRepository.save(project);

        // Entity → DTO
        return mapToDto(savedProject);
    }

    @Override
    public ProjectResponseDto getProjectById(Long id) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        return mapToDto(project);
    }

    private ProjectResponseDto mapToDto(Project project) {
        ProjectResponseDto dto = new ProjectResponseDto();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        return dto;
    }
}
