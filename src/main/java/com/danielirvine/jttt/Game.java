package com.danielirvine.jttt;
import java.io.*;

public class Game {
  private Board board;
  private final Player x, o;

  public Game(MoveProvider moveProvider, Board board, boolean xHuman, boolean oHuman) {
    this.board = board;
    this.x = createPlayer(moveProvider, 'X', xHuman);
    this.o = createPlayer(moveProvider, 'O', oHuman);
  }

  public Game(MoveProvider moveProvider, int size, boolean xHuman, boolean oHuman) {
    this(moveProvider, Board.empty(size), xHuman, oHuman);
  }

  private static Player createPlayer(MoveProvider moveProvider, char mark, boolean human) {
    return new HumanPlayer(moveProvider, mark);
  }

  public Player getNextPlayer() {
    return board.getNumPlayedSquares() % 2 == 0 ? x : o;
  }

  public Player getLastPlayer() {
    return board.getNumPlayedSquares() % 2 == 0 ? o : x;
  }

  public void playNextMove() {
    Player next = getNextPlayer();
    board = board.play(next.getNextMove(), next.getMark());
  }

  public boolean isWon() {
    return board.isWon();
  }

  public boolean isDrawn() {
    return board.isDrawn();
  }

  public boolean isFinished() {
    return isDrawn() || isWon();
  }

  public int getSize() {
    return board.getSize();
  }

  public char markAt(int sq) {
    return board.markAt(sq);
  }
}
