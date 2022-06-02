import static org.junit.Assert.*;
import org.junit.Test;

import algebrafx.Equation;

public class EquationTest2 {
    @Test
    public void test26(){
        var eq = new Equation("2x + 1 = 3");
        assertTrue(eq.validateBool());
        assertEquals("x = 1", eq.solve());
    }
    @Test
    public void test27(){
        var eq = new Equation("4.4 = 2x - 2.2");
        assertTrue(eq.validateBool());
        assertEquals("x = 3.3", eq.solve());
    }
    @Test
    public void test28(){
        var eq = new Equation("5.4 = -0.6 + 0.4num");
        assertTrue(eq.validateBool());
        assertEquals("num = 15", eq.solve());
    }
    @Test
    public void test29(){
        var eq = new Equation("3.4x + 2.5 = 7.5 - 1.6x");
        assertTrue(eq.validateBool());
        assertEquals("x = 1", eq.solve());
    }
    @Test
    public void test30(){
        var eq = new Equation("2.5x + 2.5 + 2.5 = 2.4x + 2.4 + 2.4");
        assertTrue(eq.validateBool());
        assertEquals("x = -2", eq.solve());
    }
    @Test
    public void test31(){
        var eq = new Equation("2.5x + 2.5x - 2.5 = 2.5x + 2.4 + 0.1");
        assertTrue(eq.validateBool());
        assertEquals("x = 2", eq.solve());
    }
    @Test
    public void test32(){
        var eq = new Equation("1/2 + x = 3/2");
        assertTrue(eq.validateBool());
        assertEquals("x = 1", eq.solve());
    }
}
