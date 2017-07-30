package model;

/**
 * connection between variable and index
 */
public class VariableList {
    private String[] varList;

    public VariableList (String[] varList) {
        this.varList = new String[varList.length];

        for (int i = 0; i < varList.length; i++) {
            this.varList[i] = varList[i];
        }
    }

    public String getVar(int index) {
        return this.varList[index];
    }

    // get index of given word
    // if there were no, then return -1
    public int getIndex (String var) {
        for (int i = 0; i < this.varList.length; i++) {

            if (var.equals(this.varList[i])) {
                return i;
            }
        }

        return -1;
    }

    // get size
    public int getSize () {
        return this.varList.length;
    }
}
