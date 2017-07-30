package model;

import java.util.ArrayList;
import java.util.Stack;

/**
 * This class is to interpret the input
 */
public class Interpreter {
    // this will interpret Groebner basis with given variable
    // [1; 0, 1, 1], [1: 1, 0, 0] -> y + z, x
    public static String verbalizeGroebnerBasis(GroebnerBasis gb, VariableList varList) {
        String output = "";

        for (Polynomial p : gb.getGB()) {
            for (int t = 0; t < p.getPoly().size() - 1; t++) {
                if (t == 0) {
                    output += verbalizeTerm(p.getPoly().get(0), varList) + " ";
                }
                else {
                    if (p.getPoly().get(t).getCoef().getNum() > 0) {
                        output += "+ " + verbalizeTerm(p.getPoly().get(t), varList) + " ";
                    }
                    else {
                        output += "- " + verbalizeTerm(p.getPoly().get(t), varList) + " ";
                    }
                }
            }

            if (p.getPoly().get(p.getPoly().size() - 1).getCoef().getNum() > 0) {
                if (p.getPoly().size() == 1) {
                    output += verbalizeTerm(p.getPoly().get(p.getPoly().size() - 1), varList) + "\n";
                }
                else {
                    output += "+ " + verbalizeTerm(p.getPoly().get(p.getPoly().size() - 1), varList) + "\n";
                }
            }
            else {
                output += "- " + verbalizeTerm(p.getPoly().get(p.getPoly().size() - 1), varList) + "\n";
            }
        }

        return output;
    }

    // This will verbalize term
    public static String verbalizeTerm (Term t, VariableList varList) {
        String output = "";
        if ((!t.getCoef().toString().equals("1") || t.getDim() == 0) && !t.getCoef().toString().equals("-1")) {
            if (t.getCoef().getNum() < 0) {
                output += t.getCoef().toString().substring(1);
            }
            else if (t.getCoef().toString().equals("1") && t.getDim() == 0) {
                return "1";
            }
            else {
                output += t.getCoef();
            }
        }
        else if (t.getCoef().toString().equals("-1") && t.getDim() == 0) {
            return "1";
        }

        for (int i = 0; i < varList.getSize() - 1; i++) {
            if (t.getExp()[i] != 0) {
                output += varList.getVar(i);
                if (t.getExp()[i] != 1) {
                    output += "^" + t.getExp()[i] + " ";
                }
            }
        }

        if (t.getExp()[varList.getSize() - 1] != 0) {
            output += varList.getVar(varList.getSize() - 1);
            if (t.getExp()[varList.getSize() - 1] != 1) {
                output += "^" + t.getExp()[varList.getSize() - 1];
            }
        }

        return output;
    }

    // this will interpret variable list
    // x, y, z -> {"x", "y", "z"}
    public static VariableList interpretVarList(String str) {
        String[] var = str.split(",");

        for (int i = 0; i < var.length; i++) {
            var[i] = var[i].trim();
        }

        return new VariableList(var);
    }

    // this will interpret the ideal
    // poly1 \n poly2 \n poly3 -> <poly1, poly2, poly3>
    public static Ideal interpretIdeal (String str, VariableList varList) {
        String[] gen = str.split("\n");

        ArrayList<Polynomial> id = new ArrayList<Polynomial>();

        for (int i = 0; i < gen.length; i++) {
            id.add(interpretPolynomial(gen[i], varList));
        }

        return new Ideal(id);
    }

    // this will interpret polynomial
    // -2x^2 + yz -> [-2; 2, 0, 0] + [1, 0, 1, 1]
    public static Polynomial interpretPolynomial (String str, VariableList varList) {
        ArrayList<Term> terms = new ArrayList<Term>();

        int temp = str.length();
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == '+' || str.charAt(i) == '-') {
                if (str.substring(i, temp).split(" ").length == 0) {
                    continue;
                }
                terms.add(interpretTerm(str.substring(i, temp), varList));
                temp = i;
            }
        }

        if (temp > 0 && str.substring(0, temp).split(" ").length != 0) {
            terms.add(interpretTerm(str.substring(0, temp), varList));
        }
        return new Polynomial(terms);
    }

    // this will interpret term
    // -2x^2 y^2 z -> [-2; 2, 2, 1]
    public static Term interpretTerm (String str, VariableList varList) {
        Stack<String> num = new Stack<String>();
        ArrayList<Stack<String>> exp = new ArrayList<Stack<String>>();

        for (int i = 0; i < varList.getSize(); i++) {
            exp.add(new Stack<String>());
        }

        String varTemp = "";

        for (int i = 0; i < str.length(); i++) {
            // check for coefficient
            if (str.charAt(i) == '+' || str.charAt(i) == '-') {
                num.push(String.valueOf(str.charAt(i)));

                while (true) {
                    i++;
                    if (i == str.length()) {
                        break;
                    }

                    // 0 ~ 9
                    if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                        num.push(String.valueOf(str.charAt(i)));
                        continue;
                    }

                    // "/"
                    if (str.charAt(i) == '/') {
                        num.push(String.valueOf(str.charAt(i)));
                        continue;
                    }

                    // ' '
                    if (str.charAt(i) == ' ') {
                        continue;
                    }

                    // neither above, which means variable appears
                    i--;
                    break;
                }
            }
            // if num appears
            else if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                num.push(String.valueOf(str.charAt(i)));

                while (true) {
                    i++;
                    if (i == str.length()) {
                        break;
                    }

                    // 0 ~ 9
                    if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                        num.push(String.valueOf(str.charAt(i)));
                        continue;
                    }

                    // "/"
                    if (str.charAt(i) == '/') {
                        num.push(String.valueOf(str.charAt(i)));
                        continue;
                    }

                    // ' '
                    if (str.charAt(i) == ' ') {
                        continue;
                    }

                    // neither above, which means variable appears
                    i--;
                    break;
                }
            }
            else {
                if (str.charAt(i) != ' ') {
                    // check whether given variable is valid
                    varTemp = String.valueOf(str.charAt(i));

                    while (true) {
                        i++;

                        if (i == str.length()) {
                            i--;
                            break;
                        }

                        if (str.charAt(i) == ' ') {
                            i--;
                            break;
                        }

                        if (str.charAt(i) == '^') {
                            i--;
                            break;
                        }

                        varTemp += String.valueOf(str.charAt(i));
                    }

                    int index = varList.getIndex(varTemp);

                    // if there is no varTemp in varList, then return null
                    if (index == -1) {
                        return null;
                    }

                    while (true) {
                        i++;
                        if (i == str.length()) {
                            if (exp.get(index).size() == 0) {
                                exp.get(index).push("1");
                            }

                            break;
                        }
                        else if (str.charAt(i) == ' ') {
                            continue;
                        }
                        else if (str.charAt(i) == '^') {
                            while (true) {
                                i++;
                                if (i == str.length()) {
                                    return null;
                                }

                                if (str.charAt(i) == ' ') {
                                    continue;
                                }

                                // 0 ~ 9
                                if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                                    exp.get(index).push(String.valueOf(str.charAt(i)));
                                    while (true) {
                                        i++;
                                        if (i == str.length()) {
                                            i--;
                                            break;
                                        }

                                        if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                                            exp.get(index).push(String.valueOf(str.charAt(i)));
                                        }
                                        else {
                                            i--;
                                            break;
                                        }
                                    }
                                    break;
                                }

                                return null;
                            }
                            break;
                        }
                        else {
                            if (exp.get(index).size() == 0) {
                                i--;
                                exp.get(index).push("1");
                            }
                            break;
                        }
                    }
                }
            }
        }

        // for creating term
        int nume = 1;
        int denom = 1;

        int[] exponent = new int[varList.getSize()];

        int sizeOfNum = num.size();
        String tempNume = "";
        String tempDenom = "1";

        // if nume is -1
        if (num.size() == 0) {
            nume = 1;
            denom = 1;
        }
        else if (num.peek().equals("-")) {
            nume = -1;
        }
        // otherwise
        else {
            for (int i = 0; i < sizeOfNum; i++) {
                // check for +
                if (num.peek().equals("+")) {
                    num.pop();

                    if (num.size() == 0 && tempNume.equals("")) {
                        tempNume = "1";
                        tempDenom = "1";
                        break;
                    }
                }
                else if (num.peek().equals("/")) {
                    tempDenom = new String(tempNume);
                    tempNume = "";
                    num.pop();
                }
                else if (num.peek().equals("-")) {
                    nume *= -1;

                    // this should not happen
                    if (sizeOfNum == i - 1) {
                        return null;
                    }
                }
                else {
                    tempNume = num.pop() + tempNume;
                }

            }

            nume *= Integer.valueOf(tempNume);
            denom = Integer.valueOf(tempDenom);
        }

        Q coef = new Q(nume, denom);
        int stackSize;
        String tempExp = "";

        // determining exponents
        for (int i = 0; i < exponent.length; i++) {
            if (exp.get(i).size() == 0) {
                exponent[i] = 0;
            }
            else {
                stackSize = exp.get(i).size();
                tempExp = "";
                for (int j = 0; j < stackSize; j++) {
                    tempExp = exp.get(i).pop() + tempExp;
                }

                exponent[i] = Integer.valueOf(tempExp);
            }
        }

        return new Term(coef, exponent);
    }
}
