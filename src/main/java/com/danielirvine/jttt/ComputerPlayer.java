package com.danielirvine.jttt;

public class ComputerPlayer extends Player {
  public ComputerPlayer(char mark) {
    super(mark);
  }

  public Board playNextMove(Board board) {
    return board;
  }
}
