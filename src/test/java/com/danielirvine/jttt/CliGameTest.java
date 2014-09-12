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
  private CliGame g;

  @Test
  public void testDrawsAnEmptyBoard()
  {
    create3x3HumanHumanGame();
    g.playAll();
    assertEquals(0, occurrencesInTable("X"));
    assertEquals(0, occurrencesInTable("O"));
  }

  @Test
  public void testDrawsAnInPlayBoard()
  {
    create3x3HumanHumanGame(1, 4, 7, 5, 3);
    g.playAll();
    assertEquals(2, occurrencesInTable("O"));
    assertEquals(3, occurrencesInTable("X"));
  }

  @Test
  public void testDrawsAFourByFourBoard()
  {
    create4x4Game(1, 16, 2, 15);
    g.playAll();
    assertEquals(2, occurrencesInTable("X", 4));
    assertEquals(2, occurrencesInTable("O", 4));
  }

  @Test
  public void testShowsWinningMessageWhenWon()
  {
    create3x3HumanHumanGame(1, 4, 2, 5, 3);
    g.playAll();
    assertThat(output.toString(), containsString("X wins!"));
  }

  @Test
  public void testShowsDrawnMessageWhenDrawn()
  {
    create3x3HumanHumanGame(1, 4, 2, 5, 6, 3, 7, 8, 9);
    g.playAll();
    assertThat(output.toString(), containsString("It's a draw!"));
  }

  @Test
  public void testShowsPlayMessageWhenNotFinished()
  {
    create3x3HumanHumanGame();
    g.playAll();
    assertThat(output.toString(), containsString("Please enter a square"));
  }

  @Test
  public void testPlaysGameUntilWin()
  {
    create3x3HumanHumanGame(1, 4, 2, 5, 3);
    g.playAll();
    assertThat(output.toString(), containsString("X wins!"));
  }

  @Test
  public void testDisplaysInitialBoard()
  {
    create3x3HumanHumanGame();
    g.playAll();
    assertThat(output.toString(), containsString("9"));
  }

  @Test
  public void testCanPlayComputerPlayer()
  {
    create3x3HumanComputerGame();
  }

  private void create3x3HumanHumanGame(Integer... plays)
  {
    createGame(3, true, true, plays);
  }

  private void create3x3HumanComputerGame(Integer... plays)
  {
    createGame(3, true, false, plays);
  }

  private void create4x4Game(Integer... plays)
  {
    createGame(4, true, true, plays);
  }

  private void createGame(Integer size, boolean xHuman, boolean oHuman, Integer... plays)
  {
    StringBuilder inputSequence = new StringBuilder();
    appendLine(inputSequence, size.toString());
    appendLine(inputSequence, xHuman ? "y" : "n");
    appendLine(inputSequence, oHuman ? "y" : "n");

    for(Integer p : plays)
      appendLine(inputSequence, p.toString());

    input = new ByteArrayInputStream(inputSequence.toString().getBytes());
    g = new CliGame(output, input);
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
    String lastBoard = getLastBoardShown(size);
    return lastBoard.length() - lastBoard.replace(c, "").length();
  }

  private String getLastBoardShown(int size)
  {
    int numLines = size * 2 + 1;
    String board = "";
    String[] lines = output.toString().split(System.lineSeparator());
    int startLine = lines.length - numLines - 1;
    for(int i = startLine; i < startLine + numLines; ++i) {
      board += lines[i];
    }
    return board;
  }
}
