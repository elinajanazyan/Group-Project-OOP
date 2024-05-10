package am.aua.equationSolver.Equations;

import java.util.ArrayList;
import java.util.List;

public class SLE extends Equation {
    private List<LinearEquation> equations;

    public SLE() {
        this.equations = new ArrayList<>();
    }

    public void addEquation(LinearEquation eq) {
        equations.add(eq);
    }

    public double[] getCoefficients() {
        if (equations.isEmpty()) {
            return new double[0];
        }

        int numEquations = equations.size();
        int numCoefficientsPerEquation = equations.get(0).getCoefficients().length;
        double[] flatCoefficients = new double[numEquations * numCoefficientsPerEquation];

        for (int i = 0; i < numEquations; i++) {
            System.arraycopy(equations.get(i).getCoefficients(), 0, flatCoefficients, i * numCoefficientsPerEquation, numCoefficientsPerEquation);
        }

        return flatCoefficients;
    }

    public boolean isSolvable() {
        double[][] matrix = buildAugmentedMatrix();
        return hasConsistentEquations(matrix);
    }

    private double[][] buildAugmentedMatrix() {
        int numEquations = equations.size();
        int numVariables = equations.get(0).getCoefficients().length - 1;
        double[][] matrix = new double[numEquations][numVariables + 1];

        for (int i = 0; i < numEquations; i++) {
            double[] coeffs = equations.get(i).getCoefficients();
            System.arraycopy(coeffs, 0, matrix[i], 0, coeffs.length);
        }
        return matrix;
    }

    private boolean hasConsistentEquations(double[][] matrix) {
        int rowCount = matrix.length;
        int colCount = matrix[0].length;

        for (int row = 0; row < rowCount; row++) {
            int pivot = findPivot(matrix, row);
            if (pivot == -1) {
                if (matrix[row][colCount - 1] != 0) {
                    return false;
                }
                continue;
            }

            normalizeRow(matrix, row, pivot);
            eliminateColumn(matrix, row, pivot);
        }

        return true;
    }

    private int findPivot(double[][] matrix, int row) {
        int colCount = matrix[0].length - 1;
        for (int col = 0; col < colCount; col++) {
            if (Math.abs(matrix[row][col]) > 0) {
                return col;
            }
        }
        return -1;
    }

    private void normalizeRow(double[][] matrix, int row, int pivot) {
        double pivotValue = matrix[row][pivot];
        int colCount = matrix[0].length;
        for (int col = pivot; col < colCount; col++) {
            matrix[row][col] /= pivotValue;
        }
    }

    private void eliminateColumn(double[][] matrix, int row, int pivot) {
        int rowCount = matrix.length;
        int colCount = matrix[0].length;
        for (int r = 0; r < rowCount; r++) {
            if (r == row) continue;
            double factor = matrix[r][pivot];
            for (int col = pivot; col < colCount; col++) {
                matrix[r][col] -= factor * matrix[row][col];
            }
        }
    }

    public double[] solve() throws WrongInputException {
        if (!isSolvable()) {
            throw new IllegalStateException("The system of equations is not solvable.");
        }

        double[][] matrix = buildAugmentedMatrix();
        int rowCount = matrix.length;
        int colCount = matrix[0].length - 1;

        // Perform row reduction
        for (int row = 0; row < rowCount; row++) {
            int pivot = findPivot(matrix, row);
            if (pivot == -1) {
                continue;
            }

            normalizeRow(matrix, row, pivot);
            eliminateColumn(matrix, row, pivot);
        }

        boolean hasFreeVariables = rowCount < colCount;
        for (int i = rowCount - 1; i >= 0; i--) {
            if (Math.abs(matrix[i][i]) <= 0) {
                hasFreeVariables = true;
                break;
            }
        }

        if (hasFreeVariables) {
            throw new IllegalStateException();
        }

        double[] solution = new double[colCount];
        for (int i = rowCount - 1; i >= 0; i--) {
            solution[i] = matrix[i][colCount];
            for (int j = i + 1; j < colCount; j++) {
                solution[i] -= matrix[i][j] * solution[j];
            }
            solution[i] /= matrix[i][i];
        }

        return solution;
    }
}
