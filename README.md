# SENG637 Assignment 3 Report

## Part A: Control-Flow Coverage
- TODO: insert coverage metrics (statement/branch/condition) and tool screenshots.
- TODO: summarize tools tried, any issues, fixes.

## Part B: Data-Flow Coverage (Manual)
- Methods analyzed: DataUtilities.calculateColumnTotal (2-arg and 3-arg) and Range.constrain.
- Flowcharts: see Appendix A (images calculateColumnTotal.png, contains.png).
- DU tables and test coverage mappings: see Appendix B.

# Appendix A: Data-flow Graphs (Part B)

**DataUtilities.calculateColumnTotal**  
![calculateColumnTotal flowchart](./calculateColumnTotal.png)

**Range.constrain**  
![constrain flowchart](./contains.png)

# Appendix B: Data Flow Tables (Part B)

### DataUtilities.calculateColumnTotal DU-pairs coverage

| Var    | DU-pairs (def -> use) | Reachable? |
|--------|-----------------------|------------|
| data   | getRowCount(); getValue(r,col) | Yes |
| column | getValue(r,col) | Yes |
| total  | total=0 -> total += ... -> return | Yes |
| rowCount | r < rowCount | Yes |
| r      | init/incr -> guard; -> getValue | Yes |
| r2     | init/incr -> guard; -> getValue | No (dead) |
| n      | def -> (n!=null); def -> n.doubleValue() | Yes |

**Test coverage (2-arg overload)**

| Test (class)                                   | Non-null add | Null skip | Multi-row sum | Empty table | Notes |
|------------------------------------------------|--------------|-----------|---------------|-------------|-------|
| calculateColumnTotalForSingleRow (DataUtilitiesTest) | Yes | No | No | No | single value |
| calculateColumnTotalForTwoValues (DataUtilitiesTest) | Yes | No | Yes | No | sums 2 rows |
| calculateColumnTotalForNegativeValues (DataUtilitiesTest) | Yes | No | Yes | No | negative values |
| calculateColumnTotalForLargeNumbers (DataUtilitiesTest) | Yes | No | Yes | No | large doubles |
| columnTotalMultipleRowsAddsAll (DataUtilitiesTest) | Yes | No | Yes | No | 3 rows |
| columnTotalSkipsNulls (DataUtilitiesTest) | Yes | Yes | Yes | No | mixes null/non-null |
| calculateColumnTotalForEmptyTableIsZero (DataUtilitiesTest) | No | No | No | Yes | rowCount=0 |

**Test coverage (3-arg overload)**

| Test (class)                                            | Valid rows in-bounds | Out-of-bounds skipped | Empty valid[] | Negative index ignored | Duplicates counted |
|---------------------------------------------------------|----------------------|-----------------------|---------------|------------------------|--------------------|
| columnTotalWithValidRowsFiltersRows (DataUtilitiesExtraTest) | Yes | No | No | No | No |
| columnTotalWithValidRowsSkipsOutOfBounds (DataUtilitiesExtraTest) | No | Yes | No | No | No |
| columnTotalWithValidRowsEmptyArrayReturnsZero (DataUtilitiesExtraTest) | No | No | Yes | No | No |
| columnTotalWithValidRowsIgnoresNegativeIndices (DataUtilitiesExtraTest) | No | No | No | Yes | No |
| columnTotalWithValidRowsCountsDuplicates (DataUtilitiesExtraTest) | No | No | No | No | Yes |

### Range.constrain  path coverage

| Test (RangeExtraTest) | contains? | value > upper? | value < lower? | result source | Notes |
|-----------------------|-----------|----------------|----------------|---------------|-------|
| constrainInsideReturnsSame | Yes | No | No | value | inside bounds |
| constrainBelowClampsToLower | No | No | Yes | lower | clamps low |
| constrainAboveClampsToUpper | No | Yes | No | upper | clamps high |
| constrainNaNReturnsNaN | No | No | No | value | NaN passes through |
| constrainWithInfiniteBoundsPassesThrough | Yes | No | No | value | unbounded range |

## Part C: Test Suite Development

New tests, strategy, and mapping to adequacy goal:
  
  The new tests were added using a coverage driven strategy. After writing the original functional tests, we reviewed DataUtilities method by method and added cases for missed paths such as null inputs, empty inputs, null cell values,     duplicate valid indices, out of bounds filtered indices, and deep copy behavior. The goal was not only to check correct outputs, but also to exercise exceptional cases and edge cases that affect control flow. This improved statement coverage by executing more method bodies and exception paths, improved branch coverage by forcing both true and false outcomes of key decisions such as null checks and bounds checks, and improved condition coverage by making predicates like n != null, row < rowCount, col < colCount, and array null and equality checks evaluate both ways. Some remaining uncovered branches appear infeasible due to seeded faults in the provided code, so they should be reported as unreachable rather than untreated.
  
Five key test cases and their coverage impact:

1. cloneNullSourceThrowsIllegalArgumentException():
   
    This test checks that the clone(double[][]) method properly rejects a null input by throwing an IllegalArgumentException. Its coverage impact is important because it exercises the defensive error handling path instead of the normal cloning path. Without this test, only valid input behavior would be covered, so the exception branch would remain untested.

2. columnTotalSkipsNulls():
   
  This test verifies that calculateColumnTotal(Values2D, int) ignores null values instead of trying to add them. It improves coverage by forcing the condition that checks whether a value is null to evaluate false for at least one iteration. Most normal test cases only cover non null values, so this case adds branch and condition coverage while also confirming that null entries do not affect the total.

3. columnTotalWithValidRowsSkipsOutOfBounds():
   
  This test uses a filtered row array that contains one valid row index and one invalid row index. It checks that the method includes the valid row in the total and skips the out of bounds row safely. This increases branch coverage because it exercises both outcomes of the row bounds check, which would not happen if all indices were valid.

4. rowTotalWithValidColsSkipsOutOfBounds():
   
  This test does the same kind of check for calculateRowTotal(Values2D, int, int[]), but on columns instead of rows. It confirms that valid columns are counted and invalid columns are ignored. Its main coverage contribution is that it executes both sides of the column bounds decision, which strengthens branch and condition coverage for the filtered row total method.

5. cumulativePercentagesHandlesNullsAsZero():
   
  This test verifies that getCumulativePercentages(KeyedValues) handles a null value correctly by treating it as contributing nothing to the running total. It improves coverage by exercising the null handling condition inside the cumulative calculation logic. It also strengthens the quality of the test suite because it checks both control flow and correctness of the resulting percentages.

## Part D: Coverage Tool Evaluation and Reflection
- TODO: pros/cons of tools used, integration notes.
- TODO: team workflow, challenges, lessons learned, feedback.

---


