import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.Scanner;

/**
 * Created by user on 04.08.17.
 */
public class Star extends Application {

    double cx;
    double cy;
    double rad;


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Star");
        primaryStage.setWidth(400);
        primaryStage.setHeight(400);

        Pane root = new Pane();

        dataInput();

        root.getChildren().addAll(drawStar(cx, cy, rad));


        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Line[] drawStar(double cx, double cy, double rad){
        Line[] lines = new Line[10];

        StarTop[] starTops = new StarTop[]{
                new StarTop(cx, cy - rad),
                new StarTop(cx + Math.cos(18 * (Math.PI / 180)) * rad,
                        cy - Math.sin(18 * (Math.PI / 180)) * rad),
                new StarTop(cx + Math.sin(36 * (Math.PI / 180)) * rad,
                        cy + Math.cos(36 * (Math.PI / 180)) * rad),
                new StarTop(cx - Math.sin(36 * (Math.PI / 180)) * rad,
                        cy + Math.cos(36 * (Math.PI / 180)) * rad),
                new StarTop(cx - Math.cos(18 * (Math.PI / 180)) * rad,
                        cy - Math.sin(18 * (Math.PI / 180)) * rad)
        };

        double d = 0.35;

        lines[0] = new Line(starTops[0].x, starTops[0].y,
                cx + Math.sin(36 * Math.PI / 180) * d * rad,
                cy - Math.cos(36 * Math.PI / 180) * d * rad);
        lines[1] = new Line(starTops[1].x, starTops[1].y,
                cx + Math.sin(36 * Math.PI / 180) * d * rad,
                cy - Math.cos(36 * Math.PI / 180) * d * rad);
        lines[2] = new Line(starTops[0].x, starTops[0].y,
                cx - Math.sin(36 * Math.PI / 180) * d * rad,
                cy - Math.cos(36 * Math.PI / 180) * d * rad);
        lines[3] = new Line(starTops[4].x, starTops[4].y,
                cx - Math.sin(36 * Math.PI / 180) * d * rad,
                cy - Math.cos(36 * Math.PI / 180) * d * rad);
        lines[4] = new Line(starTops[1].x, starTops[1].y,
                cx + Math.sin(72 * Math.PI / 180) * d * rad,
                cy + Math.cos(72 * Math.PI / 180) * d * rad);
        lines[5] = new Line(starTops[2].x, starTops[2].y,
                cx + Math.sin(72 * Math.PI / 180) * d * rad,
                cy + Math.cos(72 * Math.PI / 180) * d * rad);
        lines[6] = new Line(starTops[4].x, starTops[4].y,
                cx - Math.sin(72 * Math.PI / 180) * d * rad,
                cy + Math.cos(72 * Math.PI / 180) * d * rad);
        lines[7] = new Line(starTops[3].x, starTops[3].y,
                cx - Math.sin(72 * Math.PI / 180) * d * rad,
                cy + Math.cos(72 * Math.PI / 180) * d * rad);
        lines[8] = new Line(starTops[2].x, starTops[2].y, cx, cy + d * rad);
        lines[9] = new Line(starTops[3].x, starTops[3].y, cx, cy + d * rad);

        return lines;
    }

    public void dataInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Input X");
        cx = sc.nextInt();
        System.out.println("Input Y");
        cy = sc.nextInt();
        System.out.println("Input radius");
        rad = sc.nextInt();
    }

}
