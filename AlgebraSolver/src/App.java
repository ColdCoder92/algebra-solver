import java.util.Scanner;
/* To-Do List for Algebra Solver Project:
 * Day 1 (5/2/2022):
 * 1) Make a small imput-program for App.java ✅
 * 2) Set-up the Equation class with the solve method ✅
 * 3) Test the solver program with the following equations:
 *  -> x + 1 = 2 ✅
 *  -> 1 + x = 2 ✅
 *  -> 2 = x + 1 ✅
 *  -> 2 = 1 + x ✅
 *  -> x - 1 = 2 ✅
 *  -> 1 - x = 2 ✅
 *  -> 2 = x - 1 ✅
 *  -> 2 = 1 - x ✅
 *  -> x = 1 + 2 ✅
 *  -> x = 2 - 1 ✅
 *  -> 1 + 2 = x ✅
 *  -> 2 - 1 = x ✅
 * 4) Create a validate method for the Equation class, then incorporate it into
 *    the program. ✅
 */
public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("Welcome! Please enter an equation to solve: ");
        String equationString = input.nextLine();
        Equation eq = new Equation(equationString);
        if (eq.validate()){
            eq.solve();
        }
        else {
            System.out.println("Sorry! That equation is invalid");
        }
        input.close();
    }
}
