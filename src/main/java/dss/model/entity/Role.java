package dss.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dss.utils.RoleUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="roles")
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name;


    @ManyToMany(mappedBy="roles")
    @JsonIgnore
    private List<User> users;

    public String getUkrName() {
        return RoleUtils.getLocalizedName(name);
    }

}
