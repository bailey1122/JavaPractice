package serialization;
import java.io.*;

//this is an example of how serialization works
class Worker implements Serializable {
    private static final long serialVersionUID = 22L; // serialVersionUID should be here
    transient int a; // we won't get this value after serialization
    // because the properties of the class which
    // tagged with modifier 'transient' cannot be serialized
    static int b;
    String name;
    int age;

    public Worker(int a, int b, String name, int age) {
        this.a = a;
        this.b = b;
        this.name = name;
        this.age = age;
    }
}

class SerializExample {
    // print info
    public static void print(Worker obj) {
        System.out.println("transient int a = " + obj.a);
        System.out.println("static int b = " + obj.b);
        System.out.println("name = " + obj.name);
        System.out.println("age = " + obj.age);
    }
    public static void main(String[] args) {
        Worker object = new Worker(10, 20, "David", 25);
        String filename = "WorkerInfo.txt"; // name of file which you want to save

        // Start of serialization
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(object);

            objectOutputStream.close();
            fileOutputStream.close();

            System.out.println("Object has been serialized\n" + "Data before serialization");
            print(object); // show value

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("");

        object = null;

        // Start of deserialization
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            object = (Worker)objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();

            System.out.println("Object has been deserialized\n" + "Data after serialization");
            print(object); // show value


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFound");
        }

    }
}
