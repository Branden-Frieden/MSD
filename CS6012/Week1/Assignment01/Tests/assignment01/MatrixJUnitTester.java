package assignment01;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixJUnitTester {
    private Matrix mat1, mat2, mat3, mat4;

    @BeforeEach
    void setUp() {


        mat1 = new Matrix(new int[][]{{1, 2}, {2, 3}});
        mat2 = new Matrix(new int[][]{{-1, 2}, {-2, 3}});
        mat3 = new Matrix(new int[][]{{0, 1}, {0, -2}, {2, 3}});
        mat4 = new Matrix(new int[][]{{2, 1, 2}, {0, 2, 1}});
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testEquals() {
        Matrix equalMatrix = new Matrix(new int[][]{{-1, 2}, {-2, 3}});
        assert(mat2.equals(equalMatrix));
        assert(!mat2.equals(mat4));
        assert(!mat2.equals(mat3));
    }

    @Test
    void twoByTwoToString() {
        String expected = "1 2 \n2 3 \n";
        assertEquals( expected, mat1.toString());
    }

    @Test
    void threeByTwoToString() {
        String expected = "0 1 \n0 -2 \n2 3 \n";
        assertEquals( expected, mat3.toString());
    }

    @Test
    void timesWithUnbalancedDimensions() {
        Matrix expectedMatrix = new Matrix(new int[][]{{0, 2, 1}, {0, -4, -2}, {4, 8, 7}});
        assertEquals(expectedMatrix, mat3.times(mat4));
    }

    @Test
    void timesWithBalancedDimensions() {
        Matrix expectedMatrix = new Matrix(new int[][]{ {-5, 8}, {-8, 13} });
        assertEquals(expectedMatrix, mat1.times(mat2));
    }

    @Test
    void timesWithIncorrectDimensions() {
        assertEquals(null, mat1.times(mat4));
    }

    @Test
    void plusWithBalancedDimensions() {
        Matrix expectedMatrix = new Matrix(new int[][]{{0, 4}, {0, 6}});
        assertEquals(expectedMatrix, mat1.plus(mat2));
    }

    @Test
    void plusWithUnbalancedDimensions() {
        assertEquals(null, mat3.plus(mat4));
    }

}