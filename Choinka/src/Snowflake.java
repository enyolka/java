import java.awt.*;
import java.awt.geom.AffineTransform;

public class Snowflake implements XmasShape {
    int x;
    int y;
    double scale;
    Color color;
    int[] x_t = {0,-3,-12,-8,-15,-8,-12,-3,0,3,12,8,15,8,12,3};
    int[] y_t = {30,20,26,17,15,12,4,10,0,10,4,12,15,17,26,20};

    Snowflake(int x, int y,double scale, Color color){
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
    }
}
