import java.util.Arrays;

/**
 * a two dimensional transformation matrix and associated methods
 */
public class TransformMatrix {

    // the raw matrix
    public double matrix[];

    /**
     * default constructor produces identity matrix
     */
    public TransformMatrix(){
        matrix = new double[]{1.0, 0.0, 0.0, 1.0};
    }

    /**
     * constructor
     * @param m00 (double): 00 element
     * @param m01 (double): 01 element
     * @param m10 (double): 10 element
     * @param m11 (double): 11 element
     */
    public TransformMatrix(final double m00, final double m01, final double m10, final double m11){
            matrix = new double[]{m00, m01, m10, m11};
    }

    /**
     * copy constructor
     * @param mat TransformMatrix to be copied
     */
    public TransformMatrix(final TransformMatrix mat){
        matrix = Arrays.copyOf(mat.matrix, 4);
    }

    /**
     * get a copy of the current matrix
     *
     * @return (double[]): matrix
     */
    public double[] getMatrix(){
        return Arrays.copyOf(matrix, 4);
    }

    /**
     * copy another transform
     * @param rhs (TransformMatrix)
     * @return TransformMatrix pointer to self
     */
    public TransformMatrix copy(final TransformMatrix rhs){
        matrix = rhs.getMatrix();
        return this;
    }

    /**
     * Multiplies two 2x2 matrices and returns the result.
     * 
     * @param mat1 (double[]): the first matrix (2x2 as a 1D array of size 4)
     * @param mat2 (double[]): the second matrix (2x2 as a 1D array of size 4)
     * @return (double[]): the resulting matrix (2x2 as a 1D array of size 4)
     */
    public static double[] multiplyMatrices(final double[] mat1, final double[] mat2) {
        return new double[]{
            mat1[0] * mat2[0] + mat1[1] * mat2[2],
            mat1[0] * mat2[1] + mat1[1] * mat2[3],
            mat1[2] * mat2[0] + mat1[3] * mat2[2],
            mat1[2] * mat2[1] + mat1[3] * mat2[3]
        };
    }

    /**
     * update the current matrix
     * @param scale_x (double)
     * @param scale_y (double)
     * @param shear_x (double)
     * @param shear_y (double)
     * @param rotation (double)
     */
    public void updateMatrix(final Double scale_x, final Double scale_y, final Double shear_x, final Double shear_y, final Double theta){
        // construct the matrixes as one dimensional arrays
        final double shear[] = {1.0, shear_x, shear_y, 1.0};
        final double scale[] = {scale_x, 0.0, 0.0, scale_y};

        final double cos_theta = Math.cos(theta);
        final double sin_theta = Math.sin(theta);
        final double rotate[] = {cos_theta, -sin_theta, sin_theta, cos_theta};

        // Apply transformations
        this.matrix = multiplyMatrices(this.matrix, shear); 
        this.matrix = multiplyMatrices(this.matrix, scale); 
        this.matrix = multiplyMatrices(this.matrix, rotate);

    }

    /**
     * apply transformation to a data in a point and return result as new point
     * @param point (Point): input point
     * @return Point: transformed version of input.
     */
    public Point apply(final Point point){
        final double x = this.matrix[0]*point.x + this.matrix[1]*point.y;
        final double y = this.matrix[2]*point.x + this.matrix[3]*point.y;

        return new Point(x, y);
    }

    /**
     * string rep
     * @return String
     */
    public String toString(){
        StringBuilder str = new StringBuilder();

        str.append("[");
        str.append(this.matrix[0]);
        str.append(", ");
        str.append(this.matrix[1]);
        str.append("]\n");
        str.append("[");
        str.append(this.matrix[2]);
        str.append(", ");
        str.append(this.matrix[3]);
        str.append("]\n");

        return str.toString();
    }
}
