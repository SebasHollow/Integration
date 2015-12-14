/**
 * Created by Sebas.Hollow on 8/12/2015.
 */

import org.jfree.data.xy.XYSeries;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

public class Main {
    public interface Function {double calc(double x);}
    final static Function funcA = (double x) -> (2 + 3*x - Math.pow(x, 2));
    final static Runge.FancyFunc funcB = (double x, double y) -> Math.cos(1.75*x + y) + 1.2*(x-y);
    final static List<Integer> intervalArray = Arrays.asList(10, 20, 40, 80);
    final static List<Double> stepArray = Arrays.asList(0.1, 0.05, 0.025, 0.0125);

    public static void main (String[] args){
        intervalArray.forEach(n -> partA(funcA, 1, 10, n));
        stepArray.forEach(h -> partB(funcB, 0, 3, h, 1));
    }

    public static void partA(Function func, double a, double b, int n){
        final String format = "Intervals ({0}):\n\tSimpson = {1}\n\tGauss = {2}";
        final String errorFormat = "Error:\tSimpson = {0}, Gauss = {1}\n";

        Gauss gauss = new Gauss(func, a, b);
        Simpson simpson = new Simpson(func, a, b);
        double sAns = simpson.solve(n);
        double gAns = gauss.solve(n);
        System.out.println(MessageFormat.format(format, n, sAns, gAns));

        double sError = Runge.getError(sAns, simpson.solve(2*n), Gauss.p);
        double gError = Runge.getError(gAns, gauss.solve(2*n), Simpson.p);
        System.out.println(MessageFormat.format(errorFormat, sError, gError));
    }

    public static void partB(Runge.FancyFunc func, double start, double end, double h, double y){
        Runge method = new Runge(func, start, end);
        //ToDo: RungeError
        display(method.calculate(y, h));
    }


    public static void display (XYSeries series){
        Graph graph = new Graph("Ayy lmao", series);
        graph.display();
    }
}
