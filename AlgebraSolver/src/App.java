import java.util.Scanner;
/* Author: Lucas Rodriguez
 * History (Process) is Outlined in the To-Do text file
 */
public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("Welcome! Please enter an equation to solve: ");
        String equationString = input.nextLine();
        Equation eq = new Equation(equationString);
        if (eq.validate()){
            System.out.println(eq.solveTest());
        }
        else {
            System.out.println("Sorry! That equation is invalid");
        }
        input.close();
    }
}
