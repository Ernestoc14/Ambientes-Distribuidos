// Ernesto Crespo

class SynchroTest {
    public static void main(String[] args) {
        PrintTest p = new PrintTest();
        
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();
        
        t1.start();
        t2.start();
    }
}
class PrintTest extends Thread {
    synchronized public static void printThread(int n){
            for(int i=1; i<=10; i++) {
                System.out.println("Thread"+n+"esta trabajando...");
                try{
                    Thread.sleep(600);
                } 
                catch(Exception ex) {
                    System.out.println(ex.toString());
                }
            }
        System.out.println("---------------");
        try{
            Thread.sleep(1000);
        }
        catch(Exception ex){
            System.out.println(ex.toString());
        }
    }
}
class Thread1 extends Thread{
    public void run() { PrintTest.printThread(1); }
}
class Thread2 extends Thread{
    public void run() { PrintTest.printThread(2); }
}