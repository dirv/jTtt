package com.danielirvine.jttt;
import java.io.*;
import java.util.stream.*;
import java.util.*;
import static java.util.stream.Stream.*;

public class TableWriter {
  private PrintWriter out;
  private static char topLeft = '/';
  private static char topRight = '\\';
  private static char bottomLeft = '\\';
  private static char bottomRight = '/';
  private static char topJoin  = '|';
  private static char middleJoin  = '+';
  private static char bottomJoin = '|';
  private static char leftJoin = '-';
  private static char rightJoin = '-';
  private static char verticalDivider = '|';
  private static char horizontalDivider = '-';

  public TableWriter(PrintWriter out) {
    this.out = out;
  }

  public void print(List<String> strings, int size) {
    int cellWidth = strings.stream().mapToInt(String::length).max().getAsInt();

    printHeader(cellWidth, size);
    for(int i = 0; i < strings.size(); ++i) {
      printCell(strings.get(i), i, size, cellWidth);
    }
    printFooter(cellWidth, size);
  }

  private void printCell(String cell, int i, int cols, int cellWidth) {
    if (i % cols == 0) {
      if (i != 0) {
        printMidDivider(cellWidth, cols);
      }
      out.print(verticalDivider);
    }
    out.print(padLeft(cell, cellWidth + 1) + " ");
    out.print(verticalDivider);
    if (i % cols == cols - 1) {
      out.println();
    }
  }

  public static String padLeft(String s, int n) {
    return String.format("%1$" + n + "s", s);
  }

  private void printDivideLine(char left, char right, char join, int cellWidth, int cols) {
    out.print(left);
    for(int i = 0; i < cols - 1; ++i ) {
      printHorizontalDivider(cellWidth + 2);
      out.print(join);
    }
    printHorizontalDivider(cellWidth + 2);
    out.println(right);
  }

  private void printMidDivider(int cellWidth, int cols) {
    printDivideLine(leftJoin, rightJoin, middleJoin, cellWidth, cols);
  }

  private void printHeader(int cellWidth, int cols) {
    printDivideLine(topLeft, topRight, topJoin, cellWidth, cols);
  }

  private void printFooter(int cellWidth, int cols) {
    printDivideLine(bottomLeft, bottomRight, bottomJoin, cellWidth, cols);
  }

  private void printHorizontalDivider(int cellWidth) {
    for(int j = 0; j < cellWidth; ++j) {
      out.print(horizontalDivider);
    }
  }
}
