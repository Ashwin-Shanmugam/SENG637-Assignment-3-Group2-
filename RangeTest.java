package org.jfree.data;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.*;

public class RangeTest {

    private Range range;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception { }

    @Before
    public void setUp() {
        // Default range; many tests override it as needed.
        range = new Range(-2.0, 5.0);
    }

    // -----------------------------------------------------------------
    // getLowerBound() Tests
    // -----------------------------------------------------------------

    @Test
    public void test_getLowerBound_NegativeNominal() {
        range = new Range(-10.0, 10.0);
        assertEquals(-10.0, range.getLowerBound(), 0.0000001);
    }

    @Test
    public void test_getLowerBound_JustBelowZero() {
        range = new Range(-0.1, 10.0);
        assertEquals(-0.1, range.getLowerBound(), 0.0000001);
    }

    @Test
    public void test_getLowerBound_ExtremeNegative() {
        range = new Range(-Double.MAX_VALUE, 0.0);
        assertEquals(-Double.MAX_VALUE, range.getLowerBound(), 0.000000001);
    }

    @Test
    public void test_getLowerBound_ZeroBoundary() {
        range = new Range(0.0, 10.0);
        assertEquals(0.0, range.getLowerBound(), 0.0000001);
    }

    @Test
    public void test_getLowerBound_PositiveNominal() {
        range = new Range(10.0, 20.0);
        assertEquals(10.0, range.getLowerBound(), 0.000000001);
    }

    @Test
    public void test_getLowerBound_JustAboveZero() {
        range = new Range(0.1, 10.0);
        assertEquals(0.1, range.getLowerBound(), 0.000000001);
    }

    @Test
    public void test_getLowerBound_EqualBounds() {
        range = new Range(5.0, 5.0);
        assertEquals(5.0, range.getLowerBound(), 0.000000001);
    }

    // -----------------------------------------------------------------
    // getUpperBound() Tests
    // -----------------------------------------------------------------

    @Test
    public void test_getUpperBound_NegativeNominal() {
        range = new Range(-20.0, -10.0);
        assertEquals(-10.0, range.getUpperBound(), 0.000000001);
    }

    @Test
    public void test_getUpperBound_JustBelowZero() {
        range = new Range(-10.0, -0.1);
        assertEquals(-0.1, range.getUpperBound(), 0.000000001);
    }

    @Test
    public void test_getUpperBound_ZeroBoundary() {
        range = new Range(-10.0, 0.0);
        assertEquals(0.0, range.getUpperBound(), 0.000000001);
    }

    @Test
    public void test_getUpperBound_PositiveNominal() {
        range = new Range(10.0, 20.0);
        assertEquals(20.0, range.getUpperBound(), 0.000000001);
    }

    @Test
    public void test_getUpperBound_JustAboveZero() {
        range = new Range(-10.0, 0.1);
        assertEquals(0.1, range.getUpperBound(), 0.000000001);
    }

    @Test
    public void test_getUpperBound_ExtremePositive() {
        range = new Range(0.0, Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, range.getUpperBound(), 0.000000001);
    }

    @Test
    public void test_getUpperBound_EqualBounds() {
        range = new Range(5.0, 5.0);
        assertEquals(5.0, range.getUpperBound(), 0.000000001);
    }

    // ---------------------------------------------------------
    // getLength() Tests
    // ---------------------------------------------------------

    @Test
    public void test_getLength_BothPositive() {
        range = new Range(2.0, 5.0);
        assertEquals(3.0, range.getLength(), 0.000000001);
    }

    @Test
    public void test_getLength_BothNegative() {
        range = new Range(-10.0, -2.0);
        assertEquals(8.0, range.getLength(), 0.000000001);
    }

    @Test
    public void test_getLength_MixedSigns() {
        range = new Range(-5.0, 5.0);
        assertEquals(10.0, range.getLength(), 0.000000001);
    }

    @Test
    public void test_getLength_ZeroLength() {
        range = new Range(5.0, 5.0);
        assertEquals(0.0, range.getLength(), 0.000000001);
    }

    @Test
    public void test_getLength_LowerBoundZero() {
        range = new Range(0.0, 10.0);
        assertEquals(10.0, range.getLength(), 0.000000001);
    }

    @Test
    public void test_getLength_UpperBoundZero() {
        range = new Range(-10.0, 0.0);
        assertEquals(10.0, range.getLength(), 0.000000001);
    }

    @Test
    public void test_getLength_ExtremeValues() {
        range = new Range(-1_000_000.0, 1_000_000.0);
        assertEquals(2_000_000.0, range.getLength(), 0.000000001);
    }

    // ---------------------------------------------------------
    // intersects(double, double) Tests
    // ---------------------------------------------------------

    @Test
    public void intersectsWhenTouchingAtUpperBound() {
        assertTrue(range.intersects(5.0, 6.0)); // endpoint touching should intersect
    }

    @Test
    public void intersectsReturnsFalseForDisjointAbove() {
        assertFalse(range.intersects(6.0, 7.0));
    }

    @Test
    public void intersectsReturnsFalseForDisjointBelow() {
        assertFalse(range.intersects(-5.0, -3.0));
    }

    @Test
    public void intersectsWhenFullyInsideRange() {
        assertTrue(range.intersects(-1.0, 1.0));
    }

    @Test
    public void intersectsWhenRangeFullyInsideInterval() {
        assertTrue(range.intersects(-3.0, 6.0));
    }

    // ---------------------------------------------------------
    // constrain(double) Tests
    // ---------------------------------------------------------

    @Test
    public void constrainReturnsUpperWhenAbove() {
        assertEquals(5.0, range.constrain(10.0), 1e-9);
    }

    @Test
    public void constrainReturnsLowerWhenBelow() {
        assertEquals(-2.0, range.constrain(-10.0), 1e-9);
    }

    @Test
    public void constrainReturnsValueWhenInside() {
        assertEquals(1.5, range.constrain(1.5), 1e-9);
    }

    @Test
    public void constrainReturnsLowerAtExactLowerBound() {
        assertEquals(-2.0, range.constrain(-2.0), 1e-9);
    }

    @Test
    public void constrainReturnsUpperAtExactUpperBound() {
        assertEquals(5.0, range.constrain(5.0), 1e-9);
    }
    
 // ---------------------------------------------------------
 // contains(double) Tests
 // ---------------------------------------------------------
 @Test
 public void testContains_ValueInside() {
     assertTrue("Value inside the range should return true", range.contains(1.0));
 }

 @Test
 public void testContains_ExactLowerBound() {
     assertTrue("Exact lower bound should return true", range.contains(-2.0));
 }

 @Test
 public void testContains_ExactUpperBound() {
     assertTrue("Exact upper bound should return true", range.contains(5.0));
 }

 @Test
 public void testContains_ValueBelowLowerBound() {
     assertFalse("Value below the range should return false", range.contains(-5.0));
 }

 @Test
 public void testContains_ValueAboveUpperBound() {
     assertFalse("Value above the range should return false", range.contains(10.0));
 }

 // ---------------------------------------------------------
 // combine(Range, Range) Tests
 // ---------------------------------------------------------
 @Test
 public void testCombine_BothNull() {
     assertNull("Combining two null ranges should return null", Range.combine(null, null));
 }

 @Test
 public void testCombine_FirstNullSecondValid() {
     Range range2 = new Range(1.0, 5.0);
     assertEquals("If first is null, should return second", range2, Range.combine(null, range2));
 }

 @Test
 public void testCombine_FirstValidSecondNull() {
     Range range1 = new Range(1.0, 5.0);
     assertEquals("If second is null, should return first", range1, Range.combine(range1, null));
 }

 @Test
 public void testCombine_BothValidOverlapping() {
     Range range1 = new Range(1.0, 5.0);
     Range range2 = new Range(3.0, 10.0);
     Range expected = new Range(1.0, 10.0);
     assertEquals("Should return outer boundaries of both ranges", expected, Range.combine(range1, range2));
 }

 // ---------------------------------------------------------
 // expandToInclude(Range, double) Tests
 // ---------------------------------------------------------
 @Test
 public void testExpandToInclude_NullRange() {
     Range expected = new Range(5.0, 5.0);
     assertEquals("Expanding a null range should return a range with value as both bounds", 
                  expected, Range.expandToInclude(null, 5.0));
 }

 @Test
 public void testExpandToInclude_ValueBelowLowerBound() {
     Range expected = new Range(-5.0, 5.0);
     assertEquals("Should expand lower bound to include value", expected, Range.expandToInclude(range, -5.0));
 }

 @Test
 public void testExpandToInclude_ValueAboveUpperBound() {
     Range expected = new Range(-2.0, 10.0);
     assertEquals("Should expand upper bound to include value", expected, Range.expandToInclude(range, 10.0));
 }

 @Test
 public void testExpandToInclude_ValueAlreadyInside() {
     assertEquals("Should return the same range if value is inside", range, Range.expandToInclude(range, 1.0));
 }
 
 // ---------------------------------------------------------
 // equals(Object) Tests
 // ---------------------------------------------------------
 @Test
 public void testEquals_NullObject() {
     assertFalse("Should return false for null object", range.equals(null));
 }

 @Test
 public void testEquals_DifferentClass() {
     assertFalse("Should return false for a non-Range object", range.equals("Not a Range"));
 }

 @Test
 public void testEquals_SameReference() {
     assertTrue("Should return true for the same reference", range.equals(range));
 }

 @Test
 public void testEquals_EquivalentObject() {
     Range equivalentRange = new Range(-2.0, 5.0);
     assertTrue("Should return true for an equivalent range object", range.equals(equivalentRange));
 }

 @Test
 public void testEquals_DifferentLowerBound() {
     Range differentLower = new Range(-1.0, 5.0);
     assertFalse("Should return false if lower bounds are different", range.equals(differentLower));
 }

 @Test
 public void testEquals_DifferentUpperBound() {
     Range differentUpper = new Range(-2.0, 6.0);
     assertFalse("Should return false if upper bounds are different", range.equals(differentUpper));
 }

 // ---------------------------------------------------------
 // shift(Range, double) Tests
 // ---------------------------------------------------------
 @Test
 public void testShift_PositiveDelta() {
     Range expected = new Range(0.0, 7.0); // (-2.0 + 2.0, 5.0 + 2.0)
     assertEquals("Should shift bounds by positive delta", expected, Range.shift(range, 2.0));
 }

 @Test
 public void testShift_NegativeDelta() {
     Range expected = new Range(-4.0, 3.0); // (-2.0 - 2.0, 5.0 - 2.0)
     assertEquals("Should shift bounds by negative delta", expected, Range.shift(range, -2.0));
 }

 @Test
 public void testShift_ZeroDelta() {
     assertEquals("Should not change bounds for zero delta", range, Range.shift(range, 0.0));
 }
 
//---------------------------------------------------------
//Exception and Validation Tests
//---------------------------------------------------------
@Test(expected = IllegalArgumentException.class)
public void testConstructor_LowerGreaterThanUpper() {
  new Range(10.0, 5.0);
}

@Test(expected = IllegalArgumentException.class)
public void testScale_NegativeFactor() {
  Range.scale(range, -1.0);
}

//---------------------------------------------------------
//combineIgnoringNaN(Range, Range) Tests
//---------------------------------------------------------
@Test
public void testCombineIgnoringNaN_Range1Null_Range2Valid() {
 assertEquals(range, Range.combineIgnoringNaN(null, range));
}

@Test
public void testCombineIgnoringNaN_Range1Null_Range2NaN() {
 Range nanRange = new Range(Double.NaN, Double.NaN);
 assertNull(Range.combineIgnoringNaN(null, nanRange));
}

@Test
public void testCombineIgnoringNaN_Range2Null_Range1Valid() {
 assertEquals(range, Range.combineIgnoringNaN(range, null));
}

@Test
public void testCombineIgnoringNaN_Range2Null_Range1NaN() {
 Range nanRange = new Range(Double.NaN, Double.NaN);
 assertNull(Range.combineIgnoringNaN(nanRange, null));
}

@Test
public void testCombineIgnoringNaN_BothNaN() {
 Range nanRange = new Range(Double.NaN, Double.NaN);
 assertNull(Range.combineIgnoringNaN(nanRange, nanRange));
}

@Test
public void testCombineIgnoringNaN_BothValid() {
 Range r1 = new Range(1.0, 3.0);
 Range r2 = new Range(2.0, 5.0);
 assertEquals(new Range(1.0, 5.0), Range.combineIgnoringNaN(r1, r2));
}

//---------------------------------------------------------
//shift(Range, double, boolean) Tests
//---------------------------------------------------------
@Test
public void testShift_AllowZeroCrossing() {
 // Shifting (-2.0, 5.0) by 3.0 should cross zero and become (1.0, 8.0)
 assertEquals(new Range(1.0, 8.0), Range.shift(range, 3.0, true));
}

@Test
public void testShift_NoZeroCrossing_PositiveValues() {
 Range r = new Range(2.0, 5.0);
 assertEquals(new Range(1.0, 4.0), Range.shift(r, -1.0, false));
}

@Test
public void testShift_NoZeroCrossing_NegativeValues() {
 Range r = new Range(-5.0, -2.0);
 assertEquals(new Range(-4.0, -1.0), Range.shift(r, 1.0, false)); 
}

@Test
public void testShift_NoZeroCrossing_ZeroValue() {
 Range r = new Range(0.0, 0.0);
 assertEquals(new Range(1.0, 1.0), Range.shift(r, 1.0, false));
}


//---------------------------------------------------------
//General Method Tests
//---------------------------------------------------------
@Test
public void testGetCentralValue() {
 assertEquals(1.5, range.getCentralValue(), 1e-9);
}

@Test
public void testIntersects_RangeObject() {
 assertTrue(range.intersects(new Range(-1.0, 1.0)));
}

@Test
public void testScale_PositiveFactor() {
 Range expected = new Range(-4.0, 10.0);
 assertEquals(expected, Range.scale(range, 2.0));
}

@Test
public void testIsNaNRange_True() {
 Range nanRange = new Range(Double.NaN, Double.NaN);
 assertTrue(nanRange.isNaNRange());
}

@Test
public void testIsNaNRange_False() {
 assertFalse(range.isNaNRange());
}

@Test
public void testHashCode() {
 range.hashCode(); // Executes hash code calculation for statement coverage
}

@Test
public void testToString() {
 assertEquals("Range[-2.0,5.0]", range.toString());
}

@Test
public void testExpand_LowerGreaterThanUpperAfterMargins() {
 // Tests the if (lower > upper) branch inside the expand method
 Range result = Range.expand(new Range(2.0, 6.0), -0.5, -0.5); 
 assertEquals(new Range(4.0, 4.0), result);
}
    @After
    public void tearDown() throws Exception { }

    @AfterClass
    public static void tearDownAfterClass() throws Exception { }
}
