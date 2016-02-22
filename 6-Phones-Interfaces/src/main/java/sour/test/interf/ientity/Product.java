package sour.test.interf.ientity;

import java.util.HashMap;
import java.util.Map;

import sour.test.interf.entity.IDevice;
import sour.test.interf.entity.IProduct;

public class Product implements IProduct {
  
  private int                   productId;
  private String                productName;
  private Map<Integer, IDevice> devices;
                                
  public Product(int id, String name)
  {
    this.productId = id;
    this.productName = name;
    this.devices = new HashMap<Integer, IDevice>();
  }
  
  public Product()
  {
  }
  
  @Override
  public void initDevices()
  {
    devices = new HashMap<Integer, IDevice>();
  }
  
  @Override
  public int getId()
  {
    return productId;
  }
  
  @Override
  public void setId(int id)
  {
    productId = id;
  }
  
  @Override
  public String getName()
  {
    return productName;
  }
  
  @Override
  public void setName(String name)
  {
    productName = name;
  }
  
  @Override
  public Map<Integer, IDevice> getDevices()
  {
    return devices;
  }
  
  @Override
  public void setDevices(Map<Integer, IDevice> map)
  {
    devices = map;
  }
  
}
