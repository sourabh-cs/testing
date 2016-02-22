package sour.test.interf;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sour.test.interf.imodel.IBrandModel;
import sour.test.interf.model.BrandModel;

@RestController
public class App {
  
  @RequestMapping(value = "/")
  public String getAll()
  {
    return "redirect:/index.html";
  }
  
  @RequestMapping(value = "brand/new")
  public @ResponseBody IBrandModel testPost(@RequestBody IBrandModel brand)
  {
    return brand;
  }
  
  public static void main(String[] args)
  {
    IBrandModel ib = new BrandModel();
  }
}