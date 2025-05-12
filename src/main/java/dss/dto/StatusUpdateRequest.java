package dss.dto;

import dss.model.entity.enums.DecisionStatus;
import lombok.Data;

@Data
public class StatusUpdateRequest {
    private DecisionStatus status;
    private String comment;
}

