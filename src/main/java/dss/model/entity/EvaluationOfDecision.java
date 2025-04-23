package dss.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "evaluation_of_decisions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationOfDecision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "decision_id", nullable = false)
    private Decision decision;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User expert;

    private double score;

    private String justification;
}
