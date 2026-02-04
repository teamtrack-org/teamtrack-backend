package com.teamtrack.service;

import com.teamtrack.dto.TaskRequestDto;
import com.teamtrack.dto.TaskResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {
    TaskResponseDto createTask(Long projectId, TaskRequestDto dto);

    Page<TaskResponseDto> getTasksByProjectId(Long projectId, Pageable pageable);

    TaskResponseDto getTaskById(Long id);

    TaskResponseDto updateTask(Long id, TaskRequestDto dto);

    TaskResponseDto updateTaskStatus(Long id, com.teamtrack.entity.Task.Status status);

    void deleteTask(Long id);
}
