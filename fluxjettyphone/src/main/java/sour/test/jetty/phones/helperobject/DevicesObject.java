package sour.test.jetty.phones.helperobject;

import java.util.HashMap;
import java.util.Map;

import sour.test.jetty.phones.Device;

public class DevicesObject {
  
  private Map<Integer, Map<String, Map<Integer, Device>>> products;
  
  public DevicesObject(int productId, Device device)
  {
    products = new HashMap<Integer, Map<String, Map<Integer, Device>>>();
    Map<String, Map<Integer, Device>> devicesTagMap = new HashMap<String, Map<Integer, Device>>();
    Map<Integer, Device> devices = new HashMap<Integer, Device>();
    devices.put(device.getId(), device);
    devicesTagMap.put("devices", devices);
    products.put(productId, devicesTagMap);
  }
  
  public Map<Integer, Map<String, Map<Integer, Device>>> getProducts()
  {
    return products;
  }
  
  public void setProducts(Map<Integer, Map<String, Map<Integer, Device>>> map)
  {
    this.products = map;
  }
}