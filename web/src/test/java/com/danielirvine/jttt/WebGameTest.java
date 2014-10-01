package com.danielirvine.jttt.web;

import com.danielirvine.jttt.*;
import org.junit.*;
import static org.junit.Assert.*;

public class WebGameTest {

  @Test
  public void createsNewGame() throws Exception {

    WebGameState actual = new WebGame(3, true, true).getGameState();

    assertEquals("---------", actual.getBoard());
    assertEquals("X's go, please click a square", actual.getStatusText());
    assertEquals(false, actual.getAlreadyHasMove());
    assertEquals(false, actual.getFinished());
  }

  @Test
  public void playsAHumanMove() throws Exception {

    WebGame webGame = new WebGame(3, true, true);
    webGame.setNextMove(5);
    webGame.playNextMove();
    WebGameState actual = webGame.getGameState();

    assertEquals("----X----", actual.getBoard());
    assertEquals("O's go, please click a square", actual.getStatusText());
  }

  @Test
  public void playsAComputerMove() throws Exception {
    WebGame webGame = new WebGame(new Board(3, "XX-OO----"), false, false);

    webGame.playNextMove();
    WebGameState actual = webGame.getGameState();
    assertEquals("XXXXOO----", true, true);
  }

}
