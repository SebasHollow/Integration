/**
 * Created by Sebas.Hollow on 8/12/2015.
 */
public class Gauss {
    public final static int p = 2;
    private final Main.Function f;
    private double a;
    private double b;

    public Gauss (Main.Function f, double a, double b){
        this.f = f;
        this.a = a;
        this.b = b;
    }

    public double solve(int n){
        final double h = (b - a) / n;
        double sum = 0;
        for (int i = 0; i < n; i++){
            final double start = a + h * i;
            final double end = a + h * (i + 1);
            sum += calc (f, start, end);
        }
        return sum;
    }

    private double calc(Main.Function f, double a, double b){
        final double x = (a + b) / 2;
        return (b - a) * f.calc(x);
    }
}
