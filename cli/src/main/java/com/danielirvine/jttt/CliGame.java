package com.danielirvine.jttt;
import java.io.*;
import java.util.stream.*;
import java.util.*;
import static java.util.stream.Collectors.*;

public class CliGame {
  private final PrintWriter writer;
  private Game game;

  public CliGame(OutputStream out, InputStream in) {
    this.writer = new PrintWriter(out, true);
    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    CliOperations cliOperations = new CliOperations(writer, reader);
    this.game = new Game(cliOperations,
        cliOperations.getSize(),
        cliOperations.getIsHuman('X'),
        cliOperations.getIsHuman('O'));
    display();
  }

  public CliGame(Game game, PrintWriter writer) {
    this.writer = writer;
    this.game = game;
  }

  public void playAll() {
    while(!game.isFinished()) {
      playNextMove();
    }
  }

  public void playNextMove() {
    game.playNextMove();
    display();
  }

  private void display() {
    displayBoard();
    displayResult();
  }

  private void displayResult() {
    if (game.isWon()) {
      writer.printf("%s wins!\n", game.getLastPlayer().getMark());
    }
    else if (game.isDrawn()) {
      writer.printf("It's a draw!\n");
    }
  }

  private void displayBoard() {
    new TableWriter(writer).print(boardStrings(), game.getSize());
  }

  private List<String> boardStrings() {
    int length = game.getSize() * game.getSize();
    return IntStream.range(0, length).mapToObj(this::cellDisplay).collect(toList());
  }

  private String cellDisplay(int sq) {
    char mark = game.markAt(sq);
    return mark == Board.unplayed ? Integer.toString(sq + 1) : Character.toString(mark);
  }
}

