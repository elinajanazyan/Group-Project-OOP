package am.aua.equationSolver.Equations;

public class QuadraticEquation extends Equation{

    public QuadraticEquation(String equation) throws WrongInputException {
        equation = equation.replaceAll("\\s", "");

        if (!equation.contains("=")) {
            equation += "=0";
        }

        String[] parts = equation.split("=");
        if (parts.length != 2) {
            throw new WrongInputException("Invalid equation format.");
        }

        String leftSide = parts[0];
        double rightSide;
        try {
            rightSide = Double.parseDouble(parts[1]);
        } catch (NumberFormatException e) {
            throw new WrongInputException("Right side of the equation is not a valid number.");
        }

        String[] terms = leftSide.split("(?=[+-])");
        if (terms.length == 0) {
            throw new WrongInputException("No terms found in the equation.");
        }

        boolean hasX2 = false, hasX = false, hasConstant = false;
        for (String term : terms) {
            if (term.contains("x^2")) {
                hasX2 = true;
                term = term.replace("x^2", "");
            } else if (term.contains("x")) {
                hasX = true;
                term = term.replace("x", "");
            } else {
                hasConstant = true;
            }

            if (!term.isEmpty() && !term.equals("+") && !term.equals("-")) {
                try {
                    Double.parseDouble(term);
                } catch (NumberFormatException e) {
                    throw new WrongInputException("Invalid format, enter an equation of the form ax^2 + bx + c = k");
                }
            }
        }

        if (!hasX2) {
            throw new WrongInputException("The equation must contain an x^2 term.");
        }

        for (String term : terms) {
            if (term.contains("x^2")) {
                term = term.replace("x^2", "");
                a += (term.isEmpty() || term.equals("+")) ? 1 : (term.equals("-") ? -1 : Double.parseDouble(term));
            } else if (term.contains("x")) {
                term = term.replace("x", "");
                b += (term.isEmpty() || term.equals("+")) ? 1 : (term.equals("-") ? -1 :  Double.parseDouble(term));
            } else if (!term.isEmpty()) {
                c +=  Double.parseDouble(term);
            }
        }
        c -= rightSide;
    }
    public double[] solve() {
        double discriminant = b * b - 4 * a * c;
        double[] roots;

        if (a == 0) {
            if (b != 0) {
                roots = new double[1];
                roots[0] = -c / b;
            } else {
                roots = new double[0];
            }
        } else if (b == 0) {
            if (c < 0) {
                roots = new double[2];
                double sqrtValue = Math.sqrt(-c / a);
                roots[0] = sqrtValue;
                roots[1] = -sqrtValue;
            } else if (c == 0) {
                roots = new double[1];
                roots[0] = 0;
            } else {
                roots = new double[0];
            }
        } else {
            // General case
            if (discriminant > 0) {
                roots = new double[2];
                roots[0] = (-b + Math.sqrt(discriminant)) / (2 * a);
                roots[1] = (-b - Math.sqrt(discriminant)) / (2 * a);
            } else if (discriminant == 0) {
                roots = new double[1];
                roots[0] = -b / (2 * a);
            } else {
                roots = new double[0];
            }
        }
        return roots;
    }

    @Override
    public double[] getCoefficients() {
        return new double[]{a, b, c};
    }

    public boolean isSolvable() {
        double discriminant = b * b - 4 * a * c;
        return a != 0 || discriminant >= 0;
    }

}
