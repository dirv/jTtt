package com.danielirvine.jttt.web;
import org.junit.*;
import static org.junit.Assert.*;
import org.springframework.ui.ModelMap;

public class GameControllerTest {

  private static final String HUMAN = "HumanPlayer";
  private static final String COMPUTER = "ComputerPlayer";
  private static final String EMPTY_BOARD = "---------";

  private final ModelMap model = new ModelMap();
  private final GameController controller = new GameController();

  @Test
  public void displaysTheIndexPage() {
    assertEquals("index", controller.index());
  }

  @Test
  public void displaysTheGamePageWhenStartingAGame() throws Exception {
    String view = controller.startGame(3, "HumanPlayer", "HumanPlayer", model);
    assertEquals("game", view);
  }

  @Test
  public void setsTheBoardSizeWhenStartingAGame() {
    controller.startGame(3, HUMAN, HUMAN, model);
    assertTrue(model.containsAttribute("boardSize"));
    assertEquals(3, model.get("boardSize"));
  }

  @Test
	public void savesAHumanHumanBoardWhenStarting() {
    controller.getBoard(3, HUMAN, HUMAN, model);
    assertTrue(model.containsAttribute("xHuman"));
    assertTrue(model.containsAttribute("oHuman"));
    assertEquals(true, model.get("xHuman"));
    assertEquals(true, model.get("oHuman"));
  }

  @Test
	public void savesAnEmptyBoardWhenStarting() {
    controller.getBoard(3, HUMAN, HUMAN, model);
    assertEquals(EMPTY_BOARD, model.get("boardString"));
  }

  @Test
	public void returnsAWebGameStateWhenStarting() {
    WebGameState state = controller.getBoard(3, HUMAN, COMPUTER, model);
    assertNotNull(state);
    assertEquals(EMPTY_BOARD, state.getBoard());
    assertEquals("human", state.getNextMove());
  }

  @Test
	public void savesTheNewBoardAfterPlayingAMove() {
    controller.makeMove(2, EMPTY_BOARD, 3, true, true, model);
    assertEquals("--X------", model.get("boardString"));
  }

  @Test
	public void returnsTheNewBoardAfterPlayingMove() {
    WebGameState state = controller.makeMove(2, EMPTY_BOARD, 3, true, true, model);
    assertEquals("--X------", state.getBoard());
  }

  @Test
  public void playsAComputerMove() {
    WebGameState state = controller.makeMove(null, "XX-OO----", 3, false, true, model);
    assertEquals("XXXOO----", state.getBoard());
  }
}

