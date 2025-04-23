package dss.controller.rest;

import dss.dto.DecisionDto;
import dss.dto.ScenarioDto;
import dss.model.entity.Decision;
import dss.model.entity.Task;
import dss.repository.DecisionRepository;
import dss.service.DecisionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/decisions")
@AllArgsConstructor
public class DecisionRestController {

    private DecisionService decisionService;

    @GetMapping
    public ResponseEntity<List<Decision>> getDecisions() {

        var decisions = decisionService.getAllDecisions();

        return ResponseEntity.ok(decisions);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Decision> getDecisionById(@PathVariable Long id) {
        return ResponseEntity.ok(decisionService.getDecisionById(id));
    }

    @PostMapping
    public ResponseEntity<Decision> createDecision(DecisionDto decision,
                                                   Task task,
                                                   List<ScenarioDto> scenarios,
                                                   Authentication authentication) {

        return ResponseEntity.ok(
                decisionService.createDecision(decision,task, scenarios, authentication)
        );
    }

    @PutMapping()
    ResponseEntity<Decision> updateDecision(Long id,
                                                           DecisionDto decision,
                                                           List<ScenarioDto> scenarios,
                                                           Authentication authentication){

        return ResponseEntity.ok(decisionService
                .updateDecision(id, decision, scenarios, authentication)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDecision(@PathVariable Long id,
                                               Authentication authentication) {

        decisionService.deleteDecisionById(id, authentication);

        return ResponseEntity.noContent().build();
    }


}
