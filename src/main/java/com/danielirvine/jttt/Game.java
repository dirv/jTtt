package com.danielirvine.jttt;
import java.io.*;

public class Game
{
  private Board board;
  private final Player x, o;
  private Player next;

  public Game(int size, boolean xHuman, boolean oHuman)
  {
    board = Board.empty(size);
    x = Player.create(xHuman, 'X');
    o = Player.create(oHuman, 'O');
    next = x;
  }


  public Player getNextPlayer()
  {
    return next;
  }

  public void playNextMove()
  {
    board = board.play(next.getNextMove(), next.getMark());
    next = next == x ? o : x;
  }

  public boolean isWon()
  {
    return board.isWon();
  }

  public boolean isDrawn()
  {
    return board.isDrawn();
  }

  public char lastPlayerMark()
  {
    return otherPlayer(next).getMark();
  }

  public char nextPlayerMark()
  {
    return next.getMark();
  }

  public int getSize()
  {
    return board.getSize();
  }

  public char markAt(int sq)
  {
    return board.markAt(sq);
  }

  private Player otherPlayer(Player p)
  {
    return p == x ? o : x;
  }
}
