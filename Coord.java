
public class Coord {
    private int x;
    private int y;

    public Coord (int x1, int y1) {
        this.x = x1;
        this.y = y1;
    }

    // ACCESSOR MUTATORS
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

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