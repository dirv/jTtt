package com.danielirvine.jttt;
import java.util.*;
import static java.util.Arrays.*;

public class FakeMoveProvider implements MoveProvider {
  private final List<Integer> moves;

  public FakeMoveProvider(Integer... moves) {
    this.moves = new ArrayList(asList(moves));
  }

  public int getMove(char mark) {
    return moves.remove(0);
  }

  public void setNextMove(int sq) {
    moves.add(sq);
  }

  public boolean hasAvailableMove() {
    return true;
  }
}
