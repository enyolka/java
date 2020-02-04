import java.awt.*;

public class Wishes implements XmasShape{
    int x;
    int y;
    double scale;
    Font font;
    String wish;
    int rotation;
    Color color = new Color(185, 7, 10);

    Wishes(int x, int y, double scale, Font font, String wish,int rotation){
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.font = font;
        this.wish = wish;
        this.rotation = rotation;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.scale(this.scale,this.scale);
        g2d.translate(this.x,this.y);
        g2d.rotate(this.rotation*Math.PI/12);
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setPaint(color);
        g2d.setFont(font);
        g2d.drawString(wish, x,y);
    }
}
