package dss.service;

import dss.model.entity.Task;

import java.util.Map;

public interface ElectreService {
//    Map<String, Double> evaluateDecisionsELECTRE(Task task);

    Map<String, Double> evaluateDecisionsELECTRE(Task task, double cThreshold, double dThreshold);
}
