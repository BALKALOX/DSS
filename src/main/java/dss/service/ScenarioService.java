package dss.service;

import dss.dto.ScenarioDto;
import dss.model.entity.Decision;
import dss.model.entity.Scenario;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScenarioService {
    List<Scenario> findAllByDecision(Decision decision);

    Scenario createScenario(ScenarioDto scenarioDto, Authentication authentication);

    Scenario findById(Long id);

    List<Scenario> findAll();

    Scenario updateScenario(Long ScenarioId, ScenarioDto scenarioDto);


    void deleteById(Long id, Authentication authentication);

    void deleteAllByDecision(Decision decision);

    List<Scenario> MapScenariosDtoToScenarios(List<ScenarioDto> scenariosDto, Authentication authentication);
}
