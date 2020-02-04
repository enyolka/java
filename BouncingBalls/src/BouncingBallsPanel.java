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
            this.vx = vx * (rand.nextBoolean() ? -1 : 1);
            this.vy = vy * (rand.nextBoolean() ? -1 : 1);
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
            this.x = this.x + (int) this.vx;
            this.y = this.y + (int) this.vy;
            if(this.x >= 650 || this.x <= 10)
                this.vx = vx*(-1);
            if(this.y <= 10 || this.y >= 600)
                this.vy = vy*(-1);
        }

        void collision(Ball other){
            if(Math.abs(this.x - other.x) <= 30 && Math.abs(this.y - other.y) <= 30) {
                this.vx = this.vx - (((this.vx-other.vx)*(this.x-other.x)-(this.vy-other.vy)*(this.y-other.y))/Math.max(Math.pow(this.x-other.x,2),Math.pow(this.y-other.y,2)))*(this.x-other.x);
                this.vy = this.vy- (((this.vx-other.vx)*(this.x-other.x)-(this.vy-other.vy)*(this.y-other.y))/Math.max(Math.pow(this.x-other.x,2),Math.pow(this.y-other.y,2)))*(this.y-other.y);
                other.vx = other.vx - (((other.vx-this.vx)*(other.x-this.x)-(other.vy-this.vy)*(other.y-this.y))/Math.max(Math.pow(other.x-this.x,2),Math.pow(other.y-this.y,2)))*(other.x-this.x);
                other.vy = other.vy - (((other.vx-this.vx)*(other.x-this.x)-(other.vy-this.vy)*(other.y-this.y))/Math.max(Math.pow(other.x-this.x,2),Math.pow(other.y-this.y,2)))*(other.y-this.y);
            }
        }
    }

    List<Ball> balls = new ArrayList<>();
    AnimationThread an = new AnimationThread();

    class AnimationThread extends Thread{
        boolean suspend = false;
        boolean stop = false;

        synchronized void wakeup(){
            suspend=false;
            notify();
        }
        void safeSuspend(){
            suspend=true;
        }

        public void run(){
            for(int i=0;!stop;i++){
                for(Ball b: balls){
                    b.move();
                    for(Ball b1: balls)
                       if(b1 != b) b.collision(b1);
                }

                repaint();
                synchronized(this){
                    try{
                        if(suspend){
                            System.out.println("suspending");
                            wait();
                        }
                    }
                    catch(InterruptedException ignored){}
                }
                try {
                    sleep(50);
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
            Ball b = new Ball(rand.nextInt(500), rand.nextInt(500),rand.nextInt(10), rand.nextInt(10));
            this.balls.add(b);
        }
        an.start();
        an.safeSuspend();
    }

    void onStart(){
        System.out.println("Start or resume animation thread");
        an.wakeup();
    }

    void onStop(){
        System.out.println("Suspend animation thread");
        an.safeSuspend();
    }

    void onPlus(){
        System.out.println("Add a ball");
        Random rand = new Random();
        Ball b = new Ball(rand.nextInt(500), rand.nextInt(500),rand.nextInt(10), rand.nextInt(10));
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