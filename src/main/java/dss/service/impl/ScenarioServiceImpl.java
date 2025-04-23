package dss.service.impl;


import dss.dto.ScenarioDto;
import dss.model.entity.Decision;
import dss.model.entity.Scenario;
import dss.repository.DecisionRepository;
import dss.repository.ScenarioRepository;
import dss.service.ScenarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ScenarioServiceImpl implements ScenarioService {

    private ScenarioRepository scenarioRepository;
    private DecisionRepository decisionRepository;
    private UserServiceImpl userService;



    @Override
    public Scenario findById(Long id) {
        return scenarioRepository.findById(id).get();
    }

    @Override
    public List<Scenario> findAll() {
        return scenarioRepository.findAll();
    }

    @Override
    public List<Scenario> findAllByDecision(Decision decision) {
        return scenarioRepository.findAllByDecision(decision);
    }

    @Override
    public Scenario createScenario(ScenarioDto scenarioDto, Authentication authentication) {

        Scenario scenario = new Scenario();
        scenario.setTitle(scenarioDto.getTitle());
        scenario.setDescription(scenarioDto.getDescription());
        scenario.setPossibility(scenarioDto.getPossibility());
        scenario.setDecision(decisionRepository.findById(scenarioDto.getDecisionId()).get());
        scenario.setUser(userService.findUserByEmail(authentication.getName()));

        return scenarioRepository.save(scenario);
    }
    @Override
    public Scenario updateScenario(Long ScenarioId, ScenarioDto scenarioDto) {
        Scenario scenario = scenarioRepository.findById(ScenarioId).get();
        if (scenario != null) {
            throw new RuntimeException("Scenario with id " + ScenarioId + " not found");
        }

        scenario.setTitle(scenarioDto.getTitle());
        scenario.setDescription(scenarioDto.getDescription());
        scenario.setPossibility(scenarioDto.getPossibility());
        scenario.setDecision(decisionRepository.findById(scenarioDto.getDecisionId()).get());
        return scenarioRepository.save(scenario);

    }

    @Override
    public void deleteById(Long id, Authentication authentication){
        scenarioRepository.deleteById(id);
    }

    @Override
    public void deleteAllByDecision(Decision decision) {
        scenarioRepository.deleteAllByDecision(decision);
    }

    @Override
    public List<Scenario> MapScenariosDtoToScenarios(List<ScenarioDto> scenariosDto, Authentication authentication) {
        List<Scenario> scenarios = new ArrayList<>();

        for (ScenarioDto scenarioDto : scenariosDto) {
            Scenario scenario = new Scenario();
            scenario.setTitle(scenarioDto.getTitle());
            scenario.setDescription(scenarioDto.getDescription());
            scenario.setPossibility(scenarioDto.getPossibility());
            scenario.setDecision(decisionRepository.findById(scenarioDto.getDecisionId()).get());
            scenario.setUser(userService.findUserByEmail(authentication.getName()));
            scenarios.add(scenario);
        }
        return scenarios;
    }
}
