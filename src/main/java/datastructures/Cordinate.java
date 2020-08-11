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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + colum;
        result = prime * result + distanceEstimate;
        result = prime * result + lineNumber;
        return result;
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