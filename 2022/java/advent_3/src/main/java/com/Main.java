package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class Main {
  public static void main(String[] args) throws IOException {
    AtomicInteger totalItemPriority = new AtomicInteger(0);
    calculatePriorityFromFile("input.txt", (line) -> {
      var half = line.length() / 2;
      String firstCompartment = line.substring(0, half);
      String secondCompartment = line.substring(half, line.length());
      char item = findCommonItem(firstCompartment, secondCompartment);
      totalItemPriority.getAndAdd(calculateCharValue(item));
    });
    System.out.println(totalItemPriority.get());

    AtomicInteger totalBadgePriority = new AtomicInteger(0);
    List<String> lines = new ArrayList<>();
    calculatePriorityFromFile("input.txt", (line) -> {
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
    try (InputStream is = Main.class.getClassLoader().getResourceAsStream(filename);
        BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
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