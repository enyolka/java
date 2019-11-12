public class Variable extends Node {

    String name;
    Double value;

    Variable(String name){
        this.name = name;
        setValue(0);
    }

    Variable(String name, double d){
        this.name = name;
        setValue(d);
    }

    public void setValue(double d){
        value = d;
    }

    public String getName() { return this.name; }

    @Override
    double evaluate() {
        return sign*value;
    }

    @Override
    Node diff(Variable var) {
        if(var.getName().equals(name)) return new Constant(sign);
        else return new Constant(0);
    }

    @Override
    boolean isZero(Variable var){
        return !var.getName().equals(name);
    }

    @Override
    public String toString() {
        String sgn=sign<0?"-":"";
        return sgn+name;
    }


}