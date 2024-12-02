package com.adventofcode.solutions;

import com.adventofcode.utils.Pair;
import com.adventofcode.utils.Ziperator;
import com.google.common.collect.Comparators;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

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
      .filter(r -> Ziperator.zipPreviousItem(r).stream().allMatch(Day2Solution::hasDifferenceWithinSafeRange))
      .toList()
      .size();
  }

  @Override
  public int solvePartTwo() {
    return getReports()
      .stream()
      .filter(Day2Solution::reportOnlyHasMaxOneAnomaly)
      .map()
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

  static boolean isSafe(List<Integer> report) {
    if (!isInOrder(report)) return false;
    return Ziperator.zipPreviousItem(report)
      .stream()
      .allMatch(Day2Solution::hasDifferenceWithinSafeRange);
  }

  static boolean isInOrder(List<Integer> report) {
    return isAscending(report) || isDescending(report);
  }

  static boolean isAscending(List<Integer> report) {
    return report.stream().sorted().toList().equals(report);
  }

  static boolean isDescending(List<Integer> report) {
    return report.stream().sorted(Comparator.reverseOrder()).toList().equals(report);
  }

  static boolean hasDifferenceWithinSafeRange(Pair<Integer, Integer> pair) {
    int diff = abs(pair.first() - pair.second());
    return diff >= 1 && diff <= 3;
  }

  /**
   * Ignores max of 1 item not in order
   */
  static boolean isInOrderPartTwo(List<Integer> report) {
    if (isInOrder(report)) return true;
    // Assuming no numbers appear twice in a report
    final AtomicInteger runningCompare = new AtomicInteger();
    Ziperator.zipPreviousItem(report)
      .forEach(p -> {
        runningCompare.addAndGet(p.first().compareTo(p.second()));
      });
    return abs(runningCompare.get()) == report.size() - 1;
  }

  static boolean reportOnlyHasMaxOneAnomaly(List<Integer> report) {
    if (isInOrder(report)) return true;

    final AtomicInteger seenAnomaly = new AtomicInteger(0);
    // Rising
    for (Pair<Integer, Integer> pair : Ziperator.zipPreviousItem(report)) {
      if (!(pair.first() < pair.second())) {
        if (seenAnomaly.get() > 1) {
          break;
        } else {
          seenAnomaly.addAndGet(1);
        }
      }
    }
    if (seenAnomaly.get() <= 1) {
      // Report is rising
      // TODO: Return the mapped report
      return false;
    }
    // Falling
    for (Pair<Integer, Integer> pair : Ziperator.zipPreviousItem(report)) {
      if (!(pair.first() > pair.second())) {
        if (seenAnomaly.get() > 1) {
          break;
        } else {
          seenAnomaly.addAndGet(1);
        }
      }
    }
    // TODO: Do something here
    // Map report?
    // It's falling, probably???
    return true;
  }

  static List<Integer> mapReportWithoutAnomalies(List<Integer> report) {
    int size = report.size();
  }

}
