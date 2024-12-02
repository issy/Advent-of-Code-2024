package com.adventofcode.solutions;

import com.adventofcode.utils.Ziperator;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
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
      .filter(Day2Solution::isSafe)
      .toList()
      .size();
  }

  @Override
  public int solvePartTwo() {
    return getReports()
      .stream()
      .filter(Day2Solution::isSafePartTwo)
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
      .map(p -> abs(p.first() - p.second()))
      .allMatch(d -> d >= 1 && d <= 3);
  }

  static boolean isSafePartTwo(List<Integer> report) {
    if (isSafe(report)) return true;
    return Ziperator.zipPreviousItem(getReportFallingIgnoring(report))
      .stream()
      .map(p -> abs(p.first() - p.second()))
      .allMatch(d -> d >= 1 && d <= 3)
      || Ziperator.zipPreviousItem(getReportRisingIgnoring(report))
      .stream()
      .map(p -> abs(p.first() - p.second()))
      .allMatch(d -> d >= 1 && d <= 3);
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
