package com.danielirvine.jttt;

public class CliGameLoop
{
  private final CliGame game;

  public CliGameLoop(CliGame game)
  {
    this.game = game;
  }

  public void playAll()
  {
    game.display();
    while(!game.isFinished())
    {
      game.playNextMove();
      game.display();
    }
  }
}


