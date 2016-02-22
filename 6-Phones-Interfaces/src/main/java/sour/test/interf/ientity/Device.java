package sour.test.interf.ientity;

import java.util.HashMap;
import java.util.Map;

import sour.test.interf.entity.IDevice;
import sour.test.interf.entity.ISpecs;

public class Device implements IDevice {
  
  private int                  deviceId;
  private String               deviceName;
  private Map<Integer, ISpecs> specs;
                               
  public Device(int id, String name)
  {
    this.deviceId = id;
    this.deviceName = name;
    this.specs = new HashMap<Integer, ISpecs>();
  }
  
  public Device()
  {
  }
  
  @Override
  public int getId()
  {
    return deviceId;
  }
  
  @Override
  public void setId(int id)
  {
    deviceId = id;
  }
  
  @Override
  public String getName()
  {
    return deviceName;
  }
  
  @Override
  public void setName(String name)
  {
    deviceName = name;
  }
  
  @Override
  public Map<Integer, ISpecs> getSpecs()
  {
    return specs;
  }
  
  @Override
  public void setSpecs(Map<Integer, ISpecs> map)
  {
    specs = map;
  }
  
}
