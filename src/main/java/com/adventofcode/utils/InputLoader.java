package com.adventofcode.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.VisibleForTesting;

public class InputLoader {

  private static Optional<String> fromFilepath(@NotNull Path filepath) {
    try {
      return Optional.of(Files.readString(filepath));
    } catch (IOException e) {
      return Optional.empty();
    }
  }

  @VisibleForTesting
  static Optional<String> loadTestFile(@NotNull String filename) {
    return fromFilepath(Path.of("src/test/resources", filename));
  }

  public static Optional<String> loadFile(@NotNull String filename) {
    return fromFilepath(Path.of("src/main/resources", filename));
  }

}
