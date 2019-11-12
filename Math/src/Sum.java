import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class Sum extends Node {

    List<Node> args = new ArrayList<>();

    Sum(){}

    Sum(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }

    Sum(Node ... ns) { args.addAll(Arrays.asList(ns)); }

    Sum add(Node n){
        args.add(n);
        return this;
    }

    Sum add(double c){
        args.add(new Constant(c));
        return this;
    }

    Sum add(double c, Node n) {
        Node mul = new Prod(c,n);
        args.add(mul);
        return this;
    }

    @Override
    double evaluate() {
        double result = 0;
        for(Node a: args)
            result += a.evaluate();
        return sign*result;
    }

    int getArgumentsCount(){return args.size();}

    @Override
    Node diff(Variable var) {
        Sum r = new Sum();
        for(Node n:args){
            if(!n.isZero(var))
                r.add(n.diff(var));
        }
        return r;
    }

    @Override
    boolean isZero(Variable var){
        for(Node node: args){
            if (!node.isZero(var)) {
                return false;
            }
        }
        return true;
    }

    public String toString(){
        StringBuilder b =  new StringBuilder();
        StringJoiner joiner = new StringJoiner(" + ");

        if(sign<0)
            b.append("-(");

        for(Node n: args){
            if(!n.toString().equals("0") && !n.toString().isEmpty())
                joiner.add(n.toString());
        }

        b.append(joiner.toString());
        if(sign<0)
            b.append(")");

        return b.toString();
    }

}