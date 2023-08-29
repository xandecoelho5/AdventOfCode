package com.xandecoelho5;

import java.math.BigInteger;
import java.util.Arrays;

public class Teste {
  public static void main(String[] args) {
    System.out.println(Arrays.toString(plusOne(new int[] { 1, 2, 3 })));
  }

  public static int[] plusOne(int[] digits) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < digits.length; i++) {
      builder.append(digits[i]);
    }
    BigInteger value = new BigInteger(builder.toString()).add(BigInteger.ONE);

    var toStr = value.toString();
    var length = toStr.length();
    int[] res = new int[length];
    for (int i = 0; i < length; i++) {
      res[i] = Integer.parseInt(String.valueOf(toStr.charAt(i)));
    }
    return res;
  }
}
