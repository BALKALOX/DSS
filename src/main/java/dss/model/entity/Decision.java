package dss.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dss.model.entity.enums.DecisionCategory;
import dss.model.entity.enums.DecisionStatus;
import dss.model.entity.enums.DecisionCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "decisions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Decision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DecisionCategory decisionCategory;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @CreationTimestamp
    private LocalDateTime created;

    @Enumerated(EnumType.STRING)
    private DecisionStatus decisionStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    @JsonIgnore
    private Task task;

    @OneToMany(mappedBy = "decision", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DecisionParameter> decisionParameters;


    @OneToMany(mappedBy = "decision", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Scenario> scenarios;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

//    @OneToMany(mappedBy = "decision", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Comment> comments;

    private double score;

    @OneToMany(mappedBy = "decision", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExpertEvaluation> expertEvaluations;


    public double getRate() {
        if (expertEvaluations == null || expertEvaluations.isEmpty()) return 0.0;
        return expertEvaluations.stream()
                .mapToDouble(ExpertEvaluation::getScore)
                .average()
                .orElse(0.0);
    }




}
