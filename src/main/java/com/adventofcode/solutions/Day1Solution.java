package com.adventofcode.solutions;

import com.adventofcode.utils.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Day1Solution implements Solution {

  private final String input;

  public Day1Solution(String input) {
    this.input = input;
  }

  @Override
  public int solvePartOne() {
    Pair<List<Integer>, List<Integer>> twoLists = getLists();
    List<Integer> leftList = twoLists.first();
    List<Integer> rightList = twoLists.second();
    int totalDistance = 0;
    for (int i = 0; i < leftList.size(); i++) {
      Integer left = leftList.get(i);
      Integer right = rightList.get(i);
      int distance = Math.abs(left - right);
      totalDistance += distance;
    }
    return totalDistance;
  }

  @Override
  public int solvePartTwo() {
    Pair<List<Integer>, List<Integer>> twoLists = getLists();
    List<Integer> leftList = twoLists.first();
    List<Integer> rightList = twoLists.second();
    AtomicInteger totalScore = new AtomicInteger();
    leftList.forEach(left -> {
      int numAppearances = rightList.stream()
        .filter(i -> Objects.equals(i, left))
        .toList()
        .size();
      int score = left * numAppearances;
      totalScore.addAndGet(score);
    });
    return totalScore.get();
  }

  Pair<List<Integer>, List<Integer>> getLists() {
    final List<Integer> leftList = input.lines()
      .map(line -> Integer.parseInt(Arrays.stream(line.split(" ")).findFirst().orElseThrow()))
      .sorted()
      .toList();
    final List<Integer> rightList = input.lines()
      .map(line -> Integer.parseInt(Arrays.stream(line.split(" ")).toList().getLast()))
      .sorted()
      .toList();
    return new Pair<>(leftList, rightList);
  }

}
