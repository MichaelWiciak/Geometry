import java.awt.*;
import java.util.ArrayList;

/**
 * object holding a two dimesional shape
 */
public class Shape {

    // lines forming shape
    private ArrayList<Line> lines;

    // display colour
    private Color color = Color.BLACK;

    /**
     * constructor provides an empty shape
     */
    public Shape(){

       lines = new ArrayList<Line>();

    }

    /**
     * set display color
     * @param c (Color): new color
     */
    public void setColor(Color c){
        color = c;
    }

    /**
     * add a line to shape
     * @param line (Line)
     */
    public void addLine(Line line){
        lines.add(line);
    }

    /**
     * render the Shape to a drawing area
     * @param context (Graphics2D): drawing area
     * @param matrix (TransformMatrix): current transform
     */
    public void draw(Graphics2D context, TransformMatrix matrix){
        // save old drawing colour
        final Color prev = context.getColor();
        context.setColor(color);

        for(Line l: lines){ // for each line
            Point[] pts = l.getPoints(); // get points

            // apply transform matrix to both points
            Point p1 = matrix.apply(pts[0]);
            Point p2 = matrix.apply(pts[1]);

            // draw line between transformed points
            context.drawLine(p1.getIntX(), p1.getIntY(), p2.getIntX(), p2.getIntY());
        }

        // restore old drawing colour
        context.setColor(prev);
    }

    public ArrayList<Line> getLines() { return lines; }
    public Color getColor() { return color; }

    public String getColorHex() {
        if (color == null) return "#000000";
        int rgb = color.getRGB();
        return String.format("#%02x%02x%02x", (rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF);
    }
}
