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
        data = new double[longest*d.length];

        for(int i = 0; i < d.length; i++){
            for(int j = 0; j < d[i].length; j++){
                data[i*cols+j] = d[i][j];
            }
            int j = d[i].length;
            while(j<longest)
                data[i*cols+j] = 0;
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
        return data[r*c];
    }

    void set(int r, int c, double value){
        this.rows = r;
        this.cols = c;
        this.data[r*c] = value;
    }

    public String toString(){
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for(int i = 0;i < rows ; i++){
            buf.append("[");
            for(int j = 0; j < cols; j++)
                buf.append(String.format("%f", data[i*cols + j]));
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
                    sum.data[i*cols+j] = data[i*cols+j] + m.data[i*cols+j];
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
                    sum.data[i*cols+j] = data[i*cols+j] - m.data[i*cols+j];
        }

        return sum;
    }

    Matrix add(double w){
        Matrix sum = new Matrix(rows, cols);
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                sum.data[i * cols + j] = data[i * cols + j] + w;
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
                sum.data[i * cols + j] = data[i * cols + j] * w;
        }
        return sum;
    }


    Matrix dot(Matrix m){
        Matrix sum = new Matrix(rows, cols);
        if(cols == m.rows){
            for(int i=0; i<rows; i++)
                for(int j=0; j<cols; j++)
                    for(int k=0; k<cols; k++)
                        sum.data[i*cols+j] = data[i*cols+k] * m.data[k*cols+j];
        }
        return sum;
    }

    public static void main(String[] args) {
        double[][]  d = {{2,2},{3,4}};
        Matrix m = new Matrix(5,5);
        System.out.println(m);
    }

}
