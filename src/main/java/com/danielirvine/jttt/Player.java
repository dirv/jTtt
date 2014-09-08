package com.danielirvine.jttt;

public class Player
{
  public static final Player x = new Player('X');
  public static final Player o = new Player('O');

  private char mark;

  public Player (char mark)
  {
    this.mark = mark;
  }

  public char getMark()
  {
    return mark;
  }

  public String toString()
  {
    return Character.toString(mark);
  }
}
