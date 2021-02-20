package mysql;

import java.io.IOException;
import java.sql.*;

// this is an example of how to connect to mysql using JDBC
public class DBExample {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        try {
            Test();
        } catch (SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
        }

    }


    public static void Test() throws SQLException, IOException, ClassNotFoundException {
        try (Connection conn = getConnection();
             Statement stat = conn.createStatement()) {
            // proper error handling in batch mode
            boolean autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);


            // create tables
            String create_emp = "CREATE TABLE employee"
                    + "(emp_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "fname VARCHAR(20) NOT NULL,"
                    + "lname VARCHAR(20) NOT NULL,"
                    + "start_date DATE NOT NULL,"
                    + "end_date DATE,"
                    + "superior_emp_id SMALLINT UNSIGNED,"
                    + "title VARCHAR(20),"
                    + "CONSTRAINT pk_emp_id PRIMARY KEY (emp_id),"
                    + "CONSTRAINT fk_superior_emp_id FOREIGN KEY (superior_emp_id)"
                    + "REFERENCES employee (emp_id)"
                    + ");";

            String create_cus = "CREATE TABLE customer"
                    + "(cust_id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "name VARCHAR(30) NOT NULL,"
                    + "city VARCHAR(20),"
                    + "postal_code VARCHAR(10),"
                    + "CONSTRAINT pk_cust_id PRIMARY KEY (cust_id)"
                    + ");";

            String create_prd = "CREATE TABLE product"
                    + "(product_type VARCHAR(20) NOT NULL,"
                    + "name VARCHAR(30) NOT NULL,"
                    + "CONSTRAINT pk_product_type PRIMARY KEY (product_type)"
                    + ");";

            String create_acc = "CREATE TABLE account"
                    + "(account_id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "product_type VARCHAR(20) NOT NULL,"
                    + "cust_id INTEGER UNSIGNED NOT NULL,"
                    + "open_date DATE NOT NULL,"
                    + "close_date DATE,"
                    + "last_activity_date DATE,"
                    + "status ENUM('ACTIVE', 'CLOSED', 'FROZEN') NOT NULL,"
                    + "open_emp_id SMALLINT UNSIGNED,"
                    + "avail_balance FLOAT(10, 2),"
                    + "pending_balance FLOAT(10, 2),"
                    + "CONSTRAINT pk_account_id PRIMARY KEY (account_id),"
                    + "CONSTRAINT fk_product_type FOREIGN KEY (product_type)"
                    + "REFERENCES product (product_type),"
                    + "CONSTRAINT fk_cust_id FOREIGN KEY (cust_id)"
                    + "REFERENCES customer (cust_id),"
                    + "CONSTRAINT fk_open_emp_id FOREIGN KEY (open_emp_id)"
                    + "REFERENCES employee (emp_id)"
                    + ");";

            stat.addBatch(create_emp); // improve the performance by using a batch instead
            stat.addBatch(create_cus); // of calling executeUpdate()
            stat.addBatch(create_prd);
            stat.addBatch(create_acc);


            String insert_emp = "INSERT INTO employee (emp_id, fname, lname,"
                    + "start_date, title)"
                    + "VALUES (NULL, 'Michael', 'Brook', '2000-01-01',"
                    + "'President')";

            String insert_emp2 = "INSERT INTO employee (emp_id, fname, lname,"
                    + "start_date, superior_emp_id, title)"
                    + "VALUES (NULL, 'John', 'Patrick', '2000-12-01',"
                    + "1, 'Head Teller')";

            String insert_emp3 = "INSERT INTO employee (emp_id, fname, lname,"
                    + "start_date, superior_emp_id, title)"
                    + "VALUES (NULL, 'Carlos', 'Veneris', '2002-02-15',"
                    + "2, 'Teller')";

            String insert_cus = "INSERT INTO customer (cust_id, name,"
                    + "city, postal_code)"
                    + "VALUES (NULL, 'John Blake', 'Dubai', '42246')";

            String insert_cus2 = "INSERT INTO customer (cust_id, name,"
                    + "city, postal_code)"
                    + "VALUES (NULL, 'Bert Harrison', 'Los Angeles', '72543')";

            String insert_cus3 = "INSERT INTO customer (cust_id, name,"
                    + "city, postal_code)"
                    + "VALUES (NULL, 'Edison Markos', 'Mexico', '92418')";

            String insert_prd = "INSERT INTO product (product_type, name)"
                    + "VALUES ('LOAN', 'Loans and other')";

            String insert_prd2 = "INSERT INTO product (product_type, name)"
                    + "VALUES ('ACCOUNTS', 'Customer Accounts')";

            String insert_prd3 = "INSERT INTO product (product_type, name)"
                    + "VALUES ('INSURANCE', 'Insurance Offerings')";

            String insert_acc = "INSERT INTO account (account_id,"
                    + "product_type, cust_id, open_date, last_activity_date,"
                    + "status, open_emp_id, avail_balance, pending_balance)"
                    + "VALUES (NULL, 'INSURANCE', 1, '2000-02-01', now(),"
                    + "'ACTIVE', 3, 1700.00, 1500.00)";

            String insert_acc2 = "INSERT INTO account (account_id,"
                    + "product_type, cust_id, open_date, last_activity_date,"
                    + "status, open_emp_id, avail_balance, pending_balance)"
                    + "VALUES (NULL, 'LOAN', 3, '2000-02-05', now(),"
                    + "'ACTIVE', 2, 6780.00, 6000.00)";

            String insert_acc3 = "INSERT INTO account (account_id,"
                    + "product_type, cust_id, open_date, last_activity_date,"
                    + "status, open_emp_id, avail_balance, pending_balance)"
                    + "VALUES (NULL, 'ACCOUNTS', 2, '2000-03-14', '2000-03-19',"
                    + "'ACTIVE', 2, 4200.00, 4200.00)";


            stat.addBatch(insert_emp);
            stat.addBatch(insert_emp2);
            stat.addBatch(insert_emp3);
            stat.addBatch(insert_cus);
            stat.addBatch(insert_cus2);
            stat.addBatch(insert_cus3);
            stat.addBatch(insert_prd);
            stat.addBatch(insert_prd2);
            stat.addBatch(insert_prd3);
            stat.addBatch(insert_acc);
            stat.addBatch(insert_acc2);
            stat.addBatch(insert_acc3);

//            int[] counts = stat.executeBatch(); // enforce all batches

            try {
                stat.executeBatch(); // enforce all batches
            } catch (SQLException e) {
                for (Throwable t : e)
                    t.printStackTrace();
            }

            conn.commit(); // if everything is going well
            conn.setAutoCommit(autoCommit);


            String sel_statistics = "SELECT a.account_id account_id, a.product_type pt,"
                    + "a.status status, a.avail_balance av_bal, a.pending_balance pg_bal,"
                    + "c.city c_city, a.open_date open_date, e.title title "
                    + "FROM account a INNER JOIN customer c "
                    + "ON a.cust_id = c.cust_id "
                    + "INNER JOIN employee e "
                    + "ON a.open_emp_id = e.emp_id;";

            try {
                ResultSet result = stat.executeQuery(sel_statistics);
                while (result.next()) { // print output
                    System.out.println(result.getString(1));
                    System.out.println(result.getString(2));
                    System.out.println(result.getString(3));
                    System.out.println(result.getString(4));
                    System.out.println(result.getString(5));
                    System.out.println(result.getString(6));
                    System.out.println(result.getString(7));
                    System.out.println(result.getString(8));
                    System.out.println("");
                }
            } catch (SQLException e) {
                for (Throwable t : e)
                    e.printStackTrace();
            }

            try {
                stat.executeUpdate("DROP TABLE account"); // drop all tables
                stat.executeUpdate("DROP TABLE product");
                stat.executeUpdate("DROP TABLE customer");
                stat.executeUpdate("DROP TABLE employee");
            } catch (SQLException e) {
                for (Throwable t : e)
                    t.printStackTrace();
            }
        }
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/example";
        String username = "root";
        String password = "1234";
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url, username, password);
    }
}
