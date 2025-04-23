package dss.controller.mvc;

import dss.dto.DecisionDto;
import dss.dto.ScenarioDto;
import dss.model.entity.Decision;
import dss.service.DecisionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("decisions")
@AllArgsConstructor
public class DecisionController {

    private final DecisionService decisionService;

    @GetMapping
    public String getAllDecisions(Model model) {
        model.addAttribute("decisions",decisionService.getAllDecisions());
        return "decisions";
    }
    @GetMapping("/{id}")
    public String getDecisionById(@PathVariable Long id, Model model) {
        model.addAttribute("decision",decisionService.getDecisionById(id));
        return "decision";
    }

    @GetMapping("/create")
    public String createDecision(Model model) {
        model.addAttribute("decision", new DecisionDto());
        return "createDecision";
    }

//    @PostMapping
//    public String createDecision(Model model, DecisionDto decisionDto,
//                                 List<ScenarioDto> scenariosDto,
//                                 Authentication authentication) {
//        var createdDecision = decisionService.createDecision(decisionDto, scenariosDto , authentication);
//
//        return "redirect:/decisions/" + createdDecision.getId();
//    }

    @GetMapping("/{id}/edit")
    public String editDecision(@PathVariable Long id, Model model) {
        model.addAttribute("decision",decisionService.getDecisionById(id));
        return "editDecision";
    }

//    @PostMapping("/{id}")
//    public String editDecision(@PathVariable Long id, Model model, DecisionDto decisionDto) {
//        var updatedDecision = decisionService.updateDecision(id, decisionDto);
//        return "redirect:/decisions/" + updatedDecision.getId();
//    }

//    @PostMapping("/{id}/delete")
//    public String deleteDecision(@PathVariable Long id) {
//        decisionService.deleteDecisionById(id);
//        return "redirect:/decisions";
//    }
}
