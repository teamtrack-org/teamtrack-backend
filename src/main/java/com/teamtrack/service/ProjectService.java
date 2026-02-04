package com.teamtrack.service;

import com.teamtrack.dto.ProjectRequestDto;
import com.teamtrack.dto.ProjectResponseDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {
    ProjectResponseDto createProject(ProjectRequestDto dto);

    ProjectResponseDto getProjectById(Long id);

    Page<ProjectResponseDto> getAllProjects(Pageable pageable);

    ProjectResponseDto updateProject(Long id, ProjectRequestDto dto);

    void deleteProject(Long id);
}
