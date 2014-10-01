package com.danielirvine.jttt.web;

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

}
