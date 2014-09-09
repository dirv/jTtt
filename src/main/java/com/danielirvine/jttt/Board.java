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

  public Player markAt(int sq)
  {
    return board[sq];
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

  public boolean isDrawn()
  {
    for(Player p : board)
      if (p == null)
        return false;

    return !isWon();
  }

  public boolean isWon()
  {
    List<List<Integer>> combos = new ArrayList<List<Integer>>();
    addRows(combos);
    addColumns(combos);
    addDiagonals(combos);

    for(List<Integer> combo : combos) {
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

  private void addRows(List<List<Integer>> combos)
  {
    for(int i = 0; i < size; ++i)
    {
      int start = i * size;
      combos.add(IntStream.range(start, start + size).boxed().collect(Collectors.toList()));
    }
  }

  private void addColumns(List<List<Integer>> combos)
  {
    int max = size*size;
    for (int i = 0; i < size; ++i)
    {
      List<Integer> combo = new ArrayList<Integer>();
      for (int j = i; j < max; j += size)
      {
        combo.add(j);
      }
      combos.add(combo);
    }
  }

  private void addDiagonals(List<List<Integer>> combos)
  {
    int max = size*size;
    List<Integer> ltr = new ArrayList<Integer>();
    for(int i = 0; i < max; i += size + 1)
    {
      ltr.add(i);
    }
    combos.add(ltr);

    List<Integer> rtl = new ArrayList<Integer>();
    for(int i = size - 1; i < max - 1; i += size - 1)
    {
      rtl.add(i);
    }
    combos.add(rtl);
  }

  public Player getNextPlayer()
  {
    Player next = Player.x;
    for (Player p : board)
      if (p != null)
        next = other(next);
    return next;
  }

  public Player getLastPlayer()
  {
    return other(getNextPlayer());
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
