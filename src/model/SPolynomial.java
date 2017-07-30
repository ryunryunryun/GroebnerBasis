package model;

/**
 * This class is for producing S - polynomial from given f, g
 *
 * Let L = lcm(f.highestTerm, g.highestTerm)
 * S(f, g) = L/f.highestTerm * f - L / g.highestterm * g
 */
public class SPolynomial {
    public static Polynomial spoly (Polynomial f, Polynomial g) {
        Term l = Term.lcm(f.getHighest(), g.getHighest());

        Polynomial output = Polynomial.multiply(f, Term.divide(l, f.getHighest()));
        Polynomial temp = Polynomial.multiply(g, Term.divide(l, g.getHighest()));

        output = Polynomial.subtract(output, temp);

        return output;
    }
}
