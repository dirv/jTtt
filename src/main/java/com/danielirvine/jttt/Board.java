package com.danielirvine.jttt;
import java.util.*;
import java.util.stream.*;

public class Board
{
  private int size;
  private Player[] board;

  private Board(int size, Player[] board)
  {
    this.size = size;
    this.board = board;
  }

  public static Board empty(int size)
  {
    return new Board(size, new Player[size*size]);
  }

  public Board play(int sq)
  {
    if (board[sq] == null)
    {
      Player[] newBoard = board.clone();
      newBoard[sq] = getNextPlayer();
      return new Board(size, newBoard);
    }

    return this;
  }

  public boolean isWon()
  {
    for(List<Integer> combo : winningRows()) {
      Object[] player = combo.stream()
        .map((sq) -> board[sq])
        .distinct()
        .toArray();
      if (player.length == 1 && player[0] != null) {
        return true;
      }
    }

    return false;
  }

  public List<List<Integer>> winningRows()
  {
    List<List<Integer>> rows = new ArrayList<List<Integer>>();
    for(int i = 0; i < size; ++i)
    {
      int start = i * size;
      rows.add(IntStream.range(start, start + size).boxed().collect(Collectors.toList()));
    }
    return rows;
  }

  public Player getNextPlayer()
  {
    Player next = Player.x;
    for (Player p : board)
      if (p != null)
        next = other(next);
    return next;
  }

  public int getSize()
  {
    return size;
  }

  public Player getPlayer(int square)
  {
    return board[square];
  }

  private Player other(Player p)
  {
    return p == Player.x ? Player.o : Player.x;
  }
}
