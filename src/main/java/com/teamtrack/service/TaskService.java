package com.teamtrack.service;

import com.teamtrack.entity.Task;
import java.util.List;

public interface TaskService {
    Task createTask(Task task);
    List<Task> getAllTasks();
}
