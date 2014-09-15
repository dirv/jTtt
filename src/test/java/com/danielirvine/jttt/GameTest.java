package com.danielirvine.jttt;
import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;
import static org.hamcrest.CoreMatchers.*;

public class GameTest
{
  private Game g;


  @Test
  public void playsAHumanMove()
  {
    g = new Game(3, true, true);
    humanPlayer().setNextMove(5);
    g.playNextMove();
    assertEquals('X', g.markAt(5));
  }

  @Test
  public void playsAComputerMove()
  {
    g = new Game(3, true, false);

  }

  private HumanPlayer humanPlayer()
  {
    return (HumanPlayer)g.getNextPlayer();
  }
}
