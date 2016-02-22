package sour.test.interf.ientity;

import java.util.HashMap;
import java.util.Map;

import sour.test.interf.entity.IBrand;
import sour.test.interf.entity.IProduct;

public class Brand implements IBrand {
  
  private int                    brandId;
  private String                 brandName;
  private Map<Integer, IProduct> products;
                                 
  public Brand()
  {
  }
  
  public Brand(String name)
  {
    this.brandId = -1;
    this.brandName = name;
    this.products = new HashMap<Integer, IProduct>();
  }
  
  public Brand(int id, String name)
  {
    this.brandId = id;
    this.brandName = name;
    this.products = new HashMap<Integer, IProduct>();
  }
  
  @Override
  public void initProducts()
  {
    products = new HashMap<Integer, IProduct>();
  }
  
  @Override
  public int getId()
  {
    return brandId;
  }
  
  @Override
  public void setId(int id)
  {
    brandId = id;
  }
  
  @Override
  public String getName()
  {
    return brandName;
  }
  
  @Override
  public void setName(String name)
  {
    brandName = name;
  }
  
  @Override
  public Map<Integer, IProduct> getProducts()
  {
    return products;
  }
  
  @Override
  public void setProducts(Map<Integer, IProduct> map)
  {
    products = map;
  }
  
}
