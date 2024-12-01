package com.adventofcode.solutions;

import com.adventofcode.utils.input.InputLoader;
import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Day1SolutionTest {

  @Test
  void canSolvePartOneWithSampleInput() {
    String input = InputLoader.loadTestFile("day1sample.txt").orElseThrow();
    int result = new Day1Solution(input).solvePartOne();
    assertThat(result).isEqualTo(11);
  }

  @Test
  void canSolvePartOneWithRealInput() {
    String input = InputLoader.loadTestFile("day1real.txt").orElseThrow();
    int result = new Day1Solution(input).solvePartOne();
    assertThat(result).isEqualTo(2057374);
  }

  @Test
  void canSolvePartTwoWithSampleInput() {
    String input = InputLoader.loadTestFile("day1sample.txt").orElseThrow();
    int result = new Day1Solution(input).solvePartTwo();
    assertThat(result).isEqualTo(31);
  }

  @Test
  void canSolvePartTwoWithRealInput() {
    String input = InputLoader.loadTestFile("day1real.txt").orElseThrow();
    int result = new Day1Solution(input).solvePartTwo();
    assertThat(result).isEqualTo(23177084);
  }

}
