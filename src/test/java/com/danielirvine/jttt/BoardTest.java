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
    b = b.play(1, 'X');
    assertBoardEquals(b, "-X-------");
  }

  @Test
  public void testDoesNotPlayMoveInSquareAlreadyPlayed()
  {
    Board b = Board.empty(3);
    b = b.play(1, 'X');
    b = b.play(1, 'O');
    assertBoardEquals(b, "-X-------");
  }

  @Test
  public void testCanMarkMultipleSquares()
  {
    Board b = Board.empty(3);
    b = b.play(0, 'X');
    b = b.play(1, 'O');
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

  private Board boardWithSequence(int size, int... plays)
  {
    char nextMark = 'X';
    Board b = Board.empty(size);
    for(int p : plays) {
      b = b.play(p, nextMark);
      nextMark = nextMark == 'X' ? 'O' : 'X';
    }

    return b;
  }

  private void assertBoardEquals(Board actual, String expected)
  {
    int length = actual.getSize() * actual.getSize();
    assertEquals(expected.length(), length);
    StringBuilder s = new StringBuilder();
    for(int i = 0; i < length; ++i)
    {
      char p = actual.markAt(i);
      if (p == 0)
      {
        s.append("-");
      }
      else
      {
        s.append(p);
      }
    }
    assertEquals(expected, s.toString());
  }
}
