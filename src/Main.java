import java.util.InputMismatchException;
import java.util.Scanner;

import am.aua.equationSolver.CLI.ExponentailEquationConsole;
import am.aua.equationSolver.CLI.LinearConsole;
import am.aua.equationSolver.CLI.QuadraticConsole;
import am.aua.equationSolver.CLI.SLEconsole;
import am.aua.equationSolver.Equations.ExponentialEquation;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please choose what type of equation to solve: "  + "\n" +
                " 1 for Linear, 2 for Quadratic, 3 for System of Linear Equations, 4 for Exponential equation");
        boolean success = false;
        while(!success) {
            try{
                int input = sc.nextInt();
                if (input == 1) {
                    LinearConsole.Linear();
                    success = true;
                } else if (input == 2) {
                    QuadraticConsole.Quadratic();
                    success = true;
                } else if (input == 3) {
                    SLEconsole.SLE();
                    success = true;
                }else if (input == 4) {
                        ExponentailEquationConsole.Exponential();
                        success = true;
                }
            else {
                    System.out.println("Enter a valid number: 1, 2, 3 or 4");
                }
            } catch(InputMismatchException e){
                System.out.println("Enter a valid number: 1, 2, 3 or 4");
                sc.nextLine();
            }
        }

    }
}