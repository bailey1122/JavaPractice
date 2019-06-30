package rmi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;

// before starting you, need to set the parameters
//'-Djava.rmi.server.codebase=http://localhost:1099/work_salary'
// in VM options or program arguments of this class and run
// cmd in 'project->out->productions->yourProject' in your Java project
// after this write 'rmiregistry' in cmd and run 'WorkSalaryServer'
// and run 'WorkSalaryClient'
public class WorkSalaryServer {
    public static void main(String[] args) throws RemoteException, NamingException {
        System.setProperty("java.security.policy", "src/rmi/server.policy"); // permissions file path
        System.setSecurityManager(new SecurityManager());

        System.out.println("Constructing server implementation...");
        WorkSalaryImpl workSalaryImpl = new WorkSalaryImpl();

        System.out.println("Binding serer implementation to registry...");
        Context namingContext = new InitialContext();
        namingContext.bind("rmi://localhost:1099/work_salary", workSalaryImpl); // 127.0.0.1 by default
        // also you can use 'rmi://127.0.0.1:1099/work_salary'
        System.out.println("Waiting or invocations from clients...");
    }
}
