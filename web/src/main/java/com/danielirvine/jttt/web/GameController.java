package com.danielirvine.jttt.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMethod;
import com.danielirvine.jttt.Board;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/")
@SessionAttributes({"boardString", "boardSize", "xHuman", "oHuman"})
public class GameController {

  private WebGame game;
  private WebGameState state;

  @RequestMapping("/")
  public String index() {
    return "index";
  }

  @RequestMapping("/game")
  public String startGame(@RequestParam("size") int size,
      @RequestParam("x") String x,
      @RequestParam("o") String o,
      ModelMap model) {
    model.addAttribute("boardSize", size);
    return "game";
  }

  @RequestMapping(value = "/get_board")
  @ResponseBody
  public WebGameState getBoard(@RequestParam("size") int size,
      @RequestParam("x") String x,
      @RequestParam("o") String o,
      ModelMap model) {
    boolean xHuman = isHumanString(x);
    boolean oHuman = isHumanString(o);
    model.addAttribute("xHuman", xHuman);
    model.addAttribute("oHuman", oHuman);
    game = new WebGame(size, xHuman, oHuman);
    return saveGame(model);
  }

  @RequestMapping(value = "/make_move")
  @ResponseBody
  public WebGameState makeMove(@RequestParam("sq") Integer sq,
      @ModelAttribute("boardString") String boardString,
      @ModelAttribute("boardSize") int boardSize,
      @ModelAttribute("xHuman") boolean xHuman,
      @ModelAttribute("oHuman") boolean oHuman,
      ModelMap model) {
    Board existingBoard = new Board(boardSize, boardString);
    game = new WebGame(existingBoard, xHuman, oHuman);
    if(sq != null) {
      game.setNextMove(sq+1);
    }
    game.playNextMove();
    return saveGame(model);
  }

  private WebGameState saveGame(ModelMap model) {
    WebGameState state = game.getGameState();
    model.addAttribute("boardString", state.getBoard());
    return state;
  }

  private static boolean isHumanString(String s) {
    return s.equals("HumanPlayer");
  }

}
