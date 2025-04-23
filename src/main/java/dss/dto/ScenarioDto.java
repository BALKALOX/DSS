package dss.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScenarioDto {

    @NotBlank
    @NotNull
    @Size(min = 1, max = 50)
    private String title;

    @NotBlank
    @NotNull
    @Size(min = 10, max = 500)
    private String description;

    @NotBlank
    @Size(min = 0, max = 1)
    private double possibility;

    @NotBlank
    @NotNull
    private Long decisionId;


}
