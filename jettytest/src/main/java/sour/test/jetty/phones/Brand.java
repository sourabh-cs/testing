package sour.test.jetty.phones;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class Brand {
  
  private String               name;
  private Map<String, Product> hm;
                               
  public Brand()
  {
  }
  
  public Brand(String name)
  {
    this.name = name;
    this.hm = new HashMap<String, Product>();
  }
  
  public String getName()
  {
    return name;
  }
  
  @XmlElement(name = "products")
  @JsonProperty("products")
  public Map<String, Product> getMap()
  {
    return hm;
  }
}
