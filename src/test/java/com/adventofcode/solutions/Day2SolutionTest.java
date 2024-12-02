package com.adventofcode.solutions;

import com.adventofcode.utils.input.InputLoader;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day2SolutionTest {

  @Test
  void canSolvePartOneWithSampleInput() {
    String input = InputLoader.loadTestFile("day2.sample.txt").orElseThrow();
    int result = new Day2Solution(input).solvePartOne();
    assertThat(result).isEqualTo(2);
  }

  @Test
  void canSolvePartOneWithRealInput() {
    String input = InputLoader.loadTestFile("day2.txt").orElseThrow();
    int result = new Day2Solution(input).solvePartOne();
    assertThat(result).isEqualTo(242);
  }

  @Test
  void canSolvePartTwoWithSampleInput() {
    String input = InputLoader.loadTestFile("day2.sample.txt").orElseThrow();
    int result = new Day2Solution(input).solvePartTwo();
    assertThat(result).isEqualTo(4);
  }

  @Test
  void canSolvePartTwoWithRealInput() {
    String input = InputLoader.loadTestFile("day2.txt").orElseThrow();
    int result = new Day2Solution(input).solvePartTwo();
    assertThat(result).isEqualTo(0); // TODO: Find real answer
  }

  @Test
  void canMap() {
    String line = "7 6 4 2 1";
    List<Integer> report = Day2Solution.mapLineToReport(line);
    assertThat(report)
      .hasSize(5)
      .containsExactly(7, 6, 4, 2, 1);
  }

  @Test
  void canTestReport() {
    String line = "7 6 4 2 1";
    List<Integer> report = Day2Solution.mapLineToReport(line);
    assertThat(Day2Solution.isAscending(report)).isFalse();
    assertThat(Day2Solution.isDescending(report)).isTrue();
    assertThat(Day2Solution.isInOrder(report)).isTrue();
  }

  @Test
  void canParseSampleInput() {
    String input = InputLoader.loadTestFile("day2.sample.txt").orElseThrow();
    List<String> lines = input.lines().toList();
    List<List<Integer>> mappedLines = lines.stream().map(line -> Arrays.stream(line.split(" ")).map(Integer::parseInt).toList()).toList();
    assertThat(mappedLines)
      .hasSize(6)
      .containsExactly(
        List.of(7, 6, 4, 2, 1),
        List.of(1, 2, 7, 8, 9),
        List.of(9, 7, 6, 2, 1),
        List.of(1, 3, 2, 4, 5),
        List.of(8, 6, 4, 4, 1),
        List.of(1, 3, 6, 7, 9)
      );
    assertThat(new Day2Solution(input).getReports())
      .hasSize(6)
      .containsExactly(
        List.of(7, 6, 4, 2, 1),
        List.of(1, 2, 7, 8, 9),
        List.of(9, 7, 6, 2, 1),
        List.of(1, 3, 2, 4, 5),
        List.of(8, 6, 4, 4, 1),
        List.of(1, 3, 6, 7, 9)
      );
    assertThat(input.lines().map(Day2Solution::mapLineToReport).toList())
      .hasSize(6)
      .containsExactly(
        List.of(7, 6, 4, 2, 1),
        List.of(1, 2, 7, 8, 9),
        List.of(9, 7, 6, 2, 1),
        List.of(1, 3, 2, 4, 5),
        List.of(8, 6, 4, 4, 1),
        List.of(1, 3, 6, 7, 9)
      );
  }

  @Test
  void canDetectAnomalies() {
    assertThat(Day2Solution.reportOnlyHasMaxOneAnomaly(List.of(1, 2, 3, 4, 5))).isTrue();
    assertThat(Day2Solution.reportOnlyHasMaxOneAnomaly(List.of(1, 3, 2, 4, 5))).isTrue();
    assertThat(Day2Solution.reportOnlyHasMaxOneAnomaly(List.of(1, 3, 2, 6, 5))).isFalse();
  }

  @Test
  void canMapReportAnomalies() {
    assertThat(Day2Solution.mapReportWithoutAnomalies(List.of(1, 2, 3, 4, 5))).containsExactly(1, 2, 3, 4, 5);
    assertThat(Day2Solution.mapReportWithoutAnomalies(List.of(1, 3, 2, 4, 5))).containsExactly(1, 2, 4, 5);
  }

  @Test
  void partTwoStuff() {
    assertThat(Day2Solution.bruteForceSafeReport(List.of(73, 70, 74, 76, 80)))
        .isEmpty();
    assertThat(Day2Solution.bruteForceSafeReport(List.of(8, 6, 4, 4, 1)))
        .isPresent()
        .hasValueSatisfying(report -> assertThat(report)
            .hasSize(4)
            .containsExactly(8, 6, 4, 1)
        );
  }

}
