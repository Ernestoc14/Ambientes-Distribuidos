// Parte 1 Barrier - Ernesot Crespo

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BarrierExample {

    public static void main(String[] args) {
	//Definimos el Numero de Hilos
        final int NUMBER_OF_THREADS = 5;
	// Creamos el Barrier
        CyclicBarrier barrier = new CyclicBarrier(NUMBER_OF_THREADS, new Runnable() {
            @Override
            public void run() {
                System.out.println("Todos los hilos han alcanzado la barrera. Continuando con la ejecucion...");
            }
        });
	// Inicializacion de los Hilos que ejecutaran el Task o Tarea
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            new Thread(new Task(barrier)).start();
        }
    }
}

// Definicion de la Clase Task
class Task implements Runnable {
    private CyclicBarrier barrier;

    public Task(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " esta trabajando en su tarea...");
            Thread.sleep((long) (Math.random() * 1000)); // Simular trabajo
            System.out.println(Thread.currentThread().getName() + " ha llegado a la barrera.");
            barrier.await(); // Esperar a que todos los hilos lleguen a la barrera
            System.out.println(Thread.currentThread().getName() + " esta continuando despues de la barrera.");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
