package dss.controller.mvc;

import dss.dto.DecisionDto;
import dss.model.entity.Decision;
import dss.model.entity.User;
import dss.service.DecisionService;
import dss.service.TaskService;
import dss.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("decisions")
@AllArgsConstructor
public class DecisionController {

    private final DecisionService decisionService;
    private final TaskService taskService;
    private final UserService userService;

    @GetMapping
    public String getAllDecisions(Model model) {
        model.addAttribute("decisions",decisionService.getAllDecisions());
        return "decisions/decisions";
    }
    @GetMapping("/{id}")
    public String viewDecision(@PathVariable Long id, Model model) {
        Decision decision = decisionService.getDecisionById(id);

        model.addAttribute("decision", decision);

        return "decisions/decision";
    }


    @GetMapping("/create")
    public String createDecision(@RequestParam("taskId") Long taskId, Model model) {
        DecisionDto decisionDto = new DecisionDto();
        decisionDto.setTaskId(taskId);
        model.addAttribute("task",taskService.getTaskById(taskId));
        model.addAttribute("decision", decisionDto);
        return "decisions/createDecision";
    }

    @GetMapping("/{id}/edit")
    public String editDecision(@PathVariable Long id, Model model) {
        model.addAttribute("decision",decisionService.getDecisionById(id));
        return "decisions/editDecision";
    }

}
