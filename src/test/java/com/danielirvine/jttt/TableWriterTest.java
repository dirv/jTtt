package com.danielirvine.jttt;
import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;
import static org.hamcrest.CoreMatchers.*;

public class TableWriterTest
{
  private OutputStream output = new ByteArrayOutputStream();
  private String[] result;

  @Test
  public void singleSquareTableHeader()
  {
    print(new String[][] { { "test" } });
    assertEquals(3, result.length);
    assertEquals("test".length() + 4, result[0].length());
  }

  @Test
  public void singleSquareTableContent()
  {
    print(new String[][] { { "test" } });
    assertThat(result[1], containsString("test"));
  }

  @Test
  public void doubleSquareTableContent()
  {
    print(new String[][] { { "hello", "hi" } });
    assertThat(result[1], containsString("hello"));
    assertThat(result[1], containsString("hi"));
  }

  @Test
  public void doubleRowTableShowsDividingLine()
  {
    print(new String[][] { { "hello" }, { "hi" } });
    assertEquals(5, result.length);
    assertThat(result[1], containsString("hello"));
    assertThat(result[3], containsString("hi"));
  }

  @Test
  public void cellAlignment()
  {
    print(new String[][] { { "hello" }, { "hi" } });
    assertThat(result[1], containsString(" hello "));
    assertThat(result[3], containsString("    hi "));
  }

  private void print(Object[][] table)
  {
    new TableWriter(new PrintWriter(output)).print(table);
    result = output.toString().split("\n");
  }
}
