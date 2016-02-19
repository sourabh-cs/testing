package sour.test.jetty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GuiController {
  
  @RequestMapping(value = "/")
  public String getAll()
  {
    return "redirect:/index.html";
  }
}
