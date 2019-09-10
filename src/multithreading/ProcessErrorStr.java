package multithreading;

import java.io.InputStream;

// returns an input stream that reads input from the process err
// output stream
public class ProcessErrorStr {
    public static void main(String[] args) {
        try {
            // create a new process
            System.out.println("Creating Process");

            Process p = Runtime.getRuntime().exec("notepad.exe");

            // get the error stream
            InputStream error = p.getErrorStream();

            for (int i = 0; i < error.available(); i++) {
                System.out.println("" + error.read());
            }

            // wait for 10 seconds and then destroy the process
            Thread.sleep(10000);
            p.destroy();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
