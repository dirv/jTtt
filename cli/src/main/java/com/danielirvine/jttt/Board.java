package com.danielirvine.jttt;
import java.nio.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;
import static java.util.stream.Stream.*;

public class Board {
  private int size;
  private String board;
  public static char unplayed = '-';

  public Board(int size, String board) {
    this.size = size;
    this.board = board;
  }

  public static Board empty(int size) {
    char[] array = new char[size*size];
    Arrays.fill(array, unplayed);
    return new Board(size, new String(array));
  }

  public char markAt(int sq) {
    return board.charAt(sq);
  }

  public Board play(int sq, char mark) {
    return isPlayed(sq) ? this : new Board(size, replace(sq, mark));
  }

  public int getSize() {
    return size;
  }

  public int getNumSquares() {
    return size * size;
  }

  public boolean isDrawn() {
    return board.indexOf(unplayed) == -1 && !isWon();
  }

  public boolean isWon() {
    Stream<IntStream> allCombos = concat(rows(), concat(columns(), diagonals()));

    return allCombos
      .filter(this::isWinningCombo)
      .findAny()
      .isPresent();
  }

  public String asString() {
    return board;
  }

  public int getNumPlayedSquares() {
    return board.replace(new Character(unplayed).toString(), "").length();
  }

  public int[] getUnplayedSquares() {
    return IntStream.range(0, getNumSquares())
      .filter(c -> board.charAt(c) == unplayed)
      .toArray();
  }

  private boolean isPlayed(int sq) {
    return markAt(sq) != unplayed;
  }

  private String replace(int sq, char newMark) {
    return board.substring(0, sq) + newMark + board.substring(sq + 1);
  }

  private boolean isWinningCombo(IntStream combo) {
    return combo
      .map(this::markAt)
      .reduce((result, sq) -> {
        if (sq != result) {
          result = unplayed;
        }
        return result;
      })
    .getAsInt() != unplayed;
  }

  private Stream<IntStream> rows() {
    return IntStream
      .range(0, size)
      .map(i -> i * size)
      .mapToObj(start -> IntStream.range(start, start + size));
  }

  private Stream<IntStream> columns() {
    return IntStream
      .range(0, size)
      .mapToObj(i -> IntStream.iterate(i, j -> j + size)
      .limit(size));
  }

  private Stream<IntStream> diagonals() {
    IntStream ltr = IntStream
      .iterate(0, i -> i + size + 1)
      .limit(size);

    IntStream rtl = IntStream
      .iterate(size - 1, i -> i + size - 1)
      .limit(size);

    return of(ltr, rtl);
  }
}
