package algebrafx;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

// Private methods are to be used only in the Equation class methods
public class Equation extends Algebra{
    private String equation;
    // Class Constructor
    public Equation(String equation){
        this.equation = equation;
    }
    // Equation Getter
    public String getEquation() {
        return this.equation;
    }
    // Equation Setter
    public void setEquation(String equation) {
        this.equation = equation;
    }
    // Optimized Variable Getter
    // Used with Pattern and Matcher class
    public String getVar(){
        String variable = "";
        Pattern alpha = Pattern.compile("[a-zA-Z]+");
        Matcher target = alpha.matcher(getEquation());
        if (target.find()){
            variable = target.group();
        }
        else {
            variable = "Not Found!";
        }
        System.out.println("equation variable = " + variable);
        return variable;
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
        if (num.length() >= 1 && isNum(num, 0) 
        && isNum(num, num.length() - 1)){
            count++;
        }
        System.out.println("constant count = " + count);
        return count;
    }
    // This method counts the number of variables in the equation
    private int varCount(String expr){
        int count = 0;
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher match = pattern.matcher(expr);
        while (match.find()){
            count++;
        }
        System.out.println("variable count = " + count);
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
        if (varCount(eqParts[0]) == 0 || varCount(eqParts[1]) == 0){
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
        if (varCount(eqParts[0]) == 0 && varCount(eqParts[1]) == 0){
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
        String solverEquation = getEquation();
        // All fractions in the solver convert to decimals for better ease
        if (equation.contains("/")){
            solverEquation = fracToDecs(equation);
        }
        String[] eqParts = solverEquation.split("=");
        eqParts[0] = eqParts[0].trim();
        eqParts[1] = eqParts[1].trim();
        String solution = "";
        double eqNum;
        double lone;

        if (numCount(eqParts[0]) >= 2 || varCount(eqParts[0]) >= 2){ 
            System.out.println("here: numCount is at least 2 in left side");
            // ... +/- num +/- num +/- ... = ?
            double simpNum = 0;
            double simpVar = 0; 
            String operator = "+";
            for (int i = 0; i < eqParts[0].length(); i++){
                if (eqParts[0].charAt(i) == '+'){
                    operator = "+";
                }
                else if (eqParts[0].charAt(i) == '-'){
                    operator = "-";
                }
                if (isNum(eqParts[0], i)){
                    if (eqParts[0].contains(".") 
                    && (eqParts[0].lastIndexOf(".", i) == i - 1 && i != 0)){
                        System.out.println("index skipped: " + i);
                        continue;
                    }
                    if (operator.equals("+")){
                        if (eqParts[0].contains(".") 
                        && eqParts[0].indexOf(".", i) == i + 1){
                            if (eqParts[0].contains(getVar())
                            && (i < eqParts[0].length() - 3)
                            && (eqParts[0].indexOf(getVar(), i) == i + 3)){
                                simpVar += Double.valueOf(
                                    eqParts[0].substring(
                                        i, eqParts[0].indexOf(getVar(), i)
                                    )
                                );
                                System.out.println("simpVar " + i + " = " + simpVar);
                                System.out.println(eqParts[0].indexOf(getVar(), i));
                            }        
                            else {
                                simpNum += Double.valueOf(
                                    eqParts[0].substring(
                                        i, eqParts[0].indexOf(".", i) + 2
                                    )
                                );       
                            }
                        }
                        else {
                            if (eqParts[0].contains(getVar())
                            && (i < eqParts[0].length() - 1)
                            && (eqParts[0].indexOf(getVar(), i) == i + 1)){
                                simpVar += 
                                Integer.valueOf(eqParts[0].substring(i, i + 1));
                            }        
                            else {
                                simpNum += 
                                Integer.valueOf(eqParts[0].substring(i, i + 1));    
                            }
                        }
                    }
                    else if (operator.equals("-")){
                        if (eqParts[0].contains(".") 
                        && eqParts[0].indexOf(".", i) == i + 1){
                            if (eqParts[0].contains(getVar())
                            && (i < eqParts[0].length() - 3)
                            && (eqParts[0].indexOf(getVar(), i) == i + 3)){
                                simpVar -= Double.valueOf(
                                    eqParts[0].substring(
                                        i, eqParts[0].indexOf(getVar(), i)
                                    )
                                );
                            }        
                            else {
                                simpNum -= Double.valueOf(
                                    eqParts[0].substring(
                                        i, eqParts[0].indexOf(".", i) + 2
                                    )
                                );       
                            }
                        }
                        else {
                            if (eqParts[0].contains(getVar())
                            && (i < eqParts[0].length() - 1)
                            && (eqParts[0].indexOf(getVar(), i) == i + 1)){
                                simpVar -= 
                                Integer.valueOf(eqParts[0].substring(i, i + 1));
                            }        
                            else {
                                simpNum -= 
                                Integer.valueOf(eqParts[0].substring(i, i + 1));    
                            }
                        }
                    }
                }
            }
            System.out.println("left simpNum = " + simpNum);
            System.out.println("left simpVar = " + simpVar);
            if (Double.toString(simpVar).contains(".0")){
                simpVar = roundOnes(simpVar);
            }
            else {
                simpVar = roundHun(simpVar);
            }
            if (eqParts[0].contains(getVar())){// (num)var +/- res = num
                if ((eqParts[0].indexOf(getVar()) != 0)
                && (eqParts[0].charAt(
                    eqParts[0].indexOf(getVar()) - 1) >= 48 
                && eqParts[0].charAt(
                    eqParts[0].indexOf(getVar()) - 1) <= 57)){
                    eqParts[0] = simpVar + getVar();
                }
                else{
                    eqParts[0] = getVar();
                }
                if (operator == "+" && simpNum > 0){
                    eqParts[0] += operator;
                }
                if (Double.toString(simpNum).contains(".0")){
                    eqParts[0] += roundOnes(simpNum);
                }
                else {
                    eqParts[0] += simpNum;    
                }
            }
            else if (getVar().length() > 0 
            && eqParts[1].contains(getVar())){
                // +/-res = var (+ is void)
                if (Double.toString(simpNum).contains(".0")){
                    eqParts[0] = Integer.toString(roundOnes(simpNum));
                }
                else {
                    eqParts[0] = Double.toString(simpNum);
                }
            }
            System.out.println("left side: " + eqParts[0]);
        }
        if (numCount(eqParts[1]) >= 2 || varCount(eqParts[1]) >= 2){ 
            System.out.println("here: numCount is at least 2 in right side");
            // ? = ... +/- num +/- num +/- ...
            double simpNum = 0;
            double simpVar = 0; 
            String operator = "+";
            for (int i = 0; i < eqParts[1].length(); i++){
                if (eqParts[1].charAt(i) == '+'){
                    operator = "+";
                }
                else if (eqParts[1].charAt(i) == '-'){
                    operator = "-";
                }
                if (isNum(eqParts[1], i)){
                    if (eqParts[1].contains(".") 
                    && eqParts[1].lastIndexOf(".", i) == i - 1){
                        continue;
                    }
                    if (operator.equals("+")){
                        if (eqParts[1].contains(".") 
                        && eqParts[1].indexOf(".", i) == i + 1){
                            if (eqParts[1].contains(getVar())
                            && (i < eqParts[1].length() - 3)
                            && (eqParts[1].indexOf(getVar(), i) == i + 3)){
                                simpVar += Double.valueOf(
                                    eqParts[1].substring(
                                        i, eqParts[1].indexOf(getVar(), i)
                                    )
                                );
                                System.out.println("simpVar " + i + " = " + simpVar);
                                System.out.println(eqParts[1].indexOf(getVar(), i));
                            }        
                            else {
                                simpNum += Double.valueOf(
                                    eqParts[1].substring(
                                        i, eqParts[1].indexOf(".", i) + 2
                                    )
                                );       
                            }
                        }
                        else {
                            if (eqParts[1].contains(getVar())
                            && (i < eqParts[1].length() - 1)
                            && (eqParts[1].indexOf(getVar(), i) == i + 1)){
                                simpVar += 
                                Integer.valueOf(eqParts[1].substring(i, i + 1));
                            }        
                            else {
                                simpNum += 
                                Integer.valueOf(eqParts[1].substring(i, i + 1));    
                            }
                        }
                    }
                    else if (operator.equals("-")){
                        if (eqParts[1].contains(".") 
                        && eqParts[1].indexOf(".", i) == i + 1){
                            if (i < eqParts[1].length() - 3){
                                if (eqParts[1].contains(getVar())
                                && (i < eqParts[1].length() - 3)
                                && (eqParts[1].indexOf(getVar(), i) == i + 3)){
                                    simpVar -= Double.valueOf(
                                        eqParts[1].substring(
                                            i, eqParts[1].indexOf(getVar(), i)
                                        )
                                    );
                                }        
                                else {
                                    simpNum -= Double.valueOf(
                                        eqParts[1].substring(
                                            i, eqParts[1].indexOf(".", i) + 2
                                        )
                                    );       
                                }
                            }
                            else {
                                simpNum -= 
                                Double.valueOf(eqParts[1].substring(i));
                            }
                        }
                        else {
                            if (eqParts[1].contains(getVar())
                            && (i < eqParts[1].length() - 1)
                            && (eqParts[1].indexOf(getVar(), i) == i + 1)){
                                simpVar -= 
                                Integer.valueOf(eqParts[1].substring(i, i + 1));
                            }        
                            else {
                                simpNum -= 
                                Integer.valueOf(eqParts[1].substring(i, i + 1));    
                            }
                        }
                    }
                }
            }
            System.out.println("right simpNum = " + simpNum);
            if (getVar().length() > 0 
            && eqParts[1].contains(getVar())){// num = var +/- res
                if ((eqParts[1].indexOf(getVar()) != 0)
                && (eqParts[1].charAt(
                    eqParts[1].indexOf(getVar()) - 1) >= 48 
                && eqParts[1].charAt(
                    eqParts[1].indexOf(getVar()) - 1) <= 57)){
                    if (eqParts[1].lastIndexOf(
                        ".", eqParts[1].indexOf(getVar())
                    )
                    == eqParts[1].indexOf(getVar()) - 2){
                        eqParts[1] = eqParts[1].substring(
                            eqParts[1].indexOf(getVar()) - 3,
                            eqParts[1].lastIndexOf(getVar()) 
                            + getVar().length()
                        );
                    }
                    else {
                        eqParts[1] = eqParts[1].substring(
                            eqParts[1].indexOf(getVar()) - 1,
                            eqParts[1].lastIndexOf(getVar()) 
                            + getVar().length()
                        );    
                    }
                }
                else{
                    eqParts[1] = eqParts[1].substring(
                        eqParts[1].indexOf(getVar()),
                        eqParts[1].lastIndexOf(getVar()) + getVar().length());
                }
                if (operator == "+" && simpNum > 0){
                    eqParts[1] += operator;
                }
                if (Double.toString(simpNum).contains(".0")){
                    eqParts[1] += roundOnes(simpNum);
                }
                else {
                    eqParts[1] += simpNum;    
                }
            }
            else if (eqParts[0].contains(getVar())){//var = +/-res (+ is void)
                if (Double.toString(simpNum).contains(".0")){
                    eqParts[1] = Integer.toString(roundOnes(simpNum));
                }
                else {
                    eqParts[1] = Double.toString(simpNum);
                }
            }
            System.out.println("right side: " + eqParts[1]);
        }
        if (eqParts[0].contains(getVar()) 
        && eqParts[1].contains(getVar())){
            System.out.println("here: both sides contain a variable");
            // * * * = * * *
            double leftCo = 1.0, rightCo = 1.0; // coefficients
            if (eqParts[0].indexOf(getVar()) != 0){
                if (eqParts[0].contains(".")
                && eqParts[0].indexOf(".") == eqParts[0].indexOf(getVar()) - 2){
                    leftCo = 
                    Double.parseDouble(
                        eqParts[0].substring(
                            eqParts[0].indexOf(getVar()) - 3, 
                            eqParts[0].indexOf(getVar())
                        )
                    );
                }
                else {
                    leftCo =
                    Double.parseDouble(
                        eqParts[0].substring(
                            eqParts[0].indexOf(getVar()) - 1, 
                            eqParts[0].indexOf(getVar())
                        )
                    );
                }
            }
            System.out.println("leftCo = " + leftCo);
            if (eqParts[1].indexOf(getVar()) != 0){
                if (eqParts[1].contains(".")
                && eqParts[1].charAt(
                    eqParts[1].indexOf(getVar()) - 2) == '.'){
                    rightCo = Double.parseDouble(eqParts[1].substring(
                        eqParts[1].indexOf(getVar()) - 3,
                        eqParts[1].indexOf(getVar())));    
                }
                else {
                    rightCo = 
                    Double.parseDouble(
                        eqParts[1].substring(
                            eqParts[1].indexOf(getVar()) - 1, 
                            eqParts[1].indexOf(getVar())
                        )
                    );
                }
            }
            System.out.println("rightCo = " + rightCo);
            if (eqParts[1].contains("+") 
            || (numCount(eqParts[1]) == 0 && !eqParts[1].contains("-"))){
                leftCo -= rightCo;
            }
            else {
                leftCo += rightCo;
            }
            rightCo -= rightCo;
            System.out.println("leftCo (After) = " + leftCo);
            if ((eqParts[0].contains(".") 
            && eqParts[0].indexOf(getVar()) > 3) 
            || (!eqParts[0].contains(".")
            && eqParts[0].indexOf(getVar()) > 2)){
                // num +/- (num)var = *
                if (eqParts[0].contains("+")){
                    if (Double.toString(leftCo).contains(".0")){
                        eqParts[0] = eqParts[0].substring(0,
                        eqParts[0].indexOf("+") + 1) + roundOnes(leftCo) 
                                                        + getVar();
                    }
                    else {
                        eqParts[0] = eqParts[0].substring(0,
                        eqParts[0].indexOf("+") + 1) + roundHun(leftCo) 
                                                        + getVar();    
                    }
                }
                else if (eqParts[0].contains("-")){
                    if (Double.toString(leftCo).contains(".0")){
                        eqParts[0] = eqParts[0].substring(0,
                        eqParts[0].indexOf("-") + 1) + roundOnes(leftCo) 
                                                        + getVar();    
                    }
                    else {
                        eqParts[0] = eqParts[0].substring(0,
                        eqParts[0].indexOf("-") + 1) + roundHun(leftCo) 
                                                        + getVar();    
                    }
                }
            }
            else {
                // (num)var +/- num = *
                if (eqParts[0].contains("+")
                || ((eqParts[0].contains("-") 
                && eqParts[0].indexOf(
                    "-", eqParts[0].indexOf(getVar())) > 0))){
                        //System.out.println("In if statement");
                    if (Double.toString(leftCo).contains(".0")){
                        eqParts[0] = roundOnes(leftCo) + eqParts[0].substring(
                            eqParts[0].indexOf(getVar())
                        );    
                    }
                    else {
                        eqParts[0] = roundHun(leftCo) + 
                        eqParts[0].substring(
                            eqParts[0].indexOf(getVar())
                        );
                    }
                }
                else { // (num)var = *
                    //System.out.println("In numvar =");
                    if (Double.toString(leftCo).contains(".0")){
                        eqParts[0] = roundOnes(leftCo) + getVar();
                    }
                    else {
                        eqParts[0] = roundHun(leftCo) + getVar();
                    }
                }
            }
            if (!eqParts[0].contains(".")){
                if (eqParts[0].contains("1")){
                    eqParts[0] = 
                    eqParts[0].substring(eqParts[0].indexOf(getVar()));
                }
                else if (eqParts[0].contains("0")){
                    eqParts[0] = "0";
                }    
            }
            System.out.println("left side: " + eqParts[0]);
            // The right side of the equation will only adopt the lone number
            if ((eqParts[1].contains(".") 
            && eqParts[1].indexOf(getVar()) > 3) 
            || (!eqParts[1].contains(".")
            && eqParts[1].indexOf(getVar()) > 2)){
                // * = num +/- (num)var previously
                if (eqParts[1].contains("+")){
                    eqParts[1] = 
                    eqParts[1].substring(0, eqParts[1].indexOf("+"));
                }
                else if (eqParts[1].contains("-")){
                    eqParts[1] = 
                    eqParts[1].substring(0, eqParts[1].indexOf("-", 1));
                }
            }
            else {
                // * = (num)var +/- num
                if (eqParts[1].contains("+")){
                    eqParts[1] = 
                    eqParts[1].substring(eqParts[1].indexOf("+") + 1);
                }
                else if (eqParts[1].contains("-") && eqParts[1].indexOf("-", 1) > 1){
                    eqParts[1] = 
                    eqParts[1].substring(eqParts[1].indexOf("-") + 1);
                }
                else {
                    eqParts[1] = "0";
                }
            }
            System.out.println("right side: " + eqParts[1]);
        }
        if (eqParts[0].length() >= 3 && numCount(eqParts[0]) >= 1
        && (eqParts[0].contains("+") || eqParts[0].contains("-"))){ 
            // * * * = *
            System.out.println("here: left side contains var and constant");
            lone = Double.parseDouble(eqParts[1]);
            if (eqParts[0].contains("+")){
                if ((eqParts[0].contains(".") 
                && eqParts[0].indexOf(getVar()) > 3) 
                || (!eqParts[0].contains(".")
                && eqParts[0].indexOf(getVar()) > 2)){   
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
                if ((eqParts[0].contains(".") 
                && eqParts[0].indexOf(getVar()) > 3) 
                || (!eqParts[0].contains(".")
                && eqParts[0].indexOf(getVar()) > 2)){   // num - (num)var = num
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
                        0, eqParts[0].indexOf(getVar())
                        )
                    );
                eqParts[0] = eqParts[0].substring(eqParts[0].indexOf(getVar()));
                System.out.println("left side: " + eqParts[0]);
            }
            System.out.println("result = " + lone);
            solution = eqParts[0] + " = ";
            if (("" + lone).contains(".0")){
                solution += roundOnes(lone);
            }
            else {
                solution += roundHun(lone);
            }
        }
        else if (eqParts[1].length() >= 3 && numCount(eqParts[1]) >= 1
        && (eqParts[1].contains("+") || eqParts[1].contains("-"))){ 
            // * = * * *
            System.out.println("here: right side contains var and constant");
            lone = Double.parseDouble(eqParts[0]);
            if (eqParts[1].contains("+")){
                if (eqParts[1].indexOf(getVar()) > 2){   // num = num + (num)var
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
                if (eqParts[1].indexOf(getVar()) > 2){   // num = num - (num)var
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
                        0, eqParts[1].indexOf(getVar())
                        )
                    );
                eqParts[1] = eqParts[1].substring(eqParts[1].indexOf(getVar()));
                System.out.println("right side: " + eqParts[1]);
            }
            solution = eqParts[1] + " = ";
            if (("" + lone).contains(".0")){
                solution += roundOnes(lone);
            }
            else {
                solution += roundHun(lone);
            }
        }
        else { // * = *
            // Swap Sides if Variable is not on Left Side
            if (!eqParts[0].contains(getVar())){    // num = var
                String temp = eqParts[0].trim();
                eqParts[0] = eqParts[1].trim();
                eqParts[1] = temp;
        
            }
            System.out.println(eqParts[0]);
            if ((getVar().length() != 0 && eqParts[0].contains(getVar()))
            && eqParts[0].indexOf(getVar()) != 0){
                double co = Double.parseDouble(eqParts[0].substring(0, eqParts[0].indexOf(getVar())));
                lone = Double.valueOf(eqParts[1]);
                lone /= co;
                lone = roundHun(lone);
                eqParts[0] = eqParts[0].substring(
                    eqParts[0].indexOf(getVar())
                );
                eqParts[1] = "" + roundHun(lone);
            }
            if (eqParts[1].contains(".0")){
                eqParts[1] = eqParts[1].substring(0, eqParts[1].indexOf("."));
            }
            if (eqParts[1].contains("0") && eqParts[1].contains("-")){
                eqParts[1] = eqParts[1].substring(1);
            }
            solution = String.join(" = ", eqParts);
        }
        return solution;
    }

    // Class String with equation
    public String toString(){
        return "Your equation is " + getEquation();
    }
}
