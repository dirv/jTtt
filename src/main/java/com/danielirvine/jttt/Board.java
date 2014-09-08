package com.danielirvine.jttt;

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

  public Board play(int sq, Player player)
  {
    if (board[sq] == null)
    {
      Player[] newBoard = board.clone();
      newBoard[sq] = player;
      return new Board(size, newBoard);
    }

    return this;
  }

  public int getSize()
  {
    return size;
  }

  public Player getPlayer(int square)
  {
    return board[square];
  }
}
