import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class DrawPanel extends JPanel {
    ArrayList<XmasShape> shapes = new ArrayList<>();

    DrawPanel(){
        setBackground(new Color(42, 93, 188));
        setOpaque(true);
        Random rand = new Random();

        for(int i=0; i<10; i++)
            for(int j =0; j<10; j++) {
                addObject(new Snowflake(rand.nextInt(3000),rand.nextInt(2000),0.5, new Color(210, 225, 254, 102)));
                addObject(new Snowflake(rand.nextInt(3000),rand.nextInt(2000),0.5, new Color(255, 255, 255, 131)));

            }

        /*for(int i=0; i<10; i++)
            for(int j =0; j<10; j++) {
                addObject(new Light(rand.nextInt(15000),rand.nextInt(10000),0.1,10, new Color(210, 225, 254, 102)));
                addObject(new Light(rand.nextInt(15000),rand.nextInt(10000),0.1,10, new Color(255, 255, 255, 131)));
        }*/

        addObject(new Bubble(-150,600,30,new Color(212, 228, 255), new Color(212, 228, 255)));
        addObject(new Wishes(10,50,2.5,new Font("TeXGyreChorus", Font.BOLD, 18), "Wesołych Świąt!",-1));

        int x = 550;
        int y = 67;
        addObject(new Tree( x,y,1,5));
        int k=100;
        for(int i=0; i<5; i++) {
            for (int j = 0; j <= i; j++) {
                int r = rand.nextInt(255);
                int g = rand.nextInt(255);
                int b = rand.nextInt(255);
                int y1 = y+60 + k*i + 5*j;
                addObject(new Bubble(x-40-j*60, y1, 0.5, new Color(r,g,b), new Color(r,g,b)));
                addObject(new Bubble(x+20+ j*60, y1, 0.5, new Color(r,g,b), new Color(r,g,b)));
            }
            k = k+10;
        }

        //addObject(new Light(1100,140,0.5,10, new Color(254, 199, 30, 136)));
        //addObject(new Light(1380,170,0.4,5, new Color(254, 199, 30)));
        addObject(new Snowflake(202,11,2.7,  new Color(254, 199, 30, 92)));
        addObject(new Light(365,30,1.5, new Color(254, 199, 30)));

        for(int i=1; i<=5; i++)
            for(int j =2; j<=6*i; j++) {
                addObject(new Light(1850+75*j-230*i, (int)(100*Math.log(j)/Math.log(2))+420*i,0.3, new Color(254, 237, 99, 183)));
            }
        /*for(int i=1; i<=5; i++)
            for(int j =2; j<=6*i; j++) {
                addObject(new Light(5500+240*j-720*i, 200+(int)(200*Math.log(j)/Math.log(2))+1300*i,0.1,5, new Color(254, 237, 99, 183)));
         }*/
    }

    public void addObject(XmasShape x){
        this.shapes.add(x);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(XmasShape s:shapes)
            s.draw((Graphics2D)g);

    }

}
