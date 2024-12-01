package com.adventofcode.utils.grid;

import java.util.Set;

public record Position(int x, int y) {
  public static Position of(int x, int y) {
    return new Position(x, y);
  }

  public Position left() {
    return new Position(x - 1, y);
  }

  public Position right() {
    return new Position(x + 1, y);
  }

  public Position up() {
    return new Position(x, y + 1);
  }

  public Position down() {
    return new Position(x, y - 1);
  }

  //   X
  // X O X
  //   X
  public Set<Position> allAdjacentPositions() {
    return Set.of(
      left(),
      right(),
      up(),
      down()
    );
  }

  // X   X
  //   O
  // X   X
  public Set<Position> allDiagonalPositions() {
    return Set.of(
      up().left(),
      up().right(),
      down().right(),
      down().left()
    );
  }

  // X X X
  // X O X
  // X X X
  public Set<Position> allPositionsAround() {
    return Set.of(
      up().left(),
      up(),
      up().right(),
      right(),
      right().down(),
      down(),
      down().left(),
      left()
    );
  }

}
