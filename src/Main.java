/**
 * Created by Sebas.Hollow on 8/12/2015.
 */

import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public interface Function {double calc(double x);}
    final static Function funcA = (double x) -> (2 + 3*x - Math.pow(x, 2));
    final static Runge.FancyFunc funcB = (double x, double y) -> Math.cos(1.75*x + y) + 1.2*(x-y);
    final static List<Integer> intervalArray = Arrays.asList(10, 20, 40, 80);
    final static List<Double> stepArray = Arrays.asList(0.1, 0.05, 0.025, 0.0125);
    public static List<XYDataItem> lastPoints = new ArrayList<>();

    public static void main (String[] args){
        //intervalArray.forEach(n -> partA(funcA, 1, 10, n));
        stepArray.forEach(h -> partB(funcB, 0, 3, h, 1));
    }

    public static void partA(Function func, double a, double b, int n){
        final String format = "\nIntervals ({0}):\n\tSimpson = {1}\n\tGauss = {2}";
        final String errorFormat = "Error:\tSimpson = {0}, Gauss = {1}";

        Simpson simpson = new Simpson(func, a, b);
        double sAns = simpson.solve(n);
        double sError = Runge.getError(sAns, simpson.solve(2*n), Simpson.p);

        Gauss gauss = new Gauss(func, a, b);
        double gAns = gauss.solve(n);
        double gError = Runge.getError(gAns, gauss.solve(2*n), Gauss.p);

        System.out.println(MessageFormat.format(format, n, sAns, gAns));
        System.out.println(MessageFormat.format(errorFormat, sError, gError));
        updateErrorPartA(sError, gError);
    }

    public static void partB(Runge.FancyFunc func, double start, double end, double h, double y){

        Runge method = new Runge(func, start, end);
        XYSeries data = method.calculate(y, h);
        XYDataItem point = getLastPoint(data);

        compareErrors(point);
        display(data);
    }

    public static void compareErrors(XYDataItem point){
        System.out.println(MessageFormat.format("\n({0}, {1})", point.getXValue(), point.getYValue()));
        final String errorFormat = "Error:\tRunge = {0}";

        int size = lastPoints.size();
        if (size > 0){
            double prevY = lastPoints.get(size-1).getYValue();
            double err = Runge.getError(point.getYValue(), prevY, Runge.p);
            System.out.println(MessageFormat.format(errorFormat, err));
            if (size > 1)
                System.out.println(MessageFormat.format("Runge and Kutta: {0} time(s)", kuttaError / err));
            kuttaError = err;
        }
        lastPoints.add(point);
    }

    public static XYDataItem getLastPoint (XYSeries series){
        List list = series.getItems();
        return (XYDataItem) list.get(list.size() - 1);
    }

    //ToDo: Clean this spaghetti
    static double simpsonErrorDiff = 0;
    static double gaussErrorDiff = 0;
    static double kuttaError = 0;
    static boolean firstDiff = true;

    public static void updateErrorPartA(double sError, double gError){
        if (!firstDiff)
            System.out.println(MessageFormat.format("Simpson: {0} times, \tGauss {1} time(s)", simpsonErrorDiff / sError, gaussErrorDiff / gError));

        simpsonErrorDiff = sError;
        gaussErrorDiff = gError;
        firstDiff = false;
    }

    public static void display (XYSeries series){
        Graph graph = new Graph("Ayy lmao", series);
        graph.display();
    }
}
