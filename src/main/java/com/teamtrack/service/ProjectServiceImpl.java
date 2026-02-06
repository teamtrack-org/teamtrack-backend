package com.teamtrack.service;

import com.teamtrack.dto.ProjectRequestDto;
import com.teamtrack.dto.ProjectResponseDto;
import com.teamtrack.dto.TaskSummaryDto;
import com.teamtrack.dto.UserDto;
import com.teamtrack.entity.Project;
import com.teamtrack.entity.User;
import com.teamtrack.exception.ResourceNotFoundException;
import com.teamtrack.repository.ProjectRepository;
import com.teamtrack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ProjectResponseDto createProject(ProjectRequestDto dto) {
        // Validate owner exists
        User owner = userRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + dto.getOwnerId()));

        Project project = new Project();
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        project.setOwner(owner);
        project.setTasks(new ArrayList<>());

        Project savedProject = projectRepository.save(project);
        return mapToDto(savedProject);
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectResponseDto getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        return mapToDto(project);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProjectResponseDto> getAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable)
                .map(this::mapToDto);
    }

    @Override
    @Transactional
    public ProjectResponseDto updateProject(Long id, ProjectRequestDto dto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        // Validate new owner exists if owner is being changed
        if (project.getOwner() != null && !project.getOwner().getId().equals(dto.getOwnerId())) {
            User newOwner = userRepository.findById(dto.getOwnerId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + dto.getOwnerId()));
            project.setOwner(newOwner);
        } else if (project.getOwner() == null) {
            User newOwner = userRepository.findById(dto.getOwnerId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + dto.getOwnerId()));
            project.setOwner(newOwner);
        }

        project.setName(dto.getName());
        project.setDescription(dto.getDescription());

        Project updatedProject = projectRepository.save(project);
        return mapToDto(updatedProject);
    }

    @Override
    @Transactional
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

        // Map owner
        if (project.getOwner() != null) {
            UserDto ownerDto = new UserDto();
            ownerDto.setId(project.getOwner().getId());
            ownerDto.setEmail(project.getOwner().getEmail());
            ownerDto.setRole(project.getOwner().getRole() != null ? project.getOwner().getRole().name() : null);
            dto.setOwner(ownerDto);
        }

        // Map tasks
        if (project.getTasks() != null) {
            List<TaskSummaryDto> taskDtos = project.getTasks().stream()
                    .map(task -> {
                        TaskSummaryDto taskDto = new TaskSummaryDto();
                        taskDto.setId(task.getId());
                        taskDto.setTitle(task.getTitle());
                        taskDto.setStatus(task.getStatus() != null ? task.getStatus().name() : null);
                        return taskDto;
                    })
                    .collect(Collectors.toList());
            dto.setTasks(taskDtos);
        } else {
            dto.setTasks(new ArrayList<>());
        }

        return dto;
    }
}
