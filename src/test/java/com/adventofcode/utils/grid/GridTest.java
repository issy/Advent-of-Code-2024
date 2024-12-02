package com.adventofcode.utils.grid;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GridTest {

  @Test
  void canGetCoordinatesCorrectly() {
    Grid grid = new Grid(List.of(
      "1.2",
      "3.4",
      "5.6"
    ));
    assertThat(grid.getValueAtPosition(Position.of(0, 2))).isEqualTo('1');
    assertThat(grid.getValueAtPosition(Position.of(2, 2))).isEqualTo('2');
    assertThat(grid.getValueAtPosition(Position.of(2, 0))).isEqualTo('6');
    assertThat(grid.getValueAtPosition(Position.of(0, 0))).isEqualTo('5');
  }

  @Test
  @Disabled("Not implemented yet")
  void canRotateClockwise() {
    // Given
    Grid grid = new Grid(List.of(
      "abc",
      "def",
      "ghi"
    ));
    // When
    Grid newGrid = grid.rotatedClockwise();
    // Then
    assertThat(newGrid.getValueAtPosition(Position.of(2, 0))).isEqualTo('c');
    assertThat(newGrid.getValueAtPosition(Position.of(2, 0))).isEqualTo('c');
  }

  @Test
  void canTestBounds() {
    Grid grid = new Grid(List.of(
      "...",
      "..."
    ));
    assertThat(grid.withinBounds(Position.of(0, 0))).isTrue();
    assertThat(grid.withinBounds(Position.of(1, 1))).isTrue();
    assertThat(grid.withinBounds(Position.of(2, 1))).isTrue();
    assertThat(grid.withinBounds(Position.of(2, 3))).isFalse();
    assertThat(grid.withinBounds(Position.of(0, 3))).isFalse();
    assertThat(grid.withinBounds(Position.of(3, 0))).isFalse();
    assertThat(grid.withinBounds(Position.of(0, 1))).isTrue();
    assertThat(grid.withinBounds(Position.of(0, -1))).isFalse();
    assertThat(grid.withinBounds(Position.of(-1, 0))).isFalse();
  }

}
