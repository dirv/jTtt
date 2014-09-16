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
  public void drawsAnEmptyBoard()
  {
    create3x3HumanHumanGame();
    playNextMove();
    assertEquals(0, occurrencesInTable("X"));
    assertEquals(0, occurrencesInTable("O"));
  }

  @Test
  public void drawsAnInPlayBoard()
  {
    create3x3HumanHumanGame(1, 4, 7, 5, 3);
    playNextMove();
    assertEquals(2, occurrencesInTable("O"));
    assertEquals(3, occurrencesInTable("X"));
  }

  @Test
  public void drawsAFourByFourBoard()
  {
    create4x4Game(1, 16, 2, 15);
    playNextMove();
    assertEquals(2, occurrencesInTable("X", 4));
    assertEquals(2, occurrencesInTable("O", 4));
  }

  @Test
  public void showsWinningMessageWhenWon()
  {
    create3x3HumanHumanGame(1, 4, 2, 5, 3);
    playNextMove();
    assertThat(output.toString(), containsString("X wins!"));
  }

  @Test
  public void showsDrawnMessageWhenDrawn()
  {
    create3x3HumanHumanGame(1, 4, 2, 5, 6, 3, 7, 8, 9);
    playNextMove();
    assertThat(output.toString(), containsString("It's a draw!"));
  }

  @Test
  public void showsPlayMessageWhenNotFinished()
  {
    create3x3HumanHumanGame();
    playNextMove();
    assertThat(output.toString(), containsString("Please enter a square"));
  }

  @Test
  public void playsGameUntilWin()
  {
    create3x3HumanHumanGame(1, 4, 2, 5, 3);
    playNextMove();
    assertThat(output.toString(), containsString("X wins!"));
  }

  @Test
  public void displaysInitialBoard()
  {
    create3x3HumanHumanGame();
    playNextMove();
    assertThat(output.toString(), containsString("9"));
  }

  @Test
  public void canPlayComputerPlayer()
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

  private void playNextMove()
  {
    g.playNextMove();
  }
}
