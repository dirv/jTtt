package com.danielirvine.jttt;

public class Board
{
  private int size;
  private Player[] board;

  public static Board empty(int size)
  {
    Board b = new Board();
    b.size = size;
    b.board = new Player[size*size];
    return b;
  }

  public int getSize()
  {
    return size;
  }

  public Player getPlayer(int square)
  {
    return board[square];
  }
}
