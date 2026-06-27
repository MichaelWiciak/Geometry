/**
 * a line defined by two points
 */
public class Line {

    // end points of line segment
    private final Point p1, p2;

    /**
     * constructor, it is up to the user to ensure that the two points are different.
     * @param p1 (Point): first defining point
     * @param p2 (Point): second defining point
     */
    public Line(Point p1, Point p2){
        this.p1=p1;
        this.p2=p2;
    }

    /**
     * getter for points
     * @return [Point]: array (p1, p2)
     */
    public Point[] getPoints(){
        Point[] points = new Point[2];
        points[0]=p1;
        points[1]=p2;

        return points;
    }

    public Point getP1() { return p1; }
    public Point getP2() { return p2; }
}
