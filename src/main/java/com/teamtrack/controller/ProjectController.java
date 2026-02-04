package com.teamtrack.controller;

import com.teamtrack.dto.ProjectRequestDto;
import com.teamtrack.dto.ProjectResponseDto;
import com.teamtrack.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    // CREATE PROJECT
    @PostMapping
    public ResponseEntity<ProjectResponseDto> createProject(
            @RequestBody ProjectRequestDto dto
    ) {
        ProjectResponseDto createdProject = projectService.createProject(dto);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    // GET ALL PROJECTS
    @GetMapping
    public ResponseEntity<List<ProjectResponseDto>> getAllProjects() {
        List<ProjectResponseDto> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }
}
