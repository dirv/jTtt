package com.danielirvine.jttt;
import org.junit.*;
import static org.junit.Assert.*;

public class BoardTest
{
  @Test
  public void testCreatesANewBoardOfSizeThree()
  {
    Board b = Board.empty(3);
    assertBoardEquals(b, "---------");
  }

  private void assertBoardEquals(Board actual, String expected)
  {
    assertEquals(expected.length(), actual.getSize() * actual.getSize());
  }
}
