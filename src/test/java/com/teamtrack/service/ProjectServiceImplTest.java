package com.teamtrack.service;

import com.teamtrack.dto.ProjectRequestDto;
import com.teamtrack.dto.ProjectResponseDto;
import com.teamtrack.entity.Project;
import com.teamtrack.exception.ResourceNotFoundException;
import com.teamtrack.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    private Project project;
    private ProjectRequestDto requestDto;

    @BeforeEach
    void setUp() {
        project = new Project();
        project.setId(1L);
        project.setName("Test Project");
        project.setDescription("Description");

        requestDto = new ProjectRequestDto("Test Project", "Description");
    }

    @Test
    void createProject_ShouldReturnDto_WhenSuccessful() {
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        ProjectResponseDto result = projectService.createProject(requestDto);

        assertNotNull(result);
        assertEquals(project.getName(), result.getName());
        verify(projectRepository, times(1)).save(any(Project.class));
    }

    @Test
    void getProjectById_ShouldReturnDto_WhenFound() {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        ProjectResponseDto result = projectService.getProjectById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void getProjectById_ShouldThrowException_WhenNotFound() {
        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> projectService.getProjectById(1L));
    }

    @Test
    void getAllProjects_ShouldReturnPage() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Project> projectPage = new PageImpl<>(Collections.singletonList(project));
        when(projectRepository.findAll(pageable)).thenReturn(projectPage);

        Page<ProjectResponseDto> result = projectService.getAllProjects(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void updateProject_ShouldReturnUpdatedDto_WhenSuccessful() {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        ProjectResponseDto result = projectService.updateProject(1L, requestDto);

        assertNotNull(result);
        verify(projectRepository).save(any(Project.class));
    }

    @Test
    void deleteProject_ShouldDelete_WhenFound() {
        when(projectRepository.existsById(1L)).thenReturn(true);

        projectService.deleteProject(1L);

        verify(projectRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteProject_ShouldThrowException_WhenNotFound() {
        when(projectRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> projectService.deleteProject(1L));
    }
}
