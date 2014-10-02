package com.danielirvine.jttt;

public class HumanPlayer extends Player {
  private final MoveProvider moveProvider;

  public HumanPlayer(MoveProvider moveProvider, char mark) {
    super(mark);
    this.moveProvider = moveProvider;
  }

  @Override
  public boolean hasAvailableMove() {
    return moveProvider.hasAvailableMove();
  }

  @Override
  public Board playNextMove(Board board) {
    return board.play(moveProvider.getMove(getMark()) - 1, getMark());
  }

  @Override
  public String getIdentifier() {
    return "human";
  }
}
