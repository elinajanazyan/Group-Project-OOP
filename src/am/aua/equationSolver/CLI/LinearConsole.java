package am.aua.equationSolver.CLI;

import am.aua.equationSolver.Equations.LinearEquation;
import am.aua.equationSolver.Equations.WrongInputException;

import java.util.Scanner;

public class LinearConsole {

    public static void Linear() {
        Scanner sc = new Scanner(System.in);
        boolean validInput = false;
        LinearEquation equation = null;

        while (!validInput) {
            try {
                System.out.println("Enter a linear equation (ax_+_by_=_c):");
                String inputEquation = sc.nextLine();
                equation = new LinearEquation(inputEquation);
                validInput = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Choose the variable to provide (x or y):");
        String variable = sc.nextLine();

        while (!variable.equals("x") && !variable.equals("y")) {
            System.out.println("Invalid choice. Please enter 'x' or 'y'.");
            variable = sc.nextLine();
        }

        System.out.println("Enter the value for " + variable + ":");
        while (!sc.hasNextDouble()) {
            sc.next();
            System.out.println("Invalid input. Please enter a numeric value.");
        }
        double value = sc.nextDouble();

        if (variable.equals("x")) {
            try{
                double y = equation.solveForY(value);
                System.out.printf("Given x = %.2f, then y = %.2f%n", value, y);
            }
            catch(WrongInputException e){
                System.out.println("Cannot divide by zero.");
            }

        } else {
            try{
                double x = equation.solveForX(value);
                System.out.printf("Given y = %.2f, then x = %.2f%n", value, x);
            }
            catch(WrongInputException e){
                System.out.println("Cannot divide by zero.");
            }

        }
    }
}
