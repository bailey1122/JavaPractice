package eqandhash;

import java.util.HashSet;

// verification whether two instances of Employee are considered as equal
public class EqualsHashcode {
    public static void main(String[] args) {
        // stored in different memory locations default
        Employee emp1 = new Employee(1, "Jorge", "Klonton", 10000);
        Employee emp2 = new Employee(1, "Jorge", "Klonton", 10000);

        HashSet <Employee> employees = new HashSet<Employee>();
        employees.add(emp1);
        employees.add(emp2);

        System.out.println("emp1 " + emp1.getFname() + " " + emp1.getLname() + " hashcode = "
                                                                + emp1.hashCode());
        System.out.println("emp2 " + emp2.getFname() + " " + emp2.getLname() + " hashcode = "
                                                                + emp2.hashCode());
        System.out.println("HashSet size = " + employees.size()); // 'cause we overrided hashcode(). Two
        // elements are equal and stored in the same memory bucket now
        System.out.println("HashSet contains Jorge = " + employees.contains(new Employee(1, "Jorge", "Klonton", 10000)));



    }
}
