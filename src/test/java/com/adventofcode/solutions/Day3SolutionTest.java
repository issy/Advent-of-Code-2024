package com.adventofcode.solutions;

import com.adventofcode.utils.input.InputLoader;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Day3SolutionTest {

  @Test
  void canSolvePartOneWithSampleInput() {
    String input = InputLoader.loadTestFile("day3.sample.txt").orElseThrow();
    int result = new Day3Solution(input).solvePartOne();
    assertThat(result).isEqualTo(161);
  }

  @Test
  void canSolvePartOneWithRealInput() {
    String input = InputLoader.loadTestFile("day3.txt").orElseThrow();
    int result = new Day3Solution(input).solvePartOne();
    assertThat(result).isEqualTo(189527826);
  }

  @Test
  void canSolvePartTwoWithSampleInput() {
    String input = InputLoader.loadTestFile("day3.sample2.txt").orElseThrow();
    int result = new Day3Solution(input).solvePartTwo();
    assertThat(result).isEqualTo(48);
  }

  @Test
  void canSolvePartTwoWithRealInput() {
    String input = InputLoader.loadTestFile("day3.txt").orElseThrow();
    int result = new Day3Solution(input).solvePartTwo();
    assertThat(result).isEqualTo(0);
  }

  @Test
  void shouldNotMatchSimple() {
    Stream.of(
      "mul(4*",
      "mul(6,9!",
      "?(12,34)",
      "mul ( 2 , 4 )"
    ).forEach(i -> {
      assertThat(Day3Solution.pattern.matcher(i).matches()).isFalse();
    });
  }

  @Test
  void shouldMatchComplex() {
    String input = InputLoader.loadTestFile("day3.sample2.txt").orElseThrow();
    List<String> names = Day3Solution.getComplexInstructions(input)
      .stream()
      .map(m -> m.group("name"))
      .toList();
    assertThat(names).containsExactly(
      "don't",
      "do"
    );
  }

}