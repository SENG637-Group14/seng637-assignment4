package org.jfree.data;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class DataUtilitiesTest {
    private Mockery mockingContext;
    private Values2D values2D;
    private KeyedValues keyedValues;

    @Before
    public void setUp() {
        mockingContext = new Mockery();
        values2D = mockingContext.mock(Values2D.class);
        keyedValues = mockingContext.mock(KeyedValues.class);
    }


    /**
     * Test case: First array is null, second array is not null.
     * Test strategy: The method should return false when the first array is null and the second array is not null.
     * Expected output: false
     */
    @Test
    public void testFirstArrayNullSecondNotNull() {
        double[][] b = new double[][] { {1.0, 2.0} };
        assertFalse("First null, second not null should return false", DataUtilities.equal(null, b));
    }


    /**
     * Test case: First array is not null, second array is null.
     * Test strategy: The method should return false when the first array is not null and the second array is null.
     * Expected output: false
     */
    @Test
    public void testFirstArrayNotNullSecondNull() {
        double[][] a = new double[][] { {1.0, 2.0} };
        assertFalse("First not null, second null should return false", DataUtilities.equal(a, null));
    }


    /**
     * Test case: Arrays with different lengths.
     * Test strategy: The method should return false when the arrays have different lengths.
     * Expected output: false
     */
    @Test
    public void testDifferentLengthArrays() {
        double[][] a = new double[][] { {1.0, 2.0} };
        double[][] b = new double[][] { {1.0, 2.0}, {3.0, 4.0} };
        assertFalse("Different array lengths should return false", DataUtilities.equal(a, b));
    }

    /**
     * Test case: Arrays with same length but different values.
     * Test strategy: The method should return false when the arrays have the same length but different values.
     * Expected output: false
     */
    @Test
    public void testSameLengthDifferentValues() {
        double[][] a = new double[][] { {1.0, 2.0} };
        double[][] b = new double[][] { {1.0, 3.0} };
        assertFalse("Same length, different values should return false", DataUtilities.equal(a, b));
    }


    /**
     * Test case: Arrays with same length and same values.
     * Test strategy: The method should return true when the arrays have the same length and same values.
     * Expected output: true
     */
    @Test
    public void testSameLengthSameValues() {
        double[][] a = new double[][] { {1.0, 2.0}, {3.0, 4.0} };
        double[][] b = new double[][] { {1.0, 2.0}, {3.0, 4.0} };
        assertTrue("Same length and values should return true", DataUtilities.equal(a, b));
    }

    /**
     * Test case: Arrays with NaN values in the same positions.
     * Test strategy: The method should return true when the arrays have NaN values in the same positions.
     * Expected output: true
     */
    @Test
    public void testArraysWithNaNValues() {
        double[][] a = new double[][] { {Double.NaN, 1.0} };
        double[][] b = new double[][] { {Double.NaN, 1.0} };
        assertTrue("Arrays with NaN in same positions should return true", DataUtilities.equal(a, b));
    }

    /**
     * Test case: Arrays with infinity values in the same positions.
     * Test strategy: The method should return true when the arrays have infinity values in the same positions.
     * Expected output: true
     */
    @Test
    public void testArraysWithInfinityValues() {
        double[][] a = new double[][] { {Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY} };
        double[][] b = new double[][] { {Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY} };
        assertTrue("Arrays with INF values in same positions should return true", DataUtilities.equal(a, b));
    }

    /**
     * Test case: Arrays with NaN values in different positions.
     * Test strategy: The method should return false when the arrays have NaN values in different positions.
     * Expected output: false
     */
    @Test
    public void testArraysWithDifferentNaNPositions() {
        double[][] a = new double[][] { {Double.NaN, 1.0} };
        double[][] b = new double[][] { {1.0, Double.NaN} };
        assertFalse("Arrays with NaN in different positions should return false", DataUtilities.equal(a, b));
    }

    /**
     * Test case: Arrays with null subarrays in the same positions.
     * Test strategy: The method should return true when the arrays have null subarrays in the same positions.
     * Expected output: true
     */
    @Test
    public void testArraysWithNullSubArrays() {
        double[][] a = new double[][] { null, {1.0, 2.0} };
        double[][] b = new double[][] { null, {1.0, 2.0} };
        assertTrue("Null subarrays in same positions should return true", DataUtilities.equal(a, b));
    }

    /**
     * Test case: Arrays with mismatched null subarrays.
     * Test strategy: The method should return false when the arrays have mismatched null subarrays.
     * Expected output: false
     */
    @Test
    public void testArraysWithMismatchedNullSubArrays() {
        double[][] a = new double[][] { null, {1.0, 2.0} };
        double[][] b = new double[][] { {1.0, 2.0}, {1.0, 2.0} };
        assertFalse("Mismatched null subarrays should return false", DataUtilities.equal(a, b));
    }


    /**
     * Test case: Calculate column total with null data.
     * Test strategy: The method should throw an IllegalArgumentException when the data is null.
     * Expected output: IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateColumnTotal_NullData() {
        DataUtilities.calculateColumnTotal(null, 0, new int[]{0, 1});
    }



    /**
     * Test case: Calculate row total with null data.
     * Test strategy: The method should throw an IllegalArgumentException when the data is null.
     * Expected output: IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateRowTotal_NullData() {
        DataUtilities.calculateRowTotal(null, 0, new int[]{0, 1});
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateColumnTotal_NullData_ThrowsException() {
        DataUtilities.calculateColumnTotal(null, 0, new int[]{0});
    }

    
    @Test
    public void testClone_ChangingCloneDoesNotAffectSource() {
        double[][] source = {{1.0, 2.0}};
        double[][] clone = DataUtilities.clone(source);
        clone[0][0] = 99.0;
        assertEquals(1.0, source[0][0], 0.00001);
    }


    
    
    
    
    

    
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testClone_NullSource_ThrowsException() {
        DataUtilities.clone(null);
    }

    @Test
    public void testClone_Regular2DArray_CreatesDeepCopy() {
        double[][] source = { {1.0, 2.0}, {3.0, 4.0} };
        double[][] cloned = DataUtilities.clone(source);

        assertNotSame("Outer array must be a new instance", source, cloned);
        for (int i = 0; i < source.length; i++) {
            assertNotSame("Each row should be a new instance", source[i], cloned[i]);
            assertArrayEquals("Row values must be equal", source[i], cloned[i], 0.00001);
        }
    }

    @Test
    public void testClone_ArrayWithNullRows_PreservesNulls() {
        double[][] source = { null, {5.0, 6.0} };
        double[][] cloned = DataUtilities.clone(source);

        assertNull("First row should be null", cloned[0]);
        assertNotNull("Second row should not be null", cloned[1]);
        assertArrayEquals("Second row values must be equal", source[1], cloned[1], 0.00001);
    }



    @Test
    public void testClone_ArrayWithEmptyRows_PreservesStructure() {
        double[][] source = { {}, {7.0} };
        double[][] cloned = DataUtilities.clone(source);

        assertEquals("First row length should be 0", 0, cloned[0].length);
        assertArrayEquals("Second row values must be equal", source[1], cloned[1], 0.00001);
    }

    @Test
    public void testClone_ModifyingOriginal_DoesNotAffectClone() {
        double[][] source = { {1.0, 2.0} };
        double[][] cloned = DataUtilities.clone(source);

        // Modify original
        source[0][0] = 100.0;

        // Clone should remain unaffected
        assertEquals("Cloned value should not change", 1.0, cloned[0][0], 0.00001);
    }
    
    





    // ---------------------------- createNumberArray2D Tests ----------------------------

    /**
     * Test case: Create a 2D Number array with an empty double array.
     * Test strategy: The Javadoc states "Constructs an array of arrays of Number objects from a corresponding structure containing double primitives."
     * This tests the creation of the 2D array when the input array is empty.
     */
    @Test
    public void testCreateNumberArray2DEmptyArray() {

        // #exercise
        double[][] data = {};
        Number[][] result = DataUtilities.createNumberArray2D(data);

        // #verify
        assertEquals("Number array length should be 0", 0, result.length);
    }
    /**
     * Test case: Create a 2D Number array with valid data.
     * Test strategy: The Javadoc states "Constructs an array of arrays of Number objects from a corresponding structure containing double primitives."
     * This test checks the basic case of converting a 2D array of doubles to a 2D array of Numbers.
     */
    @Test
    public void testCreateNumberArray2DValidData() {
        double[][] data = {
                {1.1, 2.2},
                {3.3, 4.4}
        };

        // exercise
        Number[][] result = DataUtilities.createNumberArray2D(data);

        // verify
        Number[][] expected = {
                {1.1, 2.2},
                {3.3, 4.4}
        };
        assertArrayEquals("2D Number array should match the expected values", expected, result);
    }



    /**
     * Test case: Create a 2D Number array with a single row.
     * Test strategy: The Javadoc states "Constructs an array of arrays of Number objects from a corresponding structure containing double primitives."
     * This test checks the creation of the 2D array when the input array has a single row.
     */
    @Test
    public void testCreateNumberArray2DSingleRow() {
        double[][] data = {
                {1.1, 2.2, 3.3}
        };

        // exercise
        Number[][] result = DataUtilities.createNumberArray2D(data);

        // verify
        Number[][] expected = {
                {1.1, 2.2, 3.3}
        };
        assertArrayEquals("2D Number array should match the expected values", expected, result);
    }

    /**
     * Test case: Create a 2D Number array with a single column.
     * Test strategy: The Javadoc states "Constructs an array of arrays of Number objects from a corresponding structure containing double primitives."
     * This test checks the creation of the 2D array when the input array has a single column.
     */
    @Test
    public void testCreateNumberArray2DSingleColumn() {
        double[][] data = {
                {1.1},
                {2.2},
                {3.3}
        };

        // exercise
        Number[][] result = DataUtilities.createNumberArray2D(data);

        // verify
        Number[][] expected = {
                {1.1},
                {2.2},
                {3.3}
        };
        assertArrayEquals("2D Number array should match the expected values", expected, result);
    }

  
   
    @Test
    public void testCreateNumberArray_WithNegativeValues() {
        double[] data = {-5.5, -3.3};
        Number[] result = DataUtilities.createNumberArray(data);
        Number[] expected = {-5.5, -3.3};
        assertArrayEquals("Array with negative values", expected, result);
    }
    
    @Test
    public void testCreateNumberArray2D_MixedValues() {
        double[][] data = {
            {-2.0, 0.0},
            {5.5, -1.1}
        };
        Number[][] result = DataUtilities.createNumberArray2D(data);
        Number[][] expected = {
            {-2.0, 0.0},
            {
            	5.5, -1.1}
        };
        assertArrayEquals("2D array with mixed values", expected, result);
    }
    

    // ---------------------------- createNumberArray Tests ----------------------------


    /**
     * Test case: Create a Number array with valid data.
     * Test strategy: The Javadoc states "Constructs an array of Number objects from an array of double primitives."
     * This test checks the basic case of converting an array of doubles to an array of Numbers.
     */
    @Test
    public void testCreateNumberArrayValidData() {
        double[] data = {1.1, 2.2, 3.3};

        // exercise
        Number[] result = DataUtilities.createNumberArray(data);

        // verify
        Number[] expected = {1.1, 2.2, 3.3};
        assertArrayEquals("Number array should match the expected values", expected, result);
    }

}