package algebrafx;

import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.scene.control.*;
import javafx.scene.effect.Reflection;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.*;
import javafx.scene.paint.*;
/* Author: Lucas Rodriguez
 * History (Process) is Shown in the GitHub Project Dashboard
 * Note: The fields and labels should be hidden until appearing in conjunction
 * with the animations
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
        inputLabel.setOpacity(0);
        input.setOpacity(0);
        // Create and Position Solve Button
        Button solveBtn = new Button("Solve!");
        root.add(solveBtn, 4, 2);
        solveBtn.setOpacity(0);
        // Create and Position result label and text field
        TextField result = new TextField();
        result.setEditable(false);
        Label resultLabel = new Label("Result: ");
        root.add(resultLabel, 3, 3);
        root.add(result, 3, 5);
        resultLabel.setOpacity(0);
        result.setOpacity(0);
        // Create Loading Circles
        Circle loadingCircle = new Circle(5);
        Circle loadingCircle2 = new Circle(5);
        Circle loadingCircle3 = new Circle(5);
        Circle[] loadingArr = {loadingCircle, loadingCircle2, loadingCircle3};
        Group loading = new Group(loadingCircle, loadingCircle2, loadingCircle3);
        root.add(loading, 3, 5);
        // Adjust Loading Circles
        loadingCircle2.setTranslateX(5 * loadingCircle.getRadius());
        loadingCircle3.setTranslateX(10 * loadingCircle.getRadius());
        loading.setOpacity(0);
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
        Group arms = new Group(leftArm, rightArm);
        Circle leftEye = new Circle(0, 10, 5);
        Circle rightEye = new Circle(0, 10, 5);
        Group eyes = new Group(leftEye, rightEye);
        // Additional Eyes
        Polygon triangleLeftEye = new Polygon(-5, 10, -15, 10, -10, 15);
        Polygon triangleRightEye = new Polygon(15, 10, 5, 10, 10, 15);  
        Group triangleEyes = new Group(triangleLeftEye, triangleRightEye);
        Arc happyLeftEye = new Arc(-10, 10, 5, 5, 0, 180);
        Arc happyRightEye = new Arc(10, 10, 5, 5, 0, 180);
        Group happyEyes = new Group(happyLeftEye, happyRightEye);
        Group eyeSets = new Group(triangleEyes, eyes, happyEyes);
        triangleEyes.setOpacity(0);
        //happyEyes.setOpacity(0);
        // Position Ice Cream Character
        Group iceCream = new Group(straw, arms, cherry, upperCream, lowerCream,
        eyeSets, cone);
        root.add(iceCream, 0, 2);    
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
        eyeSets.setTranslateY(-72.5);
        // Create Ice Cream Reflection
        Reflection reflectPond = new Reflection();
        reflectPond.setFraction(0.3);
        reflectPond.setTopOpacity(0.5);
        reflectPond.setTopOffset(0.2);
        iceCream.setEffect(reflectPond);
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
        // Appear Animation for Input Label
        FadeTransition appear = new FadeTransition(Duration.seconds(1), inputLabel);
        appear.setCycleCount(1);
        appear.setByValue(1);
        appear.play();
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
        // Appear Animation for Input Field 
        FadeTransition inputFieldShow = new FadeTransition(Duration.seconds(1), input);
        inputFieldShow.setCycleCount(1);
        inputFieldShow.setDelay(Duration.seconds(4));
        inputFieldShow.setByValue(1);
        inputFieldShow.play();
        // Appear Animation for Solve Button
        FadeTransition solveBtnShow = new FadeTransition(Duration.seconds(1), solveBtn);
        solveBtnShow.setCycleCount(1);
        solveBtnShow.setDelay(Duration.seconds(4));
        solveBtnShow.setByValue(1);
        solveBtnShow.play();
        // Floater Animation (Moving the Character Up and Down 20 pixels)
        TranslateTransition floater = new TranslateTransition(Duration.seconds(2), iceCream);
        floater.setDelay(Duration.seconds(4));
        floater.setCycleCount(Integer.MAX_VALUE);
        floater.setByY(-20);
        floater.setAutoReverse(true);
        floater.play();
        // Moves the Eyes toward the text field
        TranslateTransition fieldLook = new TranslateTransition(Duration.seconds(1), eyeSets);
        fieldLook.setDelay(Duration.seconds(4));
        fieldLook.setCycleCount(1);
        fieldLook.setByX(leftEye.getRadius());
        fieldLook.play();
        // Equation Solving Mechanism
        solveBtn.setOnAction(e -> {
            result.setOpacity(0);
            // Looking at Result Field Animation
            TranslateTransition resultLook = new TranslateTransition(Duration.seconds(1), eyes);
            resultLook.setCycleCount(1);
            resultLook.setByY(leftEye.getRadius());
            resultLook.play();
            // Appear Animation for Results Label
            FadeTransition resLabelShow = new FadeTransition(Duration.seconds(1), resultLabel);
            resLabelShow.setCycleCount(1);
            resLabelShow.setByValue(1);
            resLabelShow.play(); 
            // Loading Animation
            FadeTransition loadingShow = new FadeTransition(Duration.seconds(1), loading);
            loadingShow.setByValue(1);
            loadingShow.setCycleCount(1);
            loadingShow.setDelay(Duration.millis(500));
            loadingShow.play();
            for (int i = 0; i < 3; i++){
                // Loading Color Changing Animation
                FillTransition loadingCycle = new FillTransition(Duration.seconds(1), loadingArr[i], Color.BLACK, Color.WHITE);
                loadingCycle.setCycleCount(3);
                loadingCycle.setDelay(Duration.millis(500 + (i * 500)));
                loadingCycle.setAutoReverse(true);
                loadingCycle.play();
                // Loading Hover Animation
                TranslateTransition loadingHover = new TranslateTransition(Duration.seconds(1), loadingArr[i]);
                loadingHover.setCycleCount(3);
                loadingHover.setDelay(Duration.millis(500 + (i * 500)));
                loadingHover.setAutoReverse(true);
                loadingHover.setByY(-5);
                loadingHover.play();
    
            }
            String equationString = input.getText();
            Equation eq = new Equation(equationString);
            // Loading Finished Animation
            FadeTransition loadingDone = new FadeTransition(Duration.seconds(1), loading);
            loadingDone.setCycleCount(1);
            loadingDone.setByValue(-1);
            loadingDone.setDelay(Duration.seconds(5));
            loadingDone.play();
            // Result Field Show Animation
            FadeTransition resShow = new FadeTransition(Duration.seconds(1), result);
            resShow.setCycleCount(1);
            resShow.setByValue(1);
            resShow.setDelay(Duration.seconds(5));
            resShow.play();
            // Hide Regular Eyes
            FadeTransition fadeEyes = new FadeTransition(Duration.millis(500), eyes);
            fadeEyes.setByValue(-1);
            fadeEyes.setCycleCount(1);
            fadeEyes.play();
            if (eq.validateBool()){
                // Show Happy Eyes
                FadeTransition happy = new FadeTransition(Duration.millis(500), happyEyes);
                happy.setByValue(1);
                happy.setCycleCount(1);
                happy.play();
                // Raising Arms Rotate Animation (Hooray! The Result is Here!)
                RotateTransition raiseRightArm = new RotateTransition(Duration.seconds(2), rightArm);
                raiseRightArm.setDelay(Duration.seconds(4));
                raiseRightArm.setByAngle(-90);
                raiseRightArm.setCycleCount(2);
                raiseRightArm.setAutoReverse(true);
                raiseRightArm.play();
                RotateTransition raiseLeftArm = new RotateTransition(Duration.seconds(2), leftArm);
                raiseLeftArm.setDelay(Duration.seconds(4));
                raiseLeftArm.setByAngle(90);
                raiseLeftArm.setCycleCount(2);
                raiseLeftArm.setAutoReverse(true);
                raiseLeftArm.play();
                // Raising Arms Translate Adjustment
                TranslateTransition armsAdjustment = new TranslateTransition(Duration.seconds(4), arms);
                armsAdjustment.setCycleCount(2);
                armsAdjustment.setDelay(Duration.seconds(4));
                armsAdjustment.setByY(-7);
                armsAdjustment.setAutoReverse(true);
                armsAdjustment.play();

                result.setText(eq.solve());
                // Hide Happy Eyes
                FadeTransition fadeHappyEyes = new FadeTransition(Duration.millis(500), happyEyes);
                fadeHappyEyes.setByValue(-1);
                fadeHappyEyes.setCycleCount(1);
                fadeHappyEyes.play();
            }
            else {
                // Reveal Disappointed Eyes
                FadeTransition showSadEyes = new FadeTransition(Duration.millis(500), triangleEyes);
                showSadEyes.setByValue(1);
                showSadEyes.setCycleCount(1);
                showSadEyes.play(); 
                // Shake Head
                TranslateTransition shake = new TranslateTransition(Duration.seconds(2), triangleEyes);
                shake.setDelay(Duration.seconds(4));
                shake.setCycleCount(4);
                shake.setByX(-cherry.getRadius());
                shake.setAutoReverse(true);
                shake.play();
                result.setText(eq.validate());
                // Hide Disappointed Eyes
                FadeTransition hideSadEyes = new FadeTransition(Duration.millis(500), triangleEyes);
                hideSadEyes.setDelay(Duration.seconds(8));
                hideSadEyes.setByValue(-1);
                hideSadEyes.setCycleCount(1);
                hideSadEyes.play(); 
            }
            // Reveal Regular Eyes Again
            FadeTransition showEyes = new FadeTransition(Duration.millis(500), eyes);
            showEyes.setDelay(Duration.seconds(8));
            showEyes.setByValue(1);
            showEyes.setCycleCount(1);
            showEyes.play();
            // Prevent Eyes from Leaving Upper Cream
            /*TranslateTransition lookUp = new TranslateTransition(Duration.seconds(1), eyes);
            showEyes.setDelay(Duration.seconds(8));
            lookUp.setCycleCount(1);
            lookUp.setByY(-leftEye.getRadius());
            lookUp.play(); */  
        });
        Scene frame = new Scene(root, 500, 500);
        mainStage.setScene(frame);
        mainStage.show();
    }
    public static void main(String[] args) throws Exception {
        Application.launch(args);
    }
}
