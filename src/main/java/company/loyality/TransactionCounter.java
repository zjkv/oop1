package company.loyality;

public class TransactionCounter {

    private int counter;

    public TransactionCounter(int counter) {
        this.counter = counter;
    }

    public void increment() {
        new TransactionCounter(counter++);
    }

    public int getCounter() {
        return counter;
    }
}
