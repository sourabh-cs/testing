package sour.test.jetty.phones.helperobject;

import java.util.HashMap;
import java.util.Map;

import sour.test.jetty.phones.Product;

public class ProductsObject {
  
  private Map<Integer, Product> products;
  
  public ProductsObject(Product product)
  {
    products = new HashMap<Integer, Product>();
    products.put(product.getId(), product);
  }
  
  public Map<Integer, Product> getProducts()
  {
    return products;
  }
  
  public void setProducts(Map<Integer, Product> map)
  {
    this.products = map;
  }
}