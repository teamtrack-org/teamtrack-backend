package com.teamtrack.service;

import com.teamtrack.dto.ProjectRequestDto;
import com.teamtrack.dto.ProjectResponseDto;

import java.util.List;

public interface ProjectService {
    ProjectResponseDto createProject(ProjectRequestDto dto);

    ProjectResponseDto getProjectById(Long id);

    ProjectResponseDto updateProject(Long id, ProjectRequestDto dto);

    void deleteProject(Long id);
}
