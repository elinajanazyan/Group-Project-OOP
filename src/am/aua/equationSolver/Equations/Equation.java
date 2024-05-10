package am.aua.equationSolver.Equations;

public abstract class Equation implements getCoefficients {
     protected double a;
     protected double b;
     protected double c;
     public abstract double[] getCoefficients();

     public abstract boolean isSolvable();
}
