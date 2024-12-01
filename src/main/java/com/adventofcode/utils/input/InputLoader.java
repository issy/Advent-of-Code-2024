package com.adventofcode.utils.input;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.VisibleForTesting;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class InputLoader {

  private static Optional<String> fromFilepath(@NotNull Path filepath) {
    try {
      return Optional.of(Files.readString(filepath));
    } catch (IOException e) {
      return Optional.empty();
    }
  }

  @VisibleForTesting
  public static Optional<String> loadTestFile(@NotNull String filename) {
    return fromFilepath(Path.of("src/test/resources", filename));
  }

  public static Optional<String> loadFile(@NotNull String filename) {
    return fromFilepath(Path.of("src/main/resources", filename));
  }

}
