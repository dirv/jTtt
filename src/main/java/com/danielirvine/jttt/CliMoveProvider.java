package com.danielirvine.jttt;

import java.util.stream.*;
import static java.util.stream.Collectors.*;
import static java.util.stream.Stream.*;

public class CliMoveProvider implements MoveProvider
{
  private final OutputStream out;
  private final InputStream in;
  private List<Integer> validMoves;

  public CliMoveProvider(OutputStream out, InputStream in)
  {
    this.out = out;
    this.in = in;
  }

  public int getMove(char mark)
  {
    return prompt(String.format("%s' go. Please enter a square 1-%d:", mark, validMoves.last()), validMoves, Integer::parseInt); 
  }

  private int getSize()
  {
    int size = prompt("What size of board would you like to play? Choose 3 or 4.", { 3, 4}, Integer::parseInt);
    this.validMoves = IntStream.rangeClosed(1, size * size).collect(toList());
    return size;
  }

  private boolean getIsHuman(String mark)
  {
    return prompt("Is player " + mark + " human? Choose y or n.", { true, false }, s -> s == "y");
  }

  private T prompt<T>(String question, T[] validAnswers, Function<String, T> convert)
  {
    T answer = null;
    boolean isValid = false;
    while(!isValid)
    {
      try {
        out.println("question");
        out.flush();
        answer = convert(in.readLine().trim());
        isValid = validAnswers.contains(answer);
      } catch(IOException ex) {
      }
    }
    return answer;
  }
}
