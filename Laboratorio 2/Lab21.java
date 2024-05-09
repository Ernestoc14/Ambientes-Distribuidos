import java.io.*;
import java.util.*;

class SyncDemo {
    public static void main(String args[]){
        Sender send = new Sender();
        ThreadedSend S1 = new ThreadedSend("Hola", send);
        ThreadedSend S2 = new ThreadedSend("Adios", send);
    
        S1.start();
        S2.start();
        
        try{
            S1.join();
            S2.join();
        }
        catch (Exception e) {
            System.out.println("Interrumpido");
        }
    }
}

class ThreadedSend extends Thread {
    private String msg;
    Sender sender;
    
    ThreadedSend(String m, Sender obj) {
        msg = m;
        sender = obj;
    }
    public void run(){
        synchronized(sender){
            sender.send(msg);
        }
    }
}
class Sender{
    public void send(String msg){
        System.out.println("Enviando\t"+msg);
        try{
            Thread.sleep(1000);
        }
        catch( Exception e){
            System.out.println("Hilo interrumpido");
        }
        System.out.println("\n"+msg+"Enviado");
    }
}