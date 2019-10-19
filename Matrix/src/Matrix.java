public class Matrix {
    double[]data;
    int rows;
    int cols;


    Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        data = new double[rows*cols];
    }

    Matrix(double[][] d){
        int longest = 0;
        for(int i = 0; i < d.length; i++) {
            if (d[i].length > longest)
                longest = d[i].length;
        }

        this.rows = d.length;
        this.cols = longest;
        data = new double[rows*cols];

        for(int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[i].length; j++) {
                data[i * rows + j] = d[i][j];
            }
            int j = d[i].length;
            while (j < longest) {
                data[i * rows + j] = 0;
                j++;
            }
        }
    }

    double[][] asArray(){
        double[][] d = new double[rows][cols];
        int x=0;
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < cols; j++) {
                d[i][j] = this.data[j];
                x++;
            }
        return d;
    }

    double get(int r, int c){
        return data[(r-1)*this.rows+(c-1)];
    }

    void set(int r, int c, double value){
        this.rows = r;
        this.cols = c;
        this.data[(r-1)*this.rows+(c-1)] = value;
    }

    public String toString(){
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for(int i = 0;i < rows ; i++){
            buf.append("[");
            for(int j = 0; j < cols; j++)
                buf.append(String.format(" %f ", data[i*rows + j]));
            buf.append("]\n");
        }
        buf.append("]");
        return buf.toString();
    }

    void reshape(int newRows,int newCols){
        if(rows*cols != newRows*newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d",rows,cols,newRows,newCols));

        this.rows = newRows;
        this.cols = newCols;
    }

    int[] shape(){
        int[] size = {rows,cols};
        return size;
    }

    Matrix add(Matrix m){
        Matrix sum = new Matrix(rows, cols);
        if(cols != m.cols  || rows != m.rows)
            throw new RuntimeException("Wrong matrix size");
        else{
            for(int i = 0; i < rows; i++)
                for(int j = 0; j < cols; j++)
                    sum.data[i*rows+j] = data[i*rows+j] + m.data[i*rows+j];
        }
        return sum;
    }

    Matrix sub(Matrix m){
        Matrix sum = new Matrix(rows, cols);
        if(cols != m.cols  || rows != m.rows)
            throw new RuntimeException("Wrong matrix size");
        else{
            for(int i = 0; i < rows; i++)
                for(int j = 0; j < cols; j++)
                    sum.data[i*rows+j] = data[i*rows+j] + m.data[i*rows+j];;
        }
        return sum;
    }

    Matrix mul(Matrix m){
        Matrix sum = new Matrix(rows, cols);
        if(cols != m.cols  || rows != m.rows)
            throw new RuntimeException("Wrong matrix size");
        else{
            for(int i = 0; i < rows; i++)
                for(int j = 0; j < cols; j++)
                    sum.data[i*rows+j] = data[i*rows+j] + m.data[i*rows+j];;
        }
        return sum;
    }

    Matrix div(Matrix m){
        Matrix sum = new Matrix(rows, cols);
        if(cols != m.cols  || rows != m.rows)
            throw new RuntimeException("Wrong matrix size");
        else{
            for(int i = 0; i < rows; i++)
                for(int j = 0; j < cols; j++)
                    sum.data[i*rows+j] = data[i*rows+j] + m.data[i*rows+j];;
        }
        return sum;
    }

    Matrix add(double w){
        Matrix sum = new Matrix(rows, cols);
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                sum.data[i * rows + j] = data[i * rows + j] + w;
        }
        return sum;
    }

    Matrix sub(double w){
        Matrix sum = new Matrix(rows, cols);
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                sum.data[i * cols + j] = data[i * cols + j] - w;
        }
        return sum;
    }

    Matrix mul(double w){
        Matrix sum = new Matrix(rows, cols);
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                sum.data[i * rows + j] = data[i * rows + j] * w;
        }
        return sum;
    }

    Matrix div(double w){
        Matrix sum = new Matrix(rows, cols);
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                sum.data[i * rows + j] = data[i * rows + j] / w;
        }
        return sum;
    }


    Matrix dot(Matrix m){
        Matrix sum = new Matrix(rows, cols);
        if(cols == m.rows){
            for(int i=0; i<rows; i++)
                for(int j=0; j<cols; j++)
                    for(int k=0; k<cols; k++)
                        sum.data[i*rows+j] = data[i*rows+k] * m.data[k*rows+j];
        }
        return sum;
    }

    double frobenius(){
        double a = 0;
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < cols; j++)
                a += (data[i*rows+j])* (data[i*rows+j]);
        return a;
    }

    public static void main(String[] args) {

        double[][] a = {{2,3},{1,4}};
        Matrix m = new Matrix(2,2);
        Matrix n = new Matrix(2,2);
        Matrix k = new Matrix(a);

        m.set(1,1,2);
        m.set(1,2,3);
        m.set(2,1,4);
        m.set(2,2,3);

        n.set(1,1,1);
        n.set(1,2,3);
        n.set(2,1,0);
        n.set(2,2,2);

        System.out.println(m.add(n));

    }

}

