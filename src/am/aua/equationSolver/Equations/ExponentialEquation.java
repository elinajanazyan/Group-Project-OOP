package am.aua.equationSolver.Equations;


public class ExponentialEquation extends Equation{
    public ExponentialEquation(String equation) throws IllegalArgumentException {
        equation = equation.replaceAll("\\s+", "");


        String[] parts = equation.split("\\*|^x|=");
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
    public double[] getCoefficients() {
        return new double[]{a, b, c};
    }

    public static double logBase(double value, double base) {
        if (base <= 0 || base == 1 || value <= 0) {
            throw new IllegalArgumentException("Base must be greater than 0 and not equal to 1, and value must be greater than 0.");
        }
        return Math.log(value) / Math.log(base); // Using natural logarithm (base e)
    }


    public void solve() {
        double value = c/a;
        double logarithm  = logBase(value, b);
        System.out.println(logarithm);
        }
    }
