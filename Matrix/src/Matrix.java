import java.util.Random;

public class Matrix {

    double[] data;
    int rows;
    int cols;


    Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows * cols];
    }

    Matrix(double[][] d) {
        int longest = 0;
        for (int i = 0; i < d.length; i++) {
            if (d[i].length > longest)
                longest = d[i].length;
        }

        this.rows = d.length;
        this.cols = longest;
        data = new double[rows * cols];

        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[i].length; j++) {
                data[i * cols + j] = d[i][j];
            }
            int j = d[i].length;
            while (j < longest) {
                data[i * cols + j] = 0;
                j++;
            }
        }
    }

    double[][] asArray() {
        double[][] d = new double[rows][cols];
        int x = 0;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                d[i][j] = this.data[i * cols + j];
            }
        return d;
    }

    double get(int r, int c) {
        return data[r * rows + c];
    }

    int getRows() {return this.rows;}

    int getCols() {return this.cols;}

    double[] getData() { return this.data; }

    void set(int r, int c, double value) {
        data[r * rows + c] = value;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("[\n");
        for (int i = 0; i < rows; i++) {
            buf.append("[");
            for (int j = 0; j < cols; j++)
                buf.append(String.format(" %.2f ", data[i * cols + j]));
            buf.append("]\n");
        }
        buf.append("]");
        return buf.toString();
    }

    void reshape(int newRows, int newCols) {
        if (rows * cols != newRows * newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d", rows, cols, newRows, newCols));

        this.rows = newRows;
        this.cols = newCols;
    }

    int[] shape() {
        int[] size = {rows, cols};
        return size;
    }

    Matrix add(Matrix m) {
        Matrix sum = new Matrix(rows, cols);
        if (cols != m.cols || rows != m.rows)
            throw new RuntimeException("Wrong matrix size");
        else {
            for (int i = 0; i < rows; i++)
                for (int j = 0; j < cols; j++)
                    sum.data[i * cols + j] = data[i * cols + j] + m.data[i * cols + j];
        }
        return sum;
    }

    Matrix sub(Matrix m) {
        Matrix sum = new Matrix(rows, cols);
        if (cols != m.cols || rows != m.rows)
            throw new RuntimeException("Wrong matrix size");
        else {
            for (int i = 0; i < rows; i++)
                for (int j = 0; j < cols; j++)
                    sum.data[i * cols + j] = data[i * cols + j] - m.data[i * cols + j];
            ;
        }
        return sum;
    }

    Matrix mul(Matrix m) {
        Matrix sum = new Matrix(rows, cols);
        if (cols != m.cols || rows != m.rows)
            throw new RuntimeException("Wrong matrix size");
        else {
            for (int i = 0; i < rows; i++)
                for (int j = 0; j < cols; j++)
                    sum.data[i * cols + j] = data[i * cols + j] * m.data[i * cols + j];
            ;
        }
        return sum;
    }

    Matrix div(Matrix m) {
        Matrix sum = new Matrix(rows, cols);
        if (cols != m.cols || rows != m.rows)
            throw new RuntimeException("Wrong matrix size");
        else {
            for (int i = 0; i < rows; i++)
                for (int j = 0; j < cols; j++)
                    sum.data[i * cols + j] = data[i * cols + j] / m.data[i * cols + j];
            ;
        }
        return sum;
    }

    Matrix add(double w) {
        Matrix sum = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                sum.data[i * cols + j] = data[i * cols + j] + w;
        }
        return sum;
    }

    Matrix sub(double w) {
        Matrix sum = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                sum.data[i * cols + j] = data[i * cols + j] - w;
        }
        return sum;
    }

    Matrix mul(double w) {
        Matrix sum = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                sum.data[i * cols + j] = data[i * cols + j] * w;
        }
        return sum;
    }

    Matrix div(double w) {
        Matrix sum = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                sum.data[i * cols + j] = data[i * cols + j] / w;
        }
        return sum;
    }


    Matrix dot(Matrix m) {
        Matrix sum = new Matrix(rows, cols);
        if (cols == m.rows) {
            for (int i = 0; i < rows; i++)
                for (int j = 0; j < cols; j++)
                    for (int k = 0; k < cols; k++)
                        sum.data[i * cols + j] += data[i * cols + k] * m.data[k * cols + j];
        }
        return sum;
    }

    double frobenius() {
        double a = 0;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                a += (data[i * cols + j]) * (data[i * cols + j]);
        return a;
    }

    public static Matrix random(int rows, int cols) {
        Matrix m = new Matrix(rows, cols);
        Random r = new Random();
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                m.set(i, j, r.nextDouble() * 10);

        return m;
    }

    public static Matrix eye(int n) {
        Matrix m = new Matrix(n, n);
        for (int i = 0; i < n; i++) {
            m.set(i, i, 1);
        }
        return m;
    }

    Matrix scratch(int xrow, int xcol) {
        Matrix m = new Matrix(this.getRows() - 1, this.getCols() - 1);
        int a = 0;
        for (int i = 0; i<getRows(); i++) {
            if (i != xrow){
                int b=0;
                for (int j = 0; j<getCols(); j++){
                    if (j != xcol){
                        m.set(a, b, this.get(i, j));
                        b++;}
                }
            } else a--;
            a++;
        }
        return m;
    }

    double determinant(){
        double det = 0;

        if(getRows() == 1)
            return get(0,0);

        if(getRows() == 2)
            return get(0,0) * get(1,1) - get(1,0) * get(0,1);

        for(int i=0; i<getRows(); i++)
            det += get(i,0) * Math.pow(-1, i) * scratch(i,0).determinant();
        return det;
    }

    Matrix transpose(){
        Matrix transposed = new Matrix(this.getRows(), this.getCols());
        for(int i=0; i<getRows(); i++)
            for(int j=0; j<getCols(); j++) {
                transposed.set(j, i, get(i, j));
            }
        return transposed;
    }

    Matrix inv(){
        Matrix result = new Matrix(this.getRows(), this.getCols());
        double det = determinant();
        if(det == 0)
            throw new RuntimeException("Determinant equals 0");
        else{
            for(int i=0;i<this.getRows();i++){
                for(int j=0;j<this.getCols();j++){
                    result.set(i,j,Math.pow(-1,i+j) * scratch(i,j).determinant());
                }
            }
        }
        result = result.transpose();
        result = result.mul(1/determinant());
        return result;
    }


    public static void main(String[] args) {

        //double[][] a = {{2,3},{1,4}};
        Matrix m = new Matrix(3, 3);
        Matrix n = new Matrix(3, 3);
        Matrix k = Matrix.random(2, 3);
        Matrix t = new Matrix(new double[][] {{1,2,3,6},{5,6,7,8}, {9,11,11,12}, {13,14,15,16}});

        m.set(0, 0, 2);
        m.set(0, 1, 3);
        m.set(1, 1, 4);
        m.set(1, 0, 3);

        n.set(0, 0, 1);
        n.set(1, 1, 3);
        n.set(0, 1, 0);
        n.set(1, 0, 2);

       // System.out.println(k);

        //System.out.println(m.add(n));
        System.out.println(t.determinant());

    }
}
