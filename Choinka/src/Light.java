import java.awt.*;
import java.util.ArrayList;

public class Light implements XmasShape {
    int x;
    int y;
    double scale;
    Color color;
    int[] x_t = {0,-15,-10,-20,-7,0,7,20,10,15};
    int[] y_t = {25,33,20,12,12,0,12,12,20,33};

    Light(int x, int y,double scale, Color color){
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.color = color;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.scale(this.scale,this.scale);
        g2d.translate(this.x,this.y);
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setPaint(color);
        g2d.fillPolygon(this.x_t,this.y_t,this.x_t.length);
        g2d.drawPolygon(this.x_t,this.y_t,this.x_t.length);
    }
}
