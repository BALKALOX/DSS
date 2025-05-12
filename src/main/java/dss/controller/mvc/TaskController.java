package dss.controller.mvc;

import dss.model.entity.Task;
import dss.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {
    private TaskService taskService;

    @GetMapping
    public String getFilteredTasks(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String user,
            @RequestParam(required = false) String sort,
            Model model) {

        List<Task> tasks = taskService.getAllTasks();

        if (category != null && !category.isEmpty()) {
            tasks = tasks.stream()
                    .filter(t -> t.getCategory().name().equals(category))
                    .collect(Collectors.toList());
        }

        if (status != null && !status.isEmpty()) {
            tasks = tasks.stream()
                    .filter(t -> t.getStatus().name().equals(status))
                    .collect(Collectors.toList());
        }

        if (user != null && !user.isEmpty()) {
            String search = user.toLowerCase();
            tasks = tasks.stream()
                    .filter(t -> t.getUser().getName().toLowerCase().contains(search))
                    .collect(Collectors.toList());
        }

        if ("created-desc".equals(sort)) {
            tasks.sort(Comparator.comparing(Task::getCreated).reversed());
        } else if ("created-asc".equals(sort)) {
            tasks.sort(Comparator.comparing(Task::getCreated));
        }

        model.addAttribute("tasks", tasks);
        return "task/tasks";
    }

    @GetMapping("/{id}")
    public String task(@PathVariable Long id, Model model, Authentication auth) {
        model.addAttribute("task", taskService.getTaskById(id));
        return "task/taskPage";
    }

    @GetMapping("/new")
    public String createTask(Model model, Authentication auth) {
        return "task/createTask";
    }

    @GetMapping("/{id}/edit")
    public String editTask(@PathVariable Long id,
                           Model model,
                           Authentication auth) {
        var task = taskService.getTaskById(id);

        model.addAttribute("task",task);
        return "task/editTask";
    }

}
