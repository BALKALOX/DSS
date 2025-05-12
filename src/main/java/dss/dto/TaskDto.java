package dss.dto;

import dss.model.entity.enums.TaskCategory;
import dss.model.entity.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TaskDto {
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private TaskCategory category;

    private TaskStatus status;

    private List<TaskParameterDto> taskParameters;

}
