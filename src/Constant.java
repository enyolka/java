public class Constant extends Node {

    protected double value;

    Constant(double value){
        this.sign = value<0?-1:1;
        this.value = value<0?-value:value;
    }


    @Override
    protected double evaluate() {
        return sign*value;
    }

    @Override
    Node diff(Variable var) {
        return new Constant(0);
    }

    @Override
    boolean isZero(){
        return this.value==0;
    }

    @Override
    public String toString() {
        return (sign < 0 ? "-" : "") + NODE_FORMAT.format(value);
    }
}
