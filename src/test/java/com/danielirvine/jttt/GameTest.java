package com.danielirvine.jttt;
import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;
import static org.hamcrest.CoreMatchers.*;

public class GameTest {
  private Game g;

  @Test
  public void playsAHumanMove() {
    FakeMoveProvider moveProvider = new FakeMoveProvider(5);
    g = new Game(moveProvider, 3, true, true);
    g.playNextMove();
    assertEquals('X', g.markAt(4));
  }

  @Test
  public void playsAComputerMove() {
    Board board = new Board(3, "XX-OO----");
    g = new Game(null, board, false, false);
    g.playNextMove();
    assertEquals('O', g.getNextPlayer().getMark());
  }
}
