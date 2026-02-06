package com.teamtrack.service;

import com.teamtrack.dto.ProjectRequestDto;
import com.teamtrack.dto.ProjectResponseDto;
import com.teamtrack.entity.Project;
import com.teamtrack.entity.User;
import com.teamtrack.exception.ResourceNotFoundException;
import com.teamtrack.repository.ProjectRepository;
import com.teamtrack.repository.UserRepository;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    private Project project;
    private ProjectRequestDto requestDto;
    private User owner;

    @BeforeEach
    void setUp() {
        owner = new User();
        owner.setId(1L);
        owner.setEmail("test@example.com");
        owner.setRole(User.Role.USER);

        project = new Project();
        project.setId(1L);
        project.setName("Test Project");
        project.setDescription("Description");
        project.setOwner(owner);
        project.setTasks(new ArrayList<>());

        requestDto = new ProjectRequestDto("Test Project", "Description", 1L);
    }

    @Test
    void createProject_ShouldReturnDto_WhenSuccessful() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(owner));
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        ProjectResponseDto result = projectService.createProject(requestDto);

        assertNotNull(result);
        assertEquals(project.getName(), result.getName());
        assertEquals(owner.getEmail(), result.getOwner().getEmail());
        assertNotNull(result.getTasks());
        verify(userRepository, times(1)).findById(1L);
        verify(projectRepository, times(1)).save(any(Project.class));
    }

    @Test
    void createProject_ShouldThrowException_WhenOwnerNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> projectService.createProject(requestDto));
        verify(projectRepository, never()).save(any(Project.class));
    }

    @Test
    void getProjectById_ShouldReturnDto_WhenFound() {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        ProjectResponseDto result = projectService.getProjectById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(owner.getEmail(), result.getOwner().getEmail());
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
        assertEquals(owner.getEmail(), result.getContent().get(0).getOwner().getEmail());
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
    void updateProject_ShouldChangeOwner_WhenOwnerIdDifferent() {
        User newOwner = new User();
        newOwner.setId(2L);
        newOwner.setEmail("newowner@example.com");
        newOwner.setRole(User.Role.USER);

        ProjectRequestDto newRequestDto = new ProjectRequestDto("Test Project", "Description", 2L);

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(userRepository.findById(2L)).thenReturn(Optional.of(newOwner));
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        ProjectResponseDto result = projectService.updateProject(1L, newRequestDto);

        assertNotNull(result);
        verify(userRepository).findById(2L);
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
