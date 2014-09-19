package com.danielirvine.jttt;

public abstract class Player {
  private char mark;

  protected Player(char mark) {
    this.mark = mark;
  }

  public char getMark() {
    return mark;
  }

  protected abstract Board playNextMove(Board board);

  public String toString() {
    return Character.toString(mark);
  }
}
