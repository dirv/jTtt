package com.danielirvine.jttt;
import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;

public class GameTest
{
  private OutputStream output = new ByteArrayOutputStream();
  private Game g;

  @Test
  public void testDrawsAnEmptyBoard()
  {
    create3x3Game();
    g.displayBoard();
    assertEquals(9, occurrencesInOutput("-"));
  }

  @Test
  public void testDrawsAWinningBoard()
  {
    create3x3Game();
    playSequence(0, 3, 1, 4, 2);
    g.displayBoard();
    assertEquals(2, occurrencesInOutput("O"));
    assertEquals(3, occurrencesInOutput("X"));
    assertEquals(4, occurrencesInOutput("-"));
  }

  private void create3x3Game()
  {
    g = new Game(output, 3);
  }

  private void playSequence(int... moves)
  {
    for(int sq : moves)
      g.play(sq);
  }

  private int occurrencesInOutput(String c)
  {
    System.out.println(output.toString());
    String s = output.toString();
    return s.length() - s.replace(c, "").length();
  }
}
