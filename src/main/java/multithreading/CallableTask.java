package multithreading;

import java.security.InvalidParameterException;
import java.util.concurrent.Callable;

public class CallableTask implements Callable<Integer> {
    @Override
    public Integer call() throws InvalidParameterException {
        int v = -1;

        if (v < 0) throw new InvalidParameterException("Number has to be positive");

        return v + 2;
    }
}
