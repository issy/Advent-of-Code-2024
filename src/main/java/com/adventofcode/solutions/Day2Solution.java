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
    var reports = getReports()
        .stream()
        .filter(Day2Solution::reportOnlyHasMaxOneAnomaly)
        .map(Day2Solution::bruteForceSafeReport)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .filter(r -> Ziperator.zipPreviousItem(r).stream().allMatch(Day2Solution::hasDifferenceWithinSafeRange))
        .toList();
    return reports.size();
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

  static boolean reportOnlyHasMaxOneAnomaly(List<Integer> report) {
    if (isInOrder(report)) {
      return true;
    }

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
      return true;
    }
    seenAnomaly.set(0);
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
    return seenAnomaly.get() <= 1;
  }

  static List<Integer> mapReportWithoutAnomalies(List<Integer> report) {
    if (isInOrder(report)) {
      return report;
    }
    final var zipped = Ziperator.zipPreviousItem(report);
    // Rising?
    var rising = zipped.stream()
        .filter(p -> !(p.first() < p.second()))
        .toList()
        .size() >= zipped.size() - 1;
    var falling = zipped.stream()
        .filter(p -> !(p.first() > p.second()))
        .toList()
        .size() >= zipped.size() - 1;
    List<Integer> result = new ArrayList<>();
    if (rising) {
      var filtered = zipped.stream()
          .filter(p -> p.first() < p.second())
          .toList();
      filtered.forEach(p -> result.add(p.first()));
      result.add(filtered.getLast().second());
      return result;
    } else if (falling) {
      var filtered = zipped.stream()
          .filter(p -> p.first() > p.second())
          .toList();
      filtered.forEach(p -> result.add(p.first()));
      result.add(filtered.getLast().second());
      return result;
    }

    throw new IllegalStateException("Did you forget to filter?");
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
