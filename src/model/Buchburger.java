package model;

/**
 * This class represents model.Buchburger algorithm
 *
 * Let model.Ideal I = <G>, G = {g1, g2, ..., gn}
 * (1) Simplify S(g1, gj) with G. if this is non-zero, add to G
 * (2) Do this till every S is simplified to 0 under G
 *
 * G is Groebner basis of I
 */
public class Buchburger {
    public static GroebnerBasis buchburger (Ideal ideal, Order o) {
        PolynomialSet g = new PolynomialSet(ideal);
        sortAll(g, o);
        boolean isAllZero = true;
        int size;
        Polynomial s;
        Polynomial r;


        while (true) {
            size = g.getPolys().size();
            isAllZero = true;
            sortAll(g, o);

            for (int i = 0; i < size - 1; i++) {
                for (int j = i; j < size; j++) {

                    s = SPolynomial.spoly(g.getPolys().get(i), g.getPolys().get(j));
                    r = Simplification.simplify(s, g, o);

                    if (!Polynomial.isZero(r)) {
                        g.addPolynomial(new Polynomial(r));
                        isAllZero = false;
                    }
                }
            }



            if (isAllZero) {
                break;
            }
        }


        return new GroebnerBasis(g);
    }

    // sort all the polynomials in G
    private static void sortAll (PolynomialSet g, Order order) {
        for (Polynomial p : g.getPolys()) {
            Sort.sort(p, order);
        }
    }
}
