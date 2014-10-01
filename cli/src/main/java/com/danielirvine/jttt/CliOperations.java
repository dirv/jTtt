package com.danielirvine.jttt;

import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;
import static java.util.stream.Stream.*;
import static java.util.Arrays.asList;

public class CliOperations implements MoveProvider {
  private final PrintWriter out;
  private final BufferedReader in;
  private List<Integer> validMoves;
  private static final List<Integer> validSizes = asList(3, 4);
  private static final List<String> validBooleans = asList("y", "n");

  public CliOperations(PrintWriter out, BufferedReader in) {
    this.out = out;
    this.in = in;
  }

  public int getMove(char mark) {
    int topSquare = validMoves.get(validMoves.size() - 1);
    String message = String.format("%s's go. Please enter a square 1-%d:", mark, topSquare);
    return ask(message, validMoves, Integer::parseInt);
  }

  public int getSize() {
    String message = "What size of board would you like to play? Choose 3 or 4.";
    int size = ask(message, validSizes, Integer::parseInt);
    this.validMoves = IntStream.rangeClosed(1, size * size).boxed().collect(toList());
    return size;
  }

  public boolean hasAvailableMove() {
    return true;
  }

  public boolean getIsHuman(char mark) {
    String message = String.format("Is player %c human? Choose y or n.", mark);
    String answer = ask(message, validBooleans, s -> s);
    return answer.equals("y");
  }

  private <T> T ask(String question, List<T> validAnswers, Function<String, T> convert) {
    T answer = null;
    while(!validAnswers.contains(answer)) {
      try {
        out.println(question);
        answer = convert.apply(in.readLine().trim());
      } catch(Exception ex) {
      }
    }
    return answer;
  }
}
