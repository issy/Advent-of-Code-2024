package com.adventofcode.solutions;

import com.adventofcode.utils.Pair;
import com.adventofcode.utils.Ziperator;
import com.google.common.collect.Comparators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static java.lang.Math.abs;

public class Day2Solution implements Solution {

  private final String input;

  public Day2Solution(String input) {
    this.input = input;
  }

  @Override
  public int solvePartOne() {
    return getReports()
        .stream()
        .filter(Day2Solution::isInOrder)
        .filter(r -> Ziperator.zipPreviousItem(r).stream()
            .allMatch(Day2Solution::hasDifferenceWithinSafeRange))
        .toList()
        .size();
  }

  @Override
  public int solvePartTwo() {
    return getReports()
        .stream()
        .map(Day2Solution::bruteForceSafeReport)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .filter(r -> Ziperator.zipPreviousItem(r).stream().allMatch(Day2Solution::hasDifferenceWithinSafeRange))
        .toList()
        .size();
  }

  List<List<Integer>> getReports() {
    return input.lines().map(Day2Solution::mapLineToReport).toList();
  }

  static List<Integer> mapLineToReport(String line) {
    return Arrays.stream(line.split(" "))
        .map(Integer::parseInt)
        .toList();
  }

  static boolean allUnique(List<Integer> report) {
    HashSet<Integer> unique = new HashSet<>(report);
    return unique.size() == report.size();
  }

  static boolean isInOrder(List<Integer> report) {
    return allUnique(report) && (isAscending(report) || isDescending(report));
  }

  static boolean isAscending(List<Integer> report) {
    return Comparators.isInOrder(report, Comparator.naturalOrder());
  }

  static boolean isDescending(List<Integer> report) {
    return Comparators.isInOrder(report, Comparator.reverseOrder());
  }

  static boolean hasDifferenceWithinSafeRange(Pair<Integer, Integer> pair) {
    int diff = abs(pair.first() - pair.second());
    return diff >= 1 && diff <= 3;
  }

  static Optional<List<Integer>> bruteForceSafeReport(List<Integer> report) {
    if (isInOrder(report)) {
      return Optional.of(report);
    }
    return IntStream.range(0, report.size())
        .mapToObj(i -> IntStream.range(0, report.size())
            .filter(x -> x != i)
            .mapToObj(report::get)
            .toList()
        )
        .filter(Day2Solution::isInOrder)
        .filter(r -> Ziperator.zipPreviousItem(r).stream().allMatch(Day2Solution::hasDifferenceWithinSafeRange))
        .findAny();
  }

}
