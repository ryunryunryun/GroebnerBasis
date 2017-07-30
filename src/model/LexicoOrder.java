package model;

/**
 * This class is for Lexicographic order
 * a < b <=> There exists N ≥ 0 s.t. ai < bi (i < N), aN < bN
 */
public class LexicoOrder implements Order {
    public boolean isGreater(Term a, Term b) {
        Term.isSameDim(a, b);

        for (int i = 0; i < a.getExp().length; i++) {
            if (a.getExp()[i] != b.getExp()[i]) {
                return a.getExp()[i] < b.getExp()[i];
            }
        }

        return false;
    }
}
