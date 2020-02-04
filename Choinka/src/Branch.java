import java.awt.*;
import java.awt.geom.AffineTransform;

public class Branch implements XmasShape {
    int x;
    int y;
    double scale;
    int[] x_t = {-80,-60,-40,-20,0,20,40,60,80,0};
    int[] y_t = {90,100,90,100,90,100,90,100,90,0};
    GradientPaint grad = new GradientPaint(0,this.y,new Color(7, 84, 0),0,this.y+80, new Color(127, 200, 59));

    Branch(int x, int y, double scale){
        this.x = x;
        this.y = y;
        this.scale = scale;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setPaint(grad);
        g2d.fillPolygon(x_t,y_t,x_t.length);
        g2d.drawPolygon(x_t,y_t,x_t.length);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }

}
