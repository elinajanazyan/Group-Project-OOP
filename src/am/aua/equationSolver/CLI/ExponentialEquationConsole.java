package am.aua.equationSolver.CLI;

import am.aua.equationSolver.Equations.ExponentialEquation;
import am.aua.equationSolver.Equations.WrongInputException;


import java.util.Scanner;

public class ExponentialEquationConsole {
    public static void Exponential() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter exponential equation of the form a * b^x = c");
        boolean success = false;
        while (!success) {
            try {
                ExponentialEquation e = new ExponentialEquation(sc.nextLine());
                System.out.printf("%.2f", e.solve());
                success = true;
            } catch (WrongInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

