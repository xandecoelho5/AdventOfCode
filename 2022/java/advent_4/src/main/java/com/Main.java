package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
  public static void main(String[] args) throws IOException {
    getQuantityOfOverlappedPairs();
  }

  private static void getQuantityOfOverlappedPairs() throws IOException {
    int countAllOverlapping = 0;
    int countRangesOverlapping = 0;
    try (InputStream is = Main.class.getClassLoader().getResourceAsStream("input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
      String line;
      while ((line = br.readLine()) != null) {
        var sections = line.split(",");
        var firstSectionRange = getSetRangefromSection(sections[0]);
        var secondSectionRange = getSetRangefromSection(sections[1]);
        if (allSectionOverlaps(firstSectionRange, secondSectionRange)) {
          countAllOverlapping++;
        }
        if (rangeSectionOverlaps(firstSectionRange, secondSectionRange)) {
          countRangesOverlapping++;
        }
      }
    }
    System.out.println(countAllOverlapping);
    System.out.println(countRangesOverlapping);
  }

  private static Set<Integer> getSetRangefromSection(String section) {
    var numbers = section.split("-");
    var start = Integer.parseInt(numbers[0]);
    var end = Integer.parseInt(numbers[1]);

    Set<Integer> result = new HashSet<>();
    for (int i = start; i <= end; i++) {
      result.add(i);
    }
    return result;
  }

  private static boolean allSectionOverlaps(Set<Integer> first, Set<Integer> second) {
    return first.containsAll(second) || second.containsAll(first);
  }

  private static boolean rangeSectionOverlaps(Set<Integer> first, Set<Integer> second) {
    for (var value : first) {
      if (second.contains(value))
        return true;
    }
    return false;
  }
}