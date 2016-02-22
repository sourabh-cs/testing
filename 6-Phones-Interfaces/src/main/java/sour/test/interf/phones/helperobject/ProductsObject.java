package sour.test.interf.phones.helperobject;

import java.util.HashMap;
import java.util.Map;

import sour.test.interf.entity.IProduct;

public class ProductsObject {
  
  private Map<Integer, IProduct> products;
  
  public ProductsObject(IProduct product)
  {
    products = new HashMap<Integer, IProduct>();
    products.put(product.getId(), product);
  }
  
  public Map<Integer, IProduct> getProducts()
  {
    return products;
  }
  
  public void setProducts(Map<Integer, IProduct> map)
  {
    this.products = map;
  }
}