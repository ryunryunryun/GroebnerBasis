package model;

import java.util.ArrayList;

/**
 * This class represents Groebner basis!
 */
public class GroebnerBasis {
    private ArrayList<Polynomial> gb;

    public GroebnerBasis(PolynomialSet ps) {
        this.gb = new ArrayList<Polynomial>();

        for (Polynomial p : ps.getPolys()) {
            this.gb.add(new Polynomial(p));
        }
    }

    public Polynomial getBasis(int i) {
        return new Polynomial(this.gb.get(i));
    }

    public ArrayList<Polynomial> getGB () {
        return this.gb;
    }

    // erase the ones that is divisible by the other
    public void minimalize () {
        int size;
        boolean isMinimal;
        boolean doesBreak;

        while (true) {
            size = this.gb.size();
            isMinimal = true;
            doesBreak = false;

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (i != j) {
                        if (Term.divisible(this.gb.get(i).getHighest(), this.gb.get(j).getHighest())) {
                            isMinimal = false;
                            doesBreak = true;
                            this.gb.remove(i);
                            break;
                        }
                    }
                }

                if (doesBreak) {
                    break;
                }
            }

            if (isMinimal) {
                break;
            }
        }
    }

    // simplify all g in G with G - {g}
    // and divide g by g.highestTerm's coef
    public void reduce (Order o) {
        ArrayList<Polynomial> gset = new ArrayList<Polynomial>();
        PolynomialSet g;
        Q coef;

        int size = this.gb.size();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    gset.add(this.gb.get(j));
                }
            }

            g = new PolynomialSet(gset);

            this.gb.set(i, Simplification.simplify(this.gb.get(i), g, o));
            gset.clear();

            // make highestDegree's coef 1
            coef = new Q(this.gb.get(i).getHighest().getCoef());

            for (int t = 0; t < this.gb.get(i).getPoly().size(); t++) {
                this.gb.get(i).getPoly().get(t).setCoef(Q.divide(this.gb.get(i).getPoly().get(t).getCoef(), coef));
            }
        }
    }

    public String toString () {
        String output = "{";
        int size = this.gb.size();

        for (int i = 0; i < size - 1; i++) {
            output += this.gb.get(i).toString() + ", ";
        }

        output += this.gb.get(size - 1) + "}";

        return output;
    }
}
