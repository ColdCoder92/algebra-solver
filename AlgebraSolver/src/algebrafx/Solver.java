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
        root.add(inputLabel, 3, 0);
        root.add(input, 3, 1);
        Button solveBtn = new Button("Solve!");
        root.add(solveBtn, 3, 2);

        TextField result = new TextField();
        result.setEditable(false);
        Label resultLabel = new Label("Result: ");
        root.add(resultLabel, 3, 3);
        root.add(result, 3, 4);

        Stop[] stops = new Stop[] {
            new Stop(0, Color.RED),
            new Stop(1, Color.WHITE)
        };
        LinearGradient grad = new LinearGradient(0, 5, 5, 0, false, CycleMethod.REPEAT, stops);

        Polygon cone = new Polygon(-25, 25, 25, 25, 0, 75);
        Circle lowerCream = new Circle(25, Color.AQUA);
        Circle upperCream = new Circle(25, Color.PINK);
        Circle cherry = new Circle(10, Color.rgb(204, 37, 44));
        Rectangle straw = new Rectangle(30, 5, grad);
        Rectangle leftArm = new Rectangle(60, 10, Color.rgb(205, 162, 111));
        Rectangle rightArm = new Rectangle(60, 10, Color.rgb(205, 162, 111)); 
        Circle leftEye = new Circle(0, 10, 5);
        Circle rightEye = new Circle(0, 10, 5);
        Group eyes = new Group(leftEye, rightEye);
        Group iceCream = new Group(straw, leftArm, rightArm, cherry, upperCream, 
        lowerCream, eyes, cone);
        root.add(iceCream, 0, 2);

        cone.setFill(Color.rgb(205, 162, 111));
        cone.setTranslateY(-40);
        lowerCream.setTranslateY(-25);
        upperCream.setTranslateY(-50);
        cherry.setTranslateY(-80);
        straw.setTranslateX(10);
        straw.setTranslateY(-77.5);
        straw.setRotate(-45);
        iceCream.setTranslateX(-25);
        leftArm.setTranslateY(-42);
        rightArm.setTranslateX(-rightArm.getWidth());
        rightArm.setTranslateY(-35);
        rightArm.setRotate(-45);
        leftEye.setTranslateX(-cherry.getRadius());
        rightEye.setTranslateX(cherry.getRadius());
        eyes.setTranslateY(-72.5);

        solveBtn.setOnAction(e -> {
            String equationString = input.getText();
            Equation eq = new Equation(equationString);
            if (eq.validateBool()){
                result.setText(eq.solve());
            }
            else {
                result.setText(eq.validate());
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
