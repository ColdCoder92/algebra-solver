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
}
