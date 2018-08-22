### A solution to Eight Puzzle problem using Steepest Ascent Algorithm

[geeksforgeeks](https://www.geeksforgeeks.org/branch-bound-set-3-8-puzzle-problem/)
> Given a 3×3 board with 8 tiles (every tile has one number from 1 to 8) and one empty space. The objective is to place the numbers on tiles to match final configuration using the empty space. We can slide four adjacent (left, right, above and below) tiles into the empty space.

The `Node` class contains the matrix representing the 3x3 board and a list of already visited Node.
The `EightPuzzle` class contains method `SteepestAscent()`, an implementation of Steepest Ascent Hill Climbing algorithm.
`SteepestAscent()` uses method called `getPossibleNodes()` to generate the possible Nodes from the current Node.
