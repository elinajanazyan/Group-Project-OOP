//package am.aua.equationSolver.Equations;
//
//import java.util.List;
//
//public class SLE {
//    private List<LinearEquation> equations;
//
//    public SLE(List<LinearEquation> equations) {
//        this.equations = equations;
//    }
//
//    public void addEquation(String equation) throws IllegalArgumentException{
//            LinearEquation linearEquation = new LinearEquation(equation);
//            equations.add(linearEquation);
//        }
//        public double solveForX(double y) {
//            if (equations.isEmpty()) {
//                throw new IllegalStateException("No equations added to the system.");
//            }
//            return equations.get(0).solveForX(y);
//        }
//
//        public double solveForY(double x) {
//            if (equations.isEmpty()) {
//                throw new IllegalStateException("No equations added to the system.");
//            }
//            return equations.get(0).solveForY(x);
//        }
//
//        public boolean hasSolutions(){
//        double[] x = new double[equations.size()];
//        double[] y = new double[equations.size()];
//            for (int i = 0; i < equations.size(); i++) {
//                x[i] = equations.get(i).solveForX(y[]);
//                y[i] = equations.get(i).solveForX();
//            }
//        }
//    }
