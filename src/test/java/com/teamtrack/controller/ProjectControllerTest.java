package com.teamtrack.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamtrack.dto.ProjectRequestDto;
import com.teamtrack.dto.ProjectResponseDto;
import com.teamtrack.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @Autowired
    private ObjectMapper objectMapper;

    private ProjectResponseDto responseDto;
    private ProjectRequestDto requestDto;

    @BeforeEach
    void setUp() {
        responseDto = new ProjectResponseDto();
        responseDto.setId(1L);
        responseDto.setName("Test Project");
        responseDto.setDescription("Description");

        requestDto = new ProjectRequestDto("Test Project", "Description");
    }

    @Test
    void createProject_ShouldReturnCreatedProject_WhenValid() throws Exception {
        when(projectService.createProject(any(ProjectRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Test Project"));
    }

    @Test
    void getProjectById_ShouldReturnProject_WhenFound() throws Exception {
        when(projectService.getProjectById(1L)).thenReturn(responseDto);

        mockMvc.perform(get("/api/projects/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void getAllProjects_ShouldReturnPage() throws Exception {
        Page<ProjectResponseDto> page = new PageImpl<>(Collections.singletonList(responseDto));
        when(projectService.getAllProjects(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/api/projects")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Test Project"));
    }

    @Test
    void updateProject_ShouldReturnUpdatedProject_WhenValid() throws Exception {
        when(projectService.updateProject(eq(1L), any(ProjectRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(put("/api/projects/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Project"));
    }

    @Test
    void deleteProject_ShouldReturnNoContent() throws Exception {
        doNothing().when(projectService).deleteProject(1L);

        mockMvc.perform(delete("/api/projects/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
