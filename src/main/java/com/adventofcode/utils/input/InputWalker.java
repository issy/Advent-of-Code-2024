package com.adventofcode.utils.input;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class InputWalker {

  /**
   * Parses each line as an integer
   * @return a list of integers
   */
  public static List<Integer> linesAsIntegers(@NotNull String input) {
    return input.lines().map(Integer::parseInt).toList();
  }

  /**
   * Parses "blocks" of lines as groups
   * @return a list of line groups
   */
  public static List<List<String>> groupLines(@NotNull String input) {
    return Arrays.stream(input.split("\n\n")).map(s -> s.lines().toList()).toList();
  }

}
