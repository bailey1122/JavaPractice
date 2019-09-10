package exceptions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// exceptions are checked at compile time
public class Checked {
    public static void main(String[] args) throws IOException { // specify it or use try-catch block
        // throws FileNotFoundException
        FileReader file = new FileReader("C:\\test\\smth.txt");
        BufferedReader fileInput = new BufferedReader(file);

        // print first 3 lines of file
        for (int counter = 0; counter < 3; counter++)
            System.out.println(fileInput.readLine());

        fileInput.close();
    }
}
