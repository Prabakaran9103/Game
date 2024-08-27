package testings;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SceneSwitching extends Application {
    public static void main(String arg[]){
        launch();
    }

    public void start(Stage stage){

        Circle circle = new Circle(100,100,50);
        Rectangle rectangle = new Rectangle(100,100,50,50);

        Group group1 = new Group(circle);
        Group group2 = new Group(rectangle);
        
        Scene scene1 = new Scene(group1);
        Scene scene2 = new Scene(group2);

        EventHandler<KeyEvent> change = new EventHandler<KeyEvent>(){
            public void handle(KeyEvent e){
                switch(e.getCode()){
                    case W:
                        stage.setScene(scene2);
                        //stage.show();
                        break;
                    case S:
                        stage.setScene(scene1);
                        //stage.show();
                        break;
                    default:
                        break;
                }
            }
        };

        scene1.setOnKeyPressed(change);
        scene2.setOnKeyPressed(change);
        stage.setScene(scene1);
        stage.show();
    }
}
