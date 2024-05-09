// Ernesto Crespo
// Ernesto Crespo
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.nio.file.Files;

public class GFG {
  public static void main(String[] args) throws IOException {
    
    File fileName = new File("C:\Users\descr\Desktop\Ernesto\Ing. Software\2024\Aplicaciones para Amb. Distribuidos\Laboratorio 5\Textfile.txt");
    
    List<String> text = Files.readAllLines(fileName.toPath());
    
    text.parallelStream().forEach(System.out::println);
  }
}