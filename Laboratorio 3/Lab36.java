// Ernesto Crespo
import java.io.*;
import java.util.*;

class GfG {
    public static void main(String[] args) {
        Geek gk = new Geek();
        List<String> list = new ArrayList<String>();
        gk.geekName("Texto Geek", list);
        System.out.println(gk.name);
    }
}
class Geek {
    String name = "";
    public int count = 0;
    
    public void geekName(String geek, List<String> list){
        synchronized(this){
            name = geek;
            count++;
        }
        list.add(geek);
    }
}