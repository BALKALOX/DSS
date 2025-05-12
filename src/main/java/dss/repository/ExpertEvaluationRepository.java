package dss.repository;

import dss.model.entity.Decision;
import dss.model.entity.ExpertEvaluation;
import dss.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpertEvaluationRepository extends JpaRepository<ExpertEvaluation,Long> {
    List<ExpertEvaluation> findAll();
    Optional<ExpertEvaluation> findById(Long id);
    ExpertEvaluation save(ExpertEvaluation expertEvaluation);
    void deleteById(Long id);
    ExpertEvaluation findExpertEvaluationByExpertAndDecision(User expert, Decision decision);
    Optional<ExpertEvaluation> findByExpertIdAndDecisionId(Long expertId, Long decisionId);

    Optional<ExpertEvaluation> findByDecisionIdAndExpertId(Long id, Long id1);
}
