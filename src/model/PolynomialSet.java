package model;

import java.util.ArrayList;

/**
 * This class stores polynomials
 * Essentially this class is same as model.Ideal class
 */
public class PolynomialSet {
    private ArrayList<Polynomial> polys;

    public PolynomialSet(Ideal ideal) {
        polys = new ArrayList<Polynomial>();

        for (Polynomial p : ideal.getGen()) {
            polys.add(new Polynomial(p));
        }
    }

    public PolynomialSet(ArrayList<Polynomial> polys) {
        this.polys = new ArrayList<Polynomial>();

        for (Polynomial p : polys) {
            this.polys.add(new Polynomial(p));
        }
    }

    // getter
    public ArrayList<Polynomial> getPolys () {
        return this.polys;
    }

    // add polynomial to this set
    public void addPolynomial (Polynomial p) {
        this.polys.add(new Polynomial(p));
    }

}
