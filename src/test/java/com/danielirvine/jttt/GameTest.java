package com.danielirvine.jttt;
import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;
import static org.hamcrest.CoreMatchers.*;

public class GameTest
{
  private OutputStream output = new ByteArrayOutputStream();
  private Game g;

  @Test
  public void testDrawsAnEmptyBoard()
  {
    create3x3Game();
    g.display();
    assertEquals(0, occurrencesInTable("X"));
    assertEquals(0, occurrencesInTable("O"));
  }

  @Test
  public void testDrawsAnInPlayBoard()
  {
    create3x3Game();
    playSequence(0, 3, 6, 4, 2);
    g.display();
    assertEquals(2, occurrencesInTable("O"));
    assertEquals(3, occurrencesInTable("X"));
  }

  @Test
  public void testDrawsAFourByFourBoard()
  {
    create4x4Game();
    playSequence(0, 15, 1, 14);
    g.display();
    assertEquals(2, occurrencesInTable("X"));
    assertEquals(2, occurrencesInTable("O"));
  }

  @Test
  public void testShowsWinningMessageWhenWon()
  {
    create3x3Game();
    playSequence(0, 3, 1, 4, 2);
    g.display();
    assertThat(output.toString(), containsString("X wins!"));
  }

  @Test
  public void testShowsDrawnMessageWhenDrawn()
  {
    create3x3Game();
    playSequence(0, 3, 1, 4, 5, 2, 6, 7, 8);
    g.display();
    assertThat(output.toString(), containsString("It's a draw!"));
  }

  @Test
  public void testShowsPlayMessageWhenNotFinished()
  {
    create3x3Game();
    g.display();
    assertThat(output.toString(), containsString("Please enter a square"));
  }

  private void create3x3Game()
  {
    g = new Game(output, 3);
  }

  private void create4x4Game()
  {
    g = new Game(output, 4);
  }

  private void playSequence(int... moves)
  {
    for(int sq : moves)
      g.play(sq);
  }

  private int occurrencesInTable(String c)
  {
    String s = output.toString().trim();
    s = s.substring(0, s.lastIndexOf("\n"));
    return s.length() - s.replace(c, "").length();
  }
}
