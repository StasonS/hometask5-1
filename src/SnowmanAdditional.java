import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Random;


/**
 * Created by user on 04.08.17.
 */
public class SnowmanAdditional extends Application {

    int width = 600;
    int height = 600;
    int circlesCount;
    int minRad;
    int maxRad;
    int[] radiuses;
    Circle[] snowman;

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Snowman");
        primaryStage.setWidth(600);
        primaryStage.setHeight(600);

        Pane root = new Pane();

        TextField numOfCircles = new TextField("Insert Number of Circles");
        TextField minRadius = new TextField("Insert minimum Radius");
        TextField maxRadius = new TextField("Insert maximum Radius");
        setCoord(numOfCircles, 5, 5);
        setCoord(minRadius, 5, 40);
        setCoord(maxRadius, 5, 75);

        Button button1 = new Button("Build");
        Button button2 = new Button("Make red");
        Button button3 = new Button("Gradient");
        setCoord(button1, 5, 110);
        setCoord(button2, 5, 145);
        setCoord(button3, 5, 180);

        root.getChildren().addAll(numOfCircles, minRadius, maxRadius);
        root.getChildren().addAll(button1, button2, button3);


        button1.setOnAction(event -> {

            root.getChildren().clear();
            root.getChildren().addAll(numOfCircles, minRadius, maxRadius);
            root.getChildren().addAll(button1, button2, button3);

            circlesCount = Integer.parseInt(numOfCircles.getText());
            minRad = Integer.parseInt(minRadius.getText());
            maxRad = Integer.parseInt(maxRadius.getText());

            radiuses = radiuses(minRad, maxRad, circlesCount);
            snowman = generateCircles(circlesCount, height, width, radiuses);

            root.getChildren().addAll(snowman);

        });

        button2.setOnMouseClicked(event -> {
            paintAll(Color.color(1, 0, 0));
        });

        button3.setOnMouseClicked(event -> {
            makeGradient(circlesCount);
        });


        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void setCoord(TextField textField, double x, double y){
        textField.setLayoutX(x);
        textField.setLayoutY(y);
    }
    public static void setCoord(Button button, double x, double y){
        button.setLayoutX(x);
        button.setLayoutY(y);
    }

    //Generates random number for circle radius (by the task)
    public static int generateRadius(int minRad, int maxRad){
        int radius = minRad + (int)(Math.random() * ((maxRad - minRad) + 1));
        return radius;
    }

    //Returns a massif of numbers. Massif size equals to circlesCount
    public static int[] radiuses(int minRad, int maxRad, int circlesCount){
        int[] r = new int[circlesCount];
        for (int i = 0; i < r.length; i++){
            r[i] = generateRadius(minRad, maxRad);
        }
        return r;
    }

    public static int findBottom(int height, int[] radiuses){
        int sum = 0;
        for (int i = 0; i < radiuses.length; i++){
            sum += radiuses[i];
        }
        int bottom = height / 2 + sum / 2 + 100;
//        int bottom = height - 20;
        return bottom;
    }

    public Circle[] generateCircles(int count, int height, int width, int[] radiuses){
        Circle[] circles = new Circle[count + 3];

        Random rand = new Random();

        int x = (width / 3) * 2;
        int y = findBottom(height, radiuses);

        for (int i = 0; i < circles.length - 3; i++){
            Color color = Color.color(
                    rand.nextDouble(),
                    rand.nextDouble(),
                    rand.nextDouble());

            circles[i] = new Circle(x, y - radiuses[i],
                    radiuses[i], Paint.valueOf(color.toString()));
            try{
                y = y - radiuses[i] * 2;
            }
            catch (Exception e){}
        }

        Color colorNose = Color.color(
                rand.nextDouble(),
                rand.nextDouble(),
                rand.nextDouble());

        Color colorEye1 = Color.color(
                rand.nextDouble(),
                rand.nextDouble(),
                rand.nextDouble());

        Color colorEye2 = Color.color(
                rand.nextDouble(),
                rand.nextDouble(),
                rand.nextDouble());


        circles[circles.length - 3] = new Circle(
                circles[circles.length - 4].getCenterX(),
                circles[circles.length - 4].getCenterY(),
                circles[circles.length - 4].getRadius() / 10,
                Paint.valueOf(colorNose.toString()));

        circles[circles.length - 2] = new Circle(
                circles[circles.length - 4].getCenterX() - circles[circles.length - 4].getRadius() / 3,
                circles[circles.length - 4].getCenterY() - circles[circles.length - 4].getRadius() / 2,
                circles[circles.length - 4].getRadius() / 10,
                Paint.valueOf(colorEye1.toString()));

        circles[circles.length - 1] = new Circle(
                circles[circles.length - 4].getCenterX() + circles[circles.length - 4].getRadius() / 3,
                circles[circles.length - 4].getCenterY() - circles[circles.length - 4].getRadius() / 2,
                circles[circles.length - 4].getRadius() / 10,
                Paint.valueOf(colorEye2.toString()));

        return circles;
    }

    private void paintAll(Color color) {
        if (snowman == null) return;

        for(int i = 0; i < snowman.length; i++)
            snowman[i].setFill(Paint.valueOf(color.toString()));
    }

    private void makeGradient(int numOfCircles){
        if (snowman == null) return;

        double step = 1.0 / numOfCircles;
        double opacity = 1.0;

        for (int i = 0; i < snowman.length; i++){
            snowman[i].setFill(Paint.valueOf(Color.color(0, 0, 0,
                    opacity ).toString()));
            opacity -= step;
        }
    }
}
