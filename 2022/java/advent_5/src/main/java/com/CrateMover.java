package com;

import java.util.List;
import java.util.Stack;

public record CrateMover(List<Stack<String>> stacks, List<Move> moves) {

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
