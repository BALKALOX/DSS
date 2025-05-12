package dss.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DecisionParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double value;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "decision_id", nullable = false)
    @JsonBackReference
    private Decision decision;

    @ManyToOne
    @JoinColumn(name = "task_parameter_id", nullable = false)
    private TaskParameter taskParameter;
}
