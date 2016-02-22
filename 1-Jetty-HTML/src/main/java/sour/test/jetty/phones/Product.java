package sour.test.jetty.phones;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class Product {
  
  private String              name;
  private Map<String, Device> hm;
                              
  public Product(String name)
  {
    this.name = name;
    this.hm = new HashMap<String, Device>();
  }
  
  public Product()
  {
  }
  
  public String getName()
  {
    return name;
  }
  
  @XmlElement(name = "devices")
  @JsonProperty("devices")
  public Map<String, Device> getMap()
  {
    return hm;
  }
}
