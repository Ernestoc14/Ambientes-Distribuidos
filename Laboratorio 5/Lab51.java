// Ernesto Crespo

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.RecursiveAction;
class Main {
    public static void main (String[] args) {
        final int SIZE = 10;
        ForkJoinPool pool = new ForkJoinPool () ;
        double na[] = new double [SIZE];
        System.out.println ("Valores aleatorios inicializado:");
    for (int i = 0; i < na.length; i++) {
        na [i] = (double) i + Math. random () ;
        System.out.format("8.4f ", na[i]);
    }
    System.out.println ();
    CustomRecursiveAction task = new CustomRecursiveAction(na, 0, na.length);
    pool.invoke(task) ;
    System.out.println ("Valores cambiados: ");
    for (int i = 0; i < 10; i++)
    System.out.format("%.4f ", na[i]);
    System.out.println();
    }
}

class CustomRecursiveAction extends
RecursiveAction {
    final int THRESHOLD = 2;
    double [] numbers;
    int indexStart, indexLast;
    CustomRecursiveAction (double [] n, int s, int l) {
        numbers = n;
        indexStart = s;
        indexLast = 1;
    }
    @Override
    protected void compute () {
        if ((indexLast - indexStart) > THRESHOLD)
            for (int i = indexStart; i < indexLast; i++)
                numbers [i] = numbers [i] + Math.random () ;
        else
        invokeAll (new CustomRecursiveAction (numbers, indexStart, (indexStart - indexLast) / 2), new CustomRecursiveAction (numbers, (indexStart - indexLast) / 2,
                indexLast)) ;
    }
}