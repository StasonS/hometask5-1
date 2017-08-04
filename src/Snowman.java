import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by user on 03.08.17.
 */
public class Snowman extends Application {

    static int circlesCount;
    static int minRad;
    static int maxRad;
    final int WIDTH = 400;
    final int HEIGHT = 500;
    static int[] radiuses;


    @Override
    public void start(Stage primaryStage) throws Exception {

        radiuses = radiuses();
        int height = makeHeight(radiuses);

        primaryStage.setTitle("Snowman");
        primaryStage.setWidth(makeWidth(maxRad));
        primaryStage.setHeight(height);
//        primaryStage.setWidth(WIDTH);
//        primaryStage.setHeight(HEIGHT);

        Pane root = new Pane();


//        Circle circle = new Circle(
//                primaryStage.getWidth() / 2,
//                primaryStage.getHeight() / 2,
//                50);


        root.getChildren().addAll(generateCircles(circlesCount, height, radiuses));




        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Console input by the task
    public static void dataInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Input a number of circles");
        circlesCount = sc.nextInt();
        System.out.println("Input a minimum radius");
        minRad = sc.nextInt();
        System.out.println("Input a maximum radius");
        maxRad = sc.nextInt();
    }

    //Generates random number for circle radius (by the task)
    public static int generateRadius(){
        int radius = minRad + (int)(Math.random() * ((maxRad - minRad) + 1));
        return radius;
    }

    //Returns a massif of numbers. Massif size equals to circlesCount
    public static int[] radiuses(){
        int[] r = new int[circlesCount];
        for (int i = 0; i < r.length; i++){
            r[i] = generateRadius();
        }
        return r;
    }

    //Evaluates a height of window
    public static int makeHeight(int[] radiuses){
        int sum = 0;
        for (int i : radiuses){
            sum += (i * 2);
        }
        return sum + 40;
    }

    //Evaluates a width of window
    public static int makeWidth(int maxRad){
        return maxRad + 150;
    }

    //Generates a massif of circles by the task
    public Circle[] generateCircles(int count, int height, int[] radiuses){
        Circle[] circles = new Circle[count + 3];

        Random rand = new Random();

        int y = height - 20;//
        int x = makeWidth(maxRad) / 2;
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

    public static void main(String[] args) {



        dataInput();
        launch(args);
    }
}
