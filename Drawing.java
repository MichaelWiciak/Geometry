import java.util.Vector;

public class Drawing {
    // current transform
    private TransformMatrix transform;

    // array of shapes to be drawn
    private Vector<Shape> shapes;

    private DrawingExportStrategy exportStrategy;

    /**
     * constructor, provides empty Vector and identity matrix
     */
    public Drawing(){
        // identity matrix
        transform = new TransformMatrix();

        // empty drawing
        shapes = new Vector<Shape>();
    }

    /**
     * add a new shape
     * @param shape
     */
    public void add(Shape shape){
        shapes.add(shape);
    }

    /**
     * change the matrix
     */
    public void newMatrix(final TransformMatrix matrix){
        transform.copy(matrix);
    }

    /**
     * getter for copy of the matrix
     * @return TransformMatrix
     */
    public TransformMatrix copyOfMatrix(){
        return new TransformMatrix(transform);
    }

    // Set export strategy by format using the factory
    public void setExportStrategyByFormat(String format) {
        this.exportStrategy = DrawingExportStrategyFactory.getStrategy(format);
    }

    // Use the current strategy to export the drawing
    public String toFileString() {
        if (exportStrategy == null) {
            exportStrategy = DrawingExportStrategyFactory.getDefaultStrategy();
        }
        
        return exportStrategy.export(this);
    }

    public Vector<Shape> getShapes() { return shapes; }
    public TransformMatrix getTransform() { return transform; }
}
