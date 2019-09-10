package multithreading;

// for windows operating system
public class ProcessEx {
    public static void main(String[] args) {
        try {
            System.out.println("Creating Process");

            ProcessBuilder builder = new ProcessBuilder("notepad.exe");
            Process pro = builder.start(); // creates a native process and control

            // wait 10 seconds
            System.out.println("Waiting");
            Thread.sleep(10000);

            // kill the process
            pro.destroy();
            System.out.println("Process destroyed");

            // checking the exit value of subprocess
            System.out.println("exit value: " + pro.exitValue());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
