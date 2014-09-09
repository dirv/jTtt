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
  public void testCanWinWithRow()
  {
    assertTrue(boardWithSequence(3, 0, 4, 1, 5, 2).isWon());
  }

  @Test
  public void testCanWinWithColumn()
  {
    assertTrue(boardWithSequence(3, 1, 3, 4, 5, 7).isWon());
  }

  @Test
  public void testCanWinWithDiagonal()
  {
    assertTrue(boardWithSequence(3, 0, 3, 4, 5, 8).isWon());
  }

  @Test
  public void testCanWithWithDiagonal4x4()
  {
    assertTrue(boardWithSequence(4, 3, 0, 6, 1, 9, 2, 12).isWon());
  }

  @Test
  public void testCanGetLastPlayer()
  {
    Board b = Board.empty(3);
    b = b.play(0);
    assertEquals(Player.x, b.getLastPlayer());
  }

  private Board boardWithSequence(int size, int... plays)
  {
    Board b = Board.empty(size);
    for(int p : plays)
      b = b.play(p);
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
