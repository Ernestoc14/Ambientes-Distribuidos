// Lab 22 Ernesto Crespo

import java.io.*;

public class GFG {
    public static void main(String args[]){
        final Test obj = new Test();
        Thread a = new Thread(){
            public void run() {obj.test_function(15);}
        };
        
        Thread b = new Thread(){
            public void run() {obj.test_function(30);}
        };
    
        a.start();
        b.start();
    }
}

class Test  {
    synchronized void test_function(int n){
            for(int i=1; i<=3; i++) {
                System.out.println(n+i);
                try{
                    Thread.sleep(500);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }