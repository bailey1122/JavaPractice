package skeletal;

public abstract class AbstractVending implements Invending {

    @Override
    public void start() {
        System.out.println("start vending machine");
    }
    // public abstract void chooseProduct();
    @Override
    public void stop() {

    }

    @Override
    public void process() {
        start();
        chooseProduct();
        stop();
    }
}
