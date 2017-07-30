package model;

import java.util.ArrayList;

/**
 * This class represents f \in model.Q[x1, ... xn]
 * This class needs to be "sorted" using model.Order *
 */
public class Polynomial {
    private ArrayList<Term> poly;

    // normal constructor
    public Polynomial(ArrayList<Term> poly) {
        int size = poly.size();

        this.poly = new ArrayList<Term>();


        for (int i = 0; i < size; i++) {
            this.poly.add(new Term(poly.get(i)));
        }

        this.simplify();
    }

    // deep copy (clone)
    public Polynomial(Polynomial polynomial) {
        ArrayList<Term> tempPoly = new ArrayList<>();
        int size = polynomial.getPoly().size();

        for (int i = 0; i < size; i++) {
            tempPoly.add(new Term(polynomial.getPoly().get(i)));
        }

        this.poly = tempPoly;
   }

    // getter
    public ArrayList<Term> getPoly() {
        return this.poly;
    }

    public Term getHighest() {
        return this.poly.get(0);
    }

    // check whether the poly is 0 or not
    public static boolean isZero (Polynomial p) {
        if (p.getPoly().size() == 1 && p.getHighest().getCoef().isZero()) {
            return true;
        }
        return false;
    }

    // addition
    // this simplyfies automatically
    public static Polynomial add(Polynomial a, Polynomial b) {
        Polynomial output = new Polynomial(a);

        for (Term t : b.getPoly()) {
            output.poly.add(new Term(t));
        }

        output.simplify();

        return output;
    }

    // adding term
    // this simplyfies automatically
    public static Polynomial add(Polynomial a, Term b) {
        Polynomial output = new Polynomial(a);

        output.poly.add(new Term(b));
        output.simplify();

        return output;
    }

    // subtraction
    // this simplyfies automatically
    public static Polynomial subtract(Polynomial a, Polynomial b) {
        Polynomial output = new Polynomial(a);

        for (Term t : b.getPoly()) {
            output.poly.add(Term.negate(t));
        }

        output.simplify();

        return output;
    }

    // subtraticting term
    // this simplyfies automatically
    public static Polynomial subtract(Polynomial a, Term b) {
        Polynomial output = new Polynomial(a);

        output.getPoly().add(Term.negate(b));
        output.simplify();

        return output;
    }

    // multiplication
    // this simplyfies automatically
    public static Polynomial multiply(Polynomial a, Polynomial b) {
        ArrayList<Term> outputTerm = new ArrayList<Term>();


        for (Term at : a.getPoly()) {
            for (Term bt : b.getPoly()) {
                outputTerm.add(Term.multiply(at, bt));
            }
        }

        Polynomial output = new Polynomial(outputTerm);
        output.simplify();

        return output;
    }

    // multiplying by term
    // this simplyfies automatically
    public static Polynomial multiply(Polynomial a, Term b) {
        Polynomial output = new Polynomial(a);
        int size = output.getPoly().size();

        for (int i = 0; i < size; i++) {
            output.getPoly().set(i, Term.multiply(output.getPoly().get(i), b));
        }

        output.simplify();

        return output;
    }

    // simplify (add all the term with same degree, and remove 0s)
    public void simplify () {
        ArrayList<Term> temp = new ArrayList<Term>();
        int size = this.poly.size();
        int dim = this.getHighest().getExp().length;

        temp.add(Term.zero(dim));

        boolean isIn = false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < temp.size(); j++) {
                if (Term.isSameDim(this.poly.get(i), temp.get(j))) {
                    temp.set(j, Term.add(this.poly.get(i), temp.get(j)));
                    isIn = true;
                    break;
                }
            }

            if (!isIn) {
                temp.add(new Term(this.poly.get(i)));
            }

            isIn = false;
        }

        ArrayList<Term> temp2 = new ArrayList<Term>();

        // remove 0 term(s)
        for (int i = 0; i < temp.size(); i++) {
            if (!temp.get(i).getCoef().isZero()) {
                temp2.add(temp.get(i));
            }
        }

        // if pure 0, then make this 0
        if (temp2.size() == 0) {
            temp2.add(Term.zero(dim));
        }

        this.poly = temp2;
    }

    public String toString () {
        int size = this.poly.size();
        String output = "";

        for (int i = 0; i < size - 1; i++) {
            output += this.poly.get(i).toString() + " + ";
        }

        output += this.poly.get(size - 1).toString();

        return output;
    }
}