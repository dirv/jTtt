package com.danielirvine.jttt;
import java.util.*;
import java.util.stream.*;

public class Board
{
  private int size;
  private char[] board;
  private static char unplayed = 0;

  private Board(int size, char[] board)
  {
    this.size = size;
    this.board = board;
  }

  public static Board empty(int size)
  {
    return new Board(size, new char[size*size]);
  }

  public char markAt(int sq)
  {
    return board[sq];
  }

  public Board play(int sq, char mark)
  {
    if (board[sq] == unplayed)
    {
      char[] newBoard = board.clone();
      newBoard[sq] = mark;
      return new Board(size, newBoard);
    }

    return this;
  }

  public int getSize()
  {
    return size;
  }

  public boolean isDrawn()
  {
    for(char p : board)
      if (p == unplayed)
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
      long playerCount = getPlayerCount(combo);
      if (playerCount == 1 && board[combo.get(0)] != unplayed) {
        return true;
      }
    }

    return false;
  }

  private long getPlayerCount(List<Integer> sqs)
  {
    return sqs.stream()
      .map((sq) -> new Character(board[sq]))
      .distinct()
      .count();
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

}
