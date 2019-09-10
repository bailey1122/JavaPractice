package multithreading;

import java.io.OutputStream;

// gets the output stream of the subprocess
public class ProcessStreO {
    public static void main(String[] args) {
        try {
            // create a new process
            System.out.println("Creating Process");
            Process p = Runtime.getRuntime().exec("notepad.exe");

            // get the output stream
            OutputStream out = p.getOutputStream();

            // close the output stream
            System.out.println("Closing the output stream");
            out.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
