package dss.service.impl;

import dss.dto.TaskDto;
import dss.model.entity.Task;
import dss.repository.TaskRepository;
import dss.service.TaskService;
import dss.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private UserService userService;

    @Override
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id){
        return taskRepository.findById(id).get();
    }

    @Override
    public Task createTask(TaskDto taskDto, Authentication authentication){
        var task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setCategory(taskDto.getCategory());
        task.setStatus(taskDto.getStatus());
        task.setCreated(LocalDateTime.now());
        task.setUser(userService.findUserByEmail(authentication.getName()));
        return taskRepository.save(task);
    }

    @Override
    public void deleteTaskById(Long id){
        if(taskRepository.existsById(id)){
            taskRepository.deleteById(id);
        }
        else throw new RuntimeException("No such task");
    }


}
