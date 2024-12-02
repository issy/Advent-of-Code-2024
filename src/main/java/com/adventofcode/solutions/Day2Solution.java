package com.adventofcode.solutions;

import com.adventofcode.utils.Pair;
import com.adventofcode.utils.Ziperator;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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
      .filter(Day2Solution::isSafe)
      .toList()
      .size();
  }

  @Override
  public int solvePartTwo() {
    return getReports()
      .stream()
      .filter(r -> Ziperator.zipPreviousItem(r).stream().allMatch(Day2Solution::hasDifferenceWithinSafeRange))
      .filter(Day2Solution::isOrderedIgnoringOneAnomaly)
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

  static List<Integer> getReportIgnoringOneAnomaly(List<Integer> report) {
    AtomicBoolean hasSeenAnomaly = new AtomicBoolean(false);

    boolean isFalling = Ziperator.zipPreviousItem(report)
      .stream()
      .allMatch(p -> {
        if (p.first() > p.second()) {
          return true;
        } else {
          if (!hasSeenAnomaly.get()) {
            hasSeenAnomaly.set(true);
            return true;
          } else {
            return false;
          }
        }
      });
    if (isFalling) {
      if (hasSeenAnomaly.get()) {
        return Ziperator.zipPreviousItem(report)
          .stream()
          .allMatch(p -> {

          })
      } else {
        return report;
      }
    }
  }

  static boolean isOrderedIgnoringOneAnomaly(List<Integer> report) {
    AtomicBoolean hasSeenAnomaly = new AtomicBoolean(false);
    boolean isFalling = Ziperator.zipPreviousItem(report)
      .stream()
      .allMatch(p -> {
        if (p.first() > p.second()) {
          return true;
        } else {
          if (!hasSeenAnomaly.get()) {
            hasSeenAnomaly.set(true);
            return true;
          } else {
            return false;
          }
        }
      });
    if (isFalling) return true;
    hasSeenAnomaly.set(false);
    return Ziperator.zipPreviousItem(report)
      .stream()
      .allMatch(p -> {
        if (p.first() < p.second()) {
          return true;
        } else {
          if (!hasSeenAnomaly.get()) {
            hasSeenAnomaly.set(true);
            return true;
          } else {
            return false;
          }
        }
      });
  }

  static boolean isSafePartTwo(List<Integer> report) {
    if (isSafe(report)) return true;
    return isOrderedIgnoringOneAnomaly(report) && report.stream().map();
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

  static List<Integer> getReportFallingIgnoring(List<Integer> report) {
    List<Integer> result = new ArrayList<>();
    result.add(report.getFirst());
    final AtomicBoolean hasSeenAnomaly = new AtomicBoolean(false);
    Ziperator.zipPreviousItem(report)
      .forEach(p -> {
        if (!(p.first() > p.second()) && !hasSeenAnomaly.get()) {
          hasSeenAnomaly.set(true);
        } else {
          result.add(p.second());
        }
      });
    return result;
  }

  static List<Integer> getReportRisingIgnoring(List<Integer> report) {
    List<Integer> result = new ArrayList<>();
    result.add(report.getFirst());
    final AtomicBoolean hasSeenAnomaly = new AtomicBoolean(false);
    Ziperator.zipPreviousItem(report)
      .forEach(p -> {
        if (!(p.first() < p.second()) && !hasSeenAnomaly.get()) {
          hasSeenAnomaly.set(true);
        } else {
          result.add(p.second());
        }
      });
    return result;
  }

}
