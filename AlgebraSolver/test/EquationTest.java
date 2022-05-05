import static org.junit.Assert.*;
import org.junit.Test;

public class EquationTest {
    @Test
    public void test1(){
        var eq = new Equation("x + 1 = 2");
        assertTrue(eq.validate());
        assertEquals("x = 1", eq.solveTest());
    }
    @Test
    public void test2(){
        var eq = new Equation("1 + x = 2");
        assertTrue(eq.validate());
        assertEquals("x = 1", eq.solveTest());
    }
    @Test
    public void test3(){
        var eq = new Equation("2 = x + 1");
        assertTrue(eq.validate());
        assertEquals("x = 1", eq.solveTest());
    }
    @Test
    public void test4(){
        var eq = new Equation("2 = 1 + x");
        assertTrue(eq.validate());
        assertEquals("x = 1", eq.solveTest());
    }
    @Test
    public void test5(){
        var eq = new Equation("x - 1 = 2");
        assertTrue(eq.validate());
        assertEquals("x = 3", eq.solveTest());
    }
    @Test
    public void test6(){
        var eq = new Equation("1 - x = 2");
        assertTrue(eq.validate());
        assertEquals("x = -1", eq.solveTest());
    }
    @Test
    public void test7(){
        var eq = new Equation("2 = x - 1");
        assertTrue(eq.validate());
        assertEquals("x = 3", eq.solveTest());
    }
    @Test
    public void test8(){
        var eq = new Equation("2 = 1 - x");
        assertTrue(eq.validate());
        assertEquals("x = -1", eq.solveTest());
    }
    @Test
    public void test9(){
        var eq = new Equation("x = 1 + 2");
        assertTrue(eq.validate());
        assertEquals("x = 3", eq.solveTest());
    }
    @Test
    public void test10(){
        var eq = new Equation("x = 2 - 1");
        assertTrue(eq.validate());
        assertEquals("x = 1", eq.solveTest());
    }
    @Test
    public void test11(){
        var eq = new Equation("1 + 2 = x");
        assertTrue(eq.validate());
        assertEquals("x = 3", eq.solveTest());
    }
    @Test
    public void test12(){
        var eq = new Equation("2 - 1 = x");
        assertTrue(eq.validate());
        assertEquals("x = 1", eq.solveTest());
    }
    @Test
    public void test13(){
        var eq = new Equation("num + 0 = 0");
        assertTrue(eq.validate());
        assertEquals("num = 0", eq.solveTest());
    }
    @Test
    public void test14(){
        var eq = new Equation("x = 5");
        assertTrue(eq.validate());
        assertEquals("x = 5", eq.solveTest());
    }
    @Test
    public void test15(){
        var eq = new Equation("x + 2 + 2 = 8");
        assertTrue(eq.validate());
        assertEquals("x = 4", eq.solveTest());
    }
    @Test
    public void test16(){
        var eq = new Equation("x = 3 + 3 + 3 - 4");
        assertTrue(eq.validate());
        assertEquals("x = 5", eq.solveTest());
    }
}
