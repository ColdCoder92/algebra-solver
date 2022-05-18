import static org.junit.Assert.*;
import org.junit.Test;

import algebrafx.Equation;

public class EquationTest {
    @Test
    public void test1(){
        var eq = new Equation("x + 1 = 2");
        assertTrue(eq.validateBool());
        assertEquals("x = 1", eq.solve());
    }
    @Test
    public void test2(){
        var eq = new Equation("1 + x = 2");
        assertTrue(eq.validateBool());
        assertEquals("x = 1", eq.solve());
    }
    @Test
    public void test3(){
        var eq = new Equation("2 = x + 1");
        assertTrue(eq.validateBool());
        assertEquals("x = 1", eq.solve());
    }
    @Test
    public void test4(){
        var eq = new Equation("2 = 1 + x");
        assertTrue(eq.validateBool());
        assertEquals("x = 1", eq.solve());
    }
    @Test
    public void test5(){
        var eq = new Equation("x - 1 = 2");
        assertTrue(eq.validateBool());
        assertEquals("x = 3", eq.solve());
    }
    @Test
    public void test6(){
        var eq = new Equation("1 - x = 2");
        assertTrue(eq.validateBool());
        assertEquals("x = -1", eq.solve());
    }
    @Test
    public void test7(){
        var eq = new Equation("2 = x - 1");
        assertTrue(eq.validateBool());
        assertEquals("x = 3", eq.solve());
    }
    @Test
    public void test8(){
        var eq = new Equation("2 = 1 - x");
        assertTrue(eq.validateBool());
        assertEquals("x = -1", eq.solve());
    }
    @Test
    public void test9(){
        var eq = new Equation("x = 1 + 2");
        assertTrue(eq.validateBool());
        assertEquals("x = 3", eq.solve());
    }
    @Test
    public void test10(){
        var eq = new Equation("x = 2 - 1");
        assertTrue(eq.validateBool());
        assertEquals("x = 1", eq.solve());
    }
    @Test
    public void test11(){
        var eq = new Equation("1 + 2 = x");
        assertTrue(eq.validateBool());
        assertEquals("x = 3", eq.solve());
    }
    @Test
    public void test11A(){
        var eq = new Equation("2 + 3 = num");
        assertTrue(eq.validateBool());
        assertEquals("num = 5", eq.solve());
    }
    @Test
    public void test12(){
        var eq = new Equation("2 - 1 = x");
        assertTrue(eq.validateBool());
        assertEquals("x = 1", eq.solve());
    }
    @Test
    public void test13(){
        var eq = new Equation("num + 0 = 0");
        assertTrue(eq.validateBool());
        assertEquals("num = 0", eq.solve());
    }
    @Test
    public void test14(){
        var eq = new Equation("x = 5");
        assertTrue(eq.validateBool());
        assertEquals("x = 5", eq.solve());
    }
    @Test
    public void test15(){
        var eq = new Equation("x + 2 + 2 = 8");
        assertTrue(eq.validateBool());
        assertEquals("x = 4", eq.solve());
    }
    @Test
    public void test16(){
        var eq = new Equation("x = 3 + 3 + 3 - 4");
        assertTrue(eq.validateBool());
        assertEquals("x = 5", eq.solve());
    }
    @Test
    public void test17(){
        var eq = new Equation("num + 1 - 2 = 3");
        assertTrue(eq.validateBool());
        assertEquals("num = 4", eq.solve());
    }
    @Test
    public void test18(){
        var eq = new Equation("x + 2 + 3 = 4 + 5");
        assertTrue(eq.validateBool());
        assertEquals("x = 4", eq.solve());
    }
    @Test
    public void test19(){
        var eq = new Equation("x = x");
        assertTrue(eq.validateBool());
        assertEquals("0 = 0", eq.solve());
    }
    @Test
    public void test19A(){
        var eq = new Equation("num = num");
        assertTrue(eq.validateBool());
        assertEquals("0 = 0", eq.solve());
    }
    @Test
    public void test20(){
        var eq = new Equation("2x = x");
        assertTrue(eq.validateBool());
        assertEquals("x = 0", eq.solve());
    }
    @Test
    public void test21(){
        var eq = new Equation("3num = 5num");
        assertTrue(eq.validateBool());
        assertEquals("num = 0", eq.solve());
    }
    @Test
    public void test22(){
        var eq = new Equation("x + 3.5 = 4.2");
        assertTrue(eq.validateBool());
        assertEquals("x = 0.7", eq.solve());
    }
    @Test
    public void test23(){
        var eq = new Equation("4.6 = 0.4 - num");
        assertTrue(eq.validateBool());
        assertEquals("num = -4.2", eq.solve());
    }
    @Test
    public void test24(){
        var eq = new Equation("5.4 + x = -0.1");
        assertTrue(eq.validateBool());
        assertEquals("x = -5.5", eq.solve());
    }
    @Test
    public void test25(){
        var eq = new Equation("1.8 = 2.7 - x");
        assertTrue(eq.validateBool());
        assertEquals("x = 0.9", eq.solve());
    }
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
}
