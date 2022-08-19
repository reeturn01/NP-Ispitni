package classes;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TaskManager {
    public void readTasks(InputStream in) {
        Scanner scanner = new Scanner(in);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
        }
    }

    public void printTasks(PrintStream out, boolean b, boolean b1) {
        //TO DO
        throw new RuntimeException();
    }
}
