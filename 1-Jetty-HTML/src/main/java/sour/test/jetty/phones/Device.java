package sour.test.jetty.phones;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class Device {
  
  private String             name;
  private Map<String, Specs> hm;
                             
  public Device(String name)
  {
    this.name = name;
    this.hm = new HashMap<String, Specs>();
  }
  
  public Device()
  {
  }
  
  public String getName()
  {
    return name;
  }
  
  @JsonProperty("specs")
  public Map<String, Specs> getMap()
  {
    return hm;
  }
}
