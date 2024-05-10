package am.aua.equationSolver.CLI;
import am.aua.equationSolver.Equations.ExponentialEquation;
import am.aua.equationSolver.Equations.SLE;
import am.aua.equationSolver.Equations.LinearEquation;
import am.aua.equationSolver.Equations.WrongInputException;

import java.util.Scanner;

public class SLEconsole {
    public static void SLE() {
        Scanner sc = new Scanner(System.in);
        boolean success = false;
        SLE system = new SLE();

        while (!success) {
            try {
                System.out.println("Enter the first Linear Equation in the form ax + by = c:");
                String firstEquation = sc.nextLine();
                System.out.println("Enter the second Linear Equation in the form ax + by = c:");
                String secondEquation = sc.nextLine();

                system.addEquation(new LinearEquation(firstEquation));
                system.addEquation(new LinearEquation(secondEquation));

                double[] solutions = system.solve();
                System.out.println("Solutions:");
                System.out.println("x: " + solutions[0]);
                System.out.println("y: " + solutions[1]);
                success = true;
            } catch (WrongInputException e) {
                System.out.println("Invalid input: " + e.getMessage());
                system = new SLE();
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                system = new SLE();
            }
        }
    }
}

