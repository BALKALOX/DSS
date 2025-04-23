package dss.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TaskParameter {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double  weight;

    private String unit;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;
}
