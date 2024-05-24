// Parte 1 Productores/Consumidores - Ernesot Crespo

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class ProducerConsumerExample {
    public static void main(String[] args) {
        // Capacidad del buffer
        int bufferSize = 5;

        // Buffer compartido
        Queue<Integer> buffer = new LinkedList<>();

        // Semaforos
        Semaphore mutex = new Semaphore(1); // Para acceso exclusivo al buffer
        Semaphore empty = new Semaphore(bufferSize); // Para contar espacios vacios en el buffer
        Semaphore full = new Semaphore(0); // Para contar elementos lelnos en el buffer

        // Crear y arrancar hilos productores y consumidores
        Thread producer1 = new Thread(new Producer(buffer, mutex, empty, full), "Producer-1");
        Thread producer2 = new Thread(new Producer(buffer, mutex, empty, full), "Producer-2");
        Thread consumer1 = new Thread(new Consumer(buffer, mutex, empty, full), "Consumer-1");
        Thread consumer2 = new Thread(new Consumer(buffer, mutex, empty, full), "Consumer-2");

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
    }
}

// Aqui el metodo run, el productor producirea elementos los cuales seran agregados al buffer
// Antes de agregarlo, adquiere el semaforo "empty" para asegurarse de que haya espaxio disponible
// El semaforo mutex accede al buffer y luego libera "mutex" y "full" despues de producir el elemento
class Producer implements Runnable {
    private final Queue<Integer> buffer;
    private final Semaphore mutex;
    private final Semaphore empty;
    private final Semaphore full;

    public Producer(Queue<Integer> buffer, Semaphore mutex, Semaphore empty, Semaphore full) {
        this.buffer = buffer;
        this.mutex = mutex;
        this.empty = empty;
        this.full = full;
    }

    @Override
    public void run() {
        int value = 0;
        try {
            while (true) {
                empty.acquire(); // Decrementa el contador de espacios vacios
                mutex.acquire(); // Entra a la seccion critica

                // Produce un elemento
                buffer.add(value);
                System.out.println(Thread.currentThread().getName() + " produced " + value);
                value++;

                mutex.release(); // Sale de la seccion critica
                full.release(); // Incrementa el contador de elementos llenos

                // Simular tiempo de produccion
                Thread.sleep((long) (Math.random() * 1000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

//El consumidor consumira los elementos del buffer, antes de consumirlos adquiere el semaforo "full"
//para asegurarse de que hay elementos disponibles y semaforo "mutex" para acceder al buffer. Luego libera
// "mutex" y "empty" despues de consumir el elemento
class Consumer implements Runnable {
    private final Queue<Integer> buffer;
    private final Semaphore mutex;
    private final Semaphore empty;
    private final Semaphore full;

    public Consumer(Queue<Integer> buffer, Semaphore mutex, Semaphore empty, Semaphore full) {
        this.buffer = buffer;
        this.mutex = mutex;
        this.empty = empty;
        this.full = full;
    }

    @Override
    public void run() {
        try {
            while (true) {
                full.acquire(); // Decrementa el contador de elementos llenos
                mutex.acquire(); // Entra a la seccion cirtica

                // Consume un elemento
                int value = buffer.remove();
                System.out.println(Thread.currentThread().getName() + " consumed " + value);

                mutex.release(); // Sale de la seccion critica
                empty.release(); // Incrementa el contador de espacios vacios

                // Simular tiempo de consumo
                Thread.sleep((long) (Math.random() * 1000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}