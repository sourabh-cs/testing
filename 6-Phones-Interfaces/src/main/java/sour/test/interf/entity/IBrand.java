package sour.test.interf.entity;

import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import sour.test.interf.ientity.Brand;

@JsonDeserialize(as = Brand.class)
public interface IBrand {
  
  void initProducts();
  
  int getId();
  
  void setId(int id);
  
  String getName();
  
  void setName(String name);
  
  Map<Integer, IProduct> getProducts();
  
  void setProducts(Map<Integer, IProduct> map);
  
}