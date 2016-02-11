package sour.test.jetty.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sour.test.jetty.phones.Brand;

@Controller
public class GuiController {
  
  Map<String, Brand> phones = new HashMap<String, Brand>();
  
  @RequestMapping(value = "/")
  public String getAll()
  {
    return "redirect:/index.html";
  }
}
