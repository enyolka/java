import org.junit.Before;

import static org.junit.Assert.*;

public class MatrixTest {

  /*  @org.junit.Before
    public void setUp(){
        Matrix t = new Matrix(new double[][] {{1,4,6},{2,3,7},{1,3,3}});
    }*/

    @org.junit.Test
    public void testMatrix(){
        Matrix t = new Matrix(2,3);
        assertEquals(2,t.rows);
        assertEquals(3,t.cols);
    }

     @org.junit.Test
    public void testMatrix2(){
        Matrix t = new Matrix(new double[][] {{1,4,6},{1,2},{7}});
        double[][] n = t.asArray();

        assertEquals(3,n.length);
        for(int i=0; i<3; i++)
            assertEquals(3,n[i].length);

        for(int i=0; i<3; i++)
            for(int j=0; j<i; j--) //??????????
                assertEquals(0,n[i][j], 0.0001);
    }

    @org.junit.Test
    public void asArray() {
        Matrix t = new Matrix(new double[][] {{1,4,6},{2,3,7}});
        double[][] tab = t.asArray();
        assertEquals(2, tab.length);
        assertEquals(3, tab[0].length);
        assertEquals(3, tab[1].length);
    }

    @org.junit.Test
    public void get() {
        Matrix t = new Matrix(new double[][] {{1,4,6},{2,3,7}});
        assertEquals(4,t.get(0,1), 0.0001);
    }

    @org.junit.Test
    public void set() {
        Matrix t = new Matrix(2,3);
        t.set(0,0,1);
        assertEquals(1,t.get(0,0), 0.00001);
    }

    @org.junit.Test
    public void testToString() {
        String s= "[[1.0,2.3,4.56], [12.3,  45, 21.8]]";
        s= s.replaceAll("(\\[|\\]|\\s)+","");
        String[] t = s.split("(,)+");
        for(String x:t){
            System.out.println(String.format("\'%s\'",x ));
        }

        double[]d=new double[t.length];
        for(int i=0;i<t.length;i++) {
            d[i] = Double.parseDouble(t[i]);
        }

        double arr[][]=new double[1][];
        arr[0]=d;

        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.println(arr[i][j]);
            }
        }
    }

    @org.junit.Test
    public void reshape() {
    }

    @org.junit.Test
    public void shape() {
    }

    @org.junit.Test
    public void add() {
    }

    @org.junit.Test
    public void sub() {
    }

    @org.junit.Test
    public void mul() {
    }

    @org.junit.Test
    public void div() {
    }

    @org.junit.Test
    public void testAdd() {
    }

    @org.junit.Test
    public void testSub() {
    }

    @org.junit.Test
    public void testMul() {
    }

    @org.junit.Test
    public void testDiv() {
    }

    @org.junit.Test
    public void dot() {
    }

    @org.junit.Test
    public void frobenius() {
    }

    @org.junit.Test
    public void random() {
    }

    @org.junit.Test
    public void eye() {
    }
}
