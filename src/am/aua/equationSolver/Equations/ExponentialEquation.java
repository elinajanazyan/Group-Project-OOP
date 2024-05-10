package am.aua.equationSolver.Equations;

public class ExponentialEquation extends Equation {
    public ExponentialEquation(String equation) throws WrongInputException {
        equation = equation.replaceAll("\\s+", "");
        if (!(equation.indexOf("*") < equation.indexOf("^x") && equation.indexOf("^x") < equation.indexOf("="))) {
            throw new WrongInputException("Wrong input format. " +
                    "The equation should be of the form \"a * b^x = c\"");
        }
        String[] parts = equation.split("\\*|=");
        if (parts[0].contains("^x")) {
            if (parts[0].startsWith("-")) {
                parts = new String[] {"-1", parts[0].substring(1), parts[1]};
            } else {
                parts = new String[] {"1", parts[0], parts[1]};
            }
        }
        if (!equation.contains("=") || !equation.contains("^x")) {
            throw new WrongInputException("Wrong input format. " +
                    "The equation should be of the form \"a * b^x = c\"");
        } else if (parts.length != 3) {
            throw new WrongInputException("Equation format is incorrect.");
        }
        parts[1] = parts[1].replaceAll("\\^x", "");
        try {
            a = parseDouble(parts[0]);
            b = parseDouble(parts[1]);
            c = parseDouble(parts[2]);
        } catch (NumberFormatException e) {
            throw new WrongInputException("Wrong input format. " +
                    "The equation should be of the form \"a * b^x = c\"");
        }
        if (c / a < 0) {
            throw new WrongInputException("'a' and 'c' should be of the same sign");
        }
        if (a == 0) {
            throw new WrongInputException("a cannot be zero");
        }
    }

    private double parseDouble(String s) {
        if (s.equalsIgnoreCase("e")) {
            return Math.E;
        } else {
            return Double.parseDouble(s);
        }
    }

    public double[] getCoefficients() {
        return new double[]{a, b, c};
    }

    @Override
    public boolean isSolvable() {
        return b > 0 && b != 1 && a != 0 && (c / a > 0);
    }

    public static double logBase(double value, double base) throws WrongInputException {
        if (base <= 0 || base == 1 || value <= 0) {
            throw new WrongInputException("Base must be greater than 0 and not equal to 1, and value must be greater than 0.");
        }
        return Math.log(value) / Math.log(base);
    }

    public double solve() throws WrongInputException {
        return logBase(c / a, b);
    }
}
