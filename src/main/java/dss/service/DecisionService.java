package dss.service;

import dss.dto.DecisionDto;
import dss.dto.ScenarioDto;
import dss.model.entity.Decision;
import dss.model.entity.Task;
import dss.model.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DecisionService {
    List<Decision> getAllDecisions();

    Decision getDecisionById(Long id);

    List<Decision> getAllDesicionsByUser(User user);

    Decision createDecision(DecisionDto decisionDto,
                            Task task,
                            List<ScenarioDto> scenariosDto,
                            Authentication authenticationn);

    Decision updateDecision(Long id,
                            DecisionDto decisionDto,
                            List<ScenarioDto> scenariosDto,
                            Authentication authentication);

    void deleteDecisionById(Long id, Authentication authentication);

    boolean existsById(Long id);
}
