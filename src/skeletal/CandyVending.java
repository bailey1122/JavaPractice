package skeletal;

public class CandyVending implements Invending {

    private class AbstractVendingDelegator extends AbstractVending {

        @Override
        public void chooseProduct() {
            System.out.println("Produce different candies");
            System.out.println("Choose a type");
            System.out.println("pay for candy");
            System.out.println("collect candy");
        }
    }

    AbstractVendingDelegator delegator = new AbstractVendingDelegator();

    @Override
    public void start() {
        delegator.start();
    }

    @Override
    public void chooseProduct() {
        delegator.chooseProduct();
    }

    @Override
    public void stop() {
        delegator.stop();
    }

    @Override
    public void process() {
        delegator.process();
    }
}
