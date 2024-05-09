//// Ernesto Crespo
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) {
        TestResource test = new TestResource();
        Thread thread[] = new Thread[10];
        for(int i=0;i<5;i++){
            thread[i] = new Thread(new DisplayJob(test), "Hilo"+i);
        }
        for(int i=5;i<10;i++){
            thread[i] = new Thread(new ReadJob(test), "Hilo"+i);
        }
        for(int i=0;i<10;i++){
            thread[i].start();
        }
    }
}

class DisplayJob implements Runnable {
    private TestResource test;
    DisplayJob(TestResource tr){
        test = tr;
    }
    @Override
    public void run(){
        System.out.println("Mostrar Job");
        test.displayRecord(new Object());
    }
}
class ReadJob implements Runnable {
    private TestResource test;
    ReadJob(TestResource tr){
        test = tr;
    }
    @Override
    public void run(){
        System.out.println("Job Leido");
        test.readRecord(new Object());
    }
}
class TestResource {
    private final Lock displayQueueLock= new ReentrantLock();
    private final Lock readQueueLock= new ReentrantLock();
    
    public void displayRecord(Object document){
        final Lock displayLock = this.displayQueueLock;
        displayLock.lock();
        try{
            Long duration = (long) (Math.random()*10000);
            System.out.println(Thread.currentThread().getName()+":Probando Resource: mostrando un Job"+"Fecha-"+ new Date());
            Thread.sleep(duration);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        finally{
            System.out.printf("%s: El documento ha sido"+ " displayed\n",Thread.currentThread().getName());
            displayLock.unlock();
        }
    }
    public void readRecord(Object document){
        final Lock readQueueLock = this.readQueueLock;
        readQueueLock.lock();
        try{
            Long duration = (long) (Math.random()*10000);
            System.out.println(Thread.currentThread().getName()+":Probando Resource: leyendo un Job durante"+(duration/1000)+"segundos::Fecha-"+ new Date());
            Thread.sleep(duration);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        finally{
            System.out.printf("%s: El documento ha"+ "sido leido\n", Thread.currentThread().getName());
            readQueueLock.unlock();
        }
    }
}