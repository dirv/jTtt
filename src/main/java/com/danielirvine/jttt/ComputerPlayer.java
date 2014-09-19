package com.danielirvine.jttt;

public class ComputerPlayer extends Player {

  private static int infinity = 1000;

  public ComputerPlayer(char mark) {
    super(mark);
  }

  public Board playNextMove(Board board) {
    return findBestMove(board, getMark(), -infinity, infinity, board.getUnplayedSquares().length).getBoard();
  }

  private PossibleMove findBestMove(Board board, char mark, int alpha, int beta, int depth) {
    if (depth == 0) {
      return new PossibleMove(board, 0);
    }

    Board bestMove = null;
    for(int sq : board.getUnplayedSquares()) {
      Board newBoard = board.play(sq, mark);
      int score = alpha;
      if(newBoard.isWon()) {
        score = score(newBoard, depth);
      }
      else {
        score = -findBestMove(newBoard, otherMark(mark), -beta, -alpha, depth-1).getScore();
      }
      if (score > alpha) {
        bestMove = newBoard;
        alpha = score;
      }
      if (alpha >= beta) {
        break;
      }
    }
    return new PossibleMove(bestMove, alpha);
  }

  private int score(Board b, int depth) {
    if (b.isDrawn()) return 0;
    return depth + 1;
  }

  private char otherMark(char mark) {
    return mark == 'X' ? 'O' : 'X';
  }
}
