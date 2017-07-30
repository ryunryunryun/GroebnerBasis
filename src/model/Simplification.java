package model;

/**
 * This simplify a polynomial using given polynomial(s)
 * Using factory method
 * This uses "getHighest method"; needs to be sorted first
 */
public class Simplification {

    // simplify index-th term of f using g
    // returns f - f(index) / g.highestTerm * g
    public static Polynomial simplify (Polynomial f, Polynomial g, int index, Order order) {
        if (!Term.divisible(f.getPoly().get(index), g.getHighest())) {
            return new Polynomial(f);
        }
        Term temp = Term.divide(f.getPoly().get(index), g.getHighest());
        Polynomial r = Polynomial.subtract(f, Polynomial.multiply(g, temp));
        Sort.sort(r, order);

        return r;
    }

    // simplify f using G = {g1, g2, ... gn}
    public static Polynomial simplify (Polynomial f, PolynomialSet G, Order order) {
        Polynomial r = new Polynomial(f);
        int index = 0;
        boolean couldDivide;

        while (true) {
            couldDivide = false;

            for (Polynomial g : G.getPolys()) {
                if (Term.divisible(r.getPoly().get(index), g.getHighest())) {
                    r = simplify(r, g, index, order);
                    couldDivide = true;
                    index = 0;
                    break;
                }
            }

            if (Polynomial.isZero(r)) {
                break;
            }

            if (!couldDivide) {
                index++;
            }

            if (index == r.getPoly().size()) {
                break;
            }
        }
        return r;
    }
}
