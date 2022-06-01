package algebrafx;
// Created to share methods between the Equation and Expression classes
public class Algebra {
    /* If a decimal has a 0 to the right of the decimal place, the number 
    should be rounded by the ones digit */
    protected int roundOnes(double num){
        String numText = Double.toString(num);
        numText = numText.substring(0, numText.indexOf("."));
        return Integer.valueOf(numText);
    }
    // This method returns the double value rounded to the hundredths place
    protected double roundHun(double num){
        /* Compares hundredths places by getting rid of the ones and tenths 
        digits */
        //System.out.println(num*10);
        //System.out.println(Math.ceil(num * 10));
        //System.out.println((num * 10) - Math.ceil(num*10));
        if (((num * 10) - Math.floor(num*10)) >= 0.5 
        && Math.floor(num*10) <= num * 10){
            num += 0.05;
        }
        else if (num < 0 && (num * 10) - Math.ceil(num * 10) <= -0.5){
            num -= 0.05;
        }
        // ex. 5.55 * 10 = 55.5 - 55 = 0.5 >= 0.5 = true
        // ex. -5.55 * 10 = -55.5 - (-55) = -0.5 <= -0.5 = true
        String numText = "" + num;
        if (numText.length() > 3){
            numText = numText.substring(0, 4);
        }
        return Double.valueOf(numText);
    }
    // Converts fractions into decimals 
    protected String fracToDecs(String eq){
        for (int i = 0; i < eq.length(); i++){
            if (eq.charAt(i) == '/'){
                eq = 
                eq.replace(
                    eq.substring(eq.indexOf("/", i) - 1, 
                        eq.indexOf("/", i) + 2),
                    Double.parseDouble(
                        eq.substring(
                            eq.indexOf("/", i) - 1, eq.indexOf("/", i)
                        )
                    ) 
                    / Double.parseDouble(
                        eq.substring(
                            eq.indexOf("/", i) + 1, eq.indexOf("/", i) + 2
                        )
                    ) + ""
                );
            }
        }
        return eq;
    }
    /* Determines whether a character in either side of an expression is a 
       number */
    protected boolean isNum(String expr, int index){
        if ((expr.charAt(index) >= 48 && expr.charAt(index) <= 57)){
            return true;
        }
        return false;
    }
}
