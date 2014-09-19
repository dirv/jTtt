package com.danielirvine.jttt;
import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;
import static org.hamcrest.CoreMatchers.*;
import static java.util.stream.Stream.*;
import java.util.stream.*;
import java.util.*;

public class ComputerPlayerTest {

  ComputerPlayer x = new ComputerPlayer('X');
  ComputerPlayer o = new ComputerPlayer('O');

  @Test
  public void playsAWinningMove() {
    Board b = x.playNextMove(new Board(3, "X-O-XO---"));
    assertTrue(b.isWon());
    assertEquals('X', b.markAt(8));
  }

  @Test
  public void playsADrawingMove() {
    Board b = o.playNextMove(new Board(3, "XOOOXXX--"));
    assertEquals('O', b.markAt(8));
  }

  @Test
  public void playsCentreSquare() {
    Board b = x.playNextMove(new Board(3, "-X-O-O-X-"));
    assertEquals('X', b.markAt(4));
  }

  @Test
  public void playsGoodFirstMove() {
    Board b = x.playNextMove(Board.empty(3));
    int[] acceptedSquares = new int[] {0, 2, 4, 6, 8};
    List<Character> values = IntStream.of(acceptedSquares).mapToObj(b::markAt).collect(Collectors.toList());
    assertThat(values, hasItem('X'));
  }
}
