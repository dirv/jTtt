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

  public void display()
  {
    displayBoard();
    if (board.isWon()) {
      out.print(nextPlayerMark());
      out.println(" wins!");
    }
    else if (board.isDrawn()) {
      out.println("It's a draw!");
    }
    else {
      out.print(nextPlayerMark());
      out.println("'s go. Please enter a square 1-9:");
    }
    out.flush();
  }

  private char nextPlayerMark()
  {
    return board.getLastPlayer().getMark();
  }

  private void displayBoard()
  {
    int size = board.getSize();
    int squares = size * size;
    for(int i = 0; i < squares; i++)
    {
      Player p = board.markAt(i);
      out.print(p == null ? '-' : p.getMark());
      if ((i+1) % size == 0)
      {
        out.println();
      }
    }
  }
}
