package dss.service.impl;

import dss.dto.TaskDto;
import dss.dto.TaskParameterDto;
import dss.model.entity.Decision;
import dss.model.entity.Task;
import dss.model.entity.TaskParameter;
import dss.model.entity.enums.DecisionStatus;
import dss.model.entity.enums.TaskStatus;
import dss.repository.DecisionRepository;
import dss.repository.TaskRepository;
import dss.service.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private UserService userService;
    @Qualifier("AHPService")
    private AHPService ahpService;
    private TopsisService topsisService;
    private ElectreService electreService;
    private DecisionRepository decisionRepository;

    @Override
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id){
        return taskRepository.findById(id).get();
    }

    @Override
    public Task createTask(TaskDto taskDto, Authentication authentication) {
        var task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setCategory(taskDto.getCategory());
        task.setStatus(taskDto.getStatus());
        task.setCreated(LocalDateTime.now());
        task.setUser(userService.findUserByEmail(authentication.getName()));

        // створення параметрів
        var parameterList = new ArrayList<TaskParameter>();
        if (taskDto.getTaskParameters() != null) {
            for (TaskParameterDto paramDto : taskDto.getTaskParameters()) {
                var param = new TaskParameter();
                param.setName(paramDto.getName());
                param.setWeight(paramDto.getWeight());
                param.setUnit(paramDto.getUnit());
                param.setOptimizationDirection(paramDto.getOptimizationDirection());
                param.setTask(task); // зв’язок з task
                parameterList.add(param);
            }
        }

        task.setTaskParameters(parameterList);
        return taskRepository.save(task);
    }


    @Override
    public void deleteTaskById(Long id){
        if(taskRepository.existsById(id)){
            taskRepository.deleteById(id);
        }
        else throw new RuntimeException("No such task");
    }

    @Override
    public Task addTaskParameterToTask(Long taskId ,List<TaskParameterDto> taskParameterDtoList){
        var task = taskRepository.findById(taskId).get();
        var taskParameterList = new ArrayList<TaskParameter>();
        for(TaskParameterDto taskParameterDto : taskParameterDtoList){
            var taskParameter = new TaskParameter();
            taskParameter.setTask(task);
            taskParameter.setName(taskParameterDto.getName());
            taskParameter.setWeight(taskParameterDto.getWeight());
            taskParameter.setUnit(taskParameterDto.getUnit());
            taskParameter.setOptimizationDirection(taskParameterDto.getOptimizationDirection());
            System.out.println(taskParameter.getOptimizationDirection().getLabel());
            taskParameterList.add(taskParameter);
        }
        task.setTaskParameters(taskParameterList);
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, TaskDto taskDto) {
        var task = taskRepository.findById(id).orElseThrow();

        if (taskDto.getTitle() != null)
            task.setTitle(taskDto.getTitle());
        if (taskDto.getDescription() != null)
            task.setDescription(taskDto.getDescription());
        if (taskDto.getCategory() != null)
            task.setCategory(taskDto.getCategory());
        if (taskDto.getStatus() != null)
            task.setStatus(taskDto.getStatus());

        if (taskDto.getTaskParameters() != null) {
            // Створюємо мапу існуючих параметрів для швидкого доступу
            Map<String, TaskParameter> existing = task.getTaskParameters().stream()
                    .collect(Collectors.toMap(TaskParameter::getName, p -> p));

            List<TaskParameter> updatedList = new ArrayList<>();

            for (TaskParameterDto dto : taskDto.getTaskParameters()) {
                TaskParameter param = existing.getOrDefault(dto.getName(), new TaskParameter());
                param.setName(dto.getName());
                param.setWeight(dto.getWeight());
                param.setUnit(dto.getUnit());
                param.setTask(task);
                param.setOptimizationDirection(dto.getOptimizationDirection());
                updatedList.add(param);
            }

            task.getTaskParameters().clear();
            task.getTaskParameters().addAll(updatedList);
        }

        return taskRepository.save(task);
    }

    @Override
    public Map<String, Double> findBestDecisionAHP(Long taskId, double[][] comparisonMatrix) {
        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task with id: " + taskId + ", not found"));

        return ahpService.evaluateDecisionsAHP(task, comparisonMatrix);
    }

// TaskServiceImpl.java

    @Override
    public Map<String, Double> findBestDecisionTOPSIS(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Задачу не знайдено"));

        var weights = new double[task.getTaskParameters().size()];

        for(int i = 0; i < weights.length; i++){
            weights[i] = task.getTaskParameters().get(i).getWeight();
        }

        return topsisService.evaluateDecisionsTOPSIS(task, weights);
    }

    @Override
    public Map<String, Double> findBestDecisionELECTRE(Long taskId, double cThreshold, double dThreshold) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Задачу не знайдено"));

        List<Decision> decisions = task.getDecisions();
        if (decisions == null || decisions.isEmpty()) {
            throw new IllegalStateException("Немає запропонованих рішень для задачі");
        }

        return electreService.evaluateDecisionsELECTRE(task, cThreshold, dThreshold);
    }


    @Override
    public Task setSolution(Long taskId, Long decisionId) {
        var task = taskRepository.findById(taskId).orElseThrow();

        var decision = task.getDecisions().stream()
                .filter(d -> d.getId().equals(decisionId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Рішення не знайдено серед рішень задачі"));

        task.setChosenDecision(decision);
        task.setStatus(TaskStatus.SOLVED);

        decision.setDecisionStatus(DecisionStatus.APPROVED);
        decisionRepository.save(decision);


        return taskRepository.save(task);
    }

    private int getStatusPriority(DecisionStatus status) {
        return switch (status) {
            case APPROVED -> 0;
            case PROPOSED -> 1;
            case REJECTED -> 2;
            default -> 3;
        };
    }

    @Override
    public String recommendBestMethodForTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Задачу не знайдено"));

        int numParams = task.getTaskParameters().size();

        boolean hasWeights = task.getTaskParameters().stream()
                .anyMatch(p -> false);

        boolean hasDirections = task.getTaskParameters().stream()
                .allMatch(p -> p.getOptimizationDirection() != null);

        if (numParams <= 6) {
            return "AHP";
        } else if (numParams >= 7 && hasWeights && hasDirections) {
            return "TOPSIS";
        } else if (hasWeights && hasDirections) {
            return "ELECTRE";
        } else {
            return null;
        }
    }

}
