/**
 * Created by Sebas.Hollow on 8/12/2015.
 */

import org.jfree.data.xy.XYSeries;

import java.text.MessageFormat;

public class Runge {
    public static int p = 3;

    public interface FancyFunc {double calc(double x, double y);}
    private final double start;
    private final double end;
    private final FancyFunc func;

    public Runge (FancyFunc func, double start, double end){
        this.func = func;
        this.start = start;
        //Add a small number as a workaround to avoid skipping final loop in "calculate(double, double)"
        this.end = end + 0.0001;
    }

    public XYSeries calculate(double y, double h){
        final String title = MessageFormat.format("Runge (x = {0}, y = {1}, h = {2})", start, y, h);
        final XYSeries series = new XYSeries(title);
        for (double x = start; x < end; x += h){
            series.add(x, y);
            //System.out.println(MessageFormat.format("x = {0}, y = {1}", x, y));
            y += T3(x, y, h);
           }
        return series;
    }

    private double T3(double x, double y, double h){
        double k1 = func.calc(x, y);
        double k2 = func.calc(x + h/2, y + (k1 * h/2));
        double k3 = func.calc(x + h, y + h*(-k1 + 2*k2));
            return (h/6)*(k1 + 4*k2 + k3);
    }

    public static double getError (double s1, double s2, int p){
        return (Math.abs(s1 - s2) / (Math.pow(2, p) - 1));
    }
}
