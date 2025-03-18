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


//    /**
//     *  Test case: Both arrays are null.
//        Test strategy: The method should return true when both arrays are null.
//        Expected output: true
//     */
//    @Test
//    public void testBothArraysNull() {
//        assertTrue("Both arrays null should return true", DataUtilities.equal(null, null));
//    }

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

//    /**
//     * Test case: Calculate column total with a single valid row.
//     * Test strategy: The method should return the sum of the values in the specified column for the valid rows.
//     * Expected output: 10.0
//     */
//    @Test
//    public void testCalculateColumnTotal_SingleRow() {
//        mockingContext.checking(new Expectations() {{
//            oneOf(values2D).getRowCount(); will(returnValue(1));
//            oneOf(values2D).getValue(0, 0); will(returnValue(10.0));
//        }});
//
//        int[] validRows = {0};
//        double result = DataUtilities.calculateColumnTotal(values2D, 0, validRows);
//        assertEquals(10.0, result, 0.0001);
//    }

//    /**
//     * Test case: Calculate column total with multiple valid rows.
//     * Test strategy: The method should return the sum of the values in the specified column for the valid rows.
//     * Expected output: 60.0
//     */
//    @Test
//    public void testCalculateColumnTotal_MultipleRows() {
//        mockingContext.checking(new Expectations() {{
//            oneOf(values2D).getRowCount(); will(returnValue(3));
//            oneOf(values2D).getValue(0, 0); will(returnValue(10.0));
//            oneOf(values2D).getValue(1, 0); will(returnValue(20.0));
//            oneOf(values2D).getValue(2, 0); will(returnValue(30.0));
//        }});
//
//        int[] validRows = {0, 1, 2};
//        double result = DataUtilities.calculateColumnTotal(values2D, 0, validRows);
//        assertEquals(60.0, result, 0.0001);
//    }

//    /**
//     * Test case: Calculate column total with an invalid row ignored.
//     * Test strategy: The method should return the sum of the values in the specified column for the valid rows, ignoring invalid rows.
//     * Expected output: 20.0
//     */
//    @Test
//    public void testCalculateColumnTotal_InvalidRowIgnored() {
//        mockingContext.checking(new Expectations() {{
//            oneOf(values2D).getRowCount(); will(returnValue(2));
//            oneOf(values2D).getValue(0, 0); will(returnValue(5.0));
//            oneOf(values2D).getValue(1, 0); will(returnValue(15.0));
//        }});
//
//        int[] validRows = {0, 1, 2}; // row 2 is invalid
//        double result = DataUtilities.calculateColumnTotal(values2D, 0, validRows);
//        assertEquals(20.0, result, 0.0001);
//    }

//    /**
//     * Test case: Calculate column total with a null value.
//     * Test strategy: The method should return the sum of the values in the specified column for the valid rows, ignoring null values.
//     * Expected output: 50.0
//     */
//    @Test
//    public void testCalculateColumnTotal_HandlesNullValue() {
//        mockingContext.checking(new Expectations() {{
//            oneOf(values2D).getRowCount(); will(returnValue(2));
//            oneOf(values2D).getValue(0, 0); will(returnValue(null));
//            oneOf(values2D).getValue(1, 0); will(returnValue(50.0));
//        }});
//
//        int[] validRows = {0, 1};
//        double result = DataUtilities.calculateColumnTotal(values2D, 0, validRows);
//        assertEquals(50.0, result, 0.0001);
//    }

//    /**
//     * Test case: Calculate column total with empty valid rows.
//     * Test strategy: The method should return zero when the valid rows array is empty.
//     * Expected output: 0.0
//     */
//    @Test
//    public void testCalculateColumnTotal_EmptyValidRows() {
//        mockingContext.checking(new Expectations() {{
//            oneOf(values2D).getRowCount(); will(returnValue(2));
//        }});
//
//        int[] validRows = {};
//        double result = DataUtilities.calculateColumnTotal(values2D, 0, validRows);
//        assertEquals(0.0, result, 0.0001);
//    }

    /**
     * Test case: Calculate column total with null data.
     * Test strategy: The method should throw an IllegalArgumentException when the data is null.
     * Expected output: IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateColumnTotal_NullData() {
        DataUtilities.calculateColumnTotal(null, 0, new int[]{0, 1});
    }


//    /**
//     * Test case: Calculate row total with a single valid column.
//     * Test strategy: The method should return the sum of the values in the specified row for the valid columns.
//     * Expected output: 10.0
//     */
//    @Test
//    public void testCalculateRowTotal_SingleColumn() {
//        mockingContext.checking(new Expectations() {{
//            oneOf(values2D).getColumnCount(); will(returnValue(1));
//            oneOf(values2D).getValue(0, 0); will(returnValue(10.0));
//        }});
//
//        int[] validCols = {0};
//        double result = DataUtilities.calculateRowTotal(values2D, 0, validCols);
//        assertEquals(10.0, result, 0.0001);
//    }

//    /**
//     * Test case: Calculate row total with multiple valid columns.
//     * Test strategy: The method should return the sum of the values in the specified row for the valid columns.
//     * Expected output: 60.0
//     */
//    @Test
//    public void testCalculateRowTotal_MultipleColumns() {
//        mockingContext.checking(new Expectations() {{
//            oneOf(values2D).getColumnCount(); will(returnValue(3));
//            oneOf(values2D).getValue(0, 0); will(returnValue(10.0));
//            oneOf(values2D).getValue(0, 1); will(returnValue(20.0));
//            oneOf(values2D).getValue(0, 2); will(returnValue(30.0));
//        }});
//
//        int[] validCols = {0, 1, 2};
//        double result = DataUtilities.calculateRowTotal(values2D, 0, validCols);
//        assertEquals(60.0, result, 0.0001);
//    }

//    /**
//     * Test case: Calculate row total with an invalid column ignored.
//     * Test strategy: The method should return the sum of the values in the specified row for the valid columns, ignoring invalid columns.
//     * Expected output: 20.0
//     */
//    @Test
//    public void testCalculateRowTotal_InvalidColumnIgnored() {
//        mockingContext.checking(new Expectations() {{
//            oneOf(values2D).getColumnCount(); will(returnValue(2));
//            oneOf(values2D).getValue(0, 0); will(returnValue(5.0));
//            oneOf(values2D).getValue(0, 1); will(returnValue(15.0));
//        }});
//
//        int[] validCols = {0, 1, 2}; // Column 2 is invalid and should be skipped
//        double result = DataUtilities.calculateRowTotal(values2D, 0, validCols);
//        assertEquals(20.0, result, 0.0001);
//    }

//    /**
//     * Test case: Calculate row total with a null value.
//     * Test strategy: The method should return the sum of the values in the specified row for the valid columns, ignoring null values.
//     * Expected output: 50.0
//     */
//    @Test
//    public void testCalculateRowTotal_HandlesNullValues() {
//        mockingContext.checking(new Expectations() {{
//            oneOf(values2D).getColumnCount(); will(returnValue(2));
//            oneOf(values2D).getValue(0, 0); will(returnValue(null));
//            oneOf(values2D).getValue(0, 1); will(returnValue(50.0));
//        }});
//
//        int[] validCols = {0, 1};
//        double result = DataUtilities.calculateRowTotal(values2D, 0, validCols);
//        assertEquals(50.0, result, 0.0001);
//    }

//    /**
//     * Test case: Calculate row total with empty valid columns.
//     * Test strategy: The method should return zero when the valid columns array is empty.
//     * Expected output: 0.0
//     */
//    @Test
//    public void testCalculateRowTotal_EmptyValidCols() {
//        mockingContext.checking(new Expectations() {{
//            oneOf(values2D).getColumnCount(); will(returnValue(3));
//        }});
//
//        int[] validCols = {};
//        double result = DataUtilities.calculateRowTotal(values2D, 0, validCols);
//        assertEquals(0.0, result, 0.0001);
//    }

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

//    @Test
//    public void testCalculateColumnTotal_EmptyValidRows_ReturnsZero() {
//        final Values2D data = mockingContext.mock(Values2D.class);
//
//        mockingContext.checking(new Expectations() {{
//            oneOf(data).getRowCount(); will(returnValue(2);
//        }});
//
//        double result = DataUtilities.calculateColumnTotal(data, 0, new int[]{});
//        assertEquals(0.0, result, 0.00001);
//        mockingContext.assertIsSatisfied();
//    }
    
    @Test
    public void testClone_ChangingCloneDoesNotAffectSource() {
        double[][] source = {{1.0, 2.0}};
        double[][] clone = DataUtilities.clone(source);
        clone[0][0] = 99.0;
        assertEquals(1.0, source[0][0], 0.00001);
    }

//    /**
//     * Test case: Calculate column total with a single valid row.
//     * Test strategy: The method should return the sum of the values in the specified column for the valid rows.
//     * Expected output: 5.5
//     */
//    @Test
//    public void testCalculateColumnTotal_SingleValidRow() {
//        final Values2D data = mockingContext.mock(Values2D.class);
//
//        mockingContext.checking(new Expectations() {{
//            oneOf(data).getRowCount(); will(returnValue(2));
//            oneOf(data).getValue(0, 1); will(returnValue(5.5));
//        }});
//
//        int[] validRows = {0};
//        double result = DataUtilities.calculateColumnTotal(data, 1, validRows);
//        assertEquals(5.5, result, 0.00001);
//        mockingContext.assertIsSatisfied();
//    }

//    /**
//     * Test case: Calculate column total with valid rows and a null value.
//     * Test strategy: The method should return the sum of the values in the specified column for the valid rows, ignoring null values.
//     * Expected output: 4.0
//     */
//    @Test
//    public void testCalculateColumnTotal_ValidRowsWithNullValue() {
//        final Values2D data = mockingContext.mock(Values2D.class);
//
//        mockingContext.checking(new Expectations() {{
//            oneOf(data).getRowCount(); will(returnValue(3));
//            oneOf(data).getValue(0, 1); will(returnValue(null));
//            oneOf(data).getValue(2, 1); will(returnValue(4.0));
//        }});
//
//        int[] validRows = {0, 2};
//        double result = DataUtilities.calculateColumnTotal(data, 1, validRows);
//        assertEquals(4.0, result, 0.00001);
//        mockingContext.assertIsSatisfied();
//    }

//    /**
//     * Test case: Calculate column total with all valid rows.
//     * Test strategy: The method should return the sum of the values in the specified column for all valid rows.
//     * Expected output: 6.0
//     */
//    @Test
//    public void testCalculateColumnTotal_AllValidRows() {
//        final Values2D data = mockingContext.mock(Values2D.class);
//
//        mockingContext.checking(new Expectations() {{
//            oneOf(data).getRowCount(); will(returnValue(3));
//            oneOf(data).getValue(0, 0); will(returnValue(1.0));
//            oneOf(data).getValue(1, 0); will(returnValue(2.0));
//            oneOf(data).getValue(2, 0); will(returnValue(3.0));
//        }});
//
//        int[] validRows = {0, 1, 2};
//        double result = DataUtilities.calculateColumnTotal(data, 0, validRows);
//        assertEquals(6.0, result, 0.00001);
//        mockingContext.assertIsSatisfied();
//    }

//    /**
//     * Test case: Calculate column total with a row index out of bounds.
//     * Test strategy: The method should return the sum of the values in the specified column for the valid rows, ignoring rows out of bounds.
//     * Expected output: 2.0
//     */
//    @Test
//    public void testCalculateColumnTotal_RowOutOfBoundsIgnored() {
//        final Values2D data = mockingContext.mock(Values2D.class);
//
//        mockingContext.checking(new Expectations() {{
//            oneOf(data).getRowCount(); will(returnValue(2));
//            oneOf(data).getValue(0, 0); will(returnValue(2.0));
//        }});
//
//        int[] validRows = {0, 3}; // row 3 is ignored
//        double result = DataUtilities.calculateColumnTotal(data, 0, validRows);
//        assertEquals(2.0, result, 0.00001);
//        mockingContext.assertIsSatisfied();
//    }

//    /**
//     * Test case: Calculate column total with zero values.
//     * Test strategy: The method should return the sum of the values in the specified column for the valid rows, which are all zero.
//     * Expected output: 0.0
//     */
//    @Test
//    public void testCalculateColumnTotal_ZeroValues() {
//        final Values2D data = mockingContext.mock(Values2D.class);
//
//        mockingContext.checking(new Expectations() {{
//            oneOf(data).getRowCount(); will(returnValue(2));
//            oneOf(data).getValue(0, 0); will(returnValue(0.0));
//            oneOf(data).getValue(1, 0); will(returnValue(0.0));
//        }});
//
//        int[] validRows = {0, 1};
//        double result = DataUtilities.calculateColumnTotal(data, 0, validRows);
//        assertEquals(0.0, result, 0.00001);
//        mockingContext.assertIsSatisfied();
//    }
    
    
    
    
    
    
//    @Test(expected = IllegalArgumentException.class)
//    public void testCalculateColumnTotal_NullData_ThrowsException() {
//        DataUtilities.calculateColumnTotal(null, 0, new int[]{0});
//    }
//
//    @Test
//    public void testCalculateColumnTotal_EmptyValidRows_ReturnsZero() {
//        final Values2D data = mockingContext.mock(Values2D.class);
//
//        mockingContext.checking(new Expectations() {{
//            oneOf(data).getRowCount(); will(returnValue(3));
//        }});
//
//        double result = DataUtilities.calculateColumnTotal(data, 1, new int[]{});
//        assertEquals(0.0, result, 0.00001);
//
//        mockingContext.assertIsSatisfied();
//    }

//    /**
//     * Test case: Calculate column total with valid rows.
//     * Test strategy: The method should return the sum of the values in the specified column for the valid rows.
//     * Expected output: 8.5
//     */
//    @Test
//    public void testCalculateColumnTotal_WithValidRows() {
//        final Values2D data = mockingContext.mock(Values2D.class);
//
//        mockingContext.checking(new Expectations() {{
//            oneOf(data).getRowCount(); will(returnValue(3));
//            oneOf(data).getValue(0, 1); will(returnValue(5.0));
//            oneOf(data).getValue(2, 1); will(returnValue(3.5));
//        }});
//
//        int[] validRows = {0, 2};
//        double result = DataUtilities.calculateColumnTotal(data, 1, validRows);
//        assertEquals(8.5, result, 0.00001);
//
//        mockingContext.assertIsSatisfied();
//    }

//    /**
//     * Test case: Calculate column total with null value ignored.
//     * Test strategy: The method should return the sum of the values in the specified column for the valid rows, ignoring null values.
//     * Expected output: 4.0
//     */
//    @Test
//    public void testCalculateColumnTotal_WithNullValueIgnored() {
//        final Values2D data = mockingContext.mock(Values2D.class);
//
//        mockingContext.checking(new Expectations() {{
//            oneOf(data).getRowCount(); will(returnValue(3));
//            oneOf(data).getValue(0, 1); will(returnValue(null));
//            oneOf(data).getValue(2, 1); will(returnValue(4.0));
//        }});
//
//        int[] validRows = {0, 2};
//        double result = DataUtilities.calculateColumnTotal(data, 1, validRows);
//        assertEquals(4.0, result, 0.00001);
//
//        mockingContext.assertIsSatisfied();
//    }

//    /**
//     * Test case: Calculate column total with row index out of bounds ignored.
//     * Test strategy: The method should return the sum of the values in the specified column for the valid rows, ignoring rows out of bounds.
//     * Expected output: 2.5
//     */
//    @Test
//    public void testCalculateColumnTotal_RowIndexOutOfBoundsIgnored() {
//        final Values2D data = mockingContext.mock(Values2D.class);
//
//        mockingContext.checking(new Expectations() {{
//            oneOf(data).getRowCount(); will(returnValue(2));
//            oneOf(data).getValue(0, 0); will(returnValue(2.5));
//            // row index 3 is out of bounds, and ignored
//        }});
//
//        int[] validRows = {0, 3};
//        double result = DataUtilities.calculateColumnTotal(data, 0, validRows);
//        assertEquals(2.5, result, 0.00001);
//
//        mockingContext.assertIsSatisfied();
//    }

//    /**
//     * Test case: Calculate column total with all zero values.
//     * Test strategy: The method should return the sum of the values in the specified column for the valid rows, which are all zero.
//     * Expected output: 0.0
//     */
//    @Test
//    public void testCalculateColumnTotal_AllZeroValues() {
//        final Values2D data = mockingContext.mock(Values2D.class);
//
//        mockingContext.checking(new Expectations() {{
//            oneOf(data).getRowCount(); will(returnValue(2));
//            oneOf(data).getValue(0, 0); will(returnValue(0.0));
//            oneOf(data).getValue(1, 0); will(returnValue(0.0));
//        }});
//
//        int[] validRows = {0, 1};
//        double result = DataUtilities.calculateColumnTotal(data, 0, validRows);
//        assertEquals(0.0, result, 0.00001);
//
//        mockingContext.assertIsSatisfied();
//    }
    
    
    
    

// ---------------------------- calculateColumnTotal Tests ----------------------------
//    /**
//     * Test case: Calculate column total with valid data.
//     * Test strategy: The Javadoc states "Returns the sum of the values in one column of the supplied data table.
//     * With invalid input, a total of zero will be returned."
//     * This test checks the basic case of summing positive values in a column.
//     */
//    @Test
//    public void testCalculateColumnTotalForTwoValues() {
//
//        // Example
//        mockingContext.checking(new Expectations() {{
//            oneOf(values2D).getRowCount();
//            will(returnValue(2));
//            oneOf(values2D).getValue(0, 0);
//            will(returnValue(7.5));
//            oneOf(values2D).getValue(1, 0);
//            will(returnValue(2.5));
//        }});
//
//        // exercise
//        double result = DataUtilities.calculateColumnTotal(values2D, 0);
//        // #verify
//        assertEquals("Column total should be 10.0", 10.0, result, .000000001d);
//    }
    
    
    
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

//    @Test
//    public void testCalculateColumnTotal_NegativeRowCount() {
//        Mockery mockingContext = new Mockery();
//        final Values2D values2D = mockingContext.mock(Values2D.class);
//
//        mockingContext.checking(new Expectations() {{
//            oneOf(values2D).getRowCount(); will(returnValue(-1));
//        }});
//
//        double result = DataUtilities.calculateColumnTotal(values2D, 0);
//        assertEquals(0.0, result, 0.00001);
//    }

//    @Test
//    public void testCalculateRowTotal_NegativeColumnCount() {
//        Mockery mockingContext = new Mockery();
//        final Values2D values2D = mockingContext.mock(Values2D.class);
//
//        mockingContext.checking(new Expectations() {{
//            oneOf(values2D).getColumnCount(); will(returnValue(-1));
//        }});
//
//        double result = DataUtilities.calculateRowTotal(values2D, 0);
//        assertEquals(0.0, result, 0.00001);
//    }

//    @Test
//    public void testCalculateRowTotal_NegativeColumnCountTotal() {
//        Mockery mockingContext = new Mockery();
//        final Values2D values2D = mockingContext.mock(Values2D.class);
//
//        mockingContext.checking(new Expectations() {{
//            oneOf(values2D).getColumnCount(); will(returnValue(-1));
//        }});
//
//        double result = DataUtilities.calculateRowTotal(values2D, 0);
//        assertEquals(0.0, result, 0.00001);
//    }

//    @Test
//    public void testGetCumulativePercentages_NegativeItemCount() {
//        Mockery mockingContext = new Mockery();
//        final KeyedValues keyedValues = mockingContext.mock(KeyedValues.class);
//
//        mockingContext.checking(new Expectations() {{
//            allowing(keyedValues).getItemCount(); will(returnValue(-1));
//        }});
//
//        KeyedValues result = DataUtilities.getCumulativePercentages(keyedValues);
//
//        assertNotNull("Result should not be null even with negative item count", result);
//        assertEquals("No items should be added to result when itemCount is negative", 0, result.getItemCount());
//    }

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
    
    


//    /**
//     * Test case: Calculate column total with positive values.
//     * Test strategy: The Javadoc states "Returns the sum of the values in one column of the supplied data table.
//     * With invalid input, a total of zero will be returned."
//     * This test checks the basic case of summing positive values in a column.
//     */
//    @Test
//    public void testCalculateColumnTotalForPositiveValues() {
//        mockingContext.checking(new Expectations() {{
//            oneOf(values2D).getRowCount();
//            will(returnValue(2));
//            oneOf(values2D).getValue(0, 0);
//            will(returnValue(7.5));
//            oneOf(values2D).getValue(1, 0);
//            will(returnValue(2.5));
//        }});
//
//        // exercise
//        double result = DataUtilities.calculateColumnTotal(values2D, 0);
//        // verify
//        assertEquals("Column total should be 10.0", 10.0, result, .000000001d);
//    }

//    /**
//     * Test case: Calculate column total with negative values.
//     * Test strategy: The Javadoc states "Returns the sum of the values in one column of the supplied data table.
//     * With invalid input, a total of zero will be returned."
//     * This test checks the basic case of summing negative values in a column.
//     */
//    @Test
//    public void testCalculateColumnTotalForNegativeValues() {
//        mockingContext.checking(new Expectations() {{
//            oneOf(values2D).getRowCount();
//            will(returnValue(2));
//            oneOf(values2D).getValue(0, 0);
//            will(returnValue(-7.5));
//            oneOf(values2D).getValue(1, 0);
//            will(returnValue(-2.5));
//        }});
//
//        // exercise
//        double result = DataUtilities.calculateColumnTotal(values2D, 0);
//        // verify
//        assertEquals("Column total should be -10.0", -10.0, result, .000000001d);
//    }

//    /**
//     * Test case: Calculate column total with mixed values.
//     * Test strategy: The Javadoc states "Returns the sum of the values in one column of the supplied data table.
//     * With invalid input, a total of zero will be returned."
//     * This test checks the case of summing both positive and negative values in a column.
//     */
//    @Test
//    public void testCalculateColumnTotalForMixedValues() {
//        mockingContext.checking(new Expectations() {{
//            oneOf(values2D).getRowCount();
//            will(returnValue(2));
//            oneOf(values2D).getValue(0, 0);
//            will(returnValue(7.5));
//            oneOf(values2D).getValue(1, 0);
//            will(returnValue(-2.5));
//        }});
//
//        // exercise
//        double result = DataUtilities.calculateColumnTotal(values2D, 0);
//        // verify
//        assertEquals("Column total should be 5.0", 5.0, result, .000000001d);
//    }

//    /**
//     * Test case: Calculate column total with an empty data set.
//     * Test strategy: The Javadoc states "Returns the sum of the values in one column of the supplied data table.
//     * With invalid input, a total of zero will be returned."
//     * This test checks that an empty data set results in zero.
//     */
//    @Test
//    public void testCalculateColumnTotalForEmptyDataSet() {
//        mockingContext.checking(new Expectations() {{
//            oneOf(values2D).getRowCount();
//            will(returnValue(0));
//        }});
//
//        // exercise
//        double result = DataUtilities.calculateColumnTotal(values2D, 0);
//        // verify
//        assertEquals("Column total should be 0.0", 0.0, result, .000000001d);
//    }

//    /**
//     * Test case: Calculate column total with a single value.
//     * Test strategy: The Javadoc states "Returns the sum of the values in one column of the supplied data table.
//     * With invalid input, a total of zero will be returned."
//     * This test checks that a single value in the column returns that value.
//     */
//    @Test
//    public void testCalculateColumnTotalForSingleValue() {
//        mockingContext.checking(new Expectations() {{
//            oneOf(values2D).getRowCount();
//            will(returnValue(1));
//            oneOf(values2D).getValue(0, 0);
//            will(returnValue(7.5));
//        }});
//
//        // exercise
//        double result = DataUtilities.calculateColumnTotal(values2D, 0);
//        // verify
//        assertEquals("Column total should be 7.5", 7.5, result, .000000001d);
//    }



//    /**
//     * Test case: Calculate column total with invalid data, returns zero.
//     * Test strategy: The Javadoc states "With invalid input, a total of zero will be returned.".
//     * This tests that invalid data (empty dataset) results in zero.
//     */
//    @Test
//    public void CalculateColumnTotalEmptyDataSet() {
//
//
//        mockingContext.checking(new Expectations() {{
//            oneOf(values2D).getRowCount();
//            will(returnValue(0));
//        }});
//
//        // exercise
//        double result = DataUtilities.calculateColumnTotal(values2D, 0);
//        // #verify
//        assertEquals("Column total should be 0.0", 0.0, result, .000000001d);
//    }


    /**
     * Test case: Calculate column total with null data.
     * Test strategy: The Javadoc states "Returns the sum of the values in one column of the supplied data table.
     * With invalid input, a total of zero will be returned."
     * This test checks that null data results in an InvalidParameterException.
     */
//    @Test(expected = InvalidParameterException.class)
//    public void testCalculateColumnTotalForNullData() {
//        // exercise
//        DataUtilities.calculateColumnTotal(null, 0);
//    }

    // ---------------------------- calculateRowTotal Tests ----------------------------
//    /**
//     * Test case: Calculate row total with valid data.
//     * Test strategy: The Javadoc states "Returns the sum of the values in one row of the supplied data table.
//     * With invalid input, a total of zero will be returned." This test checks the basic case of summing a row.
//     */
//    @Test
//    public void testCalculateRowTotalValidDataPositiveValues() {
//
//        mockingContext.checking(new Expectations() {{
//            oneOf(values2D).getColumnCount();
//            will(returnValue(3));
//            oneOf(values2D).getValue(0, 0);
//            will(returnValue(1.0));
//            oneOf(values2D).getValue(0, 1);
//            will(returnValue(2.0));
//            oneOf(values2D).getValue(0, 2);
//            will(returnValue(3.0));
//        }});
//
//        // exercise
//        double result = DataUtilities.calculateRowTotal(values2D, 0);
//        // #verify
//        assertEquals("Row total should be 6.0", 6.0, result, .000000001d);
//    }

//    /**
//     * Test case: Calculate row total with negative values.
//     * Test strategy: The Javadoc states "Returns the sum of the values in one row of the supplied data table.
//     * With invalid input, a total of zero will be returned." This test checks the basic case of summing a row.
//     */
//    @Test
//    public void testCalculateRowTotalValidDataNegativeValues() {
//
//        mockingContext.checking(new Expectations() {{
//            oneOf(values2D).getColumnCount();
//            will(returnValue(3));
//            oneOf(values2D).getValue(0, 0);
//            will(returnValue(-1.0));
//            oneOf(values2D).getValue(0, 1);
//            will(returnValue(-2.0));
//            oneOf(values2D).getValue(0, 2);
//            will(returnValue(-3.0));
//        }});
//
//        // exercise
//        double result = DataUtilities.calculateRowTotal(values2D, 0);
//
//        // #verify
//        assertEquals("Row total should be -6.0", -6.0, result, .000000001d);
//    }

    /**
     * Test case: Calculate row total with null data.
     * Test strategy: The Javadoc states "Returns the sum of the values in one row of the supplied data table.
     * With invalid input, a total of zero will be returned."
     * This test checks that null data results in an InvalidParameterException.
     */
//    @Test(expected = InvalidParameterException.class)
//    public void testCalculateRowTotalNullData() {
//        // exercise
//        DataUtilities.calculateRowTotal(null, 0);
//    }

//    /**
//     * Test case: Calculate row total with empty data set.
//     * Test strategy: The Javadoc states "Returns the sum of the values in one row of the supplied data table.
//     * With invalid input, a total of zero will be returned."
//     * This test checks that an empty data set results in zero.
//     */
//    @Test
//    public void testCalculateRowTotalEmptyDataSet() {
//        mockingContext.checking(new Expectations() {{
//            oneOf(values2D).getColumnCount();
//            will(returnValue(0));
//        }});
//
//        // exercise
//        double result = DataUtilities.calculateRowTotal(values2D, 0);
//
//        // verify
//        assertEquals("Row total should be 0.0", 0.0, result, .000000001d);
//    }

//    /**
//     * Test case: Calculate row total with mixed values.
//     * Test strategy: The Javadoc states "Returns the sum of the values in one row of the supplied data table.
//     * With invalid input, a total of zero will be returned."
//     * This test checks the case of summing both positive and negative values in a row.
//     */
//    @Test
//    public void testCalculateRowTotalMixedValues() {
//        mockingContext.checking(new Expectations() {{
//            oneOf(values2D).getColumnCount();
//            will(returnValue(3));
//            oneOf(values2D).getValue(0, 0);
//            will(returnValue(1.0));
//            oneOf(values2D).getValue(0, 1);
//            will(returnValue(-2.0));
//            oneOf(values2D).getValue(0, 2);
//            will(returnValue(3.0));
//        }});
//
//        // exercise
//        double result = DataUtilities.calculateRowTotal(values2D, 0);
//
//        // verify
//        assertEquals("Row total should be 2.0", 2.0, result, .000000001d);
//    }

//    /**
//     * Test case: Calculate row total with a single value.
//     * Test strategy: The Javadoc states "Returns the sum of the values in one row of the supplied data table.
//     * With invalid input, a total of zero will be returned."
//     * This test checks that a single value in the row returns that value.
//     */
//    @Test
//    public void testCalculateRowTotalSingleValue() {
//        mockingContext.checking(new Expectations() {{
//            oneOf(values2D).getColumnCount();
//            will(returnValue(1));
//            oneOf(values2D).getValue(0, 0);
//            will(returnValue(7.5));
//        }});
//
//        // exercise
//        double result = DataUtilities.calculateRowTotal(values2D, 0);
//
//        // verify
//        assertEquals("Row total should be 7.5", 7.5, result, .000000001d);
//    }


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
     * Test case: Create a 2D Number array with null data.
     * Test strategy: The Javadoc states "Constructs an array of arrays of Number objects from a corresponding structure containing double primitives."
     * This test checks that null data results in an InvalidParameterException.
     */
//    @Test(expected = InvalidParameterException.class)
//    public void testCreateNumberArray2DNullData() {
//        // exercise
//        DataUtilities.createNumberArray2D(null);
//    }
//

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

    /**
     * Test case: Create a 2D Number array with null row.
     * Test strategy: The Javadoc states "Constructs an array of arrays of Number objects from a corresponding structure containing double primitives."
     * This test checks that a null row in the input array results in an InvalidParameterException.
     */
//    @Test(expected = InvalidParameterException.class)
//    public void testCreateNumberArray2DNullRow() {
//        double[][] data = {
//                {1.1, 2.2},
//                null
//        };
//
//        // exercise
//        DataUtilities.createNumberArray2D(data);
//    }

    // ---------------------------- getCumulativePercentages Tests ----------------------------

//    /**
//     * Test case: Get cumulative percentages with valid data.
//     * Test strategy: The Javadoc states "Returns a KeyedValues instance that contains the cumulative percentage values for the data in another KeyedValues instance."
//     *  This tests the creation of cumulative percentages array with valid values.
//     */
//    @Test
//    public void testGetCumulativePercentagesValidData() {
//
//        mockingContext.checking(new Expectations() {{
//            allowing(keyedValues).getItemCount();
//            will(returnValue(3));
//            allowing(keyedValues).getValue(0);
//            will(returnValue(5));
//            allowing(keyedValues).getValue(1);
//            will(returnValue(9));
//            allowing(keyedValues).getValue(2);
//            will(returnValue(2));
//            allowing(keyedValues).getKey(0);
//            will(returnValue(0));
//            allowing(keyedValues).getKey(1);
//            will(returnValue(1));
//            allowing(keyedValues).getKey(2);
//            will(returnValue(2));
//        }});
//
//        // exercise
//        KeyedValues result = DataUtilities.getCumulativePercentages(keyedValues);
//
//        // #verify
//        assertEquals("First cumulative percentage should be 0.3125", 0.3125, result.getValue(0).doubleValue(), .000000001d);
//        assertEquals("Second cumulative percentage should be 0.875", 0.875, result.getValue(1).doubleValue(), .000000001d);
//        assertEquals("Third cumulative percentage should be 1.0", 1.0, result.getValue(2).doubleValue(), .000000001d);
//    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//    @Test
//    public void testCalculateColumnTotal_NullValuesIgnored() {
//        mockingContext.checking(new Expectations() {{
//            oneOf(values2D).getRowCount(); will(returnValue(2));
//            oneOf(values2D).getValue(0, 0); will(returnValue(null));
//            oneOf(values2D).getValue(1, 0); will(returnValue(4.0));
//        }});
//
//        double result = DataUtilities.calculateColumnTotal(values2D, 0);
//        assertEquals("Column total with one null value", 4.0, result, 0.000001d);
//    }

    
//    @Test
//    public void testCalculateRowTotal_NullValuesIgnored() {
//        mockingContext.checking(new Expectations() {{
//            oneOf(values2D).getColumnCount(); will(returnValue(2));
//            oneOf(values2D).getValue(0, 0); will(returnValue(null));
//            oneOf(values2D).getValue(0, 1); will(returnValue(4.0));
//        }});
//
//        double result = DataUtilities.calculateRowTotal(values2D, 0);
//        assertEquals("Row total with one null value", 4.0, result, 0.000001d);
//    }
    
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
    
    
    
    
    
    
    
    
    
    
    

    /**
     * Test case: Get cumulative percentages with null data.
     * Test strategy: The Javadoc states "Returns a KeyedValues instance that contains the cumulative percentage values for the data in another KeyedValues instance."
     * This test checks that null data results in an InvalidParameterException.
     */
//    @Test(expected = InvalidParameterException.class)
//    public void testGetCumulativePercentagesNullData() {
//        // exercise
//        DataUtilities.getCumulativePercentages(null);
//    }
    
//    @Test
//    public void testGetCumulativePercentages_CoverUnreachableLoop() {
//        mockingContext.checking(new Expectations() {{
//            allowing(keyedValues).getItemCount(); will(returnValue(-1)); // forces second loop to run
//            // Any additional getValue() and getKey() calls are not needed since no iteration actually occurs after this
//        }});
//
//        KeyedValues result = DataUtilities.getCumulativePercentages(keyedValues);
//
//        // Even though nothing happens in the method logic-wise, it now covers the second loop
//        // which otherwise would be unreachable
//        assertNotNull("Result should not be null even with negative item count", result);
//        assertEquals("No items should be added to result when itemCount is negative", 0, result.getItemCount());
//    }

//    /**
//     * Test case: Get cumulative percentages with empty data.
//     * Test strategy: The Javadoc states "Returns a KeyedValues instance that contains the cumulative percentage values for the data in another KeyedValues instance."
//     * This test checks that an empty data set results in an empty cumulative percentages set.
//     */
//    @Test
//    public void testGetCumulativePercentagesEmptyData() {
//        mockingContext.checking(new Expectations() {{
//            allowing(keyedValues).getItemCount();
//            will(returnValue(0));
//        }});
//
//        // exercise
//        KeyedValues result = DataUtilities.getCumulativePercentages(keyedValues);
//
//        // verify
//        assertEquals("Cumulative percentages should be empty", 0, result.getItemCount());
//    }
//    /**
//     * Test case: Get cumulative percentages with single value.
//     * Test strategy: The Javadoc states "Returns a KeyedValues instance that contains the cumulative percentage values for the data in another KeyedValues instance."
//     * This test checks that a single value results in a cumulative percentage of 1.0.
//     */
//    @Test
//    public void testGetCumulativePercentagesSingleValue() {
//        mockingContext.checking(new Expectations() {{
//            allowing(keyedValues).getItemCount();
//            will(returnValue(1));
//            allowing(keyedValues).getValue(0);
//            will(returnValue(5));
//            allowing(keyedValues).getKey(0);
//            will(returnValue(0));
//        }});
//
//        // exercise
//        KeyedValues result = DataUtilities.getCumulativePercentages(keyedValues);
//
//        // verify
//        assertEquals("Cumulative percentage should be 1.0", 1.0, result.getValue(0).doubleValue(), .000000001d);
//    }
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

    /**
     * Test case: Create a Number array with null data.
     * Test strategy: The Javadoc states "Constructs an array of Number objects from an array of double primitives."
     * This test checks that null data results in an InvalidParameterException.
     */
//    @Test(expected = InvalidParameterException.class)
//    public void testCreateNumberArrayNullData() {
//        // exercise
//        DataUtilities.createNumberArray(null);
//    }

//    /**
//     * Test case: Create a Number array with an empty array.
//     * Test strategy: The Javadoc states "Constructs an array of Number objects from an array of double primitives."
//     * This test checks the creation of the array when the input array is empty.
//     */
//    @Test
//    public void testCreateNumberArrayEmptyArray() {
//        double[] data = {};
//
//        // exercise
//        Number[] result = DataUtilities.createNumberArray(data);
//
//        // verify
//        Number[] expected = {};
//        assertArrayEquals("Number array should be empty", expected, result);
//    }
//
//    /**
//     * Test case: Create a Number array with a single value.
//     * Test strategy: The Javadoc states "Constructs an array of Number objects from an array of double primitives."
//     * This test checks that a single value in the input array returns that value.
//     */
//    @Test
//    public void testCreateNumberArraySingleValue() {
//        double[] data = {7.5};
//
//        // exercise
//        Number[] result = DataUtilities.createNumberArray(data);
//
//        // verify
//        Number[] expected = {7.5};
//        assertArrayEquals("Number array should match the expected value", expected, result);
//    }
}