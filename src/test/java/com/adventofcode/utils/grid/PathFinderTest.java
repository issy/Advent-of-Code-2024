package com.adventofcode.utils.grid;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class PathFinderTest {

  @Test
  void canFindPathToPosition() {
    // Given
    Grid grid = new Grid(List.of(
      ".O.....",
      ".######",
      ".#.....",
      ".######",
      "......S"
    ));
    PathFinder pathFinder = new PathFinder(grid, '#', Position.of(6, 0));
    Optional<List<Position>> result = pathFinder.findShortestPathTo(Position.of(1, 4));
    assertThat(result).isPresent();
    assertThat(result.get())
      .hasSize(12)
      .containsExactly(
        Position.of(6, 0),
        Position.of(5, 0),
        Position.of(4, 0),
        Position.of(3, 0),
        Position.of(2, 0),
        Position.of(1, 0),
        Position.of(0, 0),
        Position.of(0, 1),
        Position.of(0, 2),
        Position.of(0, 3),
        Position.of(0, 4),
        Position.of(1, 4)
      );
  }

  @Test
  void canEliminateDeadends() {
    // Given
    Grid grid = new Grid(List.of(
      ".O.....",
      ".######",
      ".......",
      ".######",
      "......S"
    ));
    PathFinder pathFinder = new PathFinder(grid, '#', Position.of(6, 0));
    Optional<List<Position>> result = pathFinder.findShortestPathTo(Position.of(1, 4));
    assertThat(result).isPresent();
    assertThat(result.get())
      .hasSize(12)
      .containsExactly(
        Position.of(6, 0),
        Position.of(5, 0),
        Position.of(4, 0),
        Position.of(3, 0),
        Position.of(2, 0),
        Position.of(1, 0),
        Position.of(0, 0),
        Position.of(0, 1),
        Position.of(0, 2),
        Position.of(0, 3),
        Position.of(0, 4),
        Position.of(1, 4)
      );
  }

  @Test
  void canFindShortestPath() {
    // Given
    Grid grid = new Grid(List.of(
      ".O.....",
      ".#####.",
      ".......",
      ".######",
      "......S"
    ));
    PathFinder pathFinder = new PathFinder(grid, '#', Position.of(6, 0));
    Optional<List<Position>> result = pathFinder.findShortestPathTo(Position.of(1, 4));
    assertThat(result).isPresent();
    assertThat(result.get())
      .hasSize(12)
      .containsExactly(
        Position.of(6, 0),
        Position.of(5, 0),
        Position.of(4, 0),
        Position.of(3, 0),
        Position.of(2, 0),
        Position.of(1, 0),
        Position.of(0, 0),
        Position.of(0, 1),
        Position.of(0, 2),
        Position.of(0, 3),
        Position.of(0, 4),
        Position.of(1, 4)
      );
  }

  @Test
  void canFindAllPaths() {
    // Given
    Grid grid = new Grid(List.of(
      ".O.....",
      ".##.##.",
      ".......",
      ".##.###",
      "......S"
    ));
    PathFinder pathFinder = new PathFinder(grid, '#', Position.of(6, 0));
    Set<List<Position>> result = pathFinder.findPathsTo(Position.of(1, 4));
    assertThat(result)
      .isNotEmpty()
      .hasSize(6)
      .containsExactlyInAnyOrder(
        List.of(
          Position.of(6, 0),
          Position.of(5, 0),
          Position.of(4, 0),
          Position.of(3, 0),
          Position.of(2, 0),
          Position.of(1, 0),
          Position.of(0, 0),
          Position.of(0, 1),
          Position.of(0, 2),
          Position.of(0, 3),
          Position.of(0, 4),
          Position.of(1, 4)
        ),
        List.of(
          Position.of(6, 0),
          Position.of(5, 0),
          Position.of(4, 0),
          Position.of(3, 0),
          Position.of(3, 1),
          Position.of(3, 2),
          Position.of(4, 2),
          Position.of(5, 2),
          Position.of(6, 2),
          Position.of(6, 3),
          Position.of(6, 4),
          Position.of(5, 4),
          Position.of(4, 4),
          Position.of(3, 4),
          Position.of(2, 4),
          Position.of(1, 4)),
        List.of(
          Position.of(6, 0),
          Position.of(5, 0),
          Position.of(4, 0),
          Position.of(3, 0),
          Position.of(3, 1),
          Position.of(3, 2),
          Position.of(3, 3),
          Position.of(3, 4),
          Position.of(2, 4),
          Position.of(1, 4)),
        List.of(
          Position.of(6, 0),
          Position.of(5, 0),
          Position.of(4, 0),
          Position.of(3, 0),
          Position.of(2, 0),
          Position.of(1, 0),
          Position.of(0, 0),
          Position.of(0, 1),
          Position.of(0, 2),
          Position.of(1, 2),
          Position.of(2, 2),
          Position.of(3, 2),
          Position.of(4, 2),
          Position.of(5, 2),
          Position.of(6, 2),
          Position.of(6, 3),
          Position.of(6, 4),
          Position.of(5, 4),
          Position.of(4, 4),
          Position.of(3, 4),
          Position.of(2, 4),
          Position.of(1, 4)),
        List.of(
          Position.of(6, 0),
          Position.of(5, 0),
          Position.of(4, 0),
          Position.of(3, 0),
          Position.of(3, 1),
          Position.of(3, 2),
          Position.of(2, 2),
          Position.of(1, 2),
          Position.of(0, 2),
          Position.of(0, 3),
          Position.of(0, 4),
          Position.of(1, 4)),
        List.of(
          Position.of(6, 0),
          Position.of(5, 0),
          Position.of(4, 0),
          Position.of(3, 0),
          Position.of(2, 0),
          Position.of(1, 0),
          Position.of(0, 0),
          Position.of(0, 1),
          Position.of(0, 2),
          Position.of(1, 2),
          Position.of(2, 2),
          Position.of(3, 2),
          Position.of(3, 3),
          Position.of(3, 4),
          Position.of(2, 4),
          Position.of(1, 4)
        )
      );
  }

}
