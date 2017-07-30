package model;

/**
 * This interface is for "order of term"
 * Look each class that implements this for more detail
 */
public interface Order {
    // a < b
    public boolean isGreater(Term a, Term b);
}
