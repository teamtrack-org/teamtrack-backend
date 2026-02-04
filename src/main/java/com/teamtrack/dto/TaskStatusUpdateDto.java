package com.teamtrack.dto;

import com.teamtrack.entity.Task;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskStatusUpdateDto {
    @NotNull(message = "Status cannot be null")
    private Task.Status status;
}
