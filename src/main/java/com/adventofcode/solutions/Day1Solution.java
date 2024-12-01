package com.adventofcode.solutions;

import com.adventofcode.utils.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.lang.Math.abs;

public class Day1Solution implements Solution {

  private final String input;

  public Day1Solution(String input) {
    this.input = input;
  }

  @Override
  public int solvePartOne() {
    Pair<List<Integer>, List<Integer>> twoLists = getLists();
    return IntStream.range(0, twoLists.first().size())
      .mapToObj(index -> new Pair<>(twoLists.first().get(index), twoLists.second().get(index)))
      .mapToInt(pair -> abs(pair.first() - pair.second()))
      .sum();
  }

  @Override
  public int solvePartTwo() {
    Pair<List<Integer>, List<Integer>> twoLists = getLists();
    List<Integer> leftList = twoLists.first();
    List<Integer> rightList = twoLists.second();
    return (int) leftList.stream()
      .mapToLong(a -> a * rightList.stream().filter(a::equals).count())
      .sum();
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
