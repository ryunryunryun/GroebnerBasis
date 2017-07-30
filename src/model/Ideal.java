package model;

import java.util.ArrayList;

/**
 * This just holds generators of model.Ideal
 * This is just a "structure"
 */
public class Ideal {
    private ArrayList<Polynomial> generator;

    public Ideal (ArrayList<Polynomial> generator) {
        this.generator = new ArrayList<Polynomial>();

        for (Polynomial p : generator) {
            this.generator.add(new Polynomial(p));
        }
    }

    // getter
    public ArrayList<Polynomial> getGen () {
        return this.generator;
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < this.generator.size() - 1; i++) {
            output += this.generator.get(i).toString() + "\n";
        }
        output += this.generator.get(this.generator.size() - 1);

        return output;
    }
}