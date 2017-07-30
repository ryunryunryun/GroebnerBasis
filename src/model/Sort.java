package model;

/**
 * This sorts the polynomial with given order system
 */
public class Sort {
    // makes descending order; using bubble sort
    public static void sort (Polynomial poly, Order order) {
        int size = poly.getPoly().size();
        Term temp;

        for (int i = 0; i < size - 1; i++) {
            for (int j = size - 1; j > i; j--) {
                if (order.isGreater(poly.getPoly().get(i), poly.getPoly().get(j))) {
                    temp = new Term(poly.getPoly().get(i));
                    poly.getPoly().set(i, poly.getPoly().get(j));
                    poly.getPoly().set(j, temp);
                }
            }
        }
    }
}