package sour.test.jetty.phones;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Product {
  
  private int                  productId;
  private String               productName;
  private Map<Integer, Device> devices;
                               
  public Product(int id, String name)
  {
    this.productId = id;
    this.productName = name;
    this.devices = new HashMap<Integer, Device>();
  }
  
  public Product()
  {
  }
  
  public int getId()
  {
    return productId;
  }
  
  public String getName()
  {
    return productName;
  }
  
  public void setName(String name)
  {
    productName = name;
  }
  
  public Map<Integer, Device> getDevices()
  {
    return devices;
  }
}
