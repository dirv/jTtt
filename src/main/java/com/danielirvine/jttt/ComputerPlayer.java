package com.danielirvine.jttt;

public class ComputerPlayer extends Player {

  private static int infinity = 1000;

  public ComputerPlayer(char mark) {
    super(mark);
  }

  public Board playNextMove(Board board) {
    return findBestMove(board, getMark(), board.getUnplayedSquares().length).getBoard();
  }

  private PossibleMove findBestMove(Board board, char mark, int depth) {
    if (depth == 0) {
      return new PossibleMove(board, 0);
    }

    int bestScore = -infinity;
    PossibleMove bestMove = null;
    for(int sq : board.getUnplayedSquares()) {
      Board newBoard = board.play(sq, mark);
      int score;
      PossibleMove move;
      if(newBoard.isWon()) {
        score = score(newBoard, depth);
      }
      else {
        move = findBestMove(newBoard, otherMark(mark), depth-1);
        score = -move.getScore();
      }
      if (score > bestScore) {
        bestMove = new PossibleMove(newBoard, score);
        bestScore = score;
      }
    }
    return bestMove;
  }

  private int score(Board b, int depth) {
    if (b.isDrawn()) return 0;
    return depth;
  }

  private char otherMark(char mark) {
    return mark == 'X' ? 'O' : 'X';
  }
}
