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
