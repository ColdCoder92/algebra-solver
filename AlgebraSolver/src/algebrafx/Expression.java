package algebrafx;

public class Expression extends Algebra{
    private String expression;
    // Class Constructor
    public Expression(String expression){
        this.expression = expression;
    }
    // Expression Getter
    public String getExpression() {
        return expression;
    }
    // Expression Setter
    public void setExpression(String expression) {
        this.expression = expression;
    }
    // This method solves the expression by combining all numbers
    public String solve(){
        String solverExpression = getExpression();
        double result = 0;
        String operator = "+";
        for (int i = 0; i < solverExpression.length(); i++){
            switch (solverExpression.charAt(i)){
                case '+':
                    operator = "+";
                    break;
                case '-':
                    operator = "-";
                    break;
                case '*':
                    operator = "*";
                    break;
                case '/':
                    operator = "/";
                    break;
            }
            if (isNum(solverExpression, i)){
                if (solverExpression.contains(".") 
                && (solverExpression.lastIndexOf(".", i) == i - 1 && i != 0)){
                    System.out.println("index skipped: " + i);
                    continue;
                }
                if (operator.equals("+")){
                    if (solverExpression.contains(".") 
                    && solverExpression.indexOf(".", i) == i + 1){
                        result += Double.valueOf(solverExpression.substring(
                                i, solverExpression.indexOf(".", i) + 2
                        ));       
                    }
                    else {
                        result += 
                        Integer.valueOf(solverExpression.substring(i, i + 1));    
                    }
                }
                else if (operator.equals("-")){
                    if (solverExpression.contains(".") 
                    && solverExpression.indexOf(".", i) == i + 1){
                        result -= Double.valueOf(solverExpression.substring(
                                i, solverExpression.indexOf(".", i) + 2
                        ));       
                    }
                    else {
                        result -= 
                        Integer.valueOf(solverExpression.substring(i, i + 1));    
                    }
                }
            }
        }
        if (Double.toString(result).contains(".0")){
            return roundOnes(result) + "";
        }
        else {
            return roundHun(result) + "";
        }
    }
}
