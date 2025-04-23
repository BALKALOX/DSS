package dss.controller.rest;

import dss.dto.TaskDto;
import dss.model.entity.Task;
import dss.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
public class TaskRestController {

    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskDto taskDto,
                                           Authentication authentication) {
        return ResponseEntity.ok(taskService.createTask(taskDto, authentication));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }

//    @PutMapping
//    public ResponseEntity<Task> updateTask(@PathVariable Long id,
//                                           @RequestBody TaskDto taskDto,
//                                           Authentication authentication) {
//        return ResponseEntity.ok(taskService.up)
//    }
}
