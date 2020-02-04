import java.awt.*;
import java.util.ArrayList;

public class Tree implements XmasShape{
    ArrayList<Branch> branches = new ArrayList<>();
    int x;
    int y;
    double scale;
    int cons;

    Tree(int x, int y,double scale,int cons){
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.cons = cons;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.scale(this.scale,this.scale);
    }

    @Override
    public void render(Graphics2D g2d) {
        int yy = this.y;
        for(int i=cons; i>=1; i--) {
            this.branches.add(new Branch(this.x, i * yy, i * this.scale));
            yy = yy-1;
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        XmasShape.super.draw(g2d);
        for(Branch b: branches)
            b.draw(g2d);
    }
}
