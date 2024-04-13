package am.aua.equationSolver.Equations;

import java.util.ArrayList;
import java.util.List;

public class SLE {
    private List<LinearEquation> equations;

    public SLE() {
        this.equations = new ArrayList<>();
    }

    public void addEquation(LinearEquation eq) {
        equations.add(eq);
    }


    // Solve the system using Gaussian elimination
    public double[] solveSystem() {
        int n = equations.size(); // Number of equations
        double[][] matrix = new double[n][n + 1]; // Augmented matrix
        // Construct the augmented matrix from equations
        for (int i = 0; i < n; i++) {
            double[] coefficients = equations.get(i).getCoefficients();
            for (int j = 0; j < n + 1; j++) { // n + 1 because it includes the constant term
                matrix[i][j] = coefficients[j];
            }
        }
        for (int i = 0; i < n; i++) {
            int max = i;
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(matrix[j][i]) > Math.abs(matrix[max][i])) {
                    max = j;
                }
            }

            // Swap rows if necessary
            double[] temp = matrix[i];
            matrix[i] = matrix[max];
            matrix[max] = temp;

            // Singular matrix check or no unique solution
            if (matrix[i][i] == 0) {
                System.out.println("No unique solution exists");
                System.exit(0);
            }

            // Eliminate below
            for (int j = i + 1; j < n; j++) {
                double factor = matrix[j][i] / matrix[i][i];
                for (int k = i; k <= n; k++) {
                    matrix[j][k] -= factor * matrix[i][k];
                }
            }
        }

        // Back substitution
        double[] solution = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += matrix[i][j] * solution[j];
            }
            solution[i] = (matrix[i][n] - sum) / matrix[i][i];
        }

        return solution;
    }
}
