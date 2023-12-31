package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class Day3 {

  public static void main(String[] args) throws IOException {
    AtomicInteger totalItemPriority = new AtomicInteger(0);
    calculatePriorityFromFile("src/resources/day3.txt", line -> {
      var half = line.length() / 2;
      String firstCompartment = line.substring(0, half);
      String secondCompartment = line.substring(half);
      char item = findCommonItem(firstCompartment, secondCompartment);
      totalItemPriority.getAndAdd(calculateCharValue(item));
    });
    System.out.println(totalItemPriority.get());

    AtomicInteger totalBadgePriority = new AtomicInteger(0);
    List<String> lines = new ArrayList<>();
    calculatePriorityFromFile("src/resources/day3.txt", (line) -> {
      lines.add(line);
      if (lines.size() == 3) {
        lines.sort((a, b) -> b.length() - a.length());
        char badge = findCommonBadge(lines.get(0), lines.get(1), lines.get(2));
        totalBadgePriority.getAndAdd(calculateCharValue(badge));
        lines.clear();
      }
    });
    System.out.println(totalBadgePriority.get());
  }

  private static void calculatePriorityFromFile(String filename, Consumer<String> lineConsumer) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
      String line;
      while ((line = br.readLine()) != null) {
        lineConsumer.accept(line);
      }
    }
  }

  private static Character findCommonItem(String firstCompartment, String secondCompartment) {
    for (int i = 0; i < firstCompartment.length(); i++) {
      char c = firstCompartment.charAt(i);
      if (secondCompartment.indexOf(c) > -1)
        return c;
    }
    return '-';
  }

  private static Character findCommonBadge(String firstRucksack, String secondRucksack, String thirdRucksack) {
    for (int i = 0; i < firstRucksack.length(); i++) {
      char c = firstRucksack.charAt(i);
      if (secondRucksack.indexOf(c) > -1 && thirdRucksack.indexOf(c) > -1)
        return c;
    }
    return '-';
  }

  private static int calculateCharValue(char c) {
    if (c >= 'A' && c <= 'Z')
      return (int) c - 38;
    return (int) c - 96;
  }
}
