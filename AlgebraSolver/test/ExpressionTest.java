import static org.junit.Assert.*;
import org.junit.Test;

import algebrafx.Expression;


public class ExpressionTest {
    @Test
    public void test1(){
        var ex = new Expression("2 + 2");
        assertEquals("4", ex.solve());
    }
    @Test
    public void test2(){
        var ex = new Expression("2.5 - 2.5");
        assertEquals("0", ex.solve());
    }
}
