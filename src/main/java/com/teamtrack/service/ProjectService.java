package com.teamtrack.service;

import com.teamtrack.entity.Project;
import java.util.List;

public interface ProjectService {
    Project createProject(Project project);
    List<Project> getAllProjects();
}
