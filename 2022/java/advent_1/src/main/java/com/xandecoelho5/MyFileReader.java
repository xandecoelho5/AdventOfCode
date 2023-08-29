package com.xandecoelho5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MyFileReader {

  public static String readFromFilePath(String path) throws IOException {
    ClassLoader classLoader = Main.class.getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(path);
    return readFromInputStream(inputStream);
  }

  private static String readFromInputStream(InputStream inputStream) throws IOException {
    StringBuilder builder = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      while ((line = br.readLine()) != null) {
        builder.append(line).append("\n");
      }
    }
    return builder.toString();
  }
}
