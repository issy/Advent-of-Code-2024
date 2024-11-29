package com.adventofcode.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.jetbrains.annotations.NotNull;

public class InputLoader {

  public static String fromFilepath(@NotNull String filepath) throws IOException {
    return Files.readString(Path.of(filepath));
  }

}
