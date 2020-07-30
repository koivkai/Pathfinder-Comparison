package datastructures;

public class Cordinate implements Comparable<Cordinate>{
    int lineNumber;
    int colum;
    int distanceEstimate;

    public Cordinate(int lineNumber, int colum) {
        this.lineNumber = lineNumber;
        this.colum = colum;
        this.distanceEstimate = 0;
    }

    public Cordinate(int lineNumber, int colum, int distanceEstimate) {
        this.lineNumber = lineNumber;
        this.colum = colum;
        this.distanceEstimate = distanceEstimate;
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

    public int getDistanceEstimate() {
        return distanceEstimate;
    }

    public void setDistanceEstimate(int distanceEstimate) {
        this.distanceEstimate = distanceEstimate;
    }

    @Override
    public String toString() {
        return "Cordinate [colum=" + colum + ", lineNumber=" + lineNumber + "]";
    }

    @Override
    public int compareTo(Cordinate c) {
        return this.distanceEstimate - c.getDistanceEstimate();
    }

   


}