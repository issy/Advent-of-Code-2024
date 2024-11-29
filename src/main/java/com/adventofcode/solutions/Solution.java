package com.adventofcode.solutions;

public interface Solution {

  <TResultType> TResultType solvePartOne(String input);

  <TResultType> TResultType solvePartTwo(String input);

}
