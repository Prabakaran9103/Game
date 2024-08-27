import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

class RainDrop extends AnimationTimer{

        Group group;
        int X;
        ImageView drop;
        ImageView hit;
        RainDrop(Group group) throws FileNotFoundException{
            this.group = group;
            Random random = new Random();
            X = random.nextInt(0,1200);
            Image image1 = new Image(new FileInputStream("C:/Users/Prabakaran/Pictures/Saved Pictures/drop.png"));
            Image image2 = new Image(new FileInputStream("C:/Users/Prabakaran/Pictures/Saved Pictures/hit.png"));

            drop = new ImageView(image1);
            hit  = new ImageView(image2);
            drop.setLayoutX(X);
            hit.setLayoutX(X);
            drop.setLayoutY(-20);
        }

        public void handle(long now){
            group.getChildren().remove(drop);
            drop.setLayoutY(drop.getLayoutY()+2);
            group.getChildren().add(drop);
            if(drop.getLayoutY()>550){
                group.getChildren().remove(drop);
                hit.setLayoutY(550);
                group.getChildren().add(hit);
                //group.getChildren().remove(hit);
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stop();         
            }
        }
}
public class Rain extends Application{
    
    public static void main(String arg[]){
        launch();
    }

    public void start(Stage stage){

        Group group = new Group();
        new Thread(){
            RainDrop drop;
            public void run(){
                while(true){
                    try {
                        drop = new RainDrop(group);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    drop.start();
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.setTitle("Rain");
        stage.setWidth(1200);
        stage.setHeight(600);
        stage.show();
    }
}
