package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class WorkSalaryImpl extends UnicastRemoteObject implements WorkSalary {
    private Map<String, Integer> salaries;

    public WorkSalaryImpl() throws RemoteException {
        salaries = new HashMap<>();
        salaries.put("Bob", 5000);
        salaries.put("David", 7000);
        salaries.put("Michael", 3000);
    }
    @Override
    public int getSalary(String name) throws RemoteException {
        int salary = salaries.get(name);
        return salary;
    }

    @Override
    public double getBonusWithSalary(String name) throws RemoteException {
        double bonus = 100.75;
        int salary = salaries.get(name);
        return bonus + salary;
    }
}
