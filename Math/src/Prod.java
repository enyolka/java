import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class Prod extends Node {
    List<Node> args = new ArrayList<>();

    Prod(){}

    Prod(Node n1){
        args.add(n1);
    }

    Prod(double c){
        args.add(new Constant(c));
    }

    Prod(Node ... ns) {
        args.addAll(Arrays.asList(ns));
    }

    Prod(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }

    Prod(double c, Node n){
        args.add(new Constant(c));
        args.add(n);
    }

    Prod mul(Node n){
        args.add(n);
        return this;
    }

    Prod mul(double c){
        args.add(new Constant(c));
        return this;
    }

    @Override
    double evaluate() {
        double result = 1;
        for(Node a: args)
            result *= a.evaluate();
        return sign * result;
    }

    int getArgumentsCount(){
        return args.size();
    }

    @Override
    Node diff(Variable var) {
        Sum r = new Sum();

        for(int i=0;i<args.size();i++){
            Prod m = new Prod();

            for(int j=0;j<args.size();j++){
                Node f = args.get(j);
                if(j==i)
                    m.mul(f.diff(var));
                else m.mul(f);
            }
            if(!m.isZero(var))
                r.add(m);
        }
        return r;
    }

    @Override
    boolean isZero(Variable var){
        for(Node n: args){
            if(n instanceof Constant && n.evaluate() == 0)
                return  true;
        }
        return false;
    }

    Constant simplify(){
        Constant sum = new Constant(1);
        for(Node n: args){
            if(!(n instanceof Constant))
                continue;
            else{
                sum.value = sum.value * ((Constant) n).value;
                if(n.getSign()<0) sum.value = sum.value * (-1);
            }
        }
        return sum;
    }

    public String toString(){
        StringBuilder b =  new StringBuilder();
        StringJoiner joiner = new StringJoiner("*");

        for(Node n: args) {
            if (n instanceof Constant && n.evaluate() == 0)
                return b.toString();
        }

        if(sign<0) b.append("-");

        Constant sum = simplify();
        String sum_str = sum.toString();
        if(sum.value < 0)
            sum_str = "(" + sum_str + ")";
        joiner.add(sum_str);

        for(Node n: args){
            if(!(n instanceof Constant)){
                String argString = n.toString();
                if(n.getSign()<0)
                    argString = "(" + argString + ")";
                if(!argString.equals("0") && !argString.isEmpty())
                    joiner.add(argString);
            }
        }

        b.append(joiner.toString());
        if(sign<0)
            b.append(")");

        return b.toString();
    }

}