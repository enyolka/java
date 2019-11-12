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

    // ???
    boolean simplify(String s){
        if(s.equals("1")) return true;
        return false;
    }

    public String toString(){
        StringBuilder b =  new StringBuilder();
        //StringJoiner joiner = new StringJoiner("*");

        for(Node n: args) {
            if (n instanceof Constant && n.evaluate() == 0)
                return b.toString();
        }

        if(sign<0) b.append("-");
        for(Node n: args){
            int argSign = n.getSign();
            boolean useBracket = false;
            if(argSign<0)
                useBracket = true;

            String argString = n.toString();
            if(useBracket)
                b.append("(");
            if(simplify(argString)) continue;
            if(args.indexOf(n) > 0) b.append("*");
            b.append(argString);
            if(useBracket)
                b.append(")");

        }
        return b.toString();
    }

}