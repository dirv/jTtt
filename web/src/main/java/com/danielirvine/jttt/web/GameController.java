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

@Controller
@RequestMapping("/")
public class GameController {

  @RequestMapping("/")
  public String get() {
    return "index";
  }

  @RequestMapping("/game")
  public String get(@RequestParam("size") int size,
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
      @RequestParam("o") String o
      ) {
    boolean xHuman = x.equals("HumanPlayer");
    boolean oHuman = o.equals("HumanPlayer");
    WebGame game = new WebGame(size, xHuman, oHuman);
    return game.getGameState();
  }

  @RequestMapping(value = "/make_move")
  @ResponseBody
  public WebGameState makeMove(@RequestParam("sq") Integer sq) {
    Board existingBoard = null;
    boolean xHuman = true;
    boolean oHuman = true;
    WebGame game = new WebGame(existingBoard, xHuman, oHuman);
    if(sq != null) {
      game.setNextMove(sq);
    }
    game.playNextMove();
    return game.getGameState();
  }
}
