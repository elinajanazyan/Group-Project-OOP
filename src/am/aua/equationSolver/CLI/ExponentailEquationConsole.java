package am.aua.equationSolver.CLI;

import am.aua.equationSolver.Equations.ExponentialEquation;



import java.util.Scanner;
public class ExponentailEquationConsole {
    public static void Exponential() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter exponential equation of the form a * b^x = c");
        boolean success = false;
        while(!success) {
            try {
                ExponentialEquation e= new ExponentialEquation(sc.nextLine());
                e.solve();
                success = true;
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }
}

