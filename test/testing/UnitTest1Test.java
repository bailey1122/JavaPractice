package testing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest1Test {

    @Test
    public void test() {
        UnitTest1 test1 = new UnitTest1();
        int result = test1.addNumber(2, 3);
        assertEquals(5, result);
    }

}