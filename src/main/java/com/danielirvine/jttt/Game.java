package com.danielirvine.jttt;
import java.io.*;

public class Game
{
  private Board board;

  public Game(int size, boolean xHuman, boolean oHuman)
  {
    board = Board.empty(size);
  }

  public void play(int sq)
  {
    board = board.play(sq);
  }

  public boolean isWon()
  {
    return board.isWon();
  }

  public boolean isDrawn()
  {
    return board.isDrawn();
  }

  public char lastPlayerMark()
  {
    return board.getLastPlayer().getMark();
  }

  public char nextPlayerMark()
  {
    return board.getNextPlayer().getMark();
  }

  public int getSize()
  {
    return board.getSize();
  }

  public char markAt(int sq)
  {
    return board.markAt(sq).getMark();
  }
}
