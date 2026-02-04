package com.teamtrack.service;

import com.teamtrack.dto.TaskRequestDto;
import com.teamtrack.dto.TaskResponseDto;
import java.util.List;

public interface TaskService {
    TaskResponseDto createTask(Long projectId, TaskRequestDto dto);

    List<TaskResponseDto> getTasksByProjectId(Long projectId);

    TaskResponseDto getTaskById(Long id);

    TaskResponseDto updateTask(Long id, TaskRequestDto dto);

    void deleteTask(Long id);
}
