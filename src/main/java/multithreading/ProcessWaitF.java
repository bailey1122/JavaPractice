package multithreading;

// returns the exit code returned by the process.
// It doesn't return until the process on which it is called
// terminates
public class ProcessWaitF {
    public static void main(String[] args) {
        try {
            System.out.println("Creating Process");
            Process p = Runtime.getRuntime().exec("notepad.exe");

            // cause this process to stop
            // until process p is terminated
            p.waitFor();

            // when you manually close notepad.exe
            // program will continue here
            System.out.println("Waiting over");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
