package com.danielirvine.jttt;
import java.io.*;

public class CliGame
{
  private final PrintWriter out;
  private final BufferedReader in;
  private Game game;

  public CliGame(OutputStream out, InputStream in, Game game)
  {
    this.out = new PrintWriter(out);
    this.in = new BufferedReader(new InputStreamReader(in));
    this.game = game;
    display();
  }

  public CliGame(OutputStream out, InputStream in)
  {
    this(out, in, createGame());
  }

  public void playNextMove()
  {
    Player next = game.getNextPlayer();

    if(next instanceof HumanPlayer) {
      setNextHumanMove((HumanPlayer)next);
    }

    game.playNextMove();
    display();
  }

  public boolean isFinished()
  {
    return game.isDrawn() || game.isWon();
  }

  private void setNextHumanMove(HumanPlayer hp)
  {
    try {
      String read = in.readLine();
      int sq = Integer.parseInt(read.trim()) - 1;
      hp.setNextMove(sq);
    }
    catch(IOException ex)
    {
    }
  }

  public void display()
  {
    displayBoard();
    if (game.isWon()) {
      out.print(game.lastPlayerMark());
      out.println(" wins!");
    }
    else if (game.isDrawn()) {
      out.println("It's a draw!");
    }
    else {
      out.print(game.nextPlayerMark());
      out.println("'s go. Please enter a square 1-9:");
    }
    out.flush();
  }

  private Game createGame()
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
    return new Game(size, getIsHuman("X"), getIsHuman("O"));
  }

  private boolean getIsHuman(String mark)
  {
    boolean validBoolean = false;
    String human = null;
    while(!validBoolean)
    {
      try {
        out.println("Is player " + mark + " human? Choose y or n.");
        out.flush();
        human = in.readLine().trim();
        validBoolean = human.equals("y") || human.equals("n");
      }
      catch(IOException ex)
      {
      }
    }
    return human == "y";
  }

  private void displayBoard()
  {
    new TableWriter(out).print(boardToArray());
  }

  private String[][] boardToArray()
  {
    int size = game.getSize();
    String[][] a = new String[size][];
    for(int i = 0; i < size; ++i) {
      a[i] = new String[size];
      for(int j = 0; j < size; ++j) {
        int cell = i*size + j;
        char mark = game.markAt(cell);
        a[i][j] = mark == 0 ? Integer.toString(cell + 1) : Character.toString(mark);
      }
    }
    return a;
  }
}

