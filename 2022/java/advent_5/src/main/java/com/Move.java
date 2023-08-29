package com;

public record Move(int quantity, int from, int to) {

  public static Move from(String line) {
    var split = line.split(" ");
    return new Move(Integer.parseInt(split[1]), Integer.parseInt(split[3]) - 1, Integer.parseInt(split[5]) - 1);
  }
}
