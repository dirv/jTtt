package com.danielirvine.jttt.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;

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
    boolean xHuman = x == "HumanPlayer";
    boolean oHuman = o == "HumanPlayer";
    WebGame game = new WebGame(size, xHuman, oHuman);
    WebGameState state = game.getGameState();
    model.addAttribute("boardSize", size);
    return "game";
  }


}
