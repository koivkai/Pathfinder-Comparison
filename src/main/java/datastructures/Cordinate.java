package datastructures;

public class Cordinate {
    int lineNumber;
    int colum;

    public Cordinate(int lineNumber, int colum) {
        this.lineNumber = lineNumber;
        this.colum = colum;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getColum() {
        return colum;
    }

    public void setColum(int colum) {
        this.colum = colum;
    }

    @Override
    public String toString() {
        return "Cordinate [colum=" + colum + ", lineNumber=" + lineNumber + "]";
    }

    
    
}