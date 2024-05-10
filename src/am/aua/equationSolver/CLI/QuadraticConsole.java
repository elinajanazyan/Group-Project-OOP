package am.aua.equationSolver.CLI;
import am.aua.equationSolver.Equations.QuadraticEquation;
import java.util.Scanner;

public class QuadraticConsole {

    public static void Quadratic() {
        Scanner scanner = new Scanner(System.in);

        boolean validInput = false;
        while (!validInput) {
            try {
                String input = scanner.nextLine();
                QuadraticEquation equation = new QuadraticEquation(input);
                equation.solve();
                validInput = true;
            } catch (Exception e) {
                System.out.println("Please enter the equation in the correct format: ax^2 + bx + c = k");
            }
        }
    }
}
