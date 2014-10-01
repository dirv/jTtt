package com.danielirvine.jttt.web;

import com.danielirvine.jttt.*;
import org.junit.*;
import static org.junit.Assert.*;

public class WebGameTest {

  private WebGame webGame;

  @Test
  public void createsNewGame() throws Exception {

    webGame = new WebGame(3, true, true);

    assertEquals("---------", state().getBoard());
    assertEquals("X's go, please click a square", state().getStatusText());
    assertEquals(false, state().getAlreadyHasMove());
    assertEquals(false, state().getFinished());
  }

  @Test
  public void playsAHumanMove() throws Exception {

    webGame = new WebGame(3, true, true);
    webGame.setNextMove(5);
    webGame.playNextMove();

    assertEquals("----X----", state().getBoard());
    assertEquals("O's go, please click a square", state().getStatusText());
  }

  @Test
  public void playsAComputerMove() throws Exception {
    webGame = new WebGame(new Board(3, "XX-OO----"), false, false);
    webGame.playNextMove();
    assertEquals("XXXOO----", state().getBoard());
  }

  @Test
  public void determinesIfGameIsFinisehd() throws Exception {
    webGame = new WebGame(new Board(3, "XXXOO----"), false, false);
    assertEquals(true, state().getFinished());
  }

  private WebGameState state() {
    return webGame.getGameState();
  }

}
