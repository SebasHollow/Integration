/**
 * Created by Sebas.Hollow on 8/12/2015.
 */
public class Test {
    final static Main.Function slidesFuncA = (double x) -> (x * Math.pow(Math.E, 2*x));
    final static Runge.FancyFunc slidesRunge = (double x, double y) -> Math.sin(x) - y;

    public static void main(String[] args){
        Main.intervalArray.forEach(n -> Main.partA(Main.funcA, 1, 10, n));
        Main.stepArray.forEach(h -> Main.partB(Main.funcB, 0, 1, h, 1));
    }
}
