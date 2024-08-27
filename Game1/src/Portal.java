import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Portal extends Application{
    
    public static void main(String arg[]){

        launch();
    }

    public void start(Stage stage){

        //int X=0, Y=0;
        Rectangle[] body = new Rectangle[100];
        int k = 0;
        for(int i =0; i<10; i++){
            for(int j=0; j<10; j++){
                body[k++] = new Rectangle(i, j, 1, 1);
                //X++;
            }
            //Y++;
        }

        Group group = new Group(body);
        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.show();

    }
}
