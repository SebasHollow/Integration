/**
 * Created by Sebas.Hollow on 8/12/2015.
 */
public class Simpson {
    public final static int p = 2;
    private final Main.Function f;
    private double a;
    private double b;

    public Simpson (Main.Function f, double a, double b){
        this.f = f;
        this.a = a;
        this.b = b;
    }

    public double solve(int n){
        final double h = (b - a) / n;
        double sum = f.calc(a) + f.calc(b);

        for (int i = 1; i < n; i += 2) {
            final double x = a + (i * h);
            sum += 2 * f.calc(x);
        }

        for (int i = 2; i < n; i += 2){
            final double x = a + (i * h);
            sum += 4 * f.calc(x);
        }
        return (sum * (b - a)) / (3*n);
    }
}
