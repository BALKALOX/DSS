package dss.repository;

import dss.model.entity.Decision;
import dss.model.entity.Scenario;
import dss.model.entity.User;
import dss.model.entity.enums.DecisionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScenarioRepository extends JpaRepository<Scenario, Long> {
    Scenario findByTitle(String title);
    Scenario findById(long id);
    List<Scenario> findByTitleContaining(String title);
    List<Scenario> findAll();
    List<Scenario> findAllByDecision(Decision decision);
    List<Scenario> findAllByUser(User user);
    List<Scenario> findAllByDecisionDecisionType(DecisionType decisionType);
    Scenario save(Scenario scenario);
    void deleteById(long id);
    void deleteByTitle(String title);
    void deleteAllByDecision(Decision decision);

}
