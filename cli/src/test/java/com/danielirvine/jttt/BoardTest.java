package com.danielirvine.jttt;
import org.junit.*;
import static org.junit.Assert.*;

public class BoardTest {
  @Test
  public void createsANewBoardOfSizeThree() {
    Board b = Board.empty(3);
    assertBoardEquals(b, "---------");
  }

  @Test
  public void canPlayAMove() {
    Board b = Board.empty(3);
    b = b.play(1, 'X');
    assertBoardEquals(b, "-X-------");
  }

  @Test
  public void doesNotPlayMoveInSquareAlreadyPlayed() {
    Board b = Board.empty(3);
    b = b.play(1, 'X');
    b = b.play(1, 'O');
    assertBoardEquals(b, "-X-------");
  }

  @Test
  public void canMarkMultipleSquares() {
    Board b = Board.empty(3);
    b = b.play(0, 'X');
    b = b.play(1, 'O');
    assertBoardEquals(b, "XO-------");
  }

  @Test
  public void canWinWithRow() {
    assertTrue(boardWithSequence(3, 0, 4, 1, 5, 2).isWon());
  }

  @Test
  public void canWinWithColumn() {
    assertTrue(boardWithSequence(3, 1, 3, 4, 5, 7).isWon());
  }

  @Test
  public void canWinWithDiagonal() {
    assertTrue(boardWithSequence(3, 0, 3, 4, 5, 8).isWon());
  }

  @Test
  public void canWinWithDiagonal4x4() {
    assertTrue(boardWithSequence(4, 3, 0, 6, 1, 9, 2, 12).isWon());
  }

  private Board boardWithSequence(int size, int... plays) {
    char nextMark = 'X';
    Board b = Board.empty(size);
    for(int p : plays) {
      b = b.play(p, nextMark);
      nextMark = nextMark == 'X' ? 'O' : 'X';
    }

    return b;
  }

  private void assertBoardEquals(Board actual, String expected) {
    assertEquals(expected, actual.asString());
  }
}