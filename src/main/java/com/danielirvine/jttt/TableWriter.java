package com.danielirvine.jttt;
import java.io.*;

public class TableWriter
{
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

  public TableWriter(PrintWriter out)
  {
    this.out = out;
  }

  public void print(Object[][] table)
  {
    String[][] tableStrings = new String[table.length][];
    int rows = table.length;
    int cols = table[0].length;
    int cellWidth = 0;
    for(int i = 0; i < rows; ++i)
    {
      String[] row = new String[cols];
      for(int j = 0; j < cols; ++j)
      {
        String cell = table[i][j].toString();
        if( cellWidth < cell.length() )
        {
          cellWidth = cell.length();
        }
        row[j] = cell;
      }
      tableStrings[i] = row;
    }

    printHeader(cellWidth, cols);
    for(int i = 0; i < rows - 1; ++i)
    {
      printRow(tableStrings[i], cellWidth);
      printMidDivider(cellWidth, cols);
    }
    printRow(tableStrings[rows-1], cellWidth);
    printFooter(cellWidth, cols);
    out.flush();
  }

  private void printRow(String[] cols, int cellWidth)
  {
    out.print(verticalDivider);
    for(int i = 0; i < cols.length - 1; ++i)
    {
      printCell(cols[i], cellWidth);
      out.print(verticalDivider);
    }
    printCell(cols[cols.length - 1], cellWidth);
    out.println(verticalDivider);
  }

  private void printCell(String cell, int cellWidth)
  {
    out.print(padLeft(cell, cellWidth + 1) + " ");
  }

  public static String padLeft(String s, int n)
  {
    return String.format("%1$" + n + "s", s);
  }

  private void printDivideLine(int left, int right, int join, int cellWidth, int cols)
  {
    out.print(left);
    for(int i = 0; i < cols - 1; ++i )
    {
      printHorizontalDivider(cellWidth);
      out.print(join);
    }
    printHorizontalDivider(cellWidth);
    out.println(right);
  }

  private void printMidDivider(int cellWidth, int cols)
  {
    printDivideLine(leftJoin, rightJoin, middleJoin, cellWidth, cols);
  }

  private void printHeader(int cellWidth, int cols)
  {
    printDivideLine(topLeft, topRight, topJoin, cellWidth, cols);
  }

  private void printFooter(int cellWidth, int cols)
  {
    printDivideLine(bottomLeft, bottomRight, bottomJoin, cellWidth, cols);
  }

  private void printHorizontalDivider(int cellWidth)
  {
    for(int j = 0; j < cellWidth; ++j)
    {
      out.print(horizontalDivider);
    }
  }
}
