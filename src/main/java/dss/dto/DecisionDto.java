package dss.dto;

import dss.model.entity.enums.DecisionStatus;
import dss.model.entity.enums.DecisionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DecisionDto {

    private Long id;

    @NotNull(message = "Тип рішення не може бути null")
    private DecisionType decisionType;

    @NotBlank(message = "Опис рішення не може бути порожнім")
    private String description;

    @NotNull
    private DecisionStatus decisionStatus;

    @NotNull
    private Long taskId;

    @NotNull
    private Long userId;

    @Null
    private List<ScenarioDto> scenariosDto;
}

