package am.aua.equationSolver.Equations;
public class LinearEquation extends Equation{
    public LinearEquation(String equation) throws IllegalArgumentException {
        equation = equation.replaceAll("\\s+", "");
        if (equation.startsWith("x")) {
            equation = equation.replace("x", "1x");
        } else if (equation.startsWith("-x")) {
            equation = equation.replace("-x", "-1x");
        }
        if (equation.contains("+y")) {
            equation = equation.replace("+y", "+1y");
        } else if (equation.contains("-y")) {
            equation = equation.replace("-y", "-1y");
        }

        String[] parts = equation.split("x\\+|y=|x");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Equation format is incorrect.");
        }
        a = Double.parseDouble(parts[0]);
        b = Double.parseDouble(parts[1]);
        c = Double.parseDouble(parts[2]);

        if (a == 0 && b == 0) {
            throw new IllegalArgumentException("a and b cannot be zero.");
        }
    }

    public boolean isSolvable() {
        // Equation is not solvable if a and b are zero and c is not zero
        return !(a == 0 && b == 0 && c != 0);
    }


    public double solveForX(double y) {
        if (a == 0) {
            throw new ArithmeticException("Cannot solve for x when the coefficient of x is zero.");
        }
        return (c - b * y) / a;
    }

    public double solveForY(double x) {
        if (b == 0) {
            throw new ArithmeticException("Cannot solve for y when the coefficient of y is zero.");
        }
        return (c - a * x) /  b;
    }
    public double[] getCoefficients() {
        return new double[]{a, b, c};
    }

    public String toString(){
        return a + "x" + b + "y = " + c;
    }
}