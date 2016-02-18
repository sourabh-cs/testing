package sour.test.jetty.phones;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.elasticsearch.search.SearchHit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PhoneRoot {
  
  private final String              INDEX  = "phones";
  private final String              TYPE   = "brands";
                                           
  private Map<Integer, Brand>       brands;
                                    
  private Node                      node;
  private Client                    client;
                                    
  private static final ObjectMapper mapper = new ObjectMapper();
                                           
  public static void main(String[] args)
  {
    new PhoneRoot();
  }
  
  public PhoneRoot()
  {
    node = NodeBuilder.nodeBuilder().settings(Settings.builder().put("path.home", "/")).node();
    client = node.client();
    node.client().admin().cluster().prepareHealth().setWaitForYellowStatus().execute().actionGet();
    brands = new HashMap<Integer, Brand>();
    SearchResponse sr = client.prepareSearch().execute().actionGet();
    for (SearchHit sh : sr.getHits()) {
      Brand brand = mapper.convertValue(sh.getSource(), Brand.class);
      System.out.println("Brand name: " + brand.getName());
      brands.put(brand.getId(), brand);
    }
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
  
  public void addBrandToElastic(Brand brand) throws JsonProcessingException
  {
    int brandId = brand.getId();
    String brandJson = mapper.writeValueAsString(brand);
    client.prepareIndex(INDEX, TYPE, String.valueOf(brandId)).setSource(brandJson).execute()
        .actionGet();
    getBrandDocument(String.valueOf(brandId));
  }
  
  public Brand addBrand(Brand brand)
  {
    int brandId = brands.size();
    brand.setId(brandId);
    brand.initProducts();
    brands.put(brandId, brand);
    try {
      addBrandToElastic(brand);
      return brand;
    }
    catch (JsonProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }
  
  public Brand addBrand(String brandName)
  {
    Brand brand = addBrand(new Brand(brandName));
    return brand;
  }
  
  public Map<String, Object> getBrandDocument(String id)
  {
    GetResponse getResponse = client.prepareGet(INDEX, TYPE, id).execute().actionGet();
    Map<String, Object> source = getResponse.getSource();
    return source;
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
    Map<Integer, Product> products = brand.getProducts();
    for (Product product : products.values()) {
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
    try {
      addBrandToElastic(brand);
      return product;
    }
    catch (JsonProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
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
    try {
      addBrandToElastic(brand);
      return device;
    }
    catch (JsonProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
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
