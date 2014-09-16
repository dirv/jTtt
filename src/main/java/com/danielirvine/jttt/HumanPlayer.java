package com.danielirvine.jttt;

public class HumanPlayer extends Player
{
  private final MoveProvider moveProvider;

  public HumanPlayer(MoveProvider moveProvider, char mark)
  {
    super(mark);
    this.moveProvider = moveProvider;
  }

  public int getNextMove()
  {
    return moveProvider.getMove();
  }
}
