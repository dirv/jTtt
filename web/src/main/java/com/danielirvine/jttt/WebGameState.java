package com.danielirvine.jttt.web;

public class WebGameState {

  private final String board;
  private final String statusText;
  private final boolean alreadyHasMove;
  private final boolean finished;

  public WebGameState(String board, String statusText, boolean alreadyHasMove, boolean finished) {
    this.board = board;
    this.statusText = statusText;
    this.alreadyHasMove = alreadyHasMove;
    this.finished = finished;
  }

  public String getBoard() {
    return board;
  }

  public String getStatusText() {
    return statusText;
  }

  public boolean getAlreadyHasMove() {
    return alreadyHasMove;
  }

  public boolean getFinished() {
    return finished;
  }

}
