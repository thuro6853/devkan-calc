package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class CalculatorTest {

    @Parameters
    public static Iterable<Object[]> params() {
        return Arrays.asList(new Object[][]{
                {1, 1, "2"}
        });
    }

    @Parameter(0) public int a;
    @Parameter(1) public int b;
    @Parameter(2) public String expected;

    @Test
    public void testAdd() {
        Calculator sut = new Calculator();
        String actual = sut.add(a, b);
        assertThat(actual, is(expected));
    }
}
