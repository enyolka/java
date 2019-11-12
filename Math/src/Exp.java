public class Exp extends Node{
    Node e;

    Exp(Node e){ this.e = e;}
    Exp(double d){ this.e = new Constant(d);}

    @Override
    public double evaluate() {
        return Math.exp(e.evaluate());
    }

    @Override
    Node diff(Variable var){
        return new Prod(this, e.diff(var));
    }

    @Override
    boolean isZero(Variable var){ return false; }

    @Override
    public String toString(){
        String sgn = sign < 0 ? "-" : "";
        return sgn + "e^" + e.toString();
    }

    public static void main(String[] args) {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2,new Power(x,3))
                .add(new Exp(x))
                .add(-2,x)
                .add(7);
        System.out.print("exp= ");
        System.out.println(exp.toString());

        Node d = exp.diff(x);
        System.out.print("d(exp)/dx= ");
        System.out.println(d.toString());
    }
}
