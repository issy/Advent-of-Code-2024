package com.adventofcode.utils.grid;

import java.util.List;

public class Grid {

  private final List<String> rows;

  public Grid(List<String> rows) {
    this.rows = rows;
  }

  public char getValueAtPosition(Position pos) {
    return rows.get(pos.y()).charAt(pos.x());
  }

  public boolean withinBounds(Position pos) {
    return pos.x() < getColSize()
      && pos.x() >= 0
      && pos.y() < getRowSize()
      && pos.y() >= 0;
  }

  private int getRowSize() {
    return rows.getFirst().length();
  }

  private int getColSize() {
    return rows.size();
  }

}
