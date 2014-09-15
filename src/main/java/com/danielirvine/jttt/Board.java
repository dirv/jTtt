package com.danielirvine.jttt;
import java.nio.*;
import java.util.*;
import java.util.stream.*;

public class Board
{
  private int size;
  private String board;
  private static char unplayed = 0;

  private Board(int size, String board)
  {
    this.size = size;
    this.board = board;
  }

  public static Board empty(int size)
  {
    return new Board(size, CharBuffer.allocate(size*size).toString());
  }

  public char markAt(int sq)
  {
    return board.charAt(sq);
  }

  public Board play(int sq, char mark)
  {
    if (!isPlayed(sq))
    {
      return new Board(size, replace(sq, mark));
    }

    return this;
  }

  public int getSize()
  {
    return size;
  }

  public boolean isDrawn()
  {
    return board.indexOf(unplayed) == -1 && !isWon();
  }

  public boolean isWon()
  {
    List<List<Integer>> combos = new ArrayList<List<Integer>>();
    addRows(combos);
    addColumns(combos);
    addDiagonals(combos);

    for(List<Integer> combo : combos) {
      long playerCount = getPlayerCount(combo);
      if (playerCount == 1 && isPlayed(combo.get(0))) {
        return true;
      }
    }

    return false;
  }

  public String asString()
  {
    return board;
  }

  private boolean isPlayed(int sq)
  {
    return markAt(sq) != unplayed;
  }

  private String replace(int sq, char newMark)
  {
    return board.substring(0, sq) + newMark + board.substring(sq + 1);
  }

  private long getPlayerCount(List<Integer> sqs)
  {
    return sqs.stream()
      .map((sq) -> new Character(board.charAt(sq)))
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
