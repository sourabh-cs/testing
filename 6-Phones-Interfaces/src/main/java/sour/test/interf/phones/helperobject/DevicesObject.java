package sour.test.interf.phones.helperobject;

import java.util.HashMap;
import java.util.Map;

import sour.test.interf.entity.IDevice;

public class DevicesObject {
  
  private Map<Integer, Map<String, Map<Integer, IDevice>>> products;
  
  public DevicesObject(int productId, IDevice device)
  {
    products = new HashMap<Integer, Map<String, Map<Integer, IDevice>>>();
    Map<String, Map<Integer, IDevice>> devicesTagMap = new HashMap<String, Map<Integer, IDevice>>();
    Map<Integer, IDevice> devices = new HashMap<Integer, IDevice>();
    devices.put(device.getId(), device);
    devicesTagMap.put("devices", devices);
    products.put(productId, devicesTagMap);
  }
  
  public Map<Integer, Map<String, Map<Integer, IDevice>>> getProducts()
  {
    return products;
  }
  
  public void setProducts(Map<Integer, Map<String, Map<Integer, IDevice>>> map)
  {
    this.products = map;
  }
}