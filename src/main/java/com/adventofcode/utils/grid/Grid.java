package com.adventofcode.utils.grid;

import java.util.List;

public class Grid {

  private final List<String> rows;

  public Grid(List<String> rows) {
    this.rows = rows;
  }

  public char getValueAtPosition(Position pos) {
    return rows.reversed().get(pos.y()).charAt(pos.x());
  }

  public boolean withinBounds(Position pos) {
    return pos.x() < getColSize()
      && pos.x() >= 0
      && pos.y() < getRowSize()
      && pos.y() >= 0;
  }

  // S X O    X X S
  // X X X -> X X X
  // X X X    X X O
  public Grid rotatedClockwise() {
    return this;
  }

  // X X X
  // - - -
  // X X X
  private int getRowSize() {
    return rows.getFirst().length();
  }

  // X | X
  // X | X
  // X | X
  private int getColSize() {
    return rows.size();
  }

}
