package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day1 {
  public static void main(String[] args) {
    try {
      List<Integer> sums = readSumsFromFile("src/resources/day1.txt");
      printSortedSums(sums);
      printTop3Sum(sums);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static List<Integer> readSumsFromFile(String filename) throws IOException {
    List<Integer> sums = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
      String line;
      int sum = 0;
      while ((line = br.readLine()) != null) {
        if (!line.isEmpty()) {
          sum += parseLineAsInteger(line);
        } else {
          sums.add(sum);
          sum = 0;
        }
      }
      sums.add(sum);
    }
    return sums;
  }

  public static int parseLineAsInteger(String line) {
    try {
      return Integer.parseInt(line);
    } catch (NumberFormatException e) {
      return 0;
    }
  }

  public static void printSortedSums(List<Integer> sums) {
    sums.sort(Comparator.reverseOrder());
    System.out.println("Sorted sums: " + sums);
  }

  public static void printTop3Sum(List<Integer> sums) {
    int top3Sum = sums.stream().limit(3).mapToInt(Integer::intValue).sum();
    System.out.println("Top 3 sum: " + top3Sum);
  }
}
