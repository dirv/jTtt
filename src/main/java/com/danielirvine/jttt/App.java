package com.danielirvine.jttt;

public class App
{
    public static void main( String[] args )
    {
      new CliGameLoop(new CliGame(System.out, System.in)).playAll();
    }
}
