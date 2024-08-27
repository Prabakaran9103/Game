import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class see extends Application{

    public static void main(String arg[]){
        launch();
    }

    public void start(Stage stage){


        Rectangle[] border = new Rectangle[8];
        border[0] = new Rectangle(0,0,100,10);
        border[1] = new Rectangle(0,10,10,100);
        border[2] = new Rectangle(200,0,100,10);
        border[3] = new Rectangle(290,0,10,100);
        border[4] = new Rectangle(0,200,10,100);
        border[5] = new Rectangle(0,290,100,10);
        border[6] = new Rectangle(290,200,10,100);
        border[7] = new Rectangle(200,290,100,10);
        for(int i=0; i<8; i++){
            border[i].setFill(Color.RED);
        }
        Circle circle = new Circle(100,100,50);
        circle.setFill(Color.rgb(0,0,40));
        Group group = new Group(circle);
        group.getChildren().addAll(border);
        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.setHeight(339);
        stage.setWidth(316);
        stage.setTitle("MainMenu");
        stage.show();
    }
}