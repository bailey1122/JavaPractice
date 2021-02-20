package eqandhash;

// this is an example of why we need to override equals() and hashcode()
public class Employee {
    private int id;
    private String fname;
    private String lname;
    private int salary;

    public Employee(int id, String fname, String lname, int salary) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) { // instance're equal if they're stored in the same
        if (this == o) return true; // memory address or they've got the same values
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != employee.id) return false;
        if (salary != employee.salary) return false;
        if (fname != null ? !fname.equals(employee.fname) : employee.fname != null) return false;
        return lname != null ? lname.equals(employee.lname) : employee.lname == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fname != null ? fname.hashCode() : 0);
        result = 31 * result + (lname != null ? lname.hashCode() : 0);
        result = 31 * result + salary;
        return result;
    }
}
