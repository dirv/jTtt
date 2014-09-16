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
    game.playNextMove();
    display();
  }

  public void display()
  {
    displayBoard();
    if (game.isWon()) {
      out.printf("%s wins!\n", game.getLastPlayer().getMark());
    }
    else if (game.isDrawn()) {
      out.println("It's a draw!");
    }
    out.flush();
  }

  private Game createGame()
  {
    CliMoveProvider moveProvider = new CliMoveProvider(out, in);
    return new Game(
        moveProvider.getSize(),
        createPlayer(moveProvider.getIsHuman("X")),
        createPlayer(moveProvider.getIsHuman("O")));
  }

  private Player createPlayer(String mark, CliMoveProvider moveProvider)
  {
    boolean isHuamn = moveProvider.getIsHuman(mark);
    return isHuman ? new HumanPlayer(moveProvider, mark) : null;
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

