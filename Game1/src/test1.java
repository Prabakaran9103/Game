import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.imageio.stream.FileImageInputStream;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class test1 extends Application{
    
    public static void main(String arg[]){
        launch();
    }

    public void start(Stage stage) throws FileNotFoundException{
        
        //File file = new File("C:/Users/Prabakaran/Pictures/Saved Pictures/a.jpg");
        //FileInputStream image = new FileInputStream("C:/Users/Prabakaran/Pictures/Saved Pictures/a.jpg");
        Image img = new Image(new FileInputStream("C:/Users/Prabakaran/Pictures/Saved Pictures/a.jpg"));
        ImageView imageview = new ImageView(img);
        //Node node = (Node)(image);
        Group group = new Group(imageview);
        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.show();
    }
}
