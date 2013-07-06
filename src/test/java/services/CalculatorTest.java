package services;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class CalculatorTest {

    @RunWith(Parameterized.class)
    public static class NormalCases {
        @Parameters
        public static Iterable<Object[]> params() {
            return Arrays.asList(new Object[][]{
                    {1, 1, "2"},
                    {1, 0, "1"}
            });
        }

        @Parameter(0)
        public int a;
        @Parameter(1)
        public int b;
        @Parameter(2)
        public String expected;

        @Test
        public void testAdd() {
            Calculator sut = new Calculator();
            String actual = sut.add(a, b);
            assertThat(actual, is(expected));
        }
    }

    @RunWith(Parameterized.class)
    public static class IllegalCases {
        @Parameters
        public static Iterable<Object[]> params() {
            return Arrays.asList(new Object[][]{
                    {Integer.MAX_VALUE, 1},
                    {1, Integer.MAX_VALUE},
                    {Integer.MAX_VALUE - 1000, 1001},
                    {Integer.MIN_VALUE, Integer.MIN_VALUE},
            });
        }

        @Parameter(0)
        public int a;
        @Parameter(1)
        public int b;

        @Test(expected = IllegalArgumentException.class)
        public void testAdd() {
            Calculator sut = new Calculator();
            sut.add(a, b);
        }
    }
}
