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

  @Test
  public void testCanMarkMultipleSquares()
  {
    Board b = Board.empty(3);
    b = b.play(0);
    b = b.play(1);
    assertBoardEquals(b, "XO-------");
  }

  @Test
  public void testPlayerXCanWin()
  {
    assertTrue(boardWithSequence(3, 0, 4, 1, 5, 2).isWon());
  }

  private Board boardWithSequence(int size, int... plays)
  {
    Board b = Board.empty(size);
    for(int p : plays)
      b.play(p);
    return b;
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
