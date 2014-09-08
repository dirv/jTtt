package com.danielirvine.jttt;
import org.junit.*;
import static org.junit.Assert.*;

public class BoardTest
{
  @Test
  public void testCreatesANewBoardOfSizeThree()
  {
    Board b = Board.empty(3);
    assertBoardEquals(b, "---------");
  }

  @Test
  public void testCanPlayAMove()
  {
    Board b = Board.empty(3);
    b = b.play(1);
    assertBoardEquals(b, "-X-------");
  }

  @Test
  public void testDoesNotPlayMoveInSquareAlreadyPlayed()
  {
    Board b = Board.empty(3);
    b = b.play(1);
    b = b.play(1);
    assertBoardEquals(b, "-X-------");
  }

  @Test
  public void testGetNextPlayerReturnsPlayerX()
  {
    Board b = Board.empty(3);
    assertEquals(Player.x, b.getNextPlayer());
  }

  @Test
  public void testGetNextPlayerReturnsPlayerO()
  {
    Board b = Board.empty(3);
    b = b.play(1);
    assertEquals(Player.o, b.getNextPlayer());
  }

  private void assertBoardEquals(Board actual, String expected)
  {
    int length = actual.getSize() * actual.getSize();
    assertEquals(expected.length(), length);
    StringBuilder s = new StringBuilder();
    for(int i = 0; i < length; ++i)
    {
      Player p = actual.getPlayer(i);
      if (p == null)
      {
        s.append("-");
      }
      else
      {
        s.append(p.getMark());
      }
    }
    assertEquals(expected, s.toString());
  }
}
