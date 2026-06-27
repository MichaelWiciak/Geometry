/**
 * a two dimensional point
 */
public class Point {

    // coordinates
    private double x, y;

    /**
     * constructor
     * @param x (double): X coordinate
     * @param y (double): Y coordinate
     * @return Point
     */
    public Point(double x, double y){
        this.x=x;
        this.y=y;
    }

    /**
     * getter for the x-coordinate
     * @return double
     */
    public double getX(){
        return this.x;
    }

    /**
     * getter for the y-coordinate
     * @return double
     */
    public double getY(){
        return this.y;
    }

    /**
     * getter for nearest integer of x-coordinate
     * @return int
     */
    public int getIntX(){
        return (int) Math.round(this.x);
    }

    /**
     * getter for nearest integer of y-coordinate
     * @return int
     */
    public int getIntY(){
        return (int) Math.round(this.y);
    }

    /**
     * string description
     * @return String
     */
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Point(");
        str.append(this.x);
        str.append(", ");
        str.append(this.y);
        str.append(")");

        return str.toString();
    }

}
