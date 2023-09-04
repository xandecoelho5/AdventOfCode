package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Day5 {
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
    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/resources/day5.txt")))) {
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

record Move(int quantity, int from, int to) {
  public static Move from(String line) {
    var split = line.split(" ");
    return new Move(Integer.parseInt(split[1]), Integer.parseInt(split[3]) - 1, Integer.parseInt(split[5]) - 1);
  }
}

record CrateMover(List<Stack<String>> stacks, List<Move> moves) {
  private static final int STACK_LABEL_LENGTH = 3;

  public void initializeStacks(int numStacks) {
    for (int i = 0; i < numStacks; i++) {
      stacks.add(new Stack<>());
    }
  }

  public void addToStacks(String line) {
    for (int i = 0, j = 0; i <= line.length() - STACK_LABEL_LENGTH; i += STACK_LABEL_LENGTH, j++) {
      String substr = line.substring(i, i + STACK_LABEL_LENGTH);
      if (substr.contains("[")) {
        stacks().get((i - j) / STACK_LABEL_LENGTH).add(0, substr);
      }
      i++;
    }
  }

  public void doMove9000(Move move) {
    for (int i = 0; i < move.quantity(); i++) {
      stacks.get(move.to()).push(stacks.get(move.from()).pop());
    }
  }

  public void doMove9001(Move move) {
    var auxStack = new Stack<String>();
    for (int i = 0; i < move.quantity(); i++) {
      auxStack.push(stacks.get(move.from()).pop());
    }

    for (int i = 0; i < move.quantity(); i++) {
      stacks.get(move.to()).push(auxStack.pop());
    }
  }
}
