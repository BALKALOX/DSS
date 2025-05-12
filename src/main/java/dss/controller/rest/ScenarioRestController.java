package dss.controller.rest;

import dss.dto.ScenarioDto;
import dss.model.entity.Scenario;
import dss.service.ScenarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/scenarios")
@AllArgsConstructor
public class ScenarioRestController {

    private ScenarioService scenarioService;

    @GetMapping
    public ResponseEntity<List<Scenario>> getAllScenarios() {
        return ResponseEntity.ok(scenarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Scenario> getScenarioById(@PathVariable Long id) {
        return ResponseEntity.ok(scenarioService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScenarioById(@PathVariable Long id,
                                                   Authentication authentication) {
        scenarioService.deleteById(id, authentication);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Scenario> createScenario(@RequestBody ScenarioDto scenarioDto,
                                                   Authentication authentication) {
        return ResponseEntity.ok(scenarioService
                .createScenario(scenarioDto,authentication)
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<Scenario> updateScenario(@PathVariable Long id,
                                                   @RequestBody ScenarioDto scenarioDto) {
        return ResponseEntity.ok(scenarioService.updateScenario(id,scenarioDto));
    }

}
