package datastructures;

/**
 * A datastructure for a cordinate in a grid, which optionally contains third value such the fscore used in the A* algorithm
 */
public class Cordinate implements Comparable<Cordinate>{
    int lineNumber;
    int colum;
    double distanceEstimate;

    public Cordinate(int lineNumber, int colum) {
        this.lineNumber = lineNumber;
        this.colum = colum;
        this.distanceEstimate = 0;
    }

    public Cordinate(int lineNumber, int colum, double distanceEstimate) {
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

    public double getDistanceEstimate() {
        return distanceEstimate;
    }

    public void setDistanceEstimate(double distanceEstimate) {
        this.distanceEstimate = distanceEstimate;
    }

    @Override
    public String toString() {
        return "Cordinate [colum=" + colum + ", lineNumber=" + lineNumber + "]";
    }

    @Override
    public int compareTo(Cordinate c) {
        return (int)(this.distanceEstimate - c.getDistanceEstimate());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cordinate other = (Cordinate) obj;
        if (colum != other.colum)
            return false;
        if (distanceEstimate != other.distanceEstimate)
            return false;
        if (lineNumber != other.lineNumber)
            return false;
        return true;
    }

}