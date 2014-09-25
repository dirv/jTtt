package com.danielirvine.jttt;
import org.junit.*;
import static org.junit.Assert.*;

public class BoardTest {
  @Test
  public void createsANewBoardOfSizeThree() {
    Board b = Board.empty(3);
    assertEquals("---------", b.asString());
  }

  @Test
  public void canPlayAMove() {
    Board b = Board.empty(3);
    b = b.play(1, 'X');
    assertEquals("-X-------", b.asString());
  }

  @Test
  public void doesNotPlayMoveInSquareAlreadyPlayed() {
    Board b = Board.empty(3);
    b = b.play(1, 'X');
    b = b.play(1, 'O');
    assertEquals("-X-------", b.asString());
  }

  @Test
  public void canMarkMultipleSquares() {
    Board b = Board.empty(3);
    b = b.play(0, 'X');
    b = b.play(1, 'O');
    assertEquals("XO-------", b.asString());
  }

  @Test
  public void canWinWithRow() {
    Board b = new Board(3, "XXXOO----");
    assertTrue(b.isWon());
  }

  @Test
  public void canWinWithColumn() {
    Board b = new Board(3, "X-OX-OX--");
    assertTrue(b.isWon());
  }

  @Test
  public void canWinWithDiagonal() {
    Board b = new Board(3, "X--OX---X");
    assertTrue(b.isWon());
  }

  @Test
  public void canWinWithDiagonal4x4() {
    Board b = new Board(4, "OOOX--X--X--X---");
    assertTrue(b.isWon());
  }
}
