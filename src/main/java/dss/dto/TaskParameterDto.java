package dss.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@Builder
public class TaskParameterDto {
    @NotBlank
    private String name;
    @NotNull
    private double weight;
    private String unit;
    @NotNull
    private Long taskId;
}
