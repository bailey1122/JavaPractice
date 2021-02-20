package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

// this is an example of how rmi works
public interface WorkSalary extends Remote {
    // get values
    int getSalary(String name) throws RemoteException;
    double getBonusWithSalary(String name) throws RemoteException;
}
