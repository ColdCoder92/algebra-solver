package algebrafx;
//import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
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
        // Create and Position input label and text field
        TextField input = new TextField();
        Label inputLabel = new Label("Welcome! Please enter an equation to solve: ");
        root.add(inputLabel, 3, 0);
        root.add(input, 3, 2);
        Button solveBtn = new Button("Solve!");
        root.add(solveBtn, 4, 2);
        // Create and Position result label and text field
        TextField result = new TextField();
        result.setEditable(false);
        Label resultLabel = new Label("Result: ");
        root.add(resultLabel, 3, 3);
        root.add(result, 3, 5);
        // Set up Strawberry Linear Gradient
        Stop[] stops = new Stop[] {
            new Stop(0, Color.RED),
            new Stop(1, Color.WHITE)
        };
        LinearGradient grad = new LinearGradient(0, 5, 5, 0, false, CycleMethod.REPEAT, stops);
        // Create Ice Cream Character
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
        // Create Additional Parts
        Polygon triangleLeftEye = new Polygon(5, 5, 0, 5, 2.5, 7.5);
        Polygon triangleRightEye = new Polygon(15, 5, 10, 5, 12.5, 7.5);  
        Group triangleEyes = new Group(triangleLeftEye, triangleRightEye);
        root.add(triangleEyes, 0, 2); 
        // Adjust Ice Cream Parts
        cone.setFill(Color.rgb(205, 162, 111));
        cone.setTranslateY(-40);
        lowerCream.setTranslateY(-lowerCream.getRadius());
        upperCream.setTranslateY(-upperCream.getRadius() * 2);
        cherry.setTranslateY(-80);
        straw.setTranslateX(10);
        straw.setTranslateY(-77.5);
        straw.setRotate(-45);
        iceCream.setTranslateX(-30);
        rightArm.setTranslateY(-42);
        leftArm.setTranslateX(-leftArm.getWidth());
        leftArm.setTranslateY(-35);
        leftArm.setRotate(-45);
        leftEye.setTranslateX(-cherry.getRadius());
        rightEye.setTranslateX(cherry.getRadius());
        eyes.setTranslateY(-72.5);
        // Right Arm Waving Animation
        RotateTransition wave = 
        new RotateTransition(Duration.seconds(1), rightArm);
        wave.setCycleCount(4);
        wave.setFromAngle(0);
        wave.setToAngle(-45);
        wave.setAutoReverse(true);
        wave.play();
        // Right Arm Movement for Smooth Wave
        TranslateTransition armUp = 
        new TranslateTransition(Duration.seconds(1), rightArm);
        armUp.setCycleCount(4);
        armUp.setByY(-7);
        armUp.setAutoReverse(true);
        armUp.play();
        // Lowering Right Arm Animation (Setting angle to left arm's one)
        RotateTransition armDown = 
        new RotateTransition(Duration.seconds(1), rightArm);
        armDown.setDelay(Duration.seconds(4));
        armDown.setCycleCount(1);
        armDown.setByAngle(45);
        armDown.play();
        // Move Right Arm Down to Between Creams
        TranslateTransition armAdjust = new TranslateTransition(Duration.seconds(1), rightArm);
        armAdjust.setDelay(Duration.seconds(4));
        armAdjust.setCycleCount(1);
        armAdjust.setByY(7);
        armAdjust.play();
        // Floater Animation (Moving the Character Up and Down 20 pixels)
        TranslateTransition floater = new TranslateTransition(Duration.seconds(2), iceCream);
        floater.setDelay(Duration.seconds(4));
        floater.setCycleCount(Integer.MAX_VALUE);
        floater.setByY(-20);
        floater.setAutoReverse(true);
        floater.play();
        // Moves the Eyes toward the text field
        TranslateTransition fieldLook = new TranslateTransition(Duration.seconds(1), eyes);
        fieldLook.setDelay(Duration.seconds(4));
        fieldLook.setCycleCount(1);
        fieldLook.setByX(leftEye.getRadius());
        fieldLook.play();
        // Equation Solving Mechanism
        solveBtn.setOnAction(e -> {
            // Looking at Result Field Animation
            TranslateTransition resultLook = new TranslateTransition(Duration.seconds(1), eyes);
            resultLook.setCycleCount(1);
            resultLook.setByY(leftEye.getRadius());
            resultLook.play();

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
