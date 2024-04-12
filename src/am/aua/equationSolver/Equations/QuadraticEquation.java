package am.aua.equationSolver.Equations;

public class QuadraticEquation extends Equation{

    public QuadraticEquation(String equation) throws IllegalArgumentException {
        equation = equation.replaceAll("\\s", "");

        if (!equation.contains("=")) {
            equation += "=0";
        }

        String[] parts = equation.split("=");
        String leftSide = parts[0];
        double rightSide = parts.length > 1 ?  Double.parseDouble(parts[1]) : 0;

        String[] terms = leftSide.split("(?=[+-])");


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

    public void solve() {
        double discriminant = b * b - 4 * a * c;

        if (a == 0) {
            double root = -c / b;
            System.out.println("One real root: " + root);

        } if (b == 0){
                if (c < 0){
                    System.out.println("Two real and distinct roots: " +
                            Math.sqrt(-c) + " and " + -Math.sqrt(-c));
                }
        } else {
            if (discriminant > 0) {
                double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
                System.out.println("Two real and distinct roots: " + root1 + " and " + root2);
            } else if (discriminant == 0) {
                double root = -b / (2 * a);
                System.out.println("One real root: " + root);
            } else {
                System.out.println("No real roots.");
            }
        }
    }

    @Override
    public double[] getCoefficients() {
        return new double[]{a, b, c};
    }
}
