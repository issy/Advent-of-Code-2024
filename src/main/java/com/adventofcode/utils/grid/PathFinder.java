package com.adventofcode.utils.grid;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PathFinder {

  private final Grid grid;
  private final char wallCharacter;
  private final Position startPosition;
  private final boolean canMoveDiagonally;

  public PathFinder(Grid grid, char wallCharacter, Position startPosition, boolean canMoveDiagonally) {
    this.grid = grid;
    this.wallCharacter = wallCharacter;
    this.startPosition = startPosition;
    this.canMoveDiagonally = canMoveDiagonally;
  }

  public PathFinder(Grid grid, char wallCharacter, Position startPosition) {
    this.grid = grid;
    this.wallCharacter = wallCharacter;
    this.startPosition = startPosition;
    this.canMoveDiagonally = false;
  }

  public Set<List<Position>> findPathsTo(final Position target) {
    Set<List<Position>> paths = new HashSet<>();
    Set<List<Position>> completedPaths = new HashSet<>();
    paths.add(List.of(startPosition));
    while (!paths.isEmpty()) {
      final Set<List<Position>> finalPaths = new HashSet<>(paths);
      finalPaths.forEach(path -> {
        if (path.contains(target)) {
          completedPaths.add(path);
          paths.remove(path);
          return;
        }
        final Position head = path.getLast();
        Set<Position> nextPositions = head.allAdjacentPositions()
          .stream()
          .filter(grid::withinBounds)
          .filter(Predicate.not(path::contains))
          .filter(Predicate.not(this::isWallAt))
          .collect(Collectors.toSet());
        if (!nextPositions.isEmpty()) {
          // Add next paths
          nextPositions.forEach(newHead -> {
            final List<Position> newPath = new ArrayList<>(path);
            newPath.add(newHead);
            paths.add(newPath);
          });
        }
        paths.remove(path);
      });
    }
    return completedPaths;
  }

  public Optional<List<Position>> findShortestPathTo(final Position target) {
    return findPathsTo(target).stream().min(Comparator.comparing(List::size));
  }

  private boolean isWallAt(Position position) {
    return grid.getValueAtPosition(position) == wallCharacter;
  }

}
