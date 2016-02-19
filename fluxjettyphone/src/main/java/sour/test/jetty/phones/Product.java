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
  
  public void initDevices()
  {
    devices = new HashMap<Integer, Device>();
  }
  
  public int getId()
  {
    return productId;
  }
  
  public void setId(int id)
  {
    productId = id;
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
  
  public void setDevices(Map<Integer, Device> map)
  {
    devices = map;
  }
}
