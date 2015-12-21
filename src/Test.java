import org.jfree.data.xy.XYSeries;

/**
 * Created by Sebas.Hollow on 8/12/2015.
 */
public class Test {
    final static Main.Function slidesFuncA = (double x) -> (x * Math.pow(Math.E, 2*x));
    final static Runge.FancyFunc slidesRunge = (double x, double y) -> Math.sin(x) - y;

    public static void main(String[] args){
        //Main.intervalArray.forEach(n -> Main.partA(Main.funcA, 1, 10, n));
        //Main.stepArray.forEach(h -> Main.partB(Main.funcB, 0, 1, h, 1));
        Main.partB(slidesRunge, 0, Math.PI, 0.05*Math.PI, 1);
        //testGraphs();
    }

    public static void testGraphs(){
        final XYSeries series = new XYSeries("Random Data"){{
            add(1.0, 500.2);
            add(5.0, 694.1);
            add(4.0, 100.0);
            add(12.5, 734.4);
            add(17.3, 453.2);
            add(21.2, 500.2);
            add(21.9, null);
            add(25.6, 734.4);
            add(30.0, 453.2);
            add(1.0, 500.2);}
        };

        Graph graph = new Graph("Ayy lmao", series);
        graph.display();
    }
}
