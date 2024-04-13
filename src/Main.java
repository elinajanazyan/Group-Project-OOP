import java.util.InputMismatchException;
import java.util.Scanner;

import am.aua.equationSolver.CLI.ExponentialEquationConsole;
import am.aua.equationSolver.CLI.LinearConsole;
import am.aua.equationSolver.CLI.QuadraticConsole;
import am.aua.equationSolver.CLI.SLEconsole;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please choose what type of equation to solve: "  + "\n" +
                " 1 for Linear, 2 for Quadratic, 3 for System of Linear Equations, 4 for Exponential equation");
        boolean success = false;
        while(!success) {
            try{
                String input = sc.nextLine();
                input = input.replace("\\s+", "");
                if (input.length() !=1){
                    System.out.println("Enter a valid number: 1, 2, 3 or 4");
                } else {
                    if (input.equals("1")) {
                        LinearConsole.Linear();
                        success = true;
                    } else if (input.equals("2")) {
                        QuadraticConsole.Quadratic();
                        success = true;
                    } else if (input.equals("3")) {
                        SLEconsole.SLE();
                        success = true;
                    } else if (input.equals("4")) {
                        ExponentialEquationConsole.Exponential();
                        success = true;
                    }else {
                        System.out.println("Enter a valid number: 1, 2, 3 or 4");
                    }
                }

            } catch(Exception e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }

    }
}