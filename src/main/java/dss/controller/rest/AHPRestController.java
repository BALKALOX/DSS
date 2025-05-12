package dss.controller.rest;

import dss.model.entity.Decision;
import dss.service.AHPService;
import dss.service.DecisionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ahp")
@AllArgsConstructor
public class AHPRestController {

    @Qualifier("AHPService")
    private final AHPService ahpService;
    private final DecisionService decisionService;

}
