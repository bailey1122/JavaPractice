package multithreading;

// Atomicity. Use locking to enforce mutual exclusion
public class BankAccount {
    private int balance;

    synchronized int getBalance() {
        return balance;
    }

    synchronized void setBalance(int x) throws IllegalStateException {
        balance = x;

        if (balance < 0) {
            throw new IllegalStateException("Negative Balance");
        }
    }

    synchronized void deposit(int x) { // to avoid an error
        int b = getBalance();
        setBalance(b + x);
    }

    synchronized void withdraw(int x) {
        int b = getBalance();
        setBalance(b - x);
    }
}
