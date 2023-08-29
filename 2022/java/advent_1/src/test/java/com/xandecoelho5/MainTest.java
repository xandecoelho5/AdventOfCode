package com.xandecoelho5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class MainTest {

  @Test
  public void testReadSumsFromFile() throws IOException {
    List<Integer> sums = Main.readSumsFromFile("test-input.txt");
    assertEquals(Arrays.asList(15, 10, 5), sums);
  }

  @Test
  public void testParseLineAsInteger() {
    int parsedValue = Main.parseLineAsInteger("42");
    assertEquals(42, parsedValue);
  }

  @Test
  public void testParseLineAsIntegerWithInvalidInput() {
    int parsedValue = Main.parseLineAsInteger("invalid");
    assertEquals(0, parsedValue);
  }

  @Test
  public void testPrintSortedSums() {
    // arrange
    List<Integer> sums = Arrays.asList(15, 10, 5);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    // act
    Main.printSortedSums(sums);

    // assert
    assertEquals("Sorted sums: [15, 10, 5]", outContent.toString().trim());
  }

  @Test
  public void testPrintTop3Sum() {
    List<Integer> sums = Arrays.asList(15, 10, 5);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    Main.printTop3Sum(sums);
    assertEquals("Top 3 sum: 30", outContent.toString().trim());
  }
}