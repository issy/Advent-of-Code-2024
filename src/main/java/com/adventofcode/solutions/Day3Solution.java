package com.adventofcode.solutions;

import com.adventofcode.utils.Pair;
import com.adventofcode.utils.Ziperator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Day3Solution implements Solution {

  static final Pattern pattern = Pattern.compile("mul\\((?<x>\\d{1,3}),(?<y>\\d{1,3})\\)");
  static final Pattern instructionPattern = Pattern.compile("(?<name>do|don't)\\(\\)");

  private final String input;

  public Day3Solution(String input) {
    this.input = input;
  }

  @Override
  public int solvePartOne() {
    return getSimple(input)
      .stream()
      .mapToInt(p -> p.first() * p.second())
      .sum();
  }

  @Override
  public int solvePartTwo() {
    List<MatchResult> instructions = getComplexInstructions(input);
    List<Pair<Integer, Integer>> mul = new ArrayList<>(getSimple(input.substring(0, instructions.getFirst().start())));
    Ziperator.zipPreviousItem(instructions)
      .stream()
      .map(p -> p.first().group("name").equals("do") ? input.substring(p.first().end(), p.second().start()) : "")
      .map(Day3Solution::getSimple)
      .forEach(mul::addAll);
    return mul.stream()
      .mapToInt(p -> p.first() * p.second())
      .sum();
  }

  static List<Pair<Integer, Integer>> getSimple(String input) {
    return pattern.matcher(input)
      .results()
      .map(m -> new Pair<>(Integer.parseInt(m.group("x")), Integer.parseInt(m.group("y"))))
      .toList();
  }

  static List<MatchResult> getComplexInstructions(String input) {
    return instructionPattern.matcher(input)
      .results()
      .toList();
  }

}
