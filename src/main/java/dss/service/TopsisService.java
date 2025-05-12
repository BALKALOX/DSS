package dss.service;

import dss.model.entity.Task;

import java.util.Map;

public interface TopsisService {

    Map<String, Double> evaluateDecisionsTOPSIS(Task task, double[] weights);

}
