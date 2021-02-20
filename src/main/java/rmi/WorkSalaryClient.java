package rmi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Enumeration;

// run after 'WorkSalaryClient'
public class WorkSalaryClient {
    public static void main(String[] args) throws NamingException, RemoteException {
        Context namingContext = new InitialContext();

        Enumeration<NameClassPair> e = namingContext.list("rmi://127.0.0.1/"); // also you can use 'rmi://localhost/'
        while (e.hasMoreElements()) {
            System.out.println(e.nextElement().getName()); // get all registered objects
        }

        String url = "rmi://localhost/work_salary";
        WorkSalary workSalary = (WorkSalary) namingContext.lookup(url);

        String bob = "Bob";
        String david = "David";
        int salaryB = workSalary.getSalary(bob);
        int salaryD = workSalary.getSalary(david);
        double bonusSal = workSalary.getBonusWithSalary(bob);
        System.out.println(bob + ": " + salaryB);
        System.out.println(bob + "'s salary with bonus: " + bonusSal);
        System.out.println(david + ": " + salaryD);
    }
}
