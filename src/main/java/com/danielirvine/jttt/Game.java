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
    new TableWriter(out).print(boardToArray());
  }

  private String[][] boardToArray()
  {
    int size = board.getSize();
    String[][] a = new String[size][];
    for(int i = 0; i < size; ++i) {
      a[i] = new String[size];
      for(int j = 0; j < size; ++j) {
        int cell = i*size + j;
        Player p = board.markAt(cell);
        a[i][j] = p == null ? Integer.toString(cell + 1) : Character.toString(p.getMark());
      }
    }
    return a;
  }

}
