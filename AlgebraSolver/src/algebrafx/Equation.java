package algebrafx;
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
    // Equation Variable Getter
    public String getVar(){
        String variable = "";
        for (int i = 0; i < getEquation().length(); i++){
            if ((getEquation().charAt(i) >= 65 && getEquation().charAt(i) <= 90)
            || (getEquation().charAt(i) >= 97 && getEquation().charAt(i) <= 122)){
                variable += getEquation().charAt(i);
            }
        }
        //System.out.println(variable);
        return variable;
    }
    // Equation Variable Getter with Expression String Parameter
    public String getVar(String expr){
        String variable = "";
        for (int i = 0; i < expr.length(); i++){
            if ((expr.charAt(i) >= 65 && expr.charAt(i) <= 90)
            || (expr.charAt(i) >= 97 && expr.charAt(i) <= 122)){
                variable += expr.charAt(i);
            }
        }
        //System.out.println(variable);
        return variable;
    }
    /* This method determines whether both sides of the equation contain the 
       variable or not */
    public boolean bothHasVar(String expr1, String expr2){
        if (!(expr1.contains(getVar()))){
            //System.out.println("false 1");
            return false;
        }
        if (!(expr2.contains(getVar()))){
            //System.out.println("false 2");
            return false;
        }
        return true;
    }
    // This method counts all the numbers in one side of an equation
    // To be used only in the Equation class methods
    private int numCount(String expr){
        int count = 0;
        String num = "";
        for (int i = 0; i < expr.length(); i++){
            if (expr.charAt(i) >= 48 && expr.charAt(i) <= 57){
                num += "" + Integer.parseInt(expr.substring(i, i + 1));
            }
            else if(expr.charAt(i) == '.'){
                num += ".";
            }
            else {
                System.out.print(num);
                if (num.length() != 0){
                    // Should count only if the string has a number
                    num = "";
                    count++;    
                }
            }
        }
        if (num.length() >= 1){
            count++;
        }
        System.out.println(count);
        return count;
    }
    // This method counts the number of variables in the equation
    // To be used only in the Equation class methods
    /* Note: If a variable is a word or a substring with length greater than 1,
       then count it as a whole */
    private int varCount(String expr){
        int count = 0;
        Pattern pattern = Pattern.compile("(a-zA-Z)*");
        Matcher match = pattern.matcher(expr);
        if (match.find()){
            count = match.groupCount();
        }
        return count;
    }
    /* Ensures that the string is an equation by returning the reason
       for invalid equations. */
    public String validate(){
        //Equation with length of at least 3?
        if (getEquation().length() <= 3){
            return "Invalid - Too Short to be Equation!";
        }
        // Contains an "=" sign?
        if (!(getEquation().contains("="))){
            return "Invalid - No Equals Signs Present!";
        }
        // Contains 2 sides?
        String[] eqParts = getEquation().split("=");
        if (eqParts.length != 2){
            return "Invalid - Too Many Equals Signs!";
        }
        // Only 1 variable on at least one side?
        if (varCount(eqParts[0]) != 1 || varCount(eqParts[1]) != 1){
            return "Invalid - No Variables to Solve For!";
        }
        // Contains "+" or "-" sign for either side of length of at least 3?
        if ((eqParts[0].trim().length() >= 3 && getVar().length() < 2
        && !(eqParts[0].contains("+") || eqParts[0].contains("-")))
        || (eqParts[1].trim().length() >= 3  && getVar().length() < 2
        && !(eqParts[1].contains("+") || eqParts[1].contains("-")))){
            return "Invalid - At Least One Side Has No Operators for Like Terms!";
        }

        return "";
    }
    /* This method validates the string to make sure it is an equation. They must
     * have the following:
     * - Only one equals sign
     * - A length of 3 or greater
     * - Either a "+" or "-" symbol for either side with a length of at least 3
     * - Only one variable
     */
    public boolean validateBool(){
        //Equation with length of at least 3?
        if (getEquation().length() < 3){
            return false;
        }
        // Contains an "=" sign?
        if (!(getEquation().contains("="))){
            return false;
        }
        // Contains 2 sides?
        String[] eqParts = getEquation().split("=");
        if (eqParts.length != 2){
            return false;
        }
        // Only 1 variable on at least one side?
        if (varCount(eqParts[0]) != 1 || varCount(eqParts[1]) != 1){
            return false;
        }
        // Contains "+" or "-" sign for either side of length of at least 3?
        if ((eqParts[0].trim().length() >= 3 && getVar().length() < 1
        && !(eqParts[0].contains("+") || eqParts[0].contains("-")))
        || (eqParts[1].trim().length() >= 3  && getVar().length() < 1
        && !(eqParts[1].contains("+") || eqParts[1].contains("-")))){
            return false;
        }

        return true;
    }
    /* This method is meant to solve the equation by the following methods:
     * 1) Isolate the variable by adding (subtracting if "+" is present) both
     * sides of the equation by a number.
     */
    public String solve(){
        String[] eqParts = getEquation().split("=");
        eqParts[0] = eqParts[0].trim();
        eqParts[1] = eqParts[1].trim();
        String solution = "";
        double eqNum;
        double lone;

        if (numCount(eqParts[0]) >= 2){ 
            // ... +/- num +/- num +/- ... = ?
            double simpNum = 0; 
            String operator = "+";
            for (int i = 0; i < eqParts[0].length(); i++){
                if (eqParts[0].charAt(i) == '+'){
                    operator = "+";
                }
                else if (eqParts[0].charAt(i) == '-'){
                    operator = "-";
                }
                if (eqParts[0].charAt(i) >= 48 
                && eqParts[0].charAt(i) <= 57){
                    if (operator.equals("+")){
                        if (i != eqParts[0].length() && eqParts[0].indexOf(".", i) == i + 1){
                            simpNum += Double.valueOf(
                                eqParts[0].substring(
                                    i, eqParts[0].indexOf(".", i) + 2));    
                        }
                        else {
                            simpNum += 
                            Integer.valueOf(eqParts[0].substring(i, i + 1));
                        }
                    }
                    else if (operator.equals("-")){
                        if (i < eqParts[0].length() && eqParts[1].indexOf(".", i) == i + 1){
                            simpNum -= 
                            Double.valueOf(
                                eqParts[0].substring(
                                    i, eqParts[0].indexOf(".", i) + 2));
                        }
                        else {
                            simpNum -= Integer.valueOf(eqParts[0].substring(i, i + 1));
                        }
                    }
                }
            }
            if (eqParts[0].contains(getVar())){// var +/- res = num
                eqParts[0] = eqParts[0].substring(eqParts[0].indexOf(getVar()),
                eqParts[0].lastIndexOf(getVar())+getVar().length());
                if (operator == "+" && simpNum > 0){
                    eqParts[0] += operator;
                }
                eqParts[0] += simpNum;    
                System.out.println(eqParts[0]);
            }
            else if (eqParts[1].contains(getVar())){//+/-res = var (+ is void)
                eqParts[0] = Double.toString(simpNum);
            }
        }
        if (numCount(eqParts[1]) >= 2){ 
            // ? = ... +/- num +/- num +/- ...
            double simpNum = 0; 
            String operator = "+";
            for (int i = 0; i < eqParts[1].length(); i++){
                if (eqParts[1].charAt(i) == '+'){
                    operator = "+";
                }
                else if (eqParts[1].charAt(i) == '-'){
                    operator = "-";
                }
                if (eqParts[1].charAt(i) >= 48 
                && eqParts[1].charAt(i) <= 57){
                    if (operator.equals("+")){
                        if (i < eqParts[1].length() - 1 && eqParts[1].indexOf(".", i) == i + 1){
                            simpNum += Double.valueOf(eqParts[1].substring(i, eqParts[0].indexOf(".", i) + 2));
                        }
                        else {
                            simpNum += Integer.valueOf(eqParts[1].substring(i, i+1));
                        }
                    }
                    else if (operator.equals("-")){
                        simpNum -= Integer.valueOf(eqParts[1].substring(i, i+1));
                    }
                }
            }
            if (eqParts[1].contains(getVar())){// num = var +/- res
                eqParts[1] = eqParts[1].substring(eqParts[1].indexOf(getVar()),
                eqParts[1].lastIndexOf(getVar())+getVar().length())
                + operator + simpNum;    
            }
            else if (eqParts[0].contains(getVar())){//var = +/-res (+ is void)
                eqParts[1] = Double.toString(simpNum);
            }
            System.out.println(eqParts[1]);
        }
        if (eqParts[0].length() >= 3 && numCount(eqParts[0]) == 1
        && (eqParts[0].contains("+") || eqParts[0].contains("-"))){ 
            // * * * = *
            lone = Double.parseDouble(eqParts[1]);
            if (eqParts[0].contains("+")){
                if (eqParts[0].charAt(0) >= 48 
                && eqParts[0].charAt(0) <= 57){   // num + var = num
                    eqNum = Double.parseDouble(eqParts[0].substring(0, 
                    eqParts[0].indexOf("+")));
                    lone -= eqNum;
                    eqParts[0] = eqParts[0].substring(eqParts[0].indexOf("+")+1);
                }
                else {  // var + num = num
                    eqNum = Double.valueOf(
                        eqParts[0].substring(eqParts[0].indexOf("+") + 1));
                    lone -= eqNum;
                    eqParts[0] = 
                    eqParts[0].substring(0, eqParts[0].indexOf("+"));
                }
            }
            if (eqParts[0].contains("-")){
                if (eqParts[0].charAt(0) >= 48 
                && eqParts[0].charAt(0) <= 57){   // num - var = num
                    eqNum = Double.parseDouble(eqParts[0].substring(0, 
                    eqParts[0].indexOf("-")));
                    lone = (lone - eqNum) * -1;
                    eqParts[0] = 
                    eqParts[0].substring(eqParts[0].indexOf("-") + 1);
                }
                else {  // var - num = num
                    eqNum = Double.valueOf(
                        eqParts[0].substring(eqParts[0].indexOf("-") + 1));
                    lone += eqNum;
                    eqParts[0] = eqParts[0].substring(0, 
                    eqParts[0].indexOf("-"));
                }
            }
            eqParts[0] = eqParts[0].trim();
            solution = eqParts[0] + " = " + lone;
        }
        else if (eqParts[1].length() >= 3 && numCount(eqParts[1]) == 1
        && (eqParts[1].contains("+") || eqParts[1].contains("-"))){ 
            // * = * * *
            lone = Double.parseDouble(eqParts[0]);
            if (eqParts[1].contains("+")){
                if (eqParts[1].charAt(0) >= 48 
                && eqParts[1].charAt(0) <= 57){   // num = num + var
                    eqNum = Double.parseDouble(eqParts[1].substring(0, 
                    eqParts[1].indexOf("+")));
                    lone -= eqNum;
                    eqParts[1] = 
                    eqParts[1].substring(eqParts[1].indexOf("+") + 1);
                }
                else {  // num = var + num
                    eqNum = Double.valueOf(
                        eqParts[1].substring(eqParts[1].indexOf("+") + 1));
                    lone -= eqNum;
                    eqParts[1] = 
                    eqParts[1].substring(0, eqParts[1].indexOf("+"));
                }
            }
            if (eqParts[1].contains("-")){
                if (eqParts[1].charAt(0) >= 48 
                && eqParts[1].charAt(0) <= 57){   // num = num - var
                    eqNum = Double.parseDouble(eqParts[1].substring(0, 
                    eqParts[1].indexOf("-")));
                    lone = (lone - eqNum) * -1;
                    eqParts[1] = 
                    eqParts[1].substring(eqParts[1].indexOf("-") + 1);
                }
                else {  // num = var - num
                    eqNum = Double.valueOf(
                        eqParts[1].substring(eqParts[1].indexOf("-") + 1));
                    lone += eqNum;
                    eqParts[1] = eqParts[1].substring(0, eqParts[1].indexOf("-"));
                }
            }
            eqParts[1] = eqParts[1].trim();
            solution = eqParts[1] + " = " + lone;
        }
        else { // * = *
            if ((getVar(eqParts[0]).length() > 0 
            && eqParts[0].contains(getVar(eqParts[0]))) 
            && (getVar(eqParts[1]).length() > 0 
            && eqParts[1].contains(getVar(eqParts[1])))){
                // var = var
                double leftCo = 1.0, rightCo = 1.0; // coefficients
                if (numCount(eqParts[0]) == 1){
                    leftCo = Double.parseDouble(eqParts[0].substring(0, eqParts[0].indexOf(getVar(eqParts[0]))));
                }
                if (numCount(eqParts[1]) == 1){
                    rightCo = Double.parseDouble(eqParts[1].substring(0, eqParts[1].indexOf(getVar(eqParts[1]))));
                }
                leftCo -= rightCo;
                rightCo -= rightCo;
                eqParts[0] = leftCo + getVar(eqParts[0]);
                System.out.println(eqParts[0]);
                eqParts[1] = Double.toString(rightCo);
                System.out.println(eqParts[1]);
                if (eqParts[0].contains("0.0")){
                    eqParts[0] = eqParts[0].substring(0, 1);
                }
                else {
                    eqParts[0] = eqParts[0].substring(eqParts[0].indexOf(getVar(eqParts[0])));
                    if (!eqParts[0].contains("1.0")){
                        eqParts[1] = "" + Math.round(Double.parseDouble(eqParts[1]) / leftCo);
                    }
                }
            }
            if (eqParts[0].contains(".0")){
                eqParts[0] = eqParts[0].substring(0, eqParts[0].indexOf("."));
            }
            if (eqParts[1].contains(".0")){
                eqParts[1] = eqParts[1].substring(0, eqParts[1].indexOf("."));
            }
            // Swap Sides if Variable is not on Left Side
            if (eqParts[0].charAt(0) >= 48 
            && eqParts[0].charAt(0) <= 57){    // num = var
                String temp = eqParts[0].trim();
                eqParts[0] = eqParts[1].trim();
                eqParts[1] = temp;
        
            }
            solution = String.join(" = ", eqParts);
        }
        if (solution.contains(".0")){
            solution = solution.substring(0, solution.indexOf("."));
        }
        return solution;
    }

    // Class String with equation
    public String toString(){
        return "Your equation is " + getEquation();
    }
}
