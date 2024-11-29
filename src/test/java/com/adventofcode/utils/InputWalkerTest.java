package com.adventofcode.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputWalkerTest {

  @Test
  void canParseIntegers() {
    String input = InputLoader.loadTestFile("input2.txt").orElseThrow();
    assertThat(InputWalker.linesAsIntegers(input)).containsExactly(1453, 345, 567, 234, 45, 7864, 453);
  }

  @Test
  void throwsIfNonIntegerEncountered() {
    String input = InputLoader.loadTestFile("input1.txt").orElseThrow();
    assertThatThrownBy(() -> InputWalker.linesAsIntegers(input));
  }

  @Test
  void canGroupLines() {
    String input = InputLoader.loadTestFile("input3.txt").orElseThrow();
    List<List<String>> result = InputWalker.groupLines(input);
    assertThat(result).hasSize(5);
    assertThat(result.get(0)).containsExactly("1000", "2000", "3000");
    assertThat(result.get(1)).containsExactly("4000");
    assertThat(result.get(2)).containsExactly("5000", "6000");
    assertThat(result.get(3)).containsExactly("7000", "8000", "9000");
    assertThat(result.get(4)).containsExactly("10000");
  }

}
