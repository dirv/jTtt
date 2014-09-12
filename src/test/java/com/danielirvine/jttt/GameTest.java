package com.danielirvine.jttt;
import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;
import static org.hamcrest.CoreMatchers.*;

public class GameTest
{
  private Game g;


  @Test
  public void testPlaysAHumanMove()
  {
    g = new Game(3, true, true);
    ((HumanPlayer)g.getNextPlayer()).setNextMove(5);
    g.playNextMove();
    assertEquals('X', g.markAt(5));
  }


  private HumanPlayer humanPlayer()
  {
    return (HumanPlayer)g.getNextPlayer();
  }
}
