package dss.service.impl;

import dss.model.entity.Decision;
import dss.model.entity.DecisionParameter;
import dss.model.entity.Task;
import dss.model.entity.TaskParameter;
import dss.model.entity.enums.OptimizationDirection;
import dss.service.AHPService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class AHPServiceImpl implements AHPService {

    @Override
    public Map<String, Double> evaluateDecisionsAHP(Task task, double[][] comparisonMatrix) {
        System.out.println("ComparisonMat");
        printMatrix(comparisonMatrix);

        double[][] normalizedComparisonMatrix = normalizeComparisonMatrix(comparisonMatrix);
        printMatrix(normalizedComparisonMatrix);

        double[] weights = calculateWeights(normalizedComparisonMatrix);
        System.out.println("Weights");
        printArray(weights);

        double[][] decisionMatrix = buildDecisionMatrix(task);
        double[][] normalizedDecisionMatrix = normalizeDecisionMatrix(task, decisionMatrix);
        double[] resultScores = calculateScores(normalizedDecisionMatrix, weights);

        System.out.println("Result of AHP");
        printArray(resultScores);

        return buildResultMap(task.getDecisions(), resultScores);
    }

    private Map<String, Double> buildResultMap(List<Decision> decisions, double[] scores) {
        Map<String, Double> map = new LinkedHashMap<>();
        for (int i = 0; i < decisions.size(); i++) {
            map.put(decisions.get(i).getTitle(), scores[i]);
        }
        return map;
    }

    private double[][] normalizeComparisonMatrix(double[][] matrix) {
        int n = matrix.length;
        double[] colSum = new double[n];

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                colSum[j] += matrix[i][j];
            }
        }

        double[][] normalized = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                normalized[i][j] = matrix[i][j] / (colSum[j] == 0 ? 1 : colSum[j]);
            }
        }

        return normalized;
    }

    private double[] calculateWeights(double[][] normalizedMatrix) {
        int rows = normalizedMatrix.length;
        int cols = normalizedMatrix[0].length;

        double[] weights = new double[rows];
        for (int i = 0; i < rows; i++) {
            double sum = 0;
            for (int j = 0; j < cols; j++) {
                sum += normalizedMatrix[i][j];
            }
            weights[i] = sum / cols;
        }
        return weights;
    }

    private double[][] buildDecisionMatrix(Task task) {
        List<Decision> decisions = task.getDecisions();
        int criteriaCount = task.getTaskParameters().size();
        double[][] matrix = new double[decisions.size()][criteriaCount];

        for (int i = 0; i < decisions.size(); i++) {
            List<DecisionParameter> params = decisions.get(i).getDecisionParameters();
            for (int j = 0; j < criteriaCount; j++) {
                matrix[i][j] = params.get(j).getValue();
            }
        }
        return matrix;
    }

    private double[][] normalizeDecisionMatrix(Task task, double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] normalized = new double[rows][cols];

        List<TaskParameter> parameters = task.getTaskParameters();

        for (int j = 0; j < cols; j++) {
            OptimizationDirection direction = parameters.get(j).getOptimizationDirection();

            int finalJ = j;
            int finalJ1 = j;
            double extreme = direction == OptimizationDirection.MAXIMIZE ?
                    Arrays.stream(matrix).mapToDouble(row -> row[finalJ]).max().orElse(1) :
                    Arrays.stream(matrix).mapToDouble(row -> row[finalJ1]).min().orElse(1);

            for (int i = 0; i < rows; i++) {
                normalized[i][j] = direction == OptimizationDirection.MAXIMIZE ?
                        matrix[i][j] / extreme :
                        extreme / (matrix[i][j] == 0 ? 1 : matrix[i][j]);
            }
        }

        return normalized;
    }

    private double[] calculateScores(double[][] matrix, double[] weights) {
        double[] scores = new double[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                scores[i] += matrix[i][j] * weights[j];
            }
        }

        return scores;
    }

    private void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }

    private void printArray(double[] array) {
        for (double val : array) {
            System.out.print(val + "\t");
        }
        System.out.println();
    }
}
