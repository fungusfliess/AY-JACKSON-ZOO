/*
   File Name: Coord.java
   Name: Jason Liu
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Coord represents a coordinate point with x and y values,
                used for positioning elements in the zoo map.
*/

public class Coord {
    private int x;
    private int y;

    public Coord (int x1, int y1) {
        this.x = x1;
        this.y = y1;
    }

    // ACCESSOR 
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // MUTATORS
    public void setX(int x1) {
        this.x = x1;
    }

    public void setY(int y1) {
        this.y = y1;
    }

    public boolean isInGrid(int length, int width) {
        //      x is within [0, width)         y is within [0, length)
        return ((x >= 0) && (x < width)) && ((y >= 0) && (y < length)); 
    }

    public String toString () {
        return String.format("(%d, %d)", x, y);
    }
}