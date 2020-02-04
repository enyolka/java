import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {

    @Test
    public void testMatrix(){
        Matrix t = new Matrix(2,3);
        assertEquals(2,t.rows);
        assertEquals(3,t.cols);
    }

    @Test
    public void testMatrix2(){
        Matrix t = new Matrix(new double[][] {{1,4,6},{1,2},{7}});
        double[][] n = t.asArray();

        assertEquals(3,n.length);
        for(int i=0; i<3; i++)
            assertEquals(3,n[i].length);

        for(int i=0; i<3; i++)
            for(int j=3; j<i; j--)
                assertEquals(0,n[i][j], 0.0001);
    }

    @Test
    public void testAsArray() {
        Matrix t = new Matrix(new double[][] {{1,4,6},{2,3,7}});
        double[][] tab = t.asArray();
        assertEquals(2, tab.length);
        assertEquals(3, tab[0].length);
        assertEquals(3, tab[1].length);
    }

    @Test
    public void testGet() {
        Matrix t = new Matrix(new double[][] {{1,4,6},{2,3,7}});
        assertEquals(4,t.get(0,1), 0.0001);
    }

    @Test
    public void testSet() {
        Matrix t = new Matrix(2,3);
        t.set(0,0,1);
        assertEquals(1,t.get(0,0), 0.00001);
    }

    /*@Test
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
    }*/

    @Test
    public void testToString(){
        int count_space = 0;
        int count_left = 0;
        int count_right = 0;
        Matrix m = new Matrix(new double[][] {{4,6,1},{3,7,9}});
        String s = m.toString();
        for(int i=0; i < s.length(); i++) {
            if (s.charAt(i) == ' ')
                count_space++;
            else if (s.charAt(i) == '[')
                count_left++;
            else if (s.charAt(i) == ']')
                count_right++;
        }

        assertEquals(count_space,12);
        assertEquals(count_left,3);
        assertEquals(count_right,3);
    }


    @Test(expected = RuntimeException.class)
    public void testReshape() {
        Matrix t = new Matrix(2,3);
        t.reshape(4,2);
    }

    @Test
    public void testShape() {
        Matrix t = new Matrix(2,4);
        int[] x = t.shape();
        assertEquals(2,x[0] );
        assertEquals(4,x[1] );
    }

    @Test
    public void testAdd() {
        Matrix m1 = new Matrix(new double[][] {{4,6},{3,7}});
        Matrix m2 = m1.mul(-1);
        Matrix m3 = m1.add(m2);
        for(int i=0; i<2; i++)
            for(int j=0; j<2; j++)
                assertEquals(0, m3.get(i,j), 0.0001);
    }

    @Test
    public void testSub() {
        Matrix m1 = new Matrix(new double[][] {{4,6},{3,7}});
        Matrix m2 = m1.sub(m1);
        for(int i=0; i<2; i++)
            for(int j=0; j<2; j++)
                assertEquals(0, m2.get(i,j), 0.0001);
    }

    @Test
    public void testMul() {
        Matrix m1 = new Matrix(new double[][] {{4,6},{3,7}});
        Matrix m2 = new Matrix(new double[][] {{2,2},{2,2}});
        Matrix m3 = m1.mul(m2);
        for(int i=0; i<2; i++)
            for(int j=0; j<2; j++)
                assertEquals(m1.get(i,j)*2, m3.get(i,j), 0.0001);
    }

    @Test(expected = RuntimeException.class)
    public void testDiv() {
        Matrix m1 = new Matrix(new double[][] {{4,6},{16,128}});
        Matrix m2 = new Matrix(new double[][] {{2,2,3},{2,2,3}});
        Matrix m3 = m1.div(m2);
    }

    @Test
    public void testAddCons() {
        Matrix m1 = new Matrix(new double[][] {{2,2},{2,2}});
        double x = -2;
        Matrix m2 = m1.add(x);
        for(int i=0; i<2; i++)
            for(int j=0; j<2; j++)
                assertEquals(0, m2.get(i,j), 0.0001);
    }

    @Test
    public void testSubCons() {
        Matrix m1 = new Matrix(new double[][] {{2,2},{2,2}});
        double x = 2;
        Matrix m2 = m1.sub(x);
        for(int i=0; i<2; i++)
            for(int j=0; j<2; j++)
                assertEquals(0, m2.get(i,j), 0.0001);
    }

    @Test
    public void testMulCons() {
        Matrix m1 = new Matrix(new double[][] {{2,2},{4,4}});
        double x = 2;
        Matrix m2 = m1.mul(x);
        for(int i=1; i<=2; i++)
            for(int j=0; j<2; j++)
                assertEquals(4*i, m2.get(i-1,j), 0.0001);
    }

    @Test
    public void testDivCons() {
        Matrix m1 = new Matrix(new double[][] {{2,2},{4,4}});
        double x = 2;
        Matrix m2 = m1.div(x);
        for(int i=1; i<=2; i++)
            for(int j=0; j<2; j++)
                assertEquals(i, m2.get(i-1,j), 0.0001);
    }

    @Test
    public void dot() {
        Matrix m1 = new Matrix(new double[][] {{4,6},{16,128}});
        Matrix m2 = Matrix.eye(2);
        Matrix m3 = m1.dot(m2);
        for(int i=0; i<2; i++)
            for(int j=0; j<2; j++)
                assertEquals(m1.get(i,j), m3.get(i,j), 0.0001);
    }

    @Test
    public void testFrobenius() {
        Matrix m = new Matrix(new double[][] {{1,2},{3,4}});
        assertEquals(30,m.frobenius(), 0.0001);
    }

    @Test
    public void testRandom() {
        Matrix m = Matrix.random(2,3);
        assertEquals(2,m.rows);
        assertEquals(3,m.cols);
    }

    @Test
    public void testEye() {
        Matrix m = Matrix.eye(3);
        assertEquals(3, m.frobenius(), 0.0001);
    }

    @Test
    public void testDeterminant(){
        Matrix m = new Matrix(new double[][] {{1,2,3,6},{5,6,7,8}, {9,11,11,12}, {13,14,15,16}});
        assertEquals(32, m.determinant(), 0.0001);
    }

    @Test
    public void testInv(){
        Matrix m = new Matrix(new double[][] {{1,0,2},{2,1,0}, {1,0,1}});
        Matrix m1 = m.inv();
        Matrix m2 = new Matrix(new double[][] {{-1,0,2},{2,1,-4},{1,0,-1}});
        assertArrayEquals(m2.getData(),m1.getData(), 0.0001);
    }
}