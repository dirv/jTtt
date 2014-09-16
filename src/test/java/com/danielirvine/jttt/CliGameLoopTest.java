package com.danielirvine.jttt;
import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.*;
import java.util.stream.*;

public class CliGameLoopTest
{
  private OutputStream output = new ByteArrayOutputStream();
  private InputStream input = new ByteArrayInputStream(new byte[0]);
  private CliGame g;

  @Test
  public void playsGameUntilWin()
  {
    create3x3HumanHumanGame(1, 4, 2, 5, 3);
    CliGameLoop gameLoop = new CliGameLoop(g);
    gameLoop.playAll();
    assertThat(output.toString(), containsString("X wins!"));
  }

  private void create3x3HumanHumanGame(Integer... plays)
  {
    createGame(3, true, true, plays);
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
}
