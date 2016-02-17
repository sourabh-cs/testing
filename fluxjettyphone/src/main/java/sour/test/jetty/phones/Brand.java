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
  
  public Brand(int id, String name)
  {
    this.brandId = id;
    this.brandName = name;
    this.products = new HashMap<Integer, Product>();
  }
  
  public int getId()
  {
    return brandId;
  }
  
  public String getName()
  {
    return brandName;
  }
  
  public Map<Integer, Product> getProducts()
  {
    return products;
  }
}
