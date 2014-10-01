package com.danielirvine.jttt.web;

public class WebGameState {

  private final String board;
  private final String statusText;
  private final boolean requiresInput;
  private final boolean finished;

  public WebGameState(String board, String statusText, boolean requiresInput, boolean finished) {
    this.board = board;
    this.statusText = statusText;
    this.requiresInput = requiresInput;
    this.finished = finished;
  }

  public String getBoard() {
    return board;
  }

  public String getStatusText() {
    return statusText;
  }

  public boolean getRequiresInput() {
    return requiresInput;
  }

  public boolean getFinished() {
    return finished;
  }

}
