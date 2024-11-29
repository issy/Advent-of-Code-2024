package com.adventofcode.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class InputLoaderTest {

  @Test
  void canReadFileFromPath() {
    assertThat(InputLoader.loadTestFile("input1.txt"))
        .isPresent()
        .get()
        .isEqualTo("""
          two1nine
          eightwothree
          abcone2threexyz
          xtwone3four
          4nineeightseven2
          zoneight234
          7pqrstsixteen""");
  }

}
