package algebrafx;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

// Private methods are to be used only in the Equation class methods
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
    // This method returns the double value rounded to the hundredths place
    private double roundHun(double num){
        /* Compares hundredths places by getting rid of the ones and tenths 
        digits */
        //System.out.println(num*10);
        //System.out.println(Math.ceil(num * 10));
        //System.out.println((num * 10) - Math.ceil(num*10));
        if (((num * 10) - Math.floor(num*10)) >= 0.5 
        && Math.floor(num*10) <= num * 10){
            num += 0.05;
        }
        else if (num < 0 && (num * 10) - Math.ceil(num*10) <= -0.5){
            num -= 0.05;
        }
        // ex. 5.55 * 10 = 55.5 - 50 = 0.5 >= 0.5 = true
        String numText = "" + num;
        if (numText.length() > 3){
            numText = numText.substring(0, 4);
        }
        return Double.valueOf(numText);
    }
    /* Determines whether a character in either side of the equation is a 
       number */
    private boolean isNum(String expr, int index){
        if ((expr.charAt(index) >= 48 && expr.charAt(index) <= 57)){
            return true;
        }
        return false;
    }
    // This method counts all the numbers in one side of an equation
    private int numCount(String expr){
        int count = 0;
        String num = "";
        for (int i = 0; i < expr.length(); i++){
            if (isNum(expr, i)){
                num += Integer.parseInt(expr.substring(i, i + 1));
            }
            else if(expr.charAt(i) == '.'){
                num += ".";
            }
            else if ((expr.charAt(i) >= 65 && expr.charAt(i) <= 90) 
            || (expr.charAt(i) >= 97 && expr.charAt(i) <= 122)){
                num += expr.charAt(i);
            }
            else if (expr.charAt(i) == ' ' && expr.charAt(i) == '+'){
                continue;
            }
            else {
                //System.out.println(num);
                if (num.trim().length() != 0){
                    // Should count only if the string has a number
                    if (!((num.charAt(num.length()-1) >= 65
                    && num.charAt(num.length()-1) <= 90)
                    || (num.charAt(num.length()-1) >= 97
                    && num.charAt(num.length()-1) <= 122))){
                        count++;
                    }
                    num = "";
                }
            }
        }
        if (num.length() >= 1 && isNum(num, 0) && isNum(num, num.length()-1)){
            count++;
        }
        System.out.println(count);
        return count;
    }
    // This method counts the number of variables in the equation
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
     * 2) Divide both sides by the coefficient of the variable if the
     * coefficient is not 1.
     */
    public String solve(){
        String[] eqParts = getEquation().split("=");
        eqParts[0] = eqParts[0].trim();
        eqParts[1] = eqParts[1].trim();
        String solution = "";
        double eqNum;
        double lone;

        if (numCount(eqParts[0]) >= 2){ 
            System.out.println("here");
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
                if (isNum(eqParts[0], i)){
                    if (eqParts[0].contains(getVar())
                    && (i < eqParts[0].length() - 1)
                    && (eqParts[0].indexOf(getVar()) == i + 1)){
                        continue;
                    }
                    if (operator.equals("+")){
                        if (eqParts[0].indexOf(".", i) == i + 1){
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
                        if (eqParts[0].indexOf(".", i) == i + 1){
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
                if ((eqParts[0].indexOf(getVar(eqParts[0])) != 0)
                && (eqParts[0].charAt(eqParts[0].indexOf(getVar()) - 1) >= 48 
                && eqParts[0].charAt(eqParts[0].indexOf(getVar()) - 1) <= 57)){
                    eqParts[0] = eqParts[0].substring(
                        eqParts[0].indexOf(getVar()) - 1,
                        eqParts[0].lastIndexOf(getVar())+getVar().length());
    
                }
                else{
                    eqParts[0] = eqParts[0].substring(
                        eqParts[0].indexOf(getVar()),
                        eqParts[0].lastIndexOf(getVar())+getVar().length());
                }
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
            System.out.println("here");
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
                if (isNum(eqParts[1], i)){
                    if (eqParts[1].contains(getVar())
                    && (i < eqParts[1].length() - 1)
                    && (eqParts[1].indexOf(getVar()) == i + 1)){
                        continue;
                    }
                    if (operator.equals("+")){
                        if (i < eqParts[1].length() - 1 && eqParts[1].indexOf(".", i) == i + 1){
                            simpNum += Double.valueOf(eqParts[1].substring(i, eqParts[0].indexOf(".", i) + 2));
                        }
                        else {
                            simpNum += Integer.valueOf(eqParts[1].substring(i, i+1));
                        }
                    }
                    else if (operator.equals("-")){
                        if (eqParts[1].indexOf(".", i) == i + 1){
                            if (i < eqParts[1].length() - 3 ){
                                simpNum -= Double.valueOf(eqParts[1].substring(i, eqParts[0].indexOf(".", i) + 2));
                            }
                            else {
                                simpNum -= Double.valueOf(eqParts[1].substring(i));
                            }
                        }
                        else {
                            simpNum -= Integer.valueOf(eqParts[1].substring(i, i+1));
                        }
                    }
                }
            }
            if (eqParts[1].contains(getVar())){// num = var +/- res
                if ((eqParts[1].indexOf(getVar(eqParts[1])) != 0)
                && (eqParts[1].charAt(eqParts[1].indexOf(getVar()) - 1) >= 48 
                && eqParts[1].charAt(eqParts[1].indexOf(getVar()) - 1) <= 57)){
                    eqParts[1] = eqParts[1].substring(
                        eqParts[1].indexOf(getVar()) - 1,
                        eqParts[1].lastIndexOf(getVar())+getVar().length());
    
                }
                else{
                    eqParts[1] = eqParts[1].substring(
                        eqParts[1].indexOf(getVar()),
                        eqParts[1].lastIndexOf(getVar())+getVar().length());
                }
                if (operator == "+" && simpNum > 0){
                    eqParts[1] += operator;
                }
                eqParts[1] += simpNum;    
            }
            else if (eqParts[0].contains(getVar())){//var = +/-res (+ is void)
                eqParts[1] = Double.toString(simpNum);
            }
            System.out.println(eqParts[1]);
        }
        if ((getVar(eqParts[0]).length() > 0 
        && eqParts[0].contains(getVar(eqParts[0]))) 
        && (getVar(eqParts[1]).length() > 0 
        && eqParts[1].contains(getVar(eqParts[1])))){
            // * * * = * * *
            double leftCo = 1.0, rightCo = 1.0; // coefficients
            if (eqParts[0].indexOf(getVar(eqParts[0])) != 0){
                leftCo = Double.parseDouble(eqParts[0].substring(0, eqParts[0].indexOf(getVar(eqParts[0]))));
            }
            if (eqParts[1].indexOf(getVar(eqParts[1])) != 0){
                rightCo = Double.parseDouble(eqParts[1].substring(
                    eqParts[1].indexOf(getVar(eqParts[1])) - 3,
                    eqParts[1].indexOf(getVar(eqParts[1]))));
            }
            if (eqParts[1].contains("+") 
            || (numCount(eqParts[1]) == 0 && !eqParts[1].contains("-"))){
                leftCo -= rightCo;
            }
            else {
                leftCo += rightCo;
            }
            leftCo -= rightCo;
            rightCo -= rightCo;
            if (eqParts[0].indexOf(getVar(eqParts[0])) > 2){
                // num +/- (num)var = *
                if (eqParts[0].contains("+")){
                    eqParts[0] = eqParts[0].substring(0,
                    eqParts[0].indexOf("+") + 1) + leftCo + getVar(eqParts[0]);
                }
                else if (eqParts[0].contains("-")){
                    eqParts[0] = eqParts[0].substring(0,
                    eqParts[0].indexOf("-") + 1) + leftCo + getVar(eqParts[0]);
                }
            }
            else {
                // (num)var +/- num = *
                if (eqParts[0].contains("+")
                || ((eqParts[0].contains("-") 
                && eqParts[0].indexOf(
                    "-", eqParts[0].indexOf(getVar(eqParts[0]))) > 1))){
                    eqParts[0] = leftCo + eqParts[0].substring(
                        eqParts[0].indexOf(getVar(eqParts[0])));
                }
                else { // (num)var = *
                    eqParts[0] = leftCo + getVar(eqParts[0]);
                }
            }
            System.out.println(eqParts[0]);
            // The right side of the equation will only adopt the lone number
            if (eqParts[1].indexOf(getVar(eqParts[1])) > 2){
                // * = num +/- (num)var previously
                if (eqParts[1].contains("+")){
                    eqParts[1] = eqParts[1].substring(0, eqParts[1].indexOf("+"));
                }
                else if (eqParts[1].contains("-")){
                    eqParts[1] = eqParts[1].substring(0, eqParts[1].indexOf("-", 1));
                }
            }
            else {
                // * = (num)var +/- num
                if (eqParts[1].contains("+")){
                    eqParts[1] = eqParts[1].substring(eqParts[1].indexOf("+") + 1);
                }
                else if (eqParts[1].contains("-") && eqParts[1].indexOf("-", 1) > 1){
                    eqParts[1] = eqParts[1].substring(eqParts[1].indexOf("-") + 1);
                }
                else {
                    eqParts[1] = "0";
                }
            }
            System.out.println(eqParts[1]);
        }
        if (eqParts[0].length() >= 3 && numCount(eqParts[0]) >= 1
        && (eqParts[0].contains("+") || eqParts[0].contains("-"))){ 
            // * * * = *
            lone = Double.parseDouble(eqParts[1]);
            if (eqParts[0].contains("+")){
                if (eqParts[0].indexOf(getVar(eqParts[0])) > 2){   
                    // num + (num)var = num
                    eqNum = Double.parseDouble(eqParts[0].substring(0, 
                    eqParts[0].indexOf("+")));
                    lone -= eqNum;
                    eqParts[0] = eqParts[0].substring(
                        eqParts[0].indexOf("+") + 1);
                }
                else {  // (num)var + num = num
                    eqNum = Double.valueOf(
                        eqParts[0].substring(eqParts[0].indexOf("+") + 1));
                    lone -= eqNum;
                    eqParts[0] = 
                    eqParts[0].substring(0, eqParts[0].indexOf("+"));
                }
            }
            else if (eqParts[0].contains("-")){
                if (eqParts[0].indexOf(getVar(eqParts[0])) > 2){   // num - (num)var = num
                    eqNum = Double.parseDouble(eqParts[0].substring(0, 
                    eqParts[0].indexOf("-")));
                    lone = (lone - eqNum) * -1;
                    eqParts[0] = 
                    eqParts[0].substring(eqParts[0].indexOf("-") + 1);
                }
                else {  // (num)var - num = num
                    eqNum = Double.valueOf(
                        eqParts[0].substring(eqParts[0].indexOf("-") + 1));
                    lone += eqNum;
                    eqParts[0] = eqParts[0].substring(0, 
                    eqParts[0].indexOf("-"));
                }
            }
            eqParts[0] = eqParts[0].trim();
            if (isNum(eqParts[0], 0)){
                lone /= Double.valueOf(
                    eqParts[0].substring(
                        0, eqParts[0].indexOf(getVar(eqParts[0]))
                        )
                    );
                eqParts[0] = eqParts[0].substring(eqParts[0].indexOf(getVar(eqParts[0])));
                System.out.println(eqParts[0]);
            }
            solution = eqParts[0] + " = " + roundHun(lone);
        }
        else if (eqParts[1].length() >= 3 && numCount(eqParts[1]) >= 1
        && (eqParts[1].contains("+") || eqParts[1].contains("-"))){ 
            // * = * * *
            lone = Double.parseDouble(eqParts[0]);
            if (eqParts[1].contains("+")){
                if (eqParts[1].indexOf(getVar(eqParts[1])) > 2){   // num = num + (num)var
                    eqNum = Double.parseDouble(eqParts[1].substring(0, 
                    eqParts[1].indexOf("+")));
                    lone -= eqNum;
                    eqParts[1] = 
                    eqParts[1].substring(eqParts[1].indexOf("+") + 1);
                }
                else {  // num = (num)var + num
                    eqNum = Double.valueOf(
                        eqParts[1].substring(eqParts[1].indexOf("+") + 1));
                    lone -= eqNum;
                    eqParts[1] = 
                    eqParts[1].substring(0, eqParts[1].indexOf("+"));
                }
            }
            if (eqParts[1].contains("-")){
                if (eqParts[1].indexOf(getVar(eqParts[1])) > 2){   // num = num - (num)var
                    eqNum = Double.parseDouble(eqParts[1].substring(0, 
                    eqParts[1].indexOf("-")));
                    lone = (lone - eqNum) * -1;
                    eqParts[1] = 
                    eqParts[1].substring(eqParts[1].indexOf("-") + 1);
                }
                else {  // num = (num)var - num
                    eqNum = Double.valueOf(
                        eqParts[1].substring(eqParts[1].indexOf("-") + 1));
                    lone += eqNum;
                    eqParts[1] = eqParts[1].substring(0, eqParts[1].indexOf("-"));
                }
            }
            eqParts[1] = eqParts[1].trim();
            if (isNum(eqParts[1], 0)){
                lone /= Double.valueOf(
                    eqParts[1].substring(
                        0, eqParts[1].indexOf(getVar(eqParts[1]))
                        )
                    );
                eqParts[1] = eqParts[1].substring(eqParts[1].indexOf(getVar(eqParts[1])));
                System.out.println(eqParts[1]);
            }
            solution = eqParts[1] + " = " + roundHun(lone);
        }
        else { // * = *
            // Swap Sides if Variable is not on Left Side
            if (isNum(eqParts[0], 0)){    // num = var
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
