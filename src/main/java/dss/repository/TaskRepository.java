package dss.repository;

import dss.model.entity.Decision;
import dss.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Task save(Task task);

    List<Task> findAll();

    Optional<Task> findById(Long id);

    boolean existsById(Long id);
    Task findByTitle(String title);
//    Task findByDecision(Decision decision);
    Task findByChosenDecision(Decision decision);
    void deleteById(Long id);

}
