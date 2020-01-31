import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BouncingBallsPanel extends JPanel {

    static class Ball {
        int x;
        int y;
        double vx;
        double vy;
        Color color;
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);

        Ball(int x, int y, double vx, double vy) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
            this.color = new Color(this.r, this.g, this.b);
        }

        public void render(Graphics2D g2d) {
            g2d.setColor(this.color);
            g2d.fillOval(0, 0, 30, 30);
        }

        public void transform(Graphics2D g2d) {
            g2d.translate(this.x, this.y);
        }

        void draw(Graphics2D g2d) {
            AffineTransform saveAT = g2d.getTransform();
            transform(g2d);
            render(g2d);
            g2d.setTransform(saveAT);
        }

        void move() {
            this.x = this.x + rand.nextInt(50)* (rand.nextBoolean() ? -1 : 1);
            this.y = this.y + rand.nextInt(50)* (rand.nextBoolean() ? -1 : 1);
        }
    }

    List<Ball> balls = new ArrayList<>();

    class AnimationThread extends Thread{
        public void run(){
            for(;;){
                for(Ball b: balls)
                    b.move();

                //wykonaj odbicia od ściany
                repaint();
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    BouncingBallsPanel(){
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
        Random rand = new Random();
        for(int i=0 ; i<rand.nextInt(10)+3; i++) {
            Ball b = new Ball(rand.nextInt(700), rand.nextInt(700),rand.nextInt(30), rand.nextInt(30));
            this.balls.add(b);
        }

    }

    void onStart(){
        System.out.println("Start or resume animation thread");
        AnimationThread an = new AnimationThread();
        an.start();
    }

    void onStop(){
        System.out.println("Suspend animation thread");
    }

    void onPlus(){
        System.out.println("Add a ball");
        Random rand = new Random();
        Ball b = new Ball(rand.nextInt(1000), rand.nextInt(1000),rand.nextInt(30), rand.nextInt(30));
        this.balls.add(b);
    }

    void onMinus(){
        System.out.println("Remove a ball");
        Random rand = new Random();
        this.balls.remove(rand.nextInt(this.balls.size()));
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(Ball b: balls)
            b.draw((Graphics2D)g);

    }
}