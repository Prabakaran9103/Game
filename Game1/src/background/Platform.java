package background;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.*;
public class Platform {
    public  Rectangle getBorder()
    {
        Rectangle border = new Rectangle(100,100,400,400);

        border.setStrokeWidth(20);
        border.setFill(Color.WHITE);
        return border;
    }
}
