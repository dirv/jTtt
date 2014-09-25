package com.danielirvine.jttt.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/")
public class GameController {

  @RequestMapping(method = RequestMethod.GET)
  public Map<String, String> get() {
    return new HashMap<String, String>();
  }
}

