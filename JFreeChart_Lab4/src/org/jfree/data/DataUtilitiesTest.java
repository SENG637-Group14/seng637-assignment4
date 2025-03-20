package org.jfree.data;

import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.Values2D;
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
    
    // New TCs
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNumberArray_NullInput_ShouldThrowException() {
        DataUtilities.createNumberArray(null);
    }
    
    @Test
    public void testCreateNumberArray_BoundaryValues() {
        double[] values = {Double.MIN_VALUE, 0, Double.MAX_VALUE};
        Number[] result = DataUtilities.createNumberArray(values);
        assertEquals(3, result.length);
    }
    
    
    @Test
    public void testNullEqualityCheck() {
        double[][] a = {{1.0, 2.0}, {3.0, 4.0}};
        double[][] b = null;

        assertFalse("Expected false for comparison with null.", DataUtilities.equal(a, b));
    }

    @Test
    public void testArrayWithDifferentLengths() {
        double[][] a = {{1.0, 2.0}, {3.0, 4.0}};
        double[][] b = {{1.0, 2.0}};

        assertFalse("Expected false for arrays with different lengths.", DataUtilities.equal(a, b));
    }

    @Test
    public void testArrayElementMismatch() {
        double[][] a = {{1.0, 2.0}, {3.0, 4.0}};
        double[][] b = {{1.0, 2.0}, {5.0, 4.0}};

        assertFalse("Expected false for arrays with mismatched elements.", DataUtilities.equal(a, b));
    }

    @Test
    public void testNegatedArrayComparison() {
        double[][] a = {{1.0, -1.0}};
        double[][] b = {{1.0, 1.0}};

        assertFalse("Expected false for arrays with negated values.", DataUtilities.equal(a, b));
    }

    @Test
    public void testLoopBoundaryWithMismatchedArray() {
        double[][] a = {{1.0, 2.0}, {3.0, 4.0}};
        double[][] b = {{1.0, 2.0}, {3.0, 5.0}};

        assertFalse("Expected false for loop boundary condition mismatch.", DataUtilities.equal(a, b));
    }

    @Test
    public void testArrayConversionWithBoundaryChange() {
        double[] data = {1.0, 2.0, 3.0};
        Number[] expected = {1.0, 2.0, 3.0};

        Number[] result = DataUtilities.createNumberArray(data);
        assertArrayEquals("Array conversion should match expected values.", expected, result);
    }

    
    @Test
    public void testArrayConversionIncrementedField() {
        double[] data = {1.0, 2.0, 3.0};
        Number[] expected = {1.0, 2.0, 3.0};

        Number[] result = DataUtilities.createNumberArray(data);
        assertArrayEquals("Array conversion should not be incremented.", expected, result);
    }

    
    @Test
    public void testArrayConversionDecrementedField() {
        double[] data = {1.0, 2.0, 3.0};
        Number[] expected = {1.0, 2.0, 3.0};

        Number[] result = DataUtilities.createNumberArray(data);
        assertArrayEquals("Array conversion should not be decremented.", expected, result);
    }

    
    @Test
    public void testGetCumulativePercentagesBoundaryChange() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("A", 5.0);
        data.addValue("B", 3.0);
        data.addValue("C", 2.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        assertEquals("Cumulative percentage should be 1.0 for the last element.", 1.0, result.getValue("C").doubleValue(), 0.0001);
    }

    
    @Test
    public void testGetCumulativePercentagesWithBoundaryCondition() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("X", 4.0);
        data.addValue("Y", 1.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        assertEquals("Cumulative percentage should be 1.0 for the last key.", 1.0, result.getValue("Y").doubleValue(), 0.0001);
    }

    
    @Test
    public void testGetCumulativePercentagesWithNegatedCondition() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("A", 10.0);
        data.addValue("B", 20.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        assertEquals("Cumulative percentage should not be affected by negated condition.", 1.0, result.getValue("B").doubleValue(), 0.0001);
    }

    

	@Test
	public void testCalculateRowTotalWithZeroSubstitution() {
	    DefaultKeyedValues2D data = new DefaultKeyedValues2D();
	    data.addValue(1.0, "Row1", "Col1");
	    data.addValue(2.0, "Row1", "Col2");
	    data.addValue(3.0, "Row1", "Col3");
	
	    assertEquals("Expected row total for row 0.", 6.0, DataUtilities.calculateRowTotal(data, 0), 0.0001);
	}
	
	@Test
	public void testCalculateRowTotalWithNoColumnCount() {
	    DefaultKeyedValues2D data = new DefaultKeyedValues2D();
	    data.addValue(1.0, "Row1", "Col1");
	
	    assertEquals("Expected row total with valid column count.", 1.0, DataUtilities.calculateRowTotal(data, 0), 0.0001);
	}
	
	@Test
	public void testCalculateRowTotalBoundaryCases() {
	    DefaultKeyedValues2D data = new DefaultKeyedValues2D();
	    data.addValue(1.0, "Row1", "Col1");
	    data.addValue(2.0, "Row1", "Col2");
	    data.addValue(3.0, "Row1", "Col3");
	
	    assertEquals("Boundary check should calculate row total correctly.", 6.0, DataUtilities.calculateRowTotal(data, 0), 0.0001);
	}
	
	@Test
	public void testCalculateRowTotalWithNullValues() {
	    DefaultKeyedValues2D data = new DefaultKeyedValues2D();
	    data.addValue(1.0, "Row1", "Col1");
	    data.addValue(null, "Row1", "Col2");
	    data.addValue(3.0, "Row1", "Col3");
	
	    assertEquals("Row total should ignore null values.", 4.0, DataUtilities.calculateRowTotal(data, 0), 0.0001);
	}
	
	@Test
	public void testCalculateRowTotalWithNegatedCondition() {
	    DefaultKeyedValues2D data = new DefaultKeyedValues2D();
	    data.addValue(1.0, "Row1", "Col1");
	    data.addValue(2.0, "Row1", "Col2");
	    data.addValue(3.0, "Row1", "Col3");
	
	    assertEquals("Row total should be valid with negated conditionals.", 6.0, DataUtilities.calculateRowTotal(data, 0), 0.0001);
	}
	
	@Test
	public void testCalculateRowTotalWithSubtractionMutation() {
	    DefaultKeyedValues2D data = new DefaultKeyedValues2D();
	    data.addValue(5.0, "Row1", "Col1");
	    data.addValue(2.0, "Row1", "Col2");
	    data.addValue(3.0, "Row1", "Col3");
	
	    assertEquals("Row total should not be affected by subtraction mutation.", 10.0, DataUtilities.calculateRowTotal(data, 0), 0.0001);
	}
	
	@Test
	public void testCalculateRowTotalReturnReplacedWithZero() {
	    DefaultKeyedValues2D data = new DefaultKeyedValues2D();
	    data.addValue(1.0, "Row1", "Col1");
	    data.addValue(2.0, "Row1", "Col2");
	    data.addValue(3.0, "Row1", "Col3");
	
	    assertEquals("Row total should not be replaced with zero.", 6.0, DataUtilities.calculateRowTotal(data, 0), 0.0001);
	}

    
   





}