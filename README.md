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
- TODO: describe new tests, strategy, mapping to adequacy goals.
- TODO: list five key test cases and their coverage impact.

## Part D: Coverage Tool Evaluation and Reflection
- TODO: pros/cons of tools used, integration notes.
- TODO: team workflow, challenges, lessons learned, feedback.

---


