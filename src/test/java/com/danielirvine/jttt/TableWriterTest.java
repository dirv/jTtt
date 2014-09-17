package com.danielirvine.jttt;
import org.junit.*;
import static org.junit.Assert.*;
import static java.util.Arrays.*;
import java.util.*;
import java.io.*;
import static org.hamcrest.CoreMatchers.*;

public class TableWriterTest
{
  private OutputStream output = new ByteArrayOutputStream();
  private String[] result;

  @Test
  public void singleSquareTableHeader()
  {
    print(asList( "test" ), 1);
    assertEquals(3, result.length);
    assertEquals("test".length() + 4, result[0].length());
  }

  @Test
  public void singleSquareTableContent()
  {
    print(asList( "test" ), 1);
    assertThat(result[1], containsString("test"));
  }

  @Test
  public void doubleSquareTableContent()
  {
    print(asList( "hello", "hi" ), 2);
    assertThat(result[1], containsString("hello"));
    assertThat(result[1], containsString("hi"));
  }

  @Test
  public void doubleRowTableShowsDividingLine()
  {
    print(asList( "hello", "hi" ), 1);
    assertEquals(5, result.length);
    assertThat(result[1], containsString("hello"));
    assertThat(result[3], containsString("hi"));
  }

  @Test
  public void cellAlignment()
  {
    print(asList( "hello", "hi" ), 1);
    assertThat(result[1], containsString(" hello "));
    assertThat(result[3], containsString("    hi "));
  }

  private void print(List<String> table, int size)
  {
    new TableWriter(new PrintWriter(output, true)).print(table, size);
    result = output.toString().split("\n");
  }
}
