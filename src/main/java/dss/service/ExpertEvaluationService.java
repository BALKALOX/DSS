package dss.service;

import dss.dto.ExpertEvaluationDto;
import dss.model.entity.Decision;
import dss.model.entity.ExpertEvaluation;
import dss.model.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ExpertEvaluationService {
    ExpertEvaluation submitEvaluation(ExpertEvaluationDto expertEvaluationDto, Authentication authentication);

    ExpertEvaluation updateEvaluation(Decision decision, ExpertEvaluationDto expertEvaluationDto, Authentication authentication);

    Map<Long, Double> calculateAverageRatingsForTask(Long taskId);

    ExpertEvaluation getByExpertAndDecision(User expert, Decision decision);
}
