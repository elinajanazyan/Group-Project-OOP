package am.aua.equationSolver.CLI;
import am.aua.equationSolver.Equations.SLE;
import am.aua.equationSolver.Equations.LinearEquation;


import java.util.Scanner;
public class SLEconsole {
    public static void SLE() {
        Scanner sc = new Scanner(System.in);

        SLE system = new SLE();
        System.out.println("Enter the first Linear Equation (ax + by = c)");
        system.addEquation(new LinearEquation(sc.nextLine()));
        System.out.println("Enter the second Linear Equation (ax + by = c)");
        system.addEquation(new LinearEquation(sc.nextLine()));

        try {
            double[] solutions = system.solveSystem();
            System.out.println("Solutions:");
            System.out.println("x: " + solutions[0]);
            System.out.println("y: " + solutions[1]);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
}
