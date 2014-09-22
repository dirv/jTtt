package com.danielirvine.jttt;
import static java.lang.Math.*;

public class ComputerPlayer extends Player {

  private static int infinity = 1000;

  public ComputerPlayer(char mark) {
    super(mark);
  }

  public Board playNextMove(Board board) {
    int maxDepth = min(board.getUnplayedSquares().length, 9);
    return findBestMove(board, getMark(), -infinity, infinity, maxDepth).getBoard();
  }

  private PossibleMove findBestMove(Board board, char mark, int alpha, int beta, int depth) {
    if (depth == 0 || board.isWon()) {
      return new PossibleMove(board, -score(board, depth));
    }

    Board bestMove = null;
    for(int sq : board.getUnplayedSquares()) {
      Board newBoard = board.play(sq, mark);
      int score = -findBestMove(newBoard, otherMark(mark), -beta, -alpha, depth-1).getScore();
      if (score > alpha) {
        bestMove = newBoard;
        alpha = score;
        if (alpha >= beta) {
          break;
        }
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
