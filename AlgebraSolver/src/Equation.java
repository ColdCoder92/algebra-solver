import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class Equation {
    private String equation;
    // Class Constructor
    public Equation(String equation){
        this.equation = equation;
    }
    // Equation Getter
    public String getEquation() {
        return equation;
    }
    // Equation Setter
    public void setEquation(String equation) {
        this.equation = equation;
    }
    // This method counts the number of variables in the equation
    /* Note: If a variable is a word or a substring with length greater than 1,
       then count it as a whole */
    public int varCount(){
        int count = 0;
        Pattern pattern = Pattern.compile("(a-zA-Z)*");
        Matcher match = pattern.matcher(getEquation());
        if (match.find()){
            count = match.groupCount();
        }
        return count;
    }
    /* This method validates the string to make sure it is a string. They must
     * have the following:
     * - Only one equals sign
     * - A length of 3 or greater
     * - Either a "+" or "-" symbol for either side with a length of at least 3
     * - Only one variable
     */
    public boolean validate(){
        //Equation with length of at least 3?
        if (getEquation().length() < 3){
            return false;
        }
        // Contains an "=" sign?
        if (!(getEquation().contains("="))){
            return false;
        }
        // Only 1 variable?
        if (varCount() != 1){
            return false;
        }
        // Contains 2 sides?
        String[] eqParts = getEquation().split("=");
        if (eqParts.length != 2){
            return false;
        }
        // Contains "+" or "-" sign for either side of length of at least 3?
        if (!(eqParts[0].length() > 3 
        && (eqParts[0].contains("+") || eqParts[0].contains("-"))
        || (eqParts[1].length() > 3 
        && (eqParts[1].contains("+") || eqParts[1].contains("-"))))){
            return false;
        }

        return true;
    }
    /* This method is meant to solve the equation by the following methods:
     * 1) Isolate the variable by adding (subtracting if "+" is present) both
     * sides of the equation by a number.
     */
    public void solve(){
        String[] eqParts = getEquation().split("=");
        eqParts[0] = eqParts[0].trim();
        eqParts[1] = eqParts[1].trim();
        String solution = "";
        int eqNum;
        int lone;
        if (eqParts[0].length() > 3){ // * * * = *
            if (!(eqParts[1].charAt(0) >= 48 
            && eqParts[1].charAt(0) <= 57)){
                int result = 0;
                if (eqParts[0].contains("+")){    // num + num = var
                    result = Integer.parseInt(eqParts[0].substring(0, 1));
                    result += Integer.parseInt(eqParts[0].substring(eqParts[0].length()-1));
                }
                else if (eqParts[0].contains("-")){ // num - num = var
                    result = Integer.parseInt(eqParts[0].substring(0, 1));
                    result -= Integer.parseInt(eqParts[0].substring(eqParts[0].length()-1));
                }
                solution = eqParts[1] + " = " + result;
            }
            else {
                lone = Integer.parseInt(eqParts[1]);
                if (eqParts[0].contains("+")){
                    if (eqParts[0].charAt(0) >= 48 
                    && eqParts[0].charAt(0) <= 57){   // num + var = num
                        eqNum = Integer.parseInt(eqParts[0].substring(0, 1));
                        lone -= eqNum;
                        eqParts[0] = eqParts[0].substring(eqParts[0].indexOf("+") + 1);
                    }
                    else {  // x + num = num
                        eqNum = Integer.valueOf(eqParts[0].substring(eqParts[0].length()-1));
                        lone -= eqNum;
                        eqParts[0] = eqParts[0].substring(0, eqParts[0].indexOf("+"));
                    }
                }
                if (eqParts[0].contains("-")){
                    if (eqParts[0].charAt(0) >= 48 
                    && eqParts[0].charAt(0) <= 57){   // num - var = num
                        eqNum = Integer.parseInt(eqParts[0].substring(0, 1));
                        lone = (lone - eqNum) * -1;
                        eqParts[0] = eqParts[0].substring(eqParts[0].indexOf("-")+1);
                    }
                    else {  // var - num = num
                        eqNum = Integer.valueOf(eqParts[0].substring(eqParts[0].length()-1));
                        lone += eqNum;
                        eqParts[0] = eqParts[0].substring(0, eqParts[0].indexOf("-"));
                    }
                }
                eqParts[0] = eqParts[0].trim();
                solution += eqParts[0] + " = " + lone;
            }
        }
        else { // * = * * *
            if (!(eqParts[0].charAt(0) >= 48 
            && eqParts[0].charAt(0) <= 57)){
                int result = 0;
                if (eqParts[1].contains("+")){    // x = num + num
                    result = Integer.parseInt(eqParts[1].substring(0, 1));
                    result += Integer.parseInt(eqParts[1].substring(eqParts[1].length()-1));
                }
                else if (eqParts[1].contains("-")){ // x = num - num
                    result = Integer.parseInt(eqParts[1].substring(0, 1));
                    result -= Integer.parseInt(eqParts[1].substring(eqParts[1].length()-1));
                }
                solution = eqParts[0] + " = " + result;
            }
            else {
                lone = Integer.parseInt(eqParts[0]);
                if (eqParts[1].contains("+")){
                    if (eqParts[1].charAt(0) >= 48 
                    && eqParts[1].charAt(0) <= 57){   // num = num + var
                        eqNum = Integer.parseInt(eqParts[1].substring(0, 1));
                        lone -= eqNum;
                        eqParts[1] = eqParts[1].substring(eqParts[1].indexOf("+")+1);
                    }
                    else {  // num = var + num
                        eqNum = Integer.valueOf(eqParts[1].substring(eqParts[1].length()-1));
                        lone -= eqNum;
                        eqParts[1] = eqParts[1].substring(0, eqParts[1].indexOf("+"));
                    }
                }
                if (eqParts[1].contains("-")){
                    if (eqParts[1].charAt(0) >= 48 
                    && eqParts[1].charAt(0) <= 57){   // num = num - var
                        eqNum = Integer.parseInt(eqParts[1].substring(0, 1));
                        lone = (lone - eqNum) * -1;
                        eqParts[1] = eqParts[1].substring(eqParts[1].indexOf("-")+1);
                    }
                    else {  // num = var - num
                        eqNum = Integer.valueOf(eqParts[1].substring(eqParts[1].length()-1));
                        lone += eqNum;
                        eqParts[1] = eqParts[1].substring(0, eqParts[1].indexOf("-"));
                    }
                }
                eqParts[1] = eqParts[1].trim();
                solution += eqParts[1] + " = " + lone;
            }
        }
        System.out.println(solution);
    }
    // Test Method for the solve() method above
    public String solveTest(){
        String[] eqParts = getEquation().split("=");
        eqParts[0] = eqParts[0].trim();
        eqParts[1] = eqParts[1].trim();
        String solution = "";
        int eqNum;
        int lone;
        if (eqParts[0].length() > 3){ // * * * = *
            if (!(eqParts[1].charAt(0) >= 48 
            && eqParts[1].charAt(0) <= 57)){
                int result = 0;
                if (eqParts[0].contains("+")){    // num + num = var
                    result = Integer.parseInt(eqParts[0].substring(0, 1));
                    result += Integer.parseInt(eqParts[0].substring(eqParts[0].length()-1));
                }
                else if (eqParts[0].contains("-")){ // num - num = var
                    result = Integer.parseInt(eqParts[0].substring(0, 1));
                    result -= Integer.parseInt(eqParts[0].substring(eqParts[0].length()-1));
                }
                solution = eqParts[1] + " = " + result;
            }
            else {
                lone = Integer.parseInt(eqParts[1]);
                if (eqParts[0].contains("+")){
                    if (eqParts[0].charAt(0) >= 48 
                    && eqParts[0].charAt(0) <= 57){   // num + var = num
                        eqNum = Integer.parseInt(eqParts[0].substring(0, 1));
                        lone -= eqNum;
                        eqParts[0] = eqParts[0].substring(eqParts[0].indexOf("+") + 1);
                    }
                    else {  // x + num = num
                        eqNum = Integer.valueOf(eqParts[0].substring(eqParts[0].length()-1));
                        lone -= eqNum;
                        eqParts[0] = eqParts[0].substring(0, eqParts[0].indexOf("+"));
                    }
                    eqParts[0] = eqParts[0].trim();
                }
                else if (eqParts[0].contains("-")){
                    if (eqParts[0].charAt(0) >= 48 
                    && eqParts[0].charAt(0) <= 57){   // num - var = num
                        eqNum = Integer.parseInt(eqParts[0].substring(0, 1));
                        lone = (lone - eqNum) * -1;
                        eqParts[0] = eqParts[0].substring(eqParts[0].indexOf("-")+1);
                    }
                    else {  // var - num = num
                        eqNum = Integer.valueOf(eqParts[0].substring(eqParts[0].length()-1));
                        lone += eqNum;
                        eqParts[0] = eqParts[0].substring(0, eqParts[0].indexOf("-"));
                    }
                }
                eqParts[0] = eqParts[0].trim();
                solution += eqParts[0] + " = " + lone;
            }
        }
        else { // * = * * *
            if (!(eqParts[0].charAt(0) >= 48 
            && eqParts[0].charAt(0) <= 57)){
                int result = 0;
                if (eqParts[1].contains("+")){    // x = num + num
                    result = Integer.parseInt(eqParts[1].substring(0, 1));
                    result += Integer.parseInt(eqParts[1].substring(eqParts[1].length()-1));
                }
                else if (eqParts[1].contains("-")){ // x = num - num
                    result = Integer.parseInt(eqParts[1].substring(0, 1));
                    result -= Integer.parseInt(eqParts[1].substring(eqParts[1].length()-1));
                }
                solution = eqParts[0] + " = " + result;
            }
            else {
                lone = Integer.parseInt(eqParts[0]);
                if (eqParts[1].contains("+")){
                    if (eqParts[1].charAt(0) >= 48 
                    && eqParts[1].charAt(0) <= 57){   // num = num + var
                        eqNum = Integer.parseInt(eqParts[1].substring(0, 1));
                        lone -= eqNum;
                        eqParts[1] = eqParts[1].substring(eqParts[1].indexOf("+")+1);
                    }
                    else {  // num = var + num
                        eqNum = Integer.valueOf(eqParts[1].substring(eqParts[1].length()-1));
                        lone -= eqNum;
                        eqParts[1] = eqParts[1].substring(0, eqParts[1].indexOf("+"));
                    }
                }
                if (eqParts[1].contains("-")){
                    if (eqParts[1].charAt(0) >= 48 
                    && eqParts[1].charAt(0) <= 57){   // num = num - var
                        eqNum = Integer.parseInt(eqParts[1].substring(0, 1));
                        lone = (lone - eqNum) * -1;
                        eqParts[1] = eqParts[1].substring(eqParts[1].indexOf("-")+1);
                    }
                    else {  // num = var - num
                        eqNum = Integer.valueOf(eqParts[1].substring(eqParts[1].length()-1));
                        lone += eqNum;
                        eqParts[1] = eqParts[1].substring(0, eqParts[1].indexOf("-"));
                    }
                }
                eqParts[1] = eqParts[1].trim();
                solution += eqParts[1] + " = " + lone;
            }
        }
        return solution;
    }

    // Class String with equation
    public String toString(){
        return "Your equation is " + getEquation();
    }
}
