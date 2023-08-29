package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

  public static void main(String[] args) throws IOException {
    Map<Character, Map<Character, Integer>> scoreMap = new HashMap<>() {
      {
        put('A', new HashMap<>() {
          {
            put('X', 1 + 3);
            put('Y', 2 + 6);
            put('Z', 3 + 0);
          }
        });
        put('B', new HashMap<>() {
          {
            put('X', 1 + 0);
            put('Y', 2 + 3);
            put('Z', 3 + 6);
          }
        });
        put('C', new HashMap<>() {
          {
            put('X', 1 + 6);
            put('Y', 2 + 0);
            put('Z', 3 + 3);
          }
        });
      }
    };

    var firstScore = getScoreAccordingToOptions(scoreMap, "input.txt");
    System.out.println("Total score of 1st part: " + firstScore);

    scoreMap.get('A').replace('X', 3 + 0);
    scoreMap.get('A').replace('Y', 1 + 3);
    scoreMap.get('A').replace('Z', 2 + 6);

    scoreMap.get('B').replace('X', 1 + 0);
    scoreMap.get('B').replace('Y', 2 + 3);
    scoreMap.get('B').replace('Z', 3 + 6);

    scoreMap.get('C').replace('X', 2 + 0);
    scoreMap.get('C').replace('Y', 3 + 3);
    scoreMap.get('C').replace('Z', 1 + 6);

    var secondScore = getScoreAccordingToOptions(scoreMap, "input.txt");
    System.out.println("Total score of 2nd part: " + secondScore);
  }

  private static int getScoreAccordingToOptions(Map<Character, Map<Character, Integer>> scoreMap, String filename)
      throws IOException {
    int scoreSum = 0;
    try (InputStream is = Main.class.getClassLoader().getResourceAsStream(filename);
        BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
      String line;
      while ((line = br.readLine()) != null) {
        var options = line.split(" ");
        var enemyOption = options[0].charAt(0);
        var myOption = options[1].charAt(0);
        scoreSum += scoreMap.get(enemyOption).get(myOption);
      }
    }
    return scoreSum;
  }
}