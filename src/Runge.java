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
        this.end = end;
    }

    public XYSeries calculate(double y, double h){
        final String title = MessageFormat.format("Runge (x = {0}, y = {1}, h = {2})", start, y, h);
        final XYSeries series = new XYSeries(title);
        series.add(start, y);
        for (double x = start+h; x - 0.001 <= end; x += h){
            y += T3(x, y, h);
            series.add(x, y);
            System.out.println(MessageFormat.format("x = {0}, y = {1}", x, y));
        }
        return series;
    }

    private double T3(double x, double y, double h){
        double k1 = h * func.calc(x, y);
        double k2 = h * func.calc(x + h/2, y + (k1 * h/2));
        double k3 = h * func.calc(x + h, y - (k1*h + k2 * 2*h));
        return (k1 + 4*k2 + k3)/6;
    }

    public static double getError (double s1, double s2, int p){
        return (Math.abs(s1 - s2) / (Math.pow(2, p) - 1));
    }
}
