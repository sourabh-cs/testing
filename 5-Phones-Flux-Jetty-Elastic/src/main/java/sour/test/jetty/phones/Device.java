package sour.test.jetty.phones;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Device {
  
  private int                 deviceId;
  private String              deviceName;
  private Map<Integer, Specs> specs;
                              
  public Device(int id, String name)
  {
    this.deviceId = id;
    this.deviceName = name;
    this.specs = new HashMap<Integer, Specs>();
  }
  
  public Device()
  {
  }
  
  public int getId()
  {
    return deviceId;
  }
  
  public void setId(int id)
  {
    deviceId = id;
  }
  
  public String getName()
  {
    return deviceName;
  }
  
  public void setName(String name)
  {
    deviceName = name;
  }
  
  public Map<Integer, Specs> getSpecs()
  {
    return specs;
  }
  
  public void setSpecs(Map<Integer, Specs> map)
  {
    specs = map;
  }
}
