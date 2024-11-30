package com.adventofcode.utils.grid;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

  @Test
  void canGetPositionsAround() {
    Position position = Position.of(5, 5);
    Set<Position> positions = position.allPositionsAround();
    assertThat(positions)
      .doesNotContain(position)
      .containsExactlyInAnyOrder(
        Position.of(4, 6),
        Position.of(5, 6),
        Position.of(6, 6),
        Position.of(6, 5),
        Position.of(6, 4),
        Position.of(5, 4),
        Position.of(4, 4),
        Position.of(4, 5)
      );
  }

  @Test
  void canGetAdjacentPositions() {
    Position position = Position.of(5, 5);
    Set<Position> positions = position.allAdjacentPositions();
    assertThat(positions)
      .doesNotContain(position)
      .containsExactlyInAnyOrder(
        Position.of(5, 6),
        Position.of(6, 5),
        Position.of(5, 4),
        Position.of(4, 5)
      );
  }

  @Test
  void canGetDiagonalPositions() {
    Position position = Position.of(5, 5);
    Set<Position> positions = position.allDiagonalPositions();
    assertThat(positions)
      .doesNotContain(position)
      .containsExactlyInAnyOrder(
        Position.of(4, 6),
        Position.of(6, 6),
        Position.of(6, 4),
        Position.of(4, 4)
      );
  }

}
