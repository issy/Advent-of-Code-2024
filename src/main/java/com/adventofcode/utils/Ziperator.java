package com.adventofcode.utils;

import java.util.List;
import java.util.stream.IntStream;

public class Ziperator {

  public static <Item> List<Pair<Item, Item>> from(List<Item> first, List<Item> second) {
    if (first.size() != second.size()) {
      throw new IllegalArgumentException("Lists must be the same length");
    }

    return new IntStream.range(0, first.size())
      .mapToObj(i -> new Pair<>(first.get(i), second.get(i)))
      .toList();
  }

  public static <Item> List<Pair<Item, Item>> zipPreviousItem(List<Item> list) {
    List<Item> trailing = IntStream.range(0, list.size() - 1).mapToObj(list::get).toList();
    List<Item> leading = IntStream.range(1, list.size()).mapToObj(list::get).toList();
    return Ziperator.from(trailing, leading);
  }

}
