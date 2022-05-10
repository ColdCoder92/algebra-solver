package algebrafx;
//import javafx.animation.Animation;
import javafx.scene.control.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.stage.*;
//import javafx.scene.effect.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.*;
import javafx.scene.paint.*;
/* Author: Lucas Rodriguez
 * History (Process) is Shown in the GitHub Project Dashboard
 */
public class Solver extends Application{

    public void start(Stage mainStage) throws Exception{
        mainStage.setTitle("Equation Solver");
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        TextField input = new TextField();
        Label inputLabel = new Label("Welcome! Please enter an equation to solve: ");
        root.add(inputLabel, 2, 0);
        root.add(input, 2, 1);
        Button solveBtn = new Button("Solve!");
        root.add(solveBtn, 2, 2);

        TextField result = new TextField();
        result.setEditable(false);
        Label resultLabel = new Label("Result: ");
        root.add(resultLabel, 2, 3);
        root.add(result, 2, 4);

        Stop[] stops = new Stop[] {
            new Stop(0, Color.RED),
            new Stop(1, Color.WHITE)
        };
        LinearGradient grad = new LinearGradient(0, 5, 5, 0, false, CycleMethod.REPEAT, stops);

        Circle lowerCream = new Circle(25, Color.AQUA);
        Circle upperCream = new Circle(25, Color.PINK);
        Rectangle straw = new Rectangle(30, 10, grad);
        root.addRow(5, lowerCream, upperCream, straw);
        upperCream.setTranslateX(-50);
        upperCream.setTranslateY(-15);
        straw.setTranslateX(-65); 
        straw.setTranslateY(-35);
        straw.setRotate(-45);
        solveBtn.setOnAction(e -> {
            String equationString = input.getText();
            Equation eq = new Equation(equationString);
            if (eq.validate()){
                result.setText(eq.solve());
            }
            else {
                result.setText(eq.solve());
            }    
        });
        Scene frame = new Scene(root, 500, 500);
        mainStage.setScene(frame);
        mainStage.show();
    }
    public static void main(String[] args) throws Exception {
        Application.launch(args);
    }
}
