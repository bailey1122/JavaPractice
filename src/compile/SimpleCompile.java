package compile;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;

// if you need to compile some code, you can use it...
public class SimpleCompile {
    public static void main(String[] args) throws IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler(); //invoke compiler Java
        int result = compiler.run(null, null, null,"C:/Users/User/Desktop/HelloWorld.java"); // file path to compile
        System.out.println(result);
    }
}
