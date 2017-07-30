package model;

/**
 * This represents rational number
 */
public class Q {
    private int num;
    private int denom;

    public Q (int num, int denom) {
        if (denom == 0) {
            throw new ArithmeticException("Zero divide");
        }

        if (num < 0 && denom < 0) {
            this.num = -num;
            this.denom = - denom;
        }
        else if (num > 0 && denom < 0) {
            this.num = -num;
            this.denom = - denom;
        }
        else {
            this.num = num;
            this.denom = denom;
        }
        reduce();
    }

    public Q (int integer) {
        this.num = integer;
        this.denom = 1;
    }

    // deep copy
    public Q (Q a) {
        this.num = a.num;
        this.denom = a.denom;
    }

    // getter
    public int getNum() {
        return this.num;
    }

    public int getDenom() {
        return this.denom;
    }

    public boolean isZero () {
        return this.num == 0;
    }

    // additive inverse
    public static Q negate (Q a) {
        return new Q(-a.num, a.denom);
    }

    // multiplicative inverse
    public static Q inverse (Q a) {
        if (a.num == 0) {
            throw new ArithmeticException("Zero divide");
        }

        return new Q(a.denom, a.num);
    }

    // add, reduced
    public static Q add(Q a, Q b) {
        Q output = new Q(a.num * b.denom + b.num * a.denom, a.denom * b.denom);

        return output;
    }

    // subtract, reduced
    public static Q subtract(Q a, Q b) {
        Q output = new Q(a.num * b.denom - b.num * a.denom, a.denom * b.denom);

        return output;
    }

    // multiply, reduced
    public static Q multiply(Q a, Q b) {
        Q output = new Q(a.num * b.num, a.denom * b.denom);

        return output;
    }

    // divide, reduced
    public static Q divide(Q a, Q b) {
        //System.out.print("in with a: " + a + ", b: " + b);
        Q output = new Q(a.num * b.denom, a.denom * b.num);
        //System.out.println("out");

        return output;
    }

    // reduce
    private void reduce() {
        int gcd = gcd(this.num, this.denom);
        this.num /= gcd;
        this.denom /= gcd;
    }

    // gcd
    private int gcd (int a, int b) {
        if (a < 0 || b < 0) {
            return gcd(Math.abs(a), Math.abs(b));
        }

        // be careful that a = b = 0 does not happen in this class
        if (Math.min(a, b) == 0) {
            return Math.max(a, b);
        }

        if (a % b == 0) {
            return b;
        }
        else {
            return gcd(b, a % b);
        }
    }

    public String toString () {

        if (this.denom == 1) {
            return "" + this.num;
        }

        return this.num + "/" + this.denom;
    }
}
