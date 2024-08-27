import javafx.scene.shape.Circle;

class ChangeRadius{

    static public void change(Circle circle){
        circle.setRadius(circle.getRadius()+10);
    }
}
public class text{
    public static void main(String arg[]){
        Circle circle = new Circle(45,45,10);
        ChangeRadius.change(circle);
        System.out.print(circle.getRadius());
    }
}