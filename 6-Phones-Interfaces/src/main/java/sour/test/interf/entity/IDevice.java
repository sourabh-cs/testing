package sour.test.interf.entity;

import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import sour.test.interf.ientity.Device;

@JsonDeserialize(as = Device.class)
public interface IDevice {
  
  int getId();
  
  void setId(int id);
  
  String getName();
  
  void setName(String name);
  
  Map<Integer, ISpecs> getSpecs();
  
  void setSpecs(Map<Integer, ISpecs> map);
  
}