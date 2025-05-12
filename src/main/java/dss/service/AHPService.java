package dss.service;

import dss.model.entity.Decision;
import dss.model.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface AHPService {

//    Decision selectBestDecision(List<Decision> decisions, double[][] comparisonMatrix);

    Map<String, Double> evaluateDecisionsAHP(Task task, double[][] comparisonMatrix);
}
