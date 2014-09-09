package com.danielirvine.jttt;
import java.io.*;

public class Game
{
  private PrintWriter out;
  private Board board;

  public Game(OutputStream out, int size)
  {
    this.out = new PrintWriter(out);
    this.board = Board.empty(size);
  }

  public void play(int sq)
  {
    board = board.play(sq);
  }

  public void displayBoard()
  {
    int squares = board.getSize() * board.getSize();
    for(int i = 0; i < squares; i++)
    {
      Player p = board.markAt(i);
      out.print(p == null ? '-' : p.getMark());
    }
    out.flush();
  }
}
