package main;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;

public class Day6 {
  public static void main(String[] args) throws Exception {
    var line = Files.readAllLines(Path.of("src/resources/day6.txt")).get(0);
    System.out.println(getMarker(line, 4));
    System.out.println(getMarker(line, 14));
  }

  private static int getMarker(String line, int distinctNumbers) {
    for (int i = 0; i < line.length() - distinctNumbers - 1; i++) {
      var set = new HashSet<>(Arrays.asList(line.substring(i, i + distinctNumbers).split("")));
      if (set.size() == distinctNumbers)
        return i + distinctNumbers;
    }
    return 0;
  }
}
