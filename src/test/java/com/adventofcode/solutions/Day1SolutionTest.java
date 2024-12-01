package com.adventofcode.solutions;

import com.adventofcode.utils.input.InputLoader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day1SolutionTest {

  @Test
  void canSolvePartOneWithSampleInput() {
    String input = InputLoader.loadTestFile("day1.sample.txt").orElseThrow();
    int result = new Day1Solution(input).solvePartOne();
    assertThat(result).isEqualTo(11);
  }

  @Test
  void canSolvePartOneWithRealInput() {
    String input = InputLoader.loadTestFile("day1.txt").orElseThrow();
    int result = new Day1Solution(input).solvePartOne();
    assertThat(result).isEqualTo(2057374);
  }

  @Test
  void canSolvePartTwoWithSampleInput() {
    String input = InputLoader.loadTestFile("day1.sample.txt").orElseThrow();
    int result = new Day1Solution(input).solvePartTwo();
    assertThat(result).isEqualTo(31);
  }

  @Test
  void canSolvePartTwoWithRealInput() {
    String input = InputLoader.loadTestFile("day1.txt").orElseThrow();
    int result = new Day1Solution(input).solvePartTwo();
    assertThat(result).isEqualTo(23177084);
  }

}
