/**
 * To compile:
 * javac -cp junit-platform-console-standalone-1.9.3.jar *.java -d target
 *
 * To run tests:
 * java -jar .\junit-platform-console-standalone-1.9.3.jar --class-path target --select-class GeometryTests
 */

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * class holding the Junit4 tests for TransformMatrix
*/
public class GeometryTests {
    /**
     * declare a field that will hold the matrix being tested
     */
    private TransformMatrix id_matrix;

    /**
     * declare a field to hold point (1, 1)
     */
    private Point unit;

    /**
     * this is our interpration of zero
     */
    static final double LOCAL_ZERO = 0.0000001;

    // this is the Junit4 version of @Before, equivalent to @BeforEach in Junit5
    @Before
    public void setUp() throws Exception {
        id_matrix = new TransformMatrix(1.0, 0.0, 0.0, 1.0);
        unit = new Point(1.0, 1.0);
    }

    /**
     * simple test of identity matrix
     */
    @Test
    public void testIdentity(){
        double id[] = {1.0, 0.0, 0.0, 1.0};

        assertArrayEquals(id, id_matrix.matrix, GeometryTests.LOCAL_ZERO);
    }

    /**
     * test apply id matrix
     */
    @Test
    public void testApply0(){
        Point same = id_matrix.apply(unit);
        assertEquals(1.0, same.x, GeometryTests.LOCAL_ZERO);
        assertEquals(1.0, same.y, GeometryTests.LOCAL_ZERO);
    }

    /**
     * test apply with shear
     */
    @Test
    public void testApply1(){
        id_matrix.matrix[0] = 2.0;
        Point same = id_matrix.apply(unit);
        assertEquals(2.0, same.x, GeometryTests.LOCAL_ZERO);
        assertEquals(1.0, same.y, GeometryTests.LOCAL_ZERO);
    }

    /**
     * test TransformMatrix.updateMatrix method with a rotation
     */
    @Test
    public void testRotationUpdate(){
        TransformMatrix tmp = new TransformMatrix();
        final double theta =  Math.toRadians(45.0);
        final double cosTheta = Math.cos(theta);
        final double sinTheta = Math.sin(theta);

        tmp.updateMatrix(1.0, 1.0, 0.0, 0.0, theta);
        final double[] shouldBe = {cosTheta, -sinTheta, sinTheta, cosTheta};

        assertArrayEquals(tmp.matrix, shouldBe, GeometryTests.LOCAL_ZERO);
    }

    /**
     * test TransformMatrix.updateMatrix method with a shift and shear
     */
    @Test
    public void testScaleShearUpdate(){
        TransformMatrix tmp = new TransformMatrix();
        final double scale_x = 1.5;
        final double scale_y = 0.5;
        final double shear_x = 0.6;
        final double shear_y = -0.3;
        final double rotation = 0.0;

        tmp.updateMatrix(scale_x, scale_y, shear_x, shear_y, rotation);
        final double[] shouldBe = {scale_x, 0.3, -0.45, scale_y};

        assertArrayEquals(shouldBe, tmp.matrix, GeometryTests.LOCAL_ZERO);
    }

    /**
     * test TransformMatrix.updateMatrix method with a scale, shear and rotation
     */
    @Test
    public void testScaleShearRotateUpdate(){
        TransformMatrix tmp = new TransformMatrix();
        final double scale_x = 1.5;
        final double scale_y = 1.0;
        final double shear_x = 1.0;
        final double shear_y = 0.0;
        final double rotation = Math.toRadians(30.0);

        tmp.updateMatrix(scale_x, scale_y, shear_x, shear_y, rotation);
        final double[] shouldBe = {1.799038105676658, 0.11602540378443882, 0.49999999999999994, 0.8660254037844387};

        assertArrayEquals(shouldBe, tmp.matrix, GeometryTests.LOCAL_ZERO);
    }

    /**
     * Test multiplying two identity matrices.
     */
    @Test
    public void testMultiplyIdentityMatrices() {
        double[] matrixA = {1.0, 0.0, 0.0, 1.0};
        double[] matrixB = {1.0, 0.0, 0.0, 1.0};
        double[] expected = {1.0, 0.0, 0.0, 1.0};

        double[] result = TransformMatrix.multiplyMatrices(matrixA, matrixB);
        assertArrayEquals(expected, result, GeometryTests.LOCAL_ZERO);
    }

    /**
     * Test multiplying a matrix with a scaling matrix.
     */
    @Test
    public void testMultiplyWithScalingMatrix() {
        double[] matrixA = {1.0, 2.0, 3.0, 4.0};
        double[] scalingMatrix = {2.0, 0.0, 0.0, 2.0};
        double[] expected = {2.0, 4.0, 6.0, 8.0};

        double[] result = TransformMatrix.multiplyMatrices(matrixA, scalingMatrix);
        assertArrayEquals(expected, result, GeometryTests.LOCAL_ZERO);
    }

    /**
     * Test multiplying two arbitrary matrices.
     */
    @Test
    public void testMultiplyArbitraryMatrices() {
        double[] matrixA = {1.0, 2.0, 3.0, 4.0};
        double[] matrixB = {2.0, 0.0, 1.0, 2.0};
        double[] expected = {4.0, 4.0, 10.0, 8.0};

        double[] result = TransformMatrix.multiplyMatrices(matrixA, matrixB);
        assertArrayEquals(expected, result, GeometryTests.LOCAL_ZERO);
    }
    /**
     * Test multiplying two arbitrary negative matrices.
     */
    @Test
    public void testMultiplyArbitraryNegativeMatrices() {
        double[] matrixA = {-1.0, -2.0, -3.0, -4.0};
        double[] matrixB = {-2.0, 0.0, -1.0, -2.0};
        double[] expected = {4.0, 4.0, 10.0, 8.0};

        double[] result = TransformMatrix.multiplyMatrices(matrixA, matrixB);
        assertArrayEquals(expected, result, GeometryTests.LOCAL_ZERO);
    }

    /**
     * Test multiplying with a zero matrix.
     */
    @Test
    public void testMultiplyWithZeroMatrix() {
        double[] matrixA = {1.0, 2.0, 3.0, 4.0};
        double[] zeroMatrix = {0.0, 0.0, 0.0, 0.0};
        double[] expected = {0.0, 0.0, 0.0, 0.0};

        double[] result = TransformMatrix.multiplyMatrices(matrixA, zeroMatrix);
        assertArrayEquals(expected, result, GeometryTests.LOCAL_ZERO);
    } 

    /**
     * Test multiplying with a matrix with negative values.
     */
    @Test
    public void testMultiplyWithNegativeMatrix() {
        double[] matrixA = {1.0, 2.0, 3.0, 4.0};
        double[] negativeMatrix = {-1.0, 0, 0, -1.0};
        double[] expected = {-1.0, -2.0, -3.0, -4.0};

        double[] result = TransformMatrix.multiplyMatrices(matrixA, negativeMatrix);
        assertArrayEquals(expected, result, GeometryTests.LOCAL_ZERO);
    }

     /**
     * Test multiplying with a matrix with floating point numbers.
     */
    @Test
    public void testMultiplyFloatingPointPrecision() {
        double[] matrixA = {1e-10, 2e-10, 3e-10, 4e-10};
        double[] matrixB = {2e10, 0.0, 0.0, 2e10};
        double[] expected = {2.0, 4.0, 6.0, 8.0};

        double[] result = TransformMatrix.multiplyMatrices(matrixA, matrixB);
        assertArrayEquals(expected, result, GeometryTests.LOCAL_ZERO);
    }

     /**
     * Test multiplying with a matrix with arbitrary large numbers.
     */
    @Test
    public void testMultiplyWithLargeNumbers() {
        double[] matrixA = {1e10, 2e10, 3e10, 4e10};
        double[] matrixB = {2e10, 0.0, 0.0, 2e10};
        double[] expected = {2.0e20, 4.0e20, 6.0e20, 8.0e20};

        double[] result = TransformMatrix.multiplyMatrices(matrixA, matrixB);
        assertArrayEquals(expected, result, GeometryTests.LOCAL_ZERO);
    }


}