package com.adventofcode.utils.grid;

import java.util.List;
import java.util.Set;

public class PathFinder {

  private final Grid grid;
  private final char wallCharacter;

  public PathFinder(Grid grid, char wallCharacter) {
    this.grid = grid;
    this.wallCharacter = wallCharacter;
  }

  public List<Position> findPathToPosition(Position target) {
    return List.of();
  }

}
