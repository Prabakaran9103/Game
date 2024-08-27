
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import java.util.Random;

public class Main extends Application{
    
    //Snake's body and it's length;
    static short length = 4;
    static Circle circle[] = new Circle[length];
    static byte speed = 5;
    static byte radius = 5;
    static short r = 0, g = 0, b = 25;
    static byte flag = 0;


    //Points;
    static Rectangle point;
    static byte score = 0;
    static Label scorelabel  = new Label(""+score);

    //DataStructure to store direction;
    static byte direction;
    static byte direction_queue[] = new byte[50];
    static byte direction_rear = -1;
    static byte direction_pointer[] = new byte[length]; //points the direction of each cirlce in queue;

    //DataStructure to store coordinate of scene
    //at the point of direction changes;
    static int coordinate_queue[][] = new int[50][2];
    static byte coordinate_rear = -1;
    static byte coordinate_pointer[] = new byte[length]; //points the coordinates of each cirlce in queue;



    public static void main(String arg[]){
        launch();
    }


    //Push direction into the direction_queue;
    public void pushDirection(byte direction){
        if(direction_rear == -1){
            direction_queue[++direction_rear] = direction;
            for(short i=0; i<length; i++)
                direction_pointer[i] = 0;
        }
        else{
            direction_rear++;
            direction_rear = (byte)(direction_rear%50);
            direction_queue[direction_rear] = direction;
        }
    }


    //Push coordinates to coordinate_queue;
    public void pushCoordinate(int x, int y){
        if(coordinate_rear == -1){
            coordinate_rear++;
            coordinate_queue[coordinate_rear][0] = x;
            coordinate_queue[coordinate_rear][1] = y;
            for(short i=0; i<length; i++)
                coordinate_pointer[i] = 0;
        }
        else{
            coordinate_rear++;
            coordinate_rear = (byte)(coordinate_rear%50);
            coordinate_queue[coordinate_rear][0] = x;
            coordinate_queue[coordinate_rear][1] = y;
        }
    }


    //To return new point to the group;
    public Rectangle newPoint(){

        Random random = new Random();
        int startX = random.nextInt(15,280);
        int startY = random.nextInt(15,280);
        startX = startX+(8-(startX%8));
        startY = startY+(8-(startY%8));
        Rectangle point = new Rectangle(startX,startY,8,8);
        return point;
    }


    //To check collision between snake and point;
    public boolean checkPoint(){

        short x1 = (short)(point.getX());
        short x2 = (short)(x1+2*radius);
        short y1 = (short)(point.getY());
        short y2 = (short)(y1+2*radius);

        short cx1 = (short)(circle[0].getCenterX());
        short cy1 = (short)(circle[0].getCenterY());

        if((x1<=cx1 && cx1<=x2) && (y1<=cy1 && cy1<=y2))
            return true;
        return false;

    }


    //To check body collision
    //Snake bites its body
    public boolean checkBite(){
        
        short x1, x2, y1, y2;
        short cx1 = (short)(circle[0].getCenterX());
        short cy1 = (short)(circle[0].getCenterY());

        for(short i=4; i<length; i++){
            x1 = (short)(circle[i].getCenterX()-radius);
            x2 = (short)(x1+2*radius);
            y1 = (short)(circle[i].getCenterY()-radius);
            y2 = (short)(y1+2*radius);
            if((x1<cx1 && cx1<x2) && (y1<cy1 && cy1<y2))
                return true;
        }

        switch(direction_queue[direction_pointer[0]]){
            case 1:
                if((cy1-radius<=10) && !(100<=cx1 && cx1<=200))
                    return true;
            case 2:
                if((cx1-radius<=10) && !(100<=cy1 && cy1<=200))
                    return true;
            case 3:
                if((cy1+radius>=290) && !(100<=cx1 && cx1<=200))
                    return true;
            case 4:
                if((cx1+radius>=290) && !(100<=cy1 && cy1<=200))
                    return true;
                
        }
        return false;
    }

    //Increase snake's size
    public void newCircle(){

        Circle new_circle[] = new Circle[length+1];
        byte new_direction_pointer[] = new byte[length+1];
        byte new_coordinate_pointer[] = new byte[length+1];

        for(short i=0; i<length; i++){
            new_circle[i] = circle[i];
            new_direction_pointer[i] = direction_pointer[i];
            new_coordinate_pointer[i] = coordinate_pointer[i];
        }
        
        //Setting direction & position of newly added circle
        //According to 'length-1' th cirlce 
        switch(direction_queue[direction_pointer[length-1]]){
            case 1:
                new_circle[length] = new Circle(circle[length-1].getCenterX(), 
                                                circle[length-1].getCenterY()+2*radius, radius, Color.BLUE);
                break;
            case 2:
                new_circle[length] = new Circle(circle[length-1].getCenterX()+2*radius,
                                                circle[length-1].getCenterY(), radius, Color.BLUE);
                break;
            case 3:
                new_circle[length] = new Circle(circle[length-1].getCenterX(),
                                                circle[length-1].getCenterY()-2*radius, radius, Color.BLUE);
                break;
            case 4:
                new_circle[length] = new Circle(circle[length-1].getCenterX()-2*radius,
                                                circle[length-1].getCenterY(), radius, Color.BLUE);
                break;
        }

        b = (short)(b+3);
        new_circle[length].setFill(Color.rgb(r,g,b));
        new_direction_pointer[length] = direction_pointer[length-1];
        new_coordinate_pointer[length] = coordinate_pointer[length-1];

        circle = new_circle;
        direction_pointer = new_direction_pointer;
        coordinate_pointer = new_coordinate_pointer;

        length++;
    }

    //Overriding Application.start()
    @Override
    public void start(Stage stage){

        Group group = new Group();
        Label lable1 = new Label("Score : ");
        HBox scorepanel = new HBox(lable1, scorelabel);
        scorepanel.setLayoutX(11);
        scorepanel.setLayoutY(11);
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
            border[i].setFill(Color.DARKRED);
        }

        point = newPoint();

        //Creating the snake body with size 4;
        circle[0] = new Circle(10,160,radius,Color.rgb(r,g,b));
        circle[1] = new Circle(10-2*radius,160,radius,Color.rgb(r,g,b));
        circle[2] = new Circle(10-4*radius,160,radius,Color.rgb(r,g,b));
        circle[3] = new Circle(10-6*radius,160,radius,Color.rgb(r,g,b));

        group.getChildren().addAll(circle);
        group.getChildren().addAll(border);
        group.getChildren().addAll(point, scorepanel);
        Scene scene = new Scene(group);
        scene.setFill(Color.AQUA);

        direction = 4;
        pushDirection(direction);

        //Animation for snake locomotion;
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now){

                /*
                if(flag>9){
                    group.getChildrssen().remove(point);
                    group.getChildren().add(point);
                }
                else{
                    group.getChildren().remove(point);
                }
                flag = (byte)(flag % 20 + 1);
                */
                group.getChildren().removeAll(circle);

                if(checkBite()){
                    stop();
                }

                //To check success collision and to increase snake body;
                if(checkPoint()){
                    group.getChildren().remove(point);
                    point = newPoint();
                    group.getChildren().add(point);
                    newCircle();
                    score++;
                    scorelabel.setText(""+score);
                }


                //Changing the direction of each seperate body of the snake 
                //when it reaches the coordinates where it should change its direction;
                for(short i=0; i<length; i++){
                    if(direction_pointer[i] != direction_rear){
                        if((short)(circle[i].getCenterX()) == coordinate_queue[coordinate_pointer[i]][0]
                        && (short)(circle[i].getCenterY()) == coordinate_queue[coordinate_pointer[i]][1]){
                            direction_pointer[i]++;
                            direction_pointer[i] = (byte)(direction_pointer[i]%50);
                            coordinate_pointer[i]++;
                            coordinate_pointer[i] = (byte)(coordinate_pointer[i]%50);
                        }
                    }

                    //Snake locomotions;
                    short a;
                    switch(direction_queue[direction_pointer[i]]){
                        case 1:
                            a = (short)(circle[i].getCenterY());
                            if(a < 0)
                                a = 300;
                            circle[i].setCenterY(a-speed);
                            break;
                        case 2:
                            a = (short)(circle[i].getCenterX());
                            if(a < 0)
                                a = 300;
                            circle[i].setCenterX(a-speed);
                            break;
                        case 3:
                            a = (short)(circle[i].getCenterY());
                            if(a > 300)
                                a = 0;
                            circle[i].setCenterY(a+speed);
                            break;
                        case 4:
                            a = (short)(circle[i].getCenterX());
                            if(a > 300)
                                a = 0;
                            circle[i].setCenterX(a+speed);
                            break;
                        default:
                            break;
                    }
                }

                group.getChildren().addAll(circle);
                /*
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                */
            }
        };


        //EventHandler to detect direction keys[WASD || ArroyKeys]
        EventHandler<KeyEvent> move = new EventHandler<KeyEvent>() {

            public void handle(KeyEvent e){

                short a = (short)(circle[0].getCenterX());
                short b = (short)(circle[0].getCenterY());

                switch(e.getCode()){
                    case W:
                    case UP:
                        if(direction != 3)
                            direction = 1;
                        break;

                    case A:
                    case LEFT:
                        if(direction != 4)
                            direction = 2;
                        break;

                    case S:
                    case DOWN:
                        if(direction != 1)
                            direction = 3;
                        break;

                    case D:
                    case RIGHT:
                        if(direction != 2)
                            direction = 4;
                        break;

                    case ESCAPE:
                        Platform.exit();
                        break;

                    default:
                        break;
                }

                //Push Direction & Coordinate to corresponding queues
                //whiled new key pressed;
                if(direction != direction_queue[direction_rear]){
                    pushDirection(direction);
                    pushCoordinate(a,b);
                }
            }
        };

        //Initialize animation;
        timer.start();


        scene.setOnKeyPressed(move);

        //Scene settings;
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setHeight(339);
        stage.setWidth(316);
        stage.setTitle("Snake");
        stage.show();
    }
}
