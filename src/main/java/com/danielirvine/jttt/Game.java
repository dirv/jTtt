package com.danielirvine.jttt;
import java.io.*;

public class Game
{
  private Board board;
  private final Player x, o;

  public Game(Board board, Player x, Player o)
  {
    this.board = board;
    this.x = x;
    this.o = x;
  }

  public Game(int size, Player x, Player o)
  {
    this(Board.empty(size), x, o);
  }

  public Player getNextPlayer()
  {
    return board.getNextPlayerMark() == 'X' ? x : o;
  }

  public Player getLastPlayer()
  {
    return board.getNextPlayerMark() == 'X' ? o : x;
  }

  public void playNextMove()
  {
    Player next = getNextPlayer();
    board = board.play(next.getNextMove(), next.getMark());
  }

  public boolean isWon()
  {
    return board.isWon();
  }

  public boolean isDrawn()
  {
    return board.isDrawn();
  }

  public int getSize()
  {
    return board.getSize();
  }

  public char markAt(int sq)
  {
    return board.markAt(sq);
  }
}
