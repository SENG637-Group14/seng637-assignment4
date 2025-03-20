package org.jfree.data;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*; 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


public class RangeTest {
    private Range exampleRange;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /*
     * Setup method executed before each test.
     * Initializes a sample range (-1, 1) for reuse in tests.
     */
    @Before
    public void setUp() throws Exception {
        exampleRange = new Range(-1, 1);
    }

    // -------------- Central Value Tests --------------

    /**
     * Test case: Get central value of the range.
     * Test strategy: The Javadoc states "Returns the central (or median) value for the range."
     * This test verifies the central value calculation for a simple range.
     */
    @Test
    public void centralValueShouldBeZero() {
        assertEquals("The central value of -1 and 1 should be 0",
                0, exampleRange.getCentralValue(), .000000001d);
    }

    /**
     * Test case: Central value for a positive range.
     * Test strategy: The method should return the midpoint of the range.
     * Expected output: (2+6)/2 = 4.0
     */
    @Test
    public void testCentralValuePositiveRange() {
        Range range = new Range(2, 6);
        assertEquals(4.0, range.getCentralValue(), 0.0001);
    }

    /**
     * Test case: Central value for a negative range.
     * Test strategy: The method should return the midpoint of the range.
     * Expected output: (-6 + -2)/2 = -4.0
     */
    @Test
    public void testCentralValueNegativeRange() {
        Range range = new Range(-6, -2);
        assertEquals(-4.0, range.getCentralValue(), 0.0001);
    }

    /**
     * Test case: Central value when the lower and upper bounds are the same.
     * Test strategy: The method should return the same value.
     * Expected output: 5.0
     */
    @Test
    public void testCentralValueSameBounds() {
        Range range = new Range(5, 5);
        assertEquals(5.0, range.getCentralValue(), 0.0001);
    }

    /**
     * Test case: Central value for an extreme range.
     * Test strategy: This tests large value handling.
     * Expected output: ( -MAX_VALUE + MAX_VALUE ) / 2 = 0.0
     */
    @Test
    public void testCentralValueExtremeRange() {
        Range range = new Range(-Double.MAX_VALUE, Double.MAX_VALUE);
        assertEquals(0.0, range.getCentralValue(), 0.0001);
    }

    // -------------- Lower Bound Tests --------------

    /**
     * Test case: Get lower bound of the range.
     * Test strategy: The Javadoc states "Returns the lower bound for the range."
     * This test verifies the lower bound for a known range.
     */
    @Test
    public void lowerBoundShouldBeNegativeOne() {
        assertEquals("Lower bound should be -1", -1, exampleRange.getLowerBound(), .000000001d);
    }

    /**
     * Test case: Lower bound for a positive range.
     * Test strategy: The method should return the minimum value in the range.
     * Expected output: 2.0
     */
    @Test
    public void testLowerBoundPositiveRange() {
        Range range = new Range(2, 6);
        assertEquals(2.0, range.getLowerBound(), 0.0001);
    }

    /**
     * Test case: Lower bound for a negative range.
     * Test strategy: The method should return the minimum value in the range.
     * Expected output: -6.0
     */
    @Test
    public void testLowerBoundNegativeRange() {
        Range range = new Range(-6, -2);
        assertEquals(-6.0, range.getLowerBound(), 0.0001);
    }

    /**
     * Test case: Lower bound for an extreme range.
     * Test strategy: This tests handling of large double values.
     * Expected output: -Double.MAX_VALUE
     */
    @Test
    public void testLowerBoundExtremeRange() {
        Range range = new Range(-Double.MAX_VALUE, Double.MAX_VALUE);
        assertEquals(-Double.MAX_VALUE, range.getLowerBound(), 0.0001);
    }

    // -------------- Upper Bound Tests --------------

    /**
     * Test case: Get upper bound of the range.
     * Test strategy: The Javadoc states "Returns the upper bound for the range."
     * This test verifies the upper bound for a known range.
     */
    @Test
    public void upperBoundShouldBeOne() {
        assertEquals("Upper bound should be 1", 1, exampleRange.getUpperBound(), .000000001d);
    }

    /**
     * Test case: Upper bound for a positive range.
     * Test strategy: The method should return the maximum value in the range.
     * Expected output: 6.0
     */
    @Test
    public void testUpperBoundPositiveRange() {
        Range range = new Range(2, 6);
        assertEquals(6.0, range.getUpperBound(), 0.0001);
    }

    /**
     * Test case: Upper bound for a negative range.
     * Test strategy: The method should return the maximum value in the range.
     * Expected output: -2.0
     */
    @Test
    public void testUpperBoundNegativeRange() {
        Range range = new Range(-6, -2);
        assertEquals(-2.0, range.getUpperBound(), 0.0001);
    }

    /**
     * Test case: Upper bound for an extreme range.
     * Test strategy: This tests handling of large double values.
     * Expected output: Double.MAX_VALUE
     */
    @Test
    public void testUpperBoundExtremeRange() {
        Range range = new Range(-Double.MAX_VALUE, Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, range.getUpperBound(), 0.0001);
    }

    /**
     * Test case: Upper bound when the range includes positive infinity.
     * Test strategy: The method should return Double.POSITIVE_INFINITY.
     * Expected output: Double.POSITIVE_INFINITY
     */
    @Test
    public void testUpperBoundPositiveInfinity() {
        Range range = new Range(-1000, Double.POSITIVE_INFINITY);
        assertEquals(Double.POSITIVE_INFINITY, range.getUpperBound(), 0.0001);
    }

    // -------------- Contains Method Tests --------------

    /**
     * Test case: Check if the range contains zero.
     * Test strategy: The Javadoc states "Returns true if the specified value is within the range."
     * Since zero is within the default range (-1,1), the result should be true.
     */
    @Test
    public void testRangeContainsZero() {
        assertTrue(exampleRange.contains(0));
    }

    /**
     * Test case: Check if the range contains a value within its bounds.
     * Test strategy: The method should return true if the value is within the given range.
     * Expected output: true (3 is within -5 to 5).
     */
    @Test
    public void testRangeContainsValueWithin() {
        assertTrue(new Range(-5, 5).contains(3));
    }

    /**
     * Test case: Check if the range does not contain a value outside its bounds.
     * Test strategy: The method should return false if the value is outside the range.
     * Expected output: false (-1 is not within 1 to 10).
     */
    @Test
    public void testRangeDoesNotContainValue() {
        assertFalse(new Range(1, 10).contains(-1));
    }

    /**
     * Test case: Check boundary value at the lower bound.
     * Test strategy: The method should return true if the value is equal to the lower bound.
     * Expected output: true (-1 is the lower bound of exampleRange).
     */
    @Test
    public void testRangeContainsLowerBound() {
        assertTrue(exampleRange.contains(-1));
    }

    /**
     * Test case: Check boundary value at the upper bound.
     * Test strategy: The method should return true if the value is equal to the upper bound.
     * Expected output: true (1 is the upper bound of exampleRange).
     */
    @Test
    public void testRangeContainsUpperBound() {
        assertTrue(exampleRange.contains(1));
    }

    /**
     * Test case: Check if the range contains an extreme value.
     * Test strategy: If the value is much larger or smaller than the range, return false.
     * Expected output: false (100 is not within -1 to 1).
     */
    @Test
    public void testRangeDoesNotContainExtremeValue() {
        assertFalse(exampleRange.contains(100));
    }

    // -------------- Combine Method Tests --------------

    /**
     * Test case: Combining two null ranges should return null.
     * Test strategy: The Javadoc states "If both ranges are null, the return value is null."
     * Expected output: null.
     */
    @Test
    public void testCombineNullRanges() {
        assertNull(Range.combine(null, null));
    }

    /**
     * Test case: Combining a null range with a valid range should return the valid range.
     * Test strategy: The Javadoc states "If one range is null, return the other range."
     * Expected output: The non-null range.
     */
    @Test
    public void testCombineOneNullRange() {
        Range range = new Range(1, 10);
        assertEquals(range, Range.combine(range, null));
    }

    /**
     * Test case: Combine two non-overlapping positive ranges.
     * Test strategy: The combined range should extend from the lower bound of the first to the upper bound of the second.
     * Expected output: Range(1, 20).
     */
    @Test
    public void testCombineNonOverlappingRanges() {
        Range range1 = new Range(1, 10);
        Range range2 = new Range(15, 20);
        Range combined = Range.combine(range1, range2);
        assertEquals(new Range(1, 20), combined);
    }

    /**
     * Test case: Combine two overlapping ranges.
     * Test strategy: The combined range should merge overlapping regions.
     * Expected output: Range(-5, 10).
     */
    @Test
    public void testCombineOverlappingRanges() {
        Range range1 = new Range(-5, 5);
        Range range2 = new Range(3, 10);
        Range combined = Range.combine(range1, range2);
        assertEquals(new Range(-5, 10), combined);
    }

    /**
     * Test case: Combine two adjacent ranges.
     * Test strategy: The combined range should include both ranges without gaps.
     * Expected output: Range(5, 15).
     */
    @Test
    public void testCombineAdjacentRanges() {
        Range range1 = new Range(5, 10);
        Range range2 = new Range(10, 15);
        Range combined = Range.combine(range1, range2);
        assertEquals(new Range(5, 15), combined);
    }

    /**
     * Test case: Combine two identical ranges.
     * Test strategy: The combined range should remain unchanged.
     * Expected output: Range(3, 7).
     */
    @Test
    public void testCombineIdenticalRanges() {
        Range range1 = new Range(3, 7);
        Range range2 = new Range(3, 7);
        Range combined = Range.combine(range1, range2);
        assertEquals(new Range(3, 7), combined);
    }

    /**
     * Test case: Combine two extreme ranges (infinity cases).
     * Test strategy: The combined range should handle extreme values.
     * Expected output: Range(-∞, ∞).
     */
    @Test
    public void testCombineExtremeRanges() {
        Range range1 = new Range(Double.NEGATIVE_INFINITY, -100);
        Range range2 = new Range(100, Double.POSITIVE_INFINITY);
        Range combined = Range.combine(range1, range2);
        assertEquals(new Range(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY), combined);
    }

    //constrain
    // Test case: Constrain a value within the range
    @Test
    public void testConstrainWithinRange() {
        Range range = new Range(-1, 1);
        assertEquals(0.5, range.constrain(0.5), 0.000000001d);
    }

    // Test case: Constrain a value below the lower bound
    @Test
    public void testConstrainBelowLowerBound() {
        Range range = new Range(-1, 1);
        assertEquals(-1, range.constrain(-2), 0.000000001d);
    }

    // Test case: Constrain a value above the upper bound
    @Test
    public void testConstrainAboveUpperBound() {
        Range range = new Range(-1, 1);
        assertEquals(1, range.constrain(2), 0.000000001d);
    }

    // Test case: Constrain a value exactly at the lower bound
    @Test
    public void testConstrainAtLowerBound() {
        Range range = new Range(-1, 1);
        assertEquals(-1, range.constrain(-1), 0.000000001d);
    }

    // Test case: Constrain a value exactly at the upper bound
    @Test
    public void testConstrainAtUpperBound() {
        Range range = new Range(-1, 1);
        assertEquals(1, range.constrain(1), 0.000000001d);
    }


    // Test case: Intersecting range within the bounds
    @Test
    public void testIntersectsWithinBounds() {
        Range range = new Range(1.0, 5.0);
        assertTrue(range.intersects(2.0, 4.0));
    }


    // Test case: Intersecting range overlapping the lower bound
    @Test
    public void testIntersectsOverlappingLowerBound() {
        Range range = new Range(-1, 1);
        assertTrue(range.intersects(-2, 0));
    }

    // Test case: Intersecting range overlapping the upper bound
    @Test
    public void testIntersectsOverlappingUpperBound() {
        Range range = new Range(-1, 1);
        assertTrue(range.intersects(0, 2));
    }

    // Test case: Intersecting range exactly matching the bounds
    @Test
    public void testIntersectsExactBounds() {
        Range range = new Range(-1, 1);
        assertTrue(range.intersects(-1, 1));
    }

    // Test case: Non-intersecting range below the lower bound
    @Test
    public void testIntersectsBelowLowerBound() {
        Range range = new Range(-1, 1);
        assertFalse(range.intersects(-3, -2));
    }

    // Test case: Non-intersecting range above the upper bound
    @Test
    public void testIntersectsAboveUpperBound() {
        Range range = new Range(-1, 1);
        assertFalse(range.intersects(2, 3));
    }

    // Test case: Both ranges are null
    @Test
    public void testCombineIgnoringNaNBothNull() {
        assertNull(Range.combineIgnoringNaN(null, null));
    }

    // Test case: First range is null, second range is NaN range
    @Test
    public void testCombineIgnoringNaNFirstNullSecondNaN() {
        Range range2 = new Range(Double.NaN, Double.NaN);
        assertNull(Range.combineIgnoringNaN(null, range2));
    }

    // Test case: First range is NaN range, second range is null
    @Test
    public void testCombineIgnoringNaNFirstNaNSecondNull() {
        Range range1 = new Range(Double.NaN, Double.NaN);
        assertNull(Range.combineIgnoringNaN(range1, null));
    }

    // Test case: First range is null, second range is valid
    @Test
    public void testCombineIgnoringNaNFirstNullSecondValid() {
        Range range2 = new Range(1, 10);
        assertEquals(range2, Range.combineIgnoringNaN(null, range2));
    }

    // Test case: First range is valid, second range is null
    @Test
    public void testCombineIgnoringNaNFirstValidSecondNull() {
        Range range1 = new Range(1, 10);
        assertEquals(range1, Range.combineIgnoringNaN(range1, null));
    }

    // Test case: Both ranges are valid
    @Test
    public void testCombineIgnoringNaNBothValid() {
        Range range1 = new Range(1, 5);
        Range range2 = new Range(3, 10);
        Range expected = new Range(1, 10);
        assertEquals(expected, Range.combineIgnoringNaN(range1, range2));
    }

    // Test case: One range has NaN lower bound, other has NaN upper bound
    @Test
    public void testCombineIgnoringNaNWithNaNBounds() {
        Range range1 = new Range(Double.NaN, 5);
        Range range2 = new Range(3, Double.NaN);
        Range expected = new Range(3, 5);
        assertEquals(expected, Range.combineIgnoringNaN(range1, range2));
    }

    // Test case: Both ranges are NaN ranges
    @Test
    public void testCombineIgnoringNaNBothNaN() {
        Range range1 = new Range(Double.NaN, Double.NaN);
        Range range2 = new Range(Double.NaN, Double.NaN);
        assertNull(Range.combineIgnoringNaN(range1, range2));
    }


    @Test
    public void testEqualsSameBounds() {
        Range r1 = new Range(-1, 1);
        Range r2 = new Range(-1, 1);
        assertTrue("Ranges with same bounds should be equal", r1.equals(r2));
        assertTrue("Equal ranges should have same hashCode", r1.hashCode() == r2.hashCode());
    }

    @Test
    public void testEqualsDifferentBounds() {
        Range r1 = new Range(-1, 1);
        Range r2 = new Range(-2, 2);
        assertFalse("Ranges with different bounds should not be equal", r1.equals(r2));
    }

    @Test
    public void testToString() {
        Range r1 = new Range(-1, 1);
        assertEquals("Range[-1.0,1.0]", r1.toString());
    }

    @Test
    public void testExpandNoChange() {
        Range r1 = new Range(0, 10);
        Range expanded = Range.expand(r1, 0.0, 0.0);
        assertEquals("Range should be unchanged with 0.0 margins", r1, expanded);
    }
    
    // New Test Cases
    @Test
    public void testExpandToIncludeWithinBounds() {
        Range r1 = new Range(0, 5);
        Range expanded = Range.expandToInclude(r1, 3);
        assertEquals("Including a value inside range should not change the range", r1, expanded);
    }

    @Test
    public void testExpandToIncludeLargerValue() {
        Range r1 = new Range(0, 5);
        Range expanded = Range.expandToInclude(r1, 10);
        assertEquals("Range[0.0,10.0]", expanded.toString());
    }

    @Test
    public void testShiftAllowZeroCrossing() {
        Range base = new Range(-2.0, 2.0);
        Range shifted = Range.shift(base, 1.0, true);
        assertEquals("Range[-1.0,3.0]", shifted.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShiftNullRange() {
        Range.shift(null, 5.0); // should throw exception
    }

    @Test
    public void testScalePositive() {
        Range base = new Range(-2, 2);
        Range scaled = Range.scale(base, 2.0);
        assertEquals("Range[-4.0,4.0]", scaled.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testScaleNegativeFactor() {
        Range base = new Range(1, 2);
        Range.scale(base, -1.0); // should throw exception
    }


    @Test
    public void testShiftNoZeroCrossingPositiveCrossingZero() {
        Range base = new Range(1.0, 2.0);
        Range shifted = Range.shift(base, -2.0, false);
        assertEquals("Range[0.0,0.0]", shifted.toString());
    }

    @Test
    public void testShiftNoZeroCrossingNegativeCrossingZero() {
        Range base = new Range(-2.0, -1.0);
        Range shifted = Range.shift(base, 3.0, false);
        assertEquals("Range[0.0,0.0]", shifted.toString());
    }
   
    @Test
    public void testIntersectsLowerEqualsUpper() {
        Range range = new Range(1.0, 5.0);
        assertFalse(range.intersects(5.0, 5.0));
    }

    @Test
    public void testIntersectsBelowRange() {
        Range range = new Range(3.0, 7.0);
        assertFalse(range.intersects(1.0, 2.0));
    }

    @Test
    public void testIntersectsAboveRange() {
        Range range = new Range(3.0, 7.0);
        assertFalse(range.intersects(8.0, 9.0));
    }

    @Test
    public void testIntersectsPartialLowerOverlap() {
        Range range = new Range(3.0, 7.0);
        assertTrue(range.intersects(2.0, 4.0));
    }

    @Test
    public void testIntersectsPartialUpperOverlap() {
        Range range = new Range(3.0, 7.0);
        assertTrue(range.intersects(6.0, 8.0));
    }

    @Test
    public void testCombineWithNullRange() {
        Range range1 = new Range(1.0, 5.0);
        Range result = Range.combine(range1, null);
        assertEquals(range1, result);
    }

    @Test
    public void testCombineIgnoringNaNWithNaNRange() {
        Range range1 = new Range(1.0, 5.0);
        Range range2 = Range.combineIgnoringNaN(range1, new Range(Double.NaN, Double.NaN));
        assertEquals(range1, range2);
    }

    @Test
    public void testCombineIgnoringNaNOneValidRange() {
        Range range = Range.combineIgnoringNaN(new Range(1.0, 5.0), null);
        assertEquals(new Range(1.0, 5.0), range);
    }

    @Test
    public void testGetCentralValueWithExtremeValues() {
        Range range = new Range(Double.MIN_VALUE, Double.MAX_VALUE);
        assertEquals((Double.MIN_VALUE + Double.MAX_VALUE) / 2, range.getCentralValue(), 1e-10);
    }


    
    @Test
    public void testRangeWithEqualLowerAndUpper() {
        Range range = new Range(1.0, 1.0);
        assertEquals(0.0, range.getLength(), 1e-10);
        assertEquals(1.0, range.getCentralValue(), 1e-10);
        assertTrue(range.contains(1.0));
        assertFalse(range.contains(0.999999999));
        assertFalse(range.contains(1.000000001));
    }

    
    @Test
    public void testWithNaNAndInfinity() {
        Range range = new Range(1.0, 2.0);
        assertFalse(range.contains(Double.NaN));
        assertFalse(range.contains(Double.POSITIVE_INFINITY));
        assertFalse(range.contains(Double.NEGATIVE_INFINITY));
        
        assertFalse(range.intersects(Double.NaN, 1.5));
        assertFalse(range.intersects(1.5, Double.NaN));
        assertTrue(range.intersects(0.0, Double.POSITIVE_INFINITY));
        assertFalse(range.intersects(Double.NEGATIVE_INFINITY, 0.0));
    }

    @Test
    public void testConstructor_ValidRange() {
        // Check by using other methods to verify the range is constructed correctly
        Range range = new Range(1.0, 5.0);
        assertEquals(4.0, range.getLength(), 0.000001);
        assertEquals(3.0, range.getCentralValue(), 0.000001);
    }

    @Test
    public void testConstructor_SameLowerAndUpper() {
        Range range = new Range(3.0, 3.0);
        assertEquals(0.0, range.getLength(), 0.000001);
        assertEquals(3.0, range.getCentralValue(), 0.000001);
    }
    
    @Test
    public void testGetLength_PositiveRange() {
        Range range = new Range(1.0, 5.0);
        assertEquals(4.0, range.getLength(), 0.000001);
    }

    @Test
    public void testGetLength_NegativeRange() {
        Range range = new Range(-5.0, -1.0);
        assertEquals(4.0, range.getLength(), 0.000001);
    }

    @Test
    public void testGetLength_MixedRange() {
        Range range = new Range(-1.0, 1.0);
        assertEquals(2.0, range.getLength(), 0.000001);
    }

    @Test
    public void testGetLength_ZeroRange() {
        Range range = new Range(0.0, 0.0);
        assertEquals(0.0, range.getLength(), 0.000001);
    }

    @Test
    public void testGetLength_LargeRange() {
        Range range = new Range(1e10, 1e10 + 1);
        assertEquals(1.0, range.getLength(), 0.000001);
    }

    @Test
    public void testGetLength_WithSameValues() {
        Range range = new Range(5.0, 5.0);
        assertEquals(0.0, range.getLength(), 0.000001);
    }
    
    @Test
    public void testGetCentralValue_NormalRange() {
        Range range = new Range(1.0, 5.0);
        assertEquals(3.0, range.getCentralValue(), 0.000001);
    }

    @Test
    public void testGetCentralValue_NegativeRange() {
        Range range = new Range(-5.0, -1.0);
        assertEquals(-3.0, range.getCentralValue(), 0.000001);
    }

    @Test
    public void testGetCentralValue_MixedRange() {
        Range range = new Range(-1.0, 1.0);
        assertEquals(0.0, range.getCentralValue(), 0.000001);
    }

    @Test
    public void testGetCentralValue_SameValues() {
        Range range = new Range(5.0, 5.0);
        assertEquals(5.0, range.getCentralValue(), 0.000001);
    }
    
    @Test
    public void testContains_ValueWithinRange() {
        Range range = new Range(1.0, 5.0);
        assertTrue(range.contains(3.0));
    }

    @Test
    public void testContains_ValueAtLowerBound() {
        Range range = new Range(1.0, 5.0);
        assertTrue(range.contains(1.0));
    }

    @Test
    public void testContains_ValueAtUpperBound() {
        Range range = new Range(1.0, 5.0);
        assertTrue(range.contains(5.0));
    }

    @Test
    public void testContains_ValueJustBelowLowerBound() {
        Range range = new Range(1.0, 5.0);
        assertFalse(range.contains(0.999999));
    }

    @Test
    public void testContains_ValueJustAboveUpperBound() {
        Range range = new Range(1.0, 5.0);
        assertFalse(range.contains(5.000001));
    }

    @Test
    public void testContains_ValueBelowLowerBound() {
        Range range = new Range(1.0, 5.0);
        assertFalse(range.contains(0.0));
    }

    @Test
    public void testContains_ValueAboveUpperBound() {
        Range range = new Range(1.0, 5.0);
        assertFalse(range.contains(6.0));
    }

    @Test
    public void testContains_SinglePointRange() {
        Range range = new Range(2.0, 2.0);
        assertTrue(range.contains(2.0));
        assertFalse(range.contains(1.999999));
        assertFalse(range.contains(2.000001));
    }

    @Test
    public void testContains_NegativeValues() {
        Range range = new Range(-5.0, -1.0);
        assertTrue(range.contains(-3.0));
        assertTrue(range.contains(-5.0));
        assertTrue(range.contains(-1.0));
        assertFalse(range.contains(-6.0));
        assertFalse(range.contains(0.0));
    }
    
    @Test
    public void testIntersects_RangesOverlap() {
        Range range = new Range(1.0, 5.0);
        assertTrue(range.intersects(3.0, 7.0));
    }

    @Test
    public void testIntersects_RangeStartsWithin() {
        Range range = new Range(1.0, 5.0);
        assertTrue(range.intersects(2.0, 4.0));
    }

    @Test
    public void testIntersects_RangeEndsWithin() {
        Range range = new Range(3.0, 7.0);
        assertTrue(range.intersects(1.0, 5.0));
    }

    @Test
    public void testIntersects_RangeContains() {
        Range range = new Range(1.0, 5.0);
        assertTrue(range.intersects(0.0, 6.0));
    }

    @Test
    public void testIntersects_RangesDoNotOverlap() {
        Range range = new Range(1.0, 5.0);
        assertFalse(range.intersects(6.0, 8.0));
    }

    @Test
    public void testIntersects_SinglePointRangeOverlap() {
        Range range = new Range(1.0, 5.0);
        assertTrue(range.intersects(3.0, 3.0));
    }

    @Test
    public void testIntersects_SinglePointRangeNoOverlap() {
        Range range = new Range(1.0, 5.0);
        assertFalse(range.intersects(6.0, 6.0));
    }

    @Test
    public void testIntersects_NegativeRanges() {
        Range range = new Range(-5.0, -1.0);
        assertTrue(range.intersects(-3.0, 0.0));
        assertFalse(range.intersects(0.0, 1.0));
    }

    @Test
    public void testIntersects_SmallOverlap() {
        Range range = new Range(1.0, 5.0);
        assertTrue(range.intersects(4.9, 5.1));
    }

    @Test
    public void testIntersects_NoOverlapCloseToBound() {
        Range range = new Range(1.0, 5.0);
        assertFalse(range.intersects(0.9, 0.95));
    }
    
    @Test
    public void testGetLength_SameValues() {
        Range range = new Range(5.0, 5.0);
        assertEquals(0.0, range.getLength(), 0.000001);
    }

    @Test
    public void testGetCentralValue_LargeRange() {
        Range range = new Range(1e10, 1e10 + 2);
        assertEquals(1e10 + 1, range.getCentralValue(), 0.000001);
    }
    
    @Test
    public void testContains_VeryCloseToBoundaries() {
        Range range = new Range(1.0, 2.0);
        assertTrue(range.contains(1.0 + 1e-10));
        assertTrue(range.contains(2.0 - 1e-10));
    }

    @Test
    public void testContains_LargeValues() {
        Range range = new Range(-1e10, 1e10);
        assertTrue(range.contains(0.0));
    }
    
   @Test
    public void testIntersects_OverlapWithSmallRange() {
        Range range = new Range(1.0, 5.0);
        assertTrue(range.intersects(4.9, 5.1));
    }

    @Test
    public void testIntersects_OverlapWithSameValues() {
        Range range = new Range(1.0, 5.0);
        assertTrue(range.intersects(2.0, 2.0));
    }

    @Test
    public void testIntersects_AdjacentNegativeRanges() {
        Range range = new Range(-5.0, -1.0);
        assertFalse(range.intersects(0.0, 1.0));
    }
    
    @Test
    public void testIntersectsWithEdgeValues() {
        Range range = new Range(10.0, 20.0);
        assertTrue(range.intersects(10.0, 15.0)); // Lower bound
        assertTrue(range.intersects(15.0, 20.0)); // Upper bound
        assertFalse(range.intersects(5.0, 9.9));  // Below range
        assertFalse(range.intersects(20.1, 25.0)); // Above range
    }
    
    @Test
    public void testConstrainWithBoundaryValues() {
        Range range = new Range(10.0, 20.0);
        assertEquals(10.0, range.constrain(5.0), 0.001);  // Below lower bound
        assertEquals(20.0, range.constrain(25.0), 0.001); // Above upper bound
        assertEquals(15.0, range.constrain(15.0), 0.001); // Within range
    }

    @Test
    public void testCombineWithNullAndNaN() {
        Range range1 = new Range(5.0, 15.0);
        Range range2 = null;
        assertEquals(range1, Range.combine(range1, range2)); // One range is null
        assertEquals(range1, Range.combine(range2, range1));

        Range nanRange = new Range(Double.NaN, Double.NaN);
        assertEquals(range1, Range.combineIgnoringNaN(range1, nanRange)); // Ignore NaN range
    }

    
    @Test
    public void testCombineIgnoringNaNWithValidRanges() {
        Range range1 = new Range(5.0, 15.0);
        Range range2 = new Range(10.0, 20.0);
        Range combined = Range.combineIgnoringNaN(range1, range2);
        assertEquals(5.0, combined.getLowerBound(), 0.001);
        assertEquals(20.0, combined.getUpperBound(), 0.001);
    }

    
    @Test
    public void testCombineIgnoringNaNWithNaN() {
        Range validRange = new Range(5.0, 15.0);
        Range nanRange = new Range(Double.NaN, Double.NaN);

        assertEquals(validRange, Range.combineIgnoringNaN(validRange, nanRange));
        assertEquals(validRange, Range.combineIgnoringNaN(nanRange, validRange));
        assertNull(Range.combineIgnoringNaN(nanRange, nanRange));
    }

    
    @Test
    public void testIsNaNRange() {
        Range nanRange = new Range(Double.NaN, Double.NaN);
        assertTrue(nanRange.isNaNRange());

        Range validRange = new Range(5.0, 15.0);
        assertFalse(validRange.isNaNRange());
    }

    
    @Test
    public void testCombineWithBoundaryRanges() {
        Range range1 = new Range(Double.MIN_VALUE, 10.0);
        Range range2 = new Range(5.0, Double.MAX_VALUE);

        Range combined = Range.combine(range1, range2);
        assertEquals(Double.MIN_VALUE, combined.getLowerBound(), 0.001);
        assertEquals(Double.MAX_VALUE, combined.getUpperBound(), 0.001);
    }

    @Test
    public void testIntersectsWithReversedBounds() {
        Range range = new Range(10.0, 20.0);
        assertFalse(range.intersects(25.0, 15.0)); // Reversed bounds (invalid case)
    }

    
    
    @Test
    public void testConstrainWithNaN() {
        Range range = new Range(10.0, 20.0);
        assertTrue(Double.isNaN(range.constrain(Double.NaN)));
    }

    
    @Test
    public void testCombineIgnoringNaNWithSingleNaNBoundary() {
        Range range1 = new Range(Double.NaN, 20.0);
        Range range2 = new Range(5.0, Double.NaN);

        Range result1 = Range.combineIgnoringNaN(range1, range2);
        assertEquals(5.0, result1.getLowerBound(), 0.001);
        assertEquals(20.0, result1.getUpperBound(), 0.001);

        Range result2 = Range.combineIgnoringNaN(range2, range1);
        assertEquals(5.0, result2.getLowerBound(), 0.001);
        assertEquals(20.0, result2.getUpperBound(), 0.001);
    }

    @Test
    public void testCombineWithIdenticalRanges() {
        Range range = new Range(5.0, 15.0);
        Range combined = Range.combine(range, range);
        assertEquals(5.0, combined.getLowerBound(), 0.001);
        assertEquals(15.0, combined.getUpperBound(), 0.001);
    }

    
    @Test
    public void testCombineIgnoringNaNWithPartialNaN() {
        Range range1 = new Range(Double.NaN, 20.0);
        Range range2 = new Range(5.0, Double.NaN);

        Range result = Range.combineIgnoringNaN(range1, range2);
        assertEquals(5.0, result.getLowerBound(), 0.001);
        assertEquals(20.0, result.getUpperBound(), 0.001);
    }

    
    @Test
    public void testIntersectsWithNaNBounds() {
        Range range = new Range(10.0, 20.0);
        assertFalse(range.intersects(Double.NaN, 15.0));
        assertFalse(range.intersects(15.0, Double.NaN));
        assertFalse(range.intersects(Double.NaN, Double.NaN));
    }

    
    @Test
    public void testConstrainWithExtremeValues() {
        Range range = new Range(10.0, 20.0);
        assertEquals(10.0, range.constrain(Double.MIN_VALUE), 0.001);
        assertEquals(20.0, range.constrain(Double.MAX_VALUE), 0.001);
    }

    @Test
    public void testCombineIgnoringNaNWithOnlyNaN() {
        Range nanRange1 = new Range(Double.NaN, Double.NaN);
        Range nanRange2 = new Range(Double.NaN, Double.NaN);

        assertNull(Range.combineIgnoringNaN(nanRange1, nanRange2));
    }

    @Test
    public void testCombineWithInvertedRanges() {
        Range range1 = new Range(20.0, 30.0);
        Range range2 = new Range(5.0, 15.0);

        Range combined = Range.combine(range1, range2);
        assertEquals(5.0, combined.getLowerBound(), 0.001);
        assertEquals(30.0, combined.getUpperBound(), 0.001);
    }

    @Test
    public void testIsNaNRangeWithPartialNaN() {
        Range range1 = new Range(Double.NaN, 20.0);
        assertFalse(range1.isNaNRange());

        Range range2 = new Range(10.0, Double.NaN);
        assertFalse(range2.isNaNRange());
    }

    @Test
    public void testIntersectsWithEqualBounds() {
        Range range = new Range(10.0, 20.0);
        assertTrue(range.intersects(15.0, 15.0)); // Exact match
        assertFalse(range.intersects(25.0, 25.0)); // Outside range
    }
    
    @Test
    public void testIntersectsWithBoundaryValues() {
        Range range = new Range(10.0, 20.0);
        assertTrue(range.intersects(10.0, 20.0)); // Exact boundaries
        assertFalse(range.intersects(20.0, 30.0)); // Outside upper bound
        assertFalse(range.intersects(5.0, 10.0));  // Outside lower bound
        assertFalse(range.intersects(21.0, 25.0)); // Beyond upper bound
        assertFalse(range.intersects(0.0, 9.9));   // Below lower bound
    }

    @Test
    public void testConstrainWithExtremes() {
        Range range = new Range(10.0, 20.0);
        assertEquals(10.0, range.constrain(Double.MIN_VALUE), 0.001); // Below lower bound
        assertEquals(20.0, range.constrain(Double.MAX_VALUE), 0.001); // Above upper bound
    }

    @Test
    public void testIncrementAndDecrementBoundaries() {
        Range range = new Range(10.0, 20.0);
        
        // Manually increment and decrement bounds to check correctness
        assertTrue(range.intersects(10.1, 20.1)); // Slight increment
        assertTrue(range.intersects(9.9, 19.9));  // Slight decrement
    }

    @Test
    public void testIntersectsWithNaN() {
        Range range = new Range(10.0, 20.0);
        assertFalse(range.intersects(Double.NaN, 15.0));
        assertFalse(range.intersects(15.0, Double.NaN));
        assertFalse(range.intersects(Double.NaN, Double.NaN));
    }

    @Test
    public void testCombineWithNullRanges() {
        Range range1 = new Range(10.0, 20.0);
        Range result1 = Range.combine(null, range1);
        assertEquals(10.0, result1.getLowerBound(), 0.001);
        assertEquals(20.0, result1.getUpperBound(), 0.001);

        Range result2 = Range.combine(range1, null);
        assertEquals(10.0, result2.getLowerBound(), 0.001);
        assertEquals(20.0, result2.getUpperBound(), 0.001);
    }

    @Test
    public void testCombineIgnoringNaNWithAllNaN() {
        Range range1 = new Range(Double.NaN, Double.NaN);
        Range range2 = new Range(Double.NaN, Double.NaN);
        assertNull(Range.combineIgnoringNaN(range1, range2)); // Expect null
    }

    
    @Test
    public void testCombineIgnoringNaN_BothNaN() {
        Range r1 = new Range(Double.NaN, Double.NaN);
        Range r2 = new Range(Double.NaN, Double.NaN);
        assertNull(Range.combineIgnoringNaN(r1, r2));
    }

    @Test
    public void testCombineIgnoringNaN_OneNaN() {
        Range r1 = new Range(Double.NaN, Double.NaN);
        Range r2 = new Range(1.0, 2.0);
        assertEquals(r2, Range.combineIgnoringNaN(r1, r2));
    }

    
    @Test
    public void testShiftWithNoZeroCrossing_PositiveToZero() {
        Range shifted = Range.shift(new Range(1.0, 3.0), -5.0, false);
        assertEquals(0.0, shifted.getLowerBound(), 0.001);
        assertEquals(0.0, shifted.getUpperBound(), 0.001);
    }

    @Test
    public void testShiftWithNoZeroCrossing_NegativeToZero() {
        Range shifted = Range.shift(new Range(-3.0, -1.0), 5.0, false);
        assertEquals(0.0, shifted.getLowerBound(), 0.001);
        assertEquals(0.0, shifted.getUpperBound(), 0.001);
    }

    
    @Test
    public void testIntersects_ExactBoundaries() {
        Range r = new Range(1.0, 5.0);
        assertTrue(r.intersects(1.0, 5.0));
        assertFalse(r.intersects(5.1, 6.0));
        assertFalse(r.intersects(0.0, 0.9));
    }

    @Test
    public void testExpandToInclude_NaNValues() {
        Range r = Range.expandToInclude(null, Double.NaN);
        assertTrue(Double.isNaN(r.getLowerBound()));
        assertTrue(Double.isNaN(r.getUpperBound()));
    }

    @Test
    public void testScale_ZeroFactor() {
        Range r = new Range(1.0, 5.0);
        Range scaled = Range.scale(r, 0.0);
        assertEquals(0.0, scaled.getLowerBound(), 0.001);
        assertEquals(0.0, scaled.getUpperBound(), 0.001);
    }

    @Test
    public void testIsNaNRange_BothNaN() {
        Range r = new Range(Double.NaN, Double.NaN);
        assertTrue(r.isNaNRange());
    }

    @Test
    public void testIsNaNRange_OneNaN() {
        Range r = new Range(Double.NaN, 5.0);
        assertFalse(r.isNaNRange());
    }

    @Test
    public void testExpandToInclude_NullRange() {
        Range result = Range.expandToInclude(null, 5.0);
        assertEquals(5.0, result.getLowerBound(), 0.001);
        assertEquals(5.0, result.getUpperBound(), 0.001);
    }

    @Test
    public void testExpandToInclude_LowerBoundEdgeIncrement() {
        Range range = new Range(2.0, 6.0);
        Range result = Range.expandToInclude(range, 1.9);
        assertEquals(1.9, result.getLowerBound(), 0.001);
        assertEquals(6.0, result.getUpperBound(), 0.001);
    }

    @Test
    public void testExpandToInclude_UpperBoundEdgeIncrement() {
        Range range = new Range(2.0, 6.0);
        Range result = Range.expandToInclude(range, 6.1);
        assertEquals(2.0, result.getLowerBound(), 0.001);
        assertEquals(6.1, result.getUpperBound(), 0.001);
    }

    @Test
    public void testExpandToInclude_LowerBoundWithNegatedValue() {
        Range range = new Range(2.0, 6.0);
        Range result = Range.expandToInclude(range, -1.0);
        assertEquals(-1.0, result.getLowerBound(), 0.001);
        assertEquals(6.0, result.getUpperBound(), 0.001);
    }

    @Test
    public void testExpandToInclude_UpperBoundWithNegatedValue() {
        Range range = new Range(2.0, 6.0);
        Range result = Range.expandToInclude(range, 8.0);
        assertEquals(2.0, result.getLowerBound(), 0.001);
        assertEquals(8.0, result.getUpperBound(), 0.001);
    }

    @Test
    public void testExpand_ZeroMargin() {
        Range range = new Range(2.0, 6.0);
        Range expanded = Range.expand(range, 0.0, 0.0);
        assertEquals(2.0, expanded.getLowerBound(), 0.001);
        assertEquals(6.0, expanded.getUpperBound(), 0.001);
    }

    @Test
    public void testExpand_NegativeMargins() {
        Range range = new Range(4.0, 10.0);
        Range expanded = Range.expand(range, -1.0, -1.0);
        assertEquals(7.0, expanded.getLowerBound(), 0.001);
        assertEquals(7.0, expanded.getUpperBound(), 0.001);
    }

    @Test
    public void testExpand_BoundaryAddSubtraction() {
        Range range = new Range(3.0, 9.0);
        Range expanded = Range.expand(range, 0.5, 0.5);
        assertEquals(0.0, expanded.getLowerBound(), 0.001);
        assertEquals(12.0, expanded.getUpperBound(), 0.001);
    }

    
    
    
    

    
   
    


}
