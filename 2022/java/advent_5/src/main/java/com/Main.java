package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) throws IOException {
    var crateMover9000 = crateMoverFromFile();
    for (Move move : crateMover9000.moves()) {
      crateMover9000.doMove9000(move);
    }
    printTopCrates(crateMover9000);

    var crateMover9001 = crateMoverFromFile();
    for (Move move : crateMover9001.moves()) {
      crateMover9001.doMove9001(move);
    }
    printTopCrates(crateMover9001);
  }

  private static CrateMover crateMoverFromFile() throws IOException {
    CrateMover crateMover = new CrateMover(new ArrayList<>(), new ArrayList<>());
    try (InputStream is = Main.class.getClassLoader().getResourceAsStream("input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
      String line;
      while ((line = br.readLine()) != null) {
        if (crateMover.stacks().isEmpty()) {
          crateMover.initializeStacks(getQuantityOfStacks(line));
        }

        if (line.contains("[")) {
          crateMover.addToStacks(line);
        } else if (line.startsWith("move")) {
          crateMover.moves().add(Move.from(line));
        }
      }
    }
    return crateMover;
  }

  private static void printTopCrates(CrateMover crateMover) {
    StringBuilder sb = new StringBuilder();
    for (var stack : crateMover.stacks()) {
      sb.append(stack.peek().substring(1, 2));
    }
    System.out.println(sb);
  }

  private static int getQuantityOfStacks(String line) {
    int res = 0;
    for (int i = 0; i <= line.length() - 3; i += 3) {
      res++;
      i++;
    }
    return res;
  }
}
