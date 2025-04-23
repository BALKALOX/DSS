package dss.service;

import dss.dto.TaskDto;
import dss.model.entity.Task;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {

    List<Task> getAllTasks();

    Task getTaskById(Long id);

    Task createTask(TaskDto taskDto, Authentication authentication);

    void deleteTaskById(Long id);
}
