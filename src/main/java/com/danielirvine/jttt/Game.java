package com.danielirvine.jttt;
import java.io.*;

public class Game
{
  private PrintWriter out;
  private BufferedReader in;
  private Board board;

  public Game(OutputStream out, InputStream in)
  {
    this.out = new PrintWriter(out);
    this.in = new BufferedReader(new InputStreamReader(in));
    createBoard();
  }

  public void playAll()
  {
    display();
    while(!board.isDrawn() && !board.isWon())
    {
      try {
        play(Integer.parseInt(in.readLine().trim()) - 1);
        display();
      }
      catch(IOException ex)
      {
      }
    }
  }

  private void createBoard()
  {
    boolean validSize = false;
    int size = 3;
    while(!validSize)
    {
      try {
        out.println("What size of board would you like to play? Choose 3 or 4.");
        out.flush();
        size = Integer.parseInt(in.readLine().trim());
        if (size == 3 || size == 4)
        {
          validSize = true;
        }
      }
      catch(IOException ex)
      {
      }
    }
    board = Board.empty(size);
  }

  public void play(int sq)
  {
    board = board.play(sq);
  }

  public void display()
  {
    displayBoard();
    if (board.isWon()) {
      out.print(lastPlayerMark());
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

  private char lastPlayerMark()
  {
    return board.getLastPlayer().getMark();
  }

  private char nextPlayerMark()
  {
    return board.getNextPlayer().getMark();
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
