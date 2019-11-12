import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

abstract public class Node {
    int sign=1;

    static final DecimalFormat NODE_FORMAT =
            new DecimalFormat("0.#####", new DecimalFormatSymbols(Locale.US));

    Node minus(){
        sign = -1;
        return this;
    }

    Node plus(){
        sign = 1;
        return this;
    }

    int getSign(){
        return sign;
    }

    abstract double evaluate();

    abstract Node diff(Variable var);

    abstract boolean isZero();

    public String toString(){return "";}

    int getArgumentsCount(){
        return 0;
    }

}
