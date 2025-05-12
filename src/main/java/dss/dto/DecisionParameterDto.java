package dss.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DecisionParameterDto {
    @NotNull
    private double value;

    @NotBlank
    @Size(min = 1, max = 255)
    private String comment;

    @NotNull
    private Long taskParameterId;
}
