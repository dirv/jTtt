package com.danielirvine.jttt;

public abstract class Player
{
  private char mark;

  protected Player(char mark)
  {
    this.mark = mark;
  }

  public static Player create(boolean human, char mark)
  {
    return new HumanPlayer(mark);
  }

  public char getMark()
  {
    return mark;
  }

  protected abstract int getNextMove();

  public String toString()
  {
    return Character.toString(mark);
  }
}
