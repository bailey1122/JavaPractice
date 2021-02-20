package reflection;

import java.lang.reflect.*;

// using reflection
public class ExReflect {
    public static void main(String[] args) {
        try {
            Methodex methodex = new Methodex();
            // obtain a java.lang.Class object for the class
            Class c = methodex.getClass(); // that you wanna manipulate
//            Class c = Class.forName();
//            Class c2 = int.class; // other second way to get it
//            Class c3 = Integer.TYPE; // other third way to get it
            Method methlist[] = c.getDeclaredMethods();
            for (int i = 0; i < methlist.length; i++) {
                Method m = methlist[i];
                System.out.println("Method: ");
                System.out.println("name = " + m.getName());
                System.out.println("declaring class = "
                        + m.getDeclaringClass());
                Class pvec[] = m.getParameterTypes();
                for (int j = 0; j < pvec.length; j++)
                    System.out.println("param #" + j + " "
                    + pvec[j]);
                Class evec[] = m.getExceptionTypes();
                for (int j = 0; j < evec.length; j++)
                    System.out.println("exc #" + j
                    + " " + evec[j]);
                System.out.println("return type = "
                        + m.getReturnType());
                System.out.println(" ------ ");
            }
        } catch (Throwable e) {
            System.err.println(e);
        }


        try {
            Constructorex constructorex = new Constructorex();
            Class c = constructorex.getClass();
            Constructor constlist[] = c.getDeclaredConstructors();
            for (int i = 0; i < constlist.length; i ++) {
                Constructor ct = constlist[i];
                System.out.println("Constructor: ");
                System.out.println("name = " + ct.getName());
                System.out.println("declaring class = "
                        + ct.getDeclaringClass());
                Class pvec[] = ct.getParameterTypes();
                for (int j = 0; j < pvec.length; j++)
                    System.out.println("param #"
                    + j + " " + pvec[j]);
                Class evec[] = ct.getExceptionTypes();
                for (int j = 0; j < evec.length; j++)
                    System.out.println("exc #" + j + " "
                    + evec[j]);
                System.out.println(" ------ ");
            }
        } catch (Throwable e) {
            System.err.println(e);
        }


        try {
            Fieldex fieldex = new Fieldex();
            Class c = fieldex.getClass();
            Field fieldlist[] = c.getDeclaredFields();
            for (int i = 0; i < fieldlist.length; i++) {
                Field fld = fieldlist[i];
                System.out.println("Field: ");
                System.out.println("name " + fld.getName());
                System.out.println("declaring class = "
                        + fld.getDeclaringClass());
                System.out.println("type = " + fld.getType());
                int mod = fld.getModifiers();
                System.out.println("modifiers = "
                        + Modifier.toString(mod));
                System.out.println(" ------ ");
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
}


class Methodex {  // get all info of methods
    private int a(Object p,
                  int x) throws NullPointerException {
        if (p == null)
            throw new NullPointerException();
        return x;
    }

    private String b(String k) {
        String m = "Hi, ";
        return m + k;
    }
}


class Constructorex { // get all info of constructors
    public Constructorex() {
    }

    protected Constructorex(String a, int b) {
    }
}


class Fieldex { // get all info of fields
    private double d;
    int k;
    public static final int i = 11;
    String s = "smth";
}


