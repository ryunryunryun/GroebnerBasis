package model;

/**
 * This class represents "a" term of polynomial
 * e.g. 2 x^2 y^2 z^3
 * is expressed as model.Term(2, new int{2, 2, 3})
 */

public class Term {
    private Q coefficient;
    private int[] exp;

    // constructor
    public Term(Q coef, int[] exp) {
        this.coefficient = new Q(coef);
        this.exp = new int[exp.length];

        for (int i = 0; i < this.exp.length; i++) {
            if (exp[i] < 0) {
                throw new ArithmeticException("Does not accept Negative exponents.");
            }
            this.exp[i] = exp[i];
        }
    }

    // constructor for making deep copy
    public Term(Term source) {
        this.coefficient = new Q(source.getCoef());

        this.exp = new int[source.getExp().length];

        for (int i = 0; i < this.exp.length; i++) {
            this.exp[i] = source.getExp()[i];
        }
    }

    // getters
    public Q getCoef() {
        return this.coefficient;
    }

    public int[] getExp() {
        return this.exp;
    }

    // setters
    public void setCoef(Q coef) {
        this.coefficient = new Q(coef);
    }

    public void setCoef(int a, int b) {
        this.coefficient = new Q(a, b);
    }

    public void setExp(int[] exp) {
        if (this.exp.length != exp.length) {
            throw new ArithmeticException("Wrong amount of variable! (model.Term)");
        }

        for (int i = 0; i < this.exp.length; i++) {
            this.exp[i] = exp[i];
        }
    }

    public void setExp(int index, int value) {
        this.exp[index] = value;
    }

    // sum of all the exps
    public int getDim() {
        int dim = 0;

        for (int i = 0; i < this.exp.length; i++) {
            dim += this.exp[i];
        }

        return dim;
    }

    // throws dimension error if # of variables are different
    public static void varirableCheck (Term a, Term b) {
        if (a.getExp().length != b.getExp().length) {
            throw new ArithmeticException("Wrong amount of variable!");
        }
    }

    // create zero
    public static Term zero (int size) {
        int[] z = new int[size];

        return new Term(new Q(0, 1), z);
    }

    // create negation
    public static Term negate (Term a) {
        Term output = new Term(a);

        output.setCoef(Q.negate(output.getCoef()));

        return output;
    }

    // check whether each exponent is same
    public static boolean isSameDim (Term a, Term b) {
        varirableCheck(a, b);

        for (int i = 0; i < a.getExp().length; i++) {
            if (a.getExp()[i] != b.getExp()[i]) {
                return false;
            }
        }

        return true;
    }

    // add coef if exponents were same.
    // This method has to be called inside "if (isSameDim(a, b))"
    public static Term add(Term a, Term b) {
        varirableCheck(a, b);

        // this needs to be erased for performance.
        if (!isSameDim(a, b)) {
            throw new ArithmeticException("Did you really encapsulated?");
        }
        Term output = new Term(a);
        output.setCoef(Q.add(a.getCoef(), b.getCoef()));

        return output;
    }

    // subtract coef if exponents were same.
    // This method has to be called inside "if (isSameDim(a, b))"
    public static Term subtract(Term a, Term b) {
        varirableCheck(a, b);

        // this needs to be erased for performance.
        if (!isSameDim(a, b)) {
            throw new ArithmeticException("Did you really encapsulated?");
        }

        Term output = new Term(a);
        output.setCoef(Q.subtract(a.getCoef(), b.getCoef()));

        return output;
    }

    // multiplication of two terms
    public static Term multiply(Term a, Term b) {
        varirableCheck(a, b);

        Term output = new Term(a);

        int[] tempExp = new int[output.getExp().length];
        for (int i = 0; i < tempExp.length; i++) {
            tempExp[i] = output.getExp()[i] + b.getExp()[i];
        }

        output.setCoef(Q.multiply(output.getCoef(), b.getCoef()));
        output.setExp(tempExp);

        return output;
    }

    // check whether it is divisible or not
    public static boolean divisible(Term a, Term b) {
        varirableCheck(a, b);

        for (int i = 0; i < a.getExp().length; i++) {
            if (a.getExp()[i] < b.getExp()[i]) {
                return false;
            }
        }

        return true;
    }

    // division of two terms
    // be careful that coef is int.
    public static Term divide(Term a, Term b) {
        varirableCheck(a, b);

        Term output = new Term(a);
        int[] tempExp = new int[output.getExp().length];
        for (int i = 0; i < tempExp.length; i++) {
            tempExp[i] = output.getExp()[i] - b.getExp()[i];

            if (tempExp[i] < 0) {
                throw new ArithmeticException("Cannot divide!");
            }
        }

        output.setCoef(Q.divide(output.getCoef(), b.getCoef()));

        output.setExp(tempExp);
        return output;
    }

    // get lcm of two terms
    // lcm([2; 1, 2], [4; 2, 1]) = [1; 2, 2]
    // in this case, lcm of coefficient is neglected
    public static Term lcm(Term a, Term b) {
        varirableCheck(a, b);

        Term output = new Term(a);
        output.setCoef(new Q(1, 1));
        for (int i = 0; i < output.getExp().length; i++) {
            output.setExp(i, Math.max(output.getExp()[i], b.getExp()[i]));
        }

        return output;
    }

    public String forTeX() {
        String output = "" + this.coefficient;

        for (int i = 0; i < this.getExp().length; i++) {
            if (this.getExp()[i] == 0) {
                continue;
            }

            // add _ for tex
            output += "x_" + i + "^" + this.getExp()[i] + " ";
        }

        return output;
    }

    public String toString() {
        String output = "[" + this.coefficient + "; ";

        for (int i = 0; i < this.getExp().length - 1; i++) {
            output += this.exp[i] + ", ";
        }
        output += this.exp[this.getExp().length - 1] + "]";

        return output;
    }
}