package sour.test.interf.entity;

import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import sour.test.interf.ientity.Product;

@JsonDeserialize(as = Product.class)
public interface IProduct {
  
  void initDevices();
  
  int getId();
  
  void setId(int id);
  
  String getName();
  
  void setName(String name);
  
  Map<Integer, IDevice> getDevices();
  
  void setDevices(Map<Integer, IDevice> map);
  
}