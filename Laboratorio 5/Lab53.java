// Ernesto Crespo
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;
import java.nio.file.Files;

public class GFG {
  public static void main(String[] args) throws IOException {
    // create a file object
    File fileName = new File("C:\Users\descr\Desktop\Ernesto\Ing. Software\2024\Aplicaciones para Amb. Distribuidos\Laboratorio 5\Textfile.txt");
    // create a stream of lines
    Stream<String> text = Files.lines(fileName.toPath());
    // display the lines
    text.parallel().forEach(System.out::println);
    // close the stream
    text.close();
  }
}