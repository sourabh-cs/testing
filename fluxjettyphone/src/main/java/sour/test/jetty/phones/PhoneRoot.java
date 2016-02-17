package sour.test.jetty.phones;

import java.util.HashMap;
import java.util.Map;

public class PhoneRoot {
  
  private Map<Integer, Brand> brands;
  
  public PhoneRoot()
  {
    brands = new HashMap<Integer, Brand>();
    addBrand("Apple");
    addBrand("Samsung");
  }
  
  public Map<Integer, Brand> getBrands()
  {
    return brands;
  }
  
  public Map<Integer, String> getBrandNames()
  {
    Map<Integer, String> brandNames = new HashMap<Integer, String>();
    for (Brand brand : brands.values()) {
      brandNames.put(brand.getId(), brand.getName());
    }
    return brandNames;
  }
  
  public Brand addBrand(String brandName)
  {
    int brandId = brands.size();
    Brand brand = new Brand(brandId, brandName);
    brands.put(brandId, brand);
    return brand;
  }
  
  public Brand getBrand(int brandId)
  {
    Brand brand = brands.get(brandId);
    return brand;
  }
  
  public Map<Integer, String> getProductNames(int brandId)
  {
    Map<Integer, String> productNames = new HashMap<Integer, String>();
    Brand brand = getBrand(brandId);
    for (Product product : brand.getProducts().values()) {
      productNames.put(product.getId(), product.getName());
    }
    return productNames;
  }
  
  public Product addProduct(int brandId, String productName)
  {
    Brand brand = getBrand(brandId);
    Map<Integer, Product> products = brand.getProducts();
    int productId = products.size();
    Product product = new Product(productId, productName);
    products.put(productId, product);
    return product;
  }
  
  public Product getProduct(int brandId, int productId)
  {
    Brand brand = getBrand(brandId);
    Map<Integer, Product> products = brand.getProducts();
    Product product = products.get(productId);
    return product;
  }
  
  public Map<Integer, String> getDeviceNames(int brandId, int productId)
  {
    Map<Integer, String> deviceNames = new HashMap<Integer, String>();
    Product product = getProduct(brandId, productId);
    for (Device device : product.getDevices().values()) {
      deviceNames.put(device.getId(), device.getName());
    }
    return deviceNames;
  }
  
  public Device addDevice(int brandId, int productId, String deviceName)
  {
    Brand brand = getBrand(brandId);
    Map<Integer, Product> products = brand.getProducts();
    Product product = products.get(productId);
    Map<Integer, Device> devices = product.getDevices();
    int deviceId = devices.size();
    Device device = new Device(deviceId, deviceName);
    devices.put(deviceId, device);
    return device;
  }
  
  public Device getDevice(int brandId, int productId, int deviceId)
  {
    Brand brand = getBrand(brandId);
    Map<Integer, Product> products = brand.getProducts();
    Product product = products.get(productId);
    Map<Integer, Device> devices = product.getDevices();
    Device device = devices.get(deviceId);
    return device;
  }
}
