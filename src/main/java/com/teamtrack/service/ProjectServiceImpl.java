package com.teamtrack.service;

import com.teamtrack.dto.ProjectRequestDto;
import com.teamtrack.dto.ProjectResponseDto;
import com.teamtrack.entity.Project;
import com.teamtrack.exception.ResourceNotFoundException;
import com.teamtrack.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public ProjectResponseDto createProject(ProjectRequestDto dto) {
        Project project = new Project();
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        Project savedProject = projectRepository.save(project);
        return mapToDto(savedProject);
    }

    @Override
    public ProjectResponseDto getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        return mapToDto(project);
    }

    @Override
    public Page<ProjectResponseDto> getAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable)
                .map(this::mapToDto);
    }

    @Override
    public ProjectResponseDto updateProject(Long id, ProjectRequestDto dto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        project.setName(dto.getName());
        project.setDescription(dto.getDescription());

        Project updatedProject = projectRepository.save(project);
        return mapToDto(updatedProject);
    }

    @Override
    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Project not found with id: " + id);
        }
        projectRepository.deleteById(id);
    }

    private ProjectResponseDto mapToDto(Project project) {
        ProjectResponseDto dto = new ProjectResponseDto();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        return dto;
    }
}
