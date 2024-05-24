// Parte 1 Mutex - Ernesot Crespo
import java.util.concurrent.Semaphore;

public class MutexExample {

    // Creacion de un semaforo de mutex
    private static final Semaphore mutex = new Semaphore(1);

    public static void main(String[] args) {
        // Crear e iniciar multiples hilos
        Thread thread1 = new Thread(new Worker(), "Thread-1");
        Thread thread2 = new Thread(new Worker(), "Thread-2");
        Thread thread3 = new Thread(new Worker(), "Thread-3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
    // Esta clase permitira crear hilos a partir de instancias Worker
    static class Worker implements Runnable {
        @Override
	// Cada hilo intenta adquirir el semarofo mediante "mutex.acquire()", si el semaforo esta disponible
	// el hilo entra en la seccion critica, sino espera hasta que este disponible
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " esta intentando adquirir el mutex...");
                mutex.acquire(); // Adquirir el semaforo

                System.out.println(Thread.currentThread().getName() + " ha adquirido el mutex.");
                
                // Simular una operacion critica
                Thread.sleep((long) (Math.random() * 1000));
                
                System.out.println(Thread.currentThread().getName() + " ha liberado el mutex.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Hilo interrumpido.");
            } finally {
                mutex.release(); // Liberar el semaforo
            }
        }
    }
}