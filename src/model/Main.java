package model;

import java.util.ArrayList;

/*
 * This class is just a main
 */
public class Main  {
    public static void main (String[] args) {
        Main m = new Main();

        //m.TermTest();
        //m.PolynomialTest();
        //m.OrderTest();
        //m.SortTest();
        //m.SimplificationTest();
        //m.SimplificationTest2();
        //m.SPolynomialTest();
        //m.BuchburgerTest();
        //m.BuchburgerTest2();
        //m.BuchburgerTest3();
        //m.QTest();
        //m.InterpreterTest();
        //m.InterpreterTest2();
        //m.InterpreterTest3();
        //m.InterpreterTest4();
        //m.InterpreterTest5();
        //m.InterpreterTest6();
        m.InterpreterTest7();
        //m.InterpreterTest8();
    }

    public void InterpreterTest8 () {
        String var = "x, y, z";

        //System.out.println(Interpreter.)
    }

    public void InterpreterTest7 () {
        //Order o = new LexicoOrder();
        Order o = new GrlexOrder();
        //Order o = new GrevlexOrder();
        String var = "x, y";
        /*
        String id = "x + y\n" +
                "y + 4z^2 x - 2\n" +
                "-x + y \n";
        */
        String id = "x + 2\n" +
                "x + 3";

        VariableList varList = Interpreter.interpretVarList(var);
        Ideal ideal = Interpreter.interpretIdeal(id, varList);

        GroebnerBasis gb = Buchburger.buchburger(ideal, o);
        gb.minimalize();
        gb.reduce(o);

        System.out.println(gb);
        System.out.println(Interpreter.verbalizeGroebnerBasis(gb, varList));
    }

    public void InterpreterTest6() {
        //Order o = new LexicoOrder();
        //Order o = new GrlexOrder();
        Order o = new GrevlexOrder();
        String var = "x, y, z";
        String id = "x^2 - 2\n" +
                "y^2 - 3\n" +
                "z - y - x\n" +
                "";
        /*
        String var = "ポン酢, p進, グレブナー基底";
        String id = "ポン酢^2 - 2\n" +
                "p進^2 - 3\n" +
                "グレブナー基底 - p進 - ポン酢";
        */

        VariableList varList = Interpreter.interpretVarList(var);
        Ideal ideal = Interpreter.interpretIdeal(id, varList);

        GroebnerBasis gb = Buchburger.buchburger(ideal, o);
        gb.minimalize();
        gb.reduce(o);

        System.out.println(gb);
        System.out.println(Interpreter.verbalizeGroebnerBasis(gb, varList));
    }

    public void InterpreterTest5() {
        String id = "x^2y + z\n" +
                "1/2x^4z\n" +
                "x^4";
        String var = "x, y, z, ポン酢";


        VariableList varList = Interpreter.interpretVarList(var);
        Ideal ideal = Interpreter.interpretIdeal(id, varList);

        System.out.println(ideal);
    }

    public void InterpreterTest4() {
        String var = "x, y, z, ポン酢";
        VariableList v = Interpreter.interpretVarList(var);

        for (int i = 0; i < v.getSize(); i++) {
            System.out.println (i + "." + v.getVar(i) + ".");
        }
    }

    public void InterpreterTest3() {
        String[] var = {"x", "y", "z"};
        VariableList vList = new VariableList(var);

        System.out.println("1. " + Interpreter.interpretPolynomial("1/4 +  x", vList));
    }
    public void InterpreterTest2() {
        String[] var = {"ポン酢", "グレブナー基底"};
        VariableList vList = new VariableList(var);
        System.out.println(Interpreter.interpretTerm("2ポン酢^2グレブナー基底^1", vList));
    }

    public void InterpreterTest() {
        String[] var = {"x", "y", "z"};
        VariableList vList = new VariableList(var);

        //System.out.println("1. " + model.Interpreter.interpretTerm("x", vList) + ", Real: [1, 1, 0, 0] ");
        //System.out.println("2. " + model.Interpreter.interpretTerm("x^2", vList) + ", Real: [1, 2, 0, 0] ");
        //System.out.println("3. " + model.Interpreter.interpretTerm("2", vList) + ", Real: [2, 0, 0, 0] ");
        //System.out.println("4. " + model.Interpreter.interpretTerm("3 x ^      2  y   ", vList) + ", Real: [3, 2, 1, 0] ");
        //System.out.println("5. " + model.Interpreter.interpretTerm("+ x y z", vList) + ", Real: [1, 1, 1, 1] ");
        //System.out.println("6. " + Interpreter.interpretTerm(" 40 / 2 x y^2z", vList));
        //System.out.println("7. " + Interpreter.interpretTerm("-1", vList));
    }

    public void BuchburgerTest3 () {
        //model.Order o = new model.LexicoOrder();
        Order o = new GrlexOrder();
        //model.Order o = new model.GrevlexOrder();

        // x^2 - 2
        Term g11 = new Term(new Q(1, 1), new int[]{2, 0, 0});
        Term g12 = new Term(new Q(-2, 1), new int[]{0, 0, 0});
        ArrayList<Term> gt1 = new ArrayList<Term>();
        gt1.add(g11);
        gt1.add(g12);
        Polynomial g1 = new Polynomial(gt1);
        Sort.sort(g1, o);
        //System.out.println(g1);

        // y^2 - 3
        Term g21 = new Term(new Q(1, 1), new int[]{0, 2, 0});
        Term g22 = new Term(new Q(-3, 1), new int[]{0, 0, 0});
        ArrayList<Term> gt2 = new ArrayList<Term>();
        gt2.add(g21);
        gt2.add(g22);
        Polynomial g2 = new Polynomial(gt2);
        Sort.sort(g2, o);
        //System.out.println(g2);

        // z - x - y
        Term g31 = new Term(new Q(1, 1), new int[]{0, 0, 1});
        Term g32 = new Term(new Q(-1, 1), new int[]{1, 0, 0});
        Term g33 = new Term(new Q(-1, 1), new int[]{0, 1, 0});
        ArrayList<Term> gt3 = new ArrayList<Term>();
        gt3.add(g31);
        gt3.add(g32);
        gt3.add(g33);
        Polynomial g3 = new Polynomial(gt3);
        Sort.sort(g3, o);
        //System.out.println(g3);

        ArrayList<Polynomial> polys = new ArrayList<Polynomial>();
        polys.add(g1);
        polys.add(g2);
        polys.add(g3);

        Ideal ideal = new Ideal(polys);

        GroebnerBasis gb = Buchburger.buchburger(ideal, o);
        gb.minimalize();
        gb.reduce(o);

        System.out.println(gb);
    }
    public void QTest() {
        Q q0 = new Q(-1, 1);

        /*
        Q q1 = new Q(-2, 3);
        Q q2 = new Q(5, 6);
        System.out.println(Q.add(q1, q2)); // 1 / 6
        System.out.println(Q.subtract(q1, q2)); // -3 / 2
        System.out.println(Q.multiply(q1, q2)); // -10/18 = -5/9
        System.out.println(Q.divide(q1, q2)); // -12/15 = -4/5
        */
    }

    public void BuchburgerTest2() {
        Order o = new LexicoOrder();

        // x^3 - 3x^2 - y + 1
        Term g11 = new Term(new Q(1, 1), new int[]{3, 0});
        Term g12 = new Term(new Q(-3, 1), new int[]{2, 0});
        Term g13 = new Term(new Q(-1, 1), new int[]{0, 1});
        Term g14 = new Term(new Q(1, 1), new int[]{0, 0});
        ArrayList<Term> gt1 = new ArrayList<Term>();
        gt1.add(g11);
        gt1.add(g12);
        gt1.add(g13);
        gt1.add(g14);
        Polynomial g1 = new Polynomial(gt1);


        // -x^2 + y^2 - 1
        Term g21 = new Term(new Q(-1, 1), new int[]{2, 0});
        Term g22 = new Term(new Q(1, 1), new int[]{0, 2});
        Term g23 = new Term(new Q(-1, 1), new int[]{0, 0});
        ArrayList<Term> gt2 = new ArrayList<Term>();
        gt2.add(g21);
        gt2.add(g22);
        gt2.add(g23);
        Polynomial g2 = new Polynomial(gt2);

        ArrayList<Polynomial> polys = new ArrayList<Polynomial>();
        polys.add(g1);
        polys.add(g2);

        Ideal ideal = new Ideal(polys);

        GroebnerBasis gb = Buchburger.buchburger(ideal, o);
        gb.minimalize();
        gb.reduce(o);

        System.out.println(gb);
    }

    public void BuchburgerTest () {
        //model.Order o = new model.LexicoOrder();
        //model.Order o = new model.GrlexOrder();
        Order o = new GrevlexOrder();

        Term g11 = new Term(new Q(1, 1), new int[]{2, 0});
        Term g21 = new Term(new Q(1, 1), new int[]{3, 0});
        Term g22 = new Term(new Q(1, 1), new int[]{0, 1});

        ArrayList<Term> termg1 = new ArrayList<Term>();
        ArrayList<Term> termg2 = new ArrayList<Term>();
        termg1.add(g11);
        termg2.add(g21);
        termg2.add(g22);

        Polynomial g1 = new Polynomial(termg1);
        Polynomial g2 = new Polynomial(termg2);

        ArrayList<Polynomial> i = new ArrayList<Polynomial>();
        i.add(g1);
        i.add(g2);

        Ideal ideal = new Ideal(i);
        GroebnerBasis gb = Buchburger.buchburger(ideal, o);
        System.out.println(gb);
        gb.minimalize();
        System.out.println(gb);
        gb.reduce(o);
        System.out.println(gb);

    }

    public void SPolynomialTest () {
        // g1 = 2x^2 + y, g2 = 3y^2
        // L = x^2 y^2
        // S(g1, g2) = (x^2 y^2) / (2x^2) * (2x^2 + y) - (x^2 y^2) / (3y^2) * (3y^2)
        //           = y^2 / 2 (2 x^2 + y) - x^2 y^2
        //           = 0.5 y^3

        Term g11 = new Term(new Q(2, 1), new int[]{2, 0});
        Term g12 = new Term(new Q(1, 1), new int[]{0, 1});
        Term g21 = new Term(new Q(3, 1), new int[]{0, 2});

        ArrayList<Term> gt1 = new ArrayList<Term>();
        ArrayList<Term> gt2 = new ArrayList<Term>();
        gt1.add(g11);
        gt1.add(g12);
        gt2.add(g21);

        Polynomial g1 = new Polynomial(gt1);
        Polynomial g2 = new Polynomial(gt2);

        System.out.println(SPolynomial.spoly(g1, g2));

    }


    public void SimplificationTest2() {
        Order o = new LexicoOrder();
        Term g11 = new Term(new Q(1, 1), new int[]{2, 0});
        Term g21 = new Term(new Q(1, 1), new int[]{3, 0});
        Term g22 = new Term(new Q(2, 1), new int[]{0, 1});
        Term g31 = new Term(new Q(-1, 1), new int[]{0, 1});
        Term f11 = new Term(new Q(1, 1), new int[]{0, 2});

        ArrayList<Term> termf = new ArrayList<Term>();
        termf.add(f11);


        ArrayList<Term> termg1 = new ArrayList<Term>();
        ArrayList<Term> termg2 = new ArrayList<Term>();
        ArrayList<Term> termg3 = new ArrayList<Term>();
        termg1.add(g11);
        termg2.add(g21);
        termg2.add(g22);
        termg3.add(g31);


        Polynomial f = new Polynomial(termf);
        Polynomial g1 = new Polynomial(termg1);
        Polynomial g2 = new Polynomial(termg2);
        Polynomial g3 = new Polynomial(termg3);

        ArrayList<Polynomial> gs = new ArrayList<Polynomial>();
        gs.add(g1);
        gs.add(g2);
        gs.add(g3);

        PolynomialSet g = new PolynomialSet(new Ideal(gs));

        System.out.println(Simplification.simplify(f, g, o));
    }


    public void SimplificationTest () {
        Order o = new LexicoOrder();
        Term a = new Term(new Q(-1, 1), new int[]{0, 1});
        Term b = new Term(new Q(1, 1), new int[]{0, 2});
        Term c = new Term(new Q(2, 1), new int[]{1, 0});

        ArrayList<Term> termf = new ArrayList<Term>();
        termf.add(a);


        ArrayList<Term> termg = new ArrayList<Term>();
        termg.add(b);

        Polynomial f = new Polynomial(termf);
        Polynomial g = new Polynomial(termg);

        System.out.println(Simplification.simplify(g, f, 0, o));
    }


    public void SortTest () {
        Term a = new Term(new Q(1, 1), new int[]{2, 1, 1});
        Term b = new Term(new Q(1, 1), new int[]{3, 0, 0});
        Term c = new Term(new Q(1, 1), new int[]{1, 3, 0});

        ArrayList<Term> po = new ArrayList<Term>();
        po.add(a);
        po.add(b);
        po.add(c);

        Polynomial p = new Polynomial(po);


        Order o = new LexicoOrder(); // c < a < b
        //model.Order o = new model.GrlexOrder(); // b < c < a
        //model.Order o = new model.GrevlexOrder(); // b < a < c

        System.out.println(p);
        Sort.sort(p, o);
        System.out.println(p);
    }

    public void OrderTest () {
        Term a = new Term(new Q(1, 1), new int[]{2, 1, 1});
        Term b = new Term(new Q(1, 1), new int[]{3, 0, 0});
        Term c = new Term(new Q(1, 1), new int[]{1, 3, 0});

        //model.Order o = new model.LexicoOrder(); // c < a < b
        //model.Order o = new model.GrlexOrder(); // b < c < a
        Order o = new GrevlexOrder(); // b < a < c

        System.out.println(o.isGreater(b, c));
        System.out.println(o.isGreater(c, a));
        System.out.println(o.isGreater(b, a));
    }

    // model.Polynomial test
    public void PolynomialTest () {
        Term a = new Term (new Q(1, 1), new int[]{0, 2, 1});
        Term b = new Term (new Q(2, 1), new int[]{1, 2, 3});
        Term c = new Term (new Q(1, 1), new int[]{1, 1, 1});
        Term d = new Term (new Q(2, 1), new int[]{1, 1, 1});
        Term e = new Term (new Q(-2, 1), new int[]{1, 1, 1});

        ArrayList<Term> p = new ArrayList<Term>();
        ArrayList<Term> p2 = new ArrayList<Term>();
        ArrayList<Term> p3 = new ArrayList<Term>();
        p.add(a);
        p.add(b);
        p.add(c);
        p.add(d);
        p.add(e);
        p2.add(c);
        p2.add(d);
        p3.add(e);

        Polynomial poly = new Polynomial(p);
        Polynomial poly2 = new Polynomial(p2);
        Polynomial poly3 = new Polynomial(p3);
        poly.simplify();

        System.out.println(poly);
        System.out.println(poly2);
        System.out.println(poly3);
        System.out.println(Polynomial.add(poly2, poly3));
        System.out.println(Polynomial.subtract(poly2, poly3));
        System.out.println(Polynomial.multiply(poly, poly));
        System.out.println(Polynomial.add(poly3, e));
        System.out.println(Polynomial.subtract(poly3, e));
        System.out.println(Polynomial.multiply(poly, e));
    }

    // term test
    public void TermTest () {
        Term a = new Term (new Q(1, 1), new int[]{0, 2, 1});
        Term b = new Term (new Q(2, 1), new int[]{1, 2, 3});
        Term c = new Term (new Q(2, 1), new int[]{1, 1, 1});
        Term d = new Term (new Q(2, 1), new int[]{1, 1, 1});
        System.out.println(Term.multiply(a, b)); // [2; 1, 4, 4]
        System.out.println(Term.divide(b, c));   // [2; 0, 1, 2]
        System.out.println(Term.lcm(a, b));
        System.out.println(Term.divisible(a, c));
        System.out.println(Term.divisible(c, a));
        if (Term.isSameDim(c, d)) {
            System.out.println(Term.add(c, d));
            System.out.println(Term.subtract(c, d));
        }
        if (Term.isSameDim(a, b)) {
            Term.add(a, b);
        } else {
            System.out.println("not addable");
        }
    }
}
