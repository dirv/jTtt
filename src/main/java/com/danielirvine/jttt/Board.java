package com.danielirvine.jttt;

public class Board
{
  private int size;

  public static Board empty(int size)
  {
    Board b = new Board();
    b.size = size;
    return b;
  }

  public int getSize()
  {
    return size;
  }

}
