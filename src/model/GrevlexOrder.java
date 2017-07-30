package model;

/**
 * This class is for graded reverse lexicographic order
 * a < b <=> dim(a) < dim(b) || (dim(a) == dim (b)) => reverse lexicographic
 */
public class GrevlexOrder implements Order{
    public boolean isGreater (Term a, Term b) {
        Term.isSameDim(a, b);

        if (a.getDim() < b.getDim()) {
            return true;
        }
        else if (a.getDim() > b.getDim()) {
            return false;
        }

        for (int i = 0; i < a.getExp().length; i++) {
            if (a.getExp()[i] != b.getExp()[i]) {
                return a.getExp()[i] > b.getExp()[i];
            }
        }

        return false;
    }
}
