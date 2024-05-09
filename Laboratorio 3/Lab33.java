// Ernesto Crespo

import java.io.*;
class GfG {
    public static void main(String[] args) {
        Multithread t = new Multithread();
        t.increment();
        System.out.println(t.getValue());
    }
}
class Multithread {
    private int i =0;
    public void increment(){
        i++;
    }
    public int getValue(){
        return i;
    }
}