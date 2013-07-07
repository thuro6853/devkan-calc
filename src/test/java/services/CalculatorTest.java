package services;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {
	
    @Test
    public void testAdd() {
        Calculator sut = new Calculator();
        assertThat(sut.add(20, 4), is("24"));
    }
    
    @Test
    public void testSubtract() {
        Calculator sut = new Calculator();
        assertThat(sut.subtract(20, 4), is("16"));
    }
    
    @Test
    public void testMultiply() {
        Calculator sut = new Calculator();
        assertThat(sut.multiply(20, 4), is("80"));
    }
    
    @Test
    public void testDivide() {
        Calculator sut = new Calculator();
        assertThat(sut.divide(20, 4), is("5"));
    }

}
