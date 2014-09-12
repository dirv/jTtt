package com.danielirvine.jttt;

public class HumanPlayer extends Player
{
  private int nextMove;

  public HumanPlayer(char mark)
  {
    super(mark);
  }

  public void setNextMove(int sq)
  {
    nextMove = sq;
  }

  public int getNextMove()
  {
    return nextMove;
  }
}
