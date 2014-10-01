package com.danielirvine.jttt.web;

import com.danielirvine.jttt.*;
import org.junit.*;
import static org.junit.Assert.*;

public class WebGameTest {

  private WebGame webGame;
  private static final boolean COMPUTER = false;
  private static final boolean HUMAN = true;

  @Test
  public void createsNewGame() throws Exception {

    webGame = new WebGame(3, HUMAN, HUMAN);

    assertEquals("---------", state().getBoard());
    assertEquals("X's go, please click a square", state().getStatusText());
    assertEquals(false, state().getAlreadyHasMove());
    assertEquals(false, state().getFinished());
  }

  @Test
  public void playsAHumanMove() throws Exception {

    webGame = new WebGame(3, HUMAN, HUMAN);
    webGame.setNextMove(5);
    webGame.playNextMove();

    assertEquals("----X----", state().getBoard());
    assertEquals("O's go, please click a square", state().getStatusText());
  }

  @Test
  public void playsAComputerMove() throws Exception {
    webGame = new WebGame(new Board(3, "XX-OO----"), COMPUTER, COMPUTER);
    webGame.playNextMove();
    assertEquals("XXXOO----", state().getBoard());
  }

  @Test
  public void determinesIfGameIsFinished() throws Exception {
    webGame = new WebGame(new Board(3, "XXXOO----"), COMPUTER, COMPUTER);
    assertEquals(true, state().getFinished());
  }

  @Test
  public void hasNoAvailableMoveAfterPlaying() {
    webGame = new WebGame(3, HUMAN, HUMAN);
    webGame.setNextMove(5);
    webGame.playNextMove();
    assertEquals(false, state().getAlreadyHasMove());
  }

  @Test
	public void hasAvailableMoveIfComputerIsNext() {
    webGame = new WebGame(3, COMPUTER, COMPUTER);
    assertEquals(true, state().getAlreadyHasMove());
  }

  @Test
  public void showsWinningMessage() {
    webGame = new WebGame(new Board(3, "XXXOO----"), COMPUTER, COMPUTER);
    assertEquals("X wins!", state().getStatusText());
  }

  @Test
  public void showsDrawMessage() {
    webGame = new WebGame(new Board(3, "XXOOOXXOX"), COMPUTER, COMPUTER);
    assertEquals("It's a draw!", state().getStatusText());
  }

  private WebGameState state() {
    return webGame.getGameState();
  }

}
