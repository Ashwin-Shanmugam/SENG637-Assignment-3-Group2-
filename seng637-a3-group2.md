**SENG 637 - Dependability and Reliability of Software Systems**

**Lab. Report #3 – Code Coverage, Adequacy Criteria and Test Case Correlation**


| Group \#:      |   1  |
| -------------- | --- |
| Student Names: | Ucid    |
|    Noshin            |  30112985   |
|          Ashwin      |   30300738  |
|      Jasneet          |  30044332   |
|Salehin                | 30270206    |



# 1 Introduction

This assignment focuses on white-box testing and evaluating the adequacy of our unit test suites using code coverage metrics. Building upon the black-box testing suite created in Assignment 2, we used code coverage tools to measure the percentage of the code exercised by our initial tests. We then actively designed new test cases targeting unexecuted branches and statements to improve our coverage for the org.jfree.data.DataUtilities and org.jfree.data.Range classes. This lab provided hands-on experience with control-flow coverage, dealing with infeasible paths, and understanding the trade-offs between coverage-based and requirements-based test generation.

# 2 Manual data-flow coverage calculations for X and Y methods

// TASK B STUFF

# 3 A detailed description of the testing strategy for the new unit test

Our testing strategy was led by the analyzing the control flow metrics obtained from our baseline Assignment 2 test suite.

1. We first ran our existing test suite using the EclEmma coverage tool in Eclipse.
- For statement coverage we used the default Instruction Coverage. Since instruction coverage measures the execution of the Java bytecode, it serves as a direct proxy for testing if source code statements (lines) are executed.
- EclEmma does not support condition coverage, so as per instructions we substituted it with method coverage.

2. Since EclEmma does not natively report condition coverage, we substituted it with method coverage, as permitted by lab instructions.

3. We analyzed the visual red/green highlights to identify unexecuted branches, instructions, and methods.

4. While we inspected the source code to find any paths not covered, we based our test oracles from the Javadoc requirements.

5. We also discovered paths that were impossible to reach. Such as the Range methods getLowerBound(), getUpperBound(), getLength() check if (lower > upper) and throw an exception. Yet the Range constructor already checks this condition and prevents instantiation if lower > upper. This is an example of an infesible path, which justifies why 100% statement coverage cannot be achieved for these methods.

# 4 A high level description of five selected test cases you have designed using coverage information, and how they have increased code coverage

Text…

# 5 A detailed report of the coverage achieved of each class and method (a screen shot from the code cover results in green and red color would suffice)

1. Range Class Coverage Achieved:
Instruction Coverage: 85.9%
Branch Coverage: 84.1%
Method Coverage: 100%
# 6 Pros and Cons of coverage tools used and Metrics you report

Tool Used: EclEmma

Pros:
- Ease of installation, and works seamlessly with Eclipse. We could simply run it by using "Coverage As -> JUnit Test".
- The red/green highliting made identifying missed branches easy and simple.

Cons:
- The default view only shows instruction coverage. We had to configure the Active Counters to observe method and branch metrics.
- It does not support condition coverage which required us to substitute it with method coverage.

# 7 A comparison on the advantages and disadvantages of requirements-based test generation and coverage-based test generation.

Text…

# 8 A discussion on how the team work/effort was divided and managed

Text…

# 9 Any difficulties encountered, challenges overcome, and lessons learned from performing the lab

A major difficulty we encountered was in handling EclEmma's limitation with condition coverage. Since the tool did not show it, we had to check the assignment rules and pivot to using Method coverage as an alternative

# 10 Comments/feedback on the lab itself

Text…
