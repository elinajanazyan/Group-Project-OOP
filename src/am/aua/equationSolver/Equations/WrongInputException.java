package am.aua.equationSolver.Equations;

public class WrongInputException extends Exception{
    public WrongInputException(){
        this("Wrong format of input");
    }
    public WrongInputException(String message){
        super(message);
    }
}
