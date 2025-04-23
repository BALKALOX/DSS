package dss.service.impl;

import dss.dto.DecisionDto;
import dss.dto.ScenarioDto;
import dss.dto.TaskDto;
import dss.model.entity.Decision;
import dss.model.entity.Task;
import dss.model.entity.User;
import dss.repository.DecisionRepository;
import dss.repository.UserRepository;
import dss.service.DecisionService;
import dss.service.ScenarioService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class DecisionServiceImpl implements DecisionService {

    private final DecisionRepository DecisionRepository;
    private final UserRepository userRepository;
    private final DecisionRepository decisionRepository;
    private final ScenarioService scenarioService;

    @Override
    public List<Decision> getAllDecisions() {
        return DecisionRepository.findAll();
    }
    @Override
    public Decision getDecisionById(Long id){
        return DecisionRepository.findById(id).get();
    }

    @Override
    public List<Decision> getAllDesicionsByUser(User user){
        return DecisionRepository.findAllByUser(user);
    }
    @Override
    public Decision createDecision(DecisionDto decisionDto,
                                   Task task,
                                   List<ScenarioDto> scenariosDto,
                                   Authentication authentication){
        Decision decision = new Decision();
        decision.setTask(task);
        decision.setDescription(decisionDto.getDescription());
        decision.setDecisionType(decisionDto.getDecisionType());
        decision.setCreated(LocalDateTime.now());
        decision.setDecisionStatus(decisionDto.getDecisionStatus());
        decision.setScenarios(
                    scenarioService.MapScenariosDtoToScenarios(
                            scenariosDto, authentication
                            )
                    );

        decision.setUser(findUserByAuthentication(authentication));

        return DecisionRepository.save(decision);
    }

    @Override
    public Decision updateDecision(Long id,
                                   DecisionDto decisionDto,
                                   List<ScenarioDto> scenariosDto,
                                   Authentication authentication){
        Decision decision = DecisionRepository.findById(id).get();
        if(decision.equals(null)){
            return null;
        }
        decision.setDescription(decisionDto.getDescription());
        decision.setDecisionType(decisionDto.getDecisionType());
        decision.setDecisionStatus(decisionDto.getDecisionStatus());
        decision.setScenarios(scenarioService.MapScenariosDtoToScenarios(
                scenariosDto,authentication
                )
        );
        return decisionRepository.save(decision);
    }

    @Override
    public void deleteDecisionById(Long id, Authentication authentication){
        if (decisionRepository.existsById(id)){
            if (decisionRepository.findById(id).get().getUser()
                    .equals(findUserByAuthentication(authentication))){

                decisionRepository.deleteById(id);
            }
            else {
                throw new RuntimeException("Decision is not made by authenticated user." +
                        " It's not possible to delete the decision.");
            }
        }
        else {
            throw new RuntimeException("Desision with id = " + id +" not found");
        }


    }

    private User findUserByAuthentication(Authentication authentication){
        return userRepository.findByEmail(authentication.getName());
    }

    @Override
    public boolean existsById(Long id){
        return DecisionRepository.existsById(id);
    }

}
