package sour.test.jetty.phones;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Brand {
  
  private int                   brandId;
  private String                brandName;
  private Map<Integer, Product> products;
                                
  public Brand()
  {
  }
  
  public Brand(String name)
  {
    this.brandId = -1;
    this.brandName = name;
    this.products = new HashMap<Integer, Product>();
  }
  
  public Brand(int id, String name)
  {
    this.brandId = id;
    this.brandName = name;
    this.products = new HashMap<Integer, Product>();
  }
  
  public void initProducts()
  {
    products = new HashMap<Integer, Product>();
  }
  
  public int getId()
  {
    return brandId;
  }
  
  public void setId(int id)
  {
    brandId = id;
  }
  
  public String getName()
  {
    return brandName;
  }
  
  public void setName(String name)
  {
    brandName = name;
  }
  
  public Map<Integer, Product> getProducts()
  {
    return products;
  }
  
  public void setProducts(Map<Integer, Product> map)
  {
    products = map;
  }
}
