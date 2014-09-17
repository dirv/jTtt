package com.danielirvine.jttt;
import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.*;
import java.util.stream.*;

public class CliGameTest
{
  private OutputStream output = new ByteArrayOutputStream();
  private InputStream input = new ByteArrayInputStream(new byte[0]);
  private FakeMoveProvider moveProvider = new FakeMoveProvider();
  private CliGame g;

  @Test
  public void drawsAnEmptyBoard()
  {
    createStartGame(3);
    assertEquals(0, occurrencesInTable("X"));
    assertEquals(0, occurrencesInTable("O"));
  }

  @Test
  public void displaysInitialBoard()
  {
    createStartGame(3);
    assertThat(output.toString(), containsString("9"));
  }

  @Test
  public void drawsAnInPlayBoard()
  {
    createGame(3, "--X-OO-X-");
    playNextMove(1);
    assertEquals(2, occurrencesInTable("O"));
    assertEquals(3, occurrencesInTable("X"));
  }

  @Test
  public void drawsAFourByFourBoard()
  {
    createStartGame(4);
    assertThat(output.toString(), containsString("16"));
  }

  @Test
  public void showsWinningMessageWhenWon()
  {
    createGame(3, "XX-OO----");
    playNextMove(3);
    assertThat(output.toString(), containsString("X wins!"));
  }

  @Test
  public void showsDrawnMessageWhenDrawn()
  {
    createGame(3, "XXOOOXXO-");
    playNextMove(9);
    assertThat(output.toString(), containsString("It's a draw!"));
  }

  @Test
  public void playsGameUntilWin()
  {
    moveProvider = new FakeMoveProvider(1, 4, 2, 5, 3);
    createGame(3, "---------");
    g.playAll();
    assertThat(output.toString(), containsString("X wins!"));
  }

  private void createGame(Integer size, String state)
  {
    Board b = new Board(size, state);
    Game game = new Game(moveProvider, b, true, true);
    g = new CliGame(game, new PrintWriter(output, true));
  }

  private void createStartGame(Integer size)
  {
    StringBuilder inputSequence = new StringBuilder();
    appendLine(inputSequence, size.toString());
    appendLine(inputSequence, "y");
    appendLine(inputSequence, "y");
    input = new ByteArrayInputStream(inputSequence.toString().getBytes());
    g = new CliGame(output, input);
  }

  private void playNextMove(int sq)
  {
    moveProvider.setNextMove(sq);
    g.playNextMove();
  }

  private void appendLine(StringBuilder sb, String text)
  {
    sb.append(text);
    sb.append(System.lineSeparator());
  }

  private int occurrencesInTable(String c)
  {
    return occurrencesInTable(c, 3);
  }

  private int occurrencesInTable(String c, int size)
  {
    int startOfTable = output.toString().indexOf("/");
    String lastBoard = output.toString().substring(startOfTable);
    return lastBoard.length() - lastBoard.replace(c, "").length();
  }
}
