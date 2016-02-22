package sour.test.interf.phones;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.elasticsearch.search.SearchHit;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sour.test.interf.entity.IBrand;
import sour.test.interf.entity.IDevice;
import sour.test.interf.entity.IProduct;
import sour.test.interf.ientity.Brand;
import sour.test.interf.ientity.Device;
import sour.test.interf.ientity.Product;
import sour.test.interf.phones.helperobject.DevicesObject;
import sour.test.interf.phones.helperobject.ProductsObject;

public class PhoneRoot {
  
  private final String              INDEX  = "phones";
  private final String              TYPE   = "brands";
                                           
  private Map<Integer, IBrand>      brands;
                                    
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
    brands = new HashMap<Integer, IBrand>();
    boolean exists = client.admin().indices().prepareExists(INDEX).execute().actionGet().isExists();
    if (!exists)
      return;
    SearchResponse sr = client.prepareSearch(INDEX).setTypes(TYPE).setFetchSource("name", "")
        .execute().actionGet();
    for (SearchHit sh : sr.getHits()) {
      System.out.println(sh.getType() + "Hit: " + sh.getId());
      System.out.println(sh.getType() + "Hit: " + sh.getSource());
      int id = Integer.parseInt(sh.getId());
      Map<String, Object> map = new HashMap<String, Object>();
      map = sh.getSource();
      String name = (String) map.get("name");
      Brand brand = new Brand(id, name);
      brands.put(id, brand);
    }
    // getProductNames(0);
  }
  
  public Map<Integer, IBrand> getBrands()
  {
    return brands;
  }
  
  public Map<Integer, String> getBrandNames()
  {
    Map<Integer, String> brandNames = new HashMap<Integer, String>();
    for (IBrand brand : brands.values()) {
      brandNames.put(brand.getId(), brand.getName());
    }
    return brandNames;
  }
  
  public void addBrandToElastic(IBrand brand) throws JsonProcessingException
  {
    int brandId = brand.getId();
    String brandJson = mapper.writeValueAsString(brand);
    client.prepareIndex(INDEX, TYPE, String.valueOf(brandId)).setSource(brandJson).execute()
        .actionGet();
  }
  
  public IBrand addBrand(IBrand brand)
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
  
  public IBrand addBrand(String brandName)
  {
    IBrand brand = addBrand(new Brand(brandName));
    return brand;
  }
  
  public Map<String, Object> getBrandDocument(String id)
  {
    GetResponse getResponse = client.prepareGet(INDEX, TYPE, id).execute().actionGet();
    Map<String, Object> source = getResponse.getSource();
    return source;
  }
  
  public IBrand getBrand(int brandId)
  {
    IBrand brand = brands.get(brandId);
    return brand;
  }
  
  public Map<Integer, String> getProductNames(int brandId)
  {
    Map<Integer, String> productNames = new HashMap<Integer, String>();
    GetResponse response = client.prepareGet(INDEX, TYPE, String.valueOf(brandId))
        .setFetchSource("products", "*.devices").execute().actionGet();
    Map<String, Object> source = response.getSource();
    Map<Integer, Object> productMap;
    if (source.get("products") instanceof Map)
      productMap = (Map<Integer, Object>) source.get("products");
    else
      return productNames;
      
    for (Object o : productMap.values()) {
      try {
        String s = mapper.writeValueAsString(o);
        System.out.println(s);
        Product product = mapper.readValue(s, Product.class);
        product.initDevices();
        brands.get(brandId).getProducts().put(product.getId(), product);
        productNames.put(product.getId(), product.getName());
      }
      catch (JsonParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      catch (JsonMappingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return productNames;
  }
  
  public void updateProductInElastic(int brandId, IProduct product) throws JsonProcessingException
  {
    ProductsObject doc = new ProductsObject(product);
    String productJson = mapper.writeValueAsString(doc);
    System.out.println(productJson);
    UpdateRequest updateRequest = new UpdateRequest(INDEX, TYPE, String.valueOf(brandId))
        .doc(productJson);
    try {
      client.update(updateRequest).get();
    }
    catch (ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  public IProduct addProduct(int brandId, String productName)
  {
    IBrand brand = getBrand(brandId);
    Map<Integer, IProduct> products = brand.getProducts();
    int productId = products.size();
    IProduct product = new Product(productId, productName);
    products.put(productId, product);
    try {
      updateProductInElastic(brandId, product);
      return product;
    }
    catch (JsonProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }
  
  public IProduct getProduct(int brandId, int productId)
  {
    IBrand brand = getBrand(brandId);
    Map<Integer, IProduct> products = brand.getProducts();
    IProduct product = products.get(productId);
    return product;
  }
  
  public Map<Integer, String> getDeviceNames(int brandId, int productId)
  {
    Map<Integer, String> deviceNames = new HashMap<Integer, String>();
    String include = "*." + productId + ".devices";
    System.out.println("Include: " + include);
    GetResponse response = client.prepareGet(INDEX, TYPE, String.valueOf(brandId))
        .setFetchSource(include, "").execute().actionGet();
    try {
      Map<String, Object> source = response.getSource();
      String s0 = mapper.writeValueAsString(source);
      System.out.println("Source: " + s0);
      
      Map<Integer, Object> productMap = (Map<Integer, Object>) source.get("products");
      String s1 = mapper.writeValueAsString(productMap);
      System.out.println("int/obj productmap: " + s1);
      
      Map<String, Object> devicesMap = (Map<String, Object>) productMap
          .get(String.valueOf(productId));
      String s2 = mapper.writeValueAsString(devicesMap);
      System.out.println("str/obj devicesmap: " + s2);
      String s = mapper.writeValueAsString(devicesMap);
      System.out.println("productmap productid " + s);
      IProduct product = mapper.readValue(s, IProduct.class);
      for (IDevice device : product.getDevices().values()) {
        deviceNames.put(device.getId(), device.getName());
      }
      brands.get(brandId).getProducts().get(productId).setDevices(product.getDevices());
      return deviceNames;
    }
    catch (JsonProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return deviceNames;
  }
  
  public void updateDeviceInElastic(int brandId, int productId, Device device)
      throws JsonProcessingException
  {
    DevicesObject doc = new DevicesObject(productId, device);
    String deviceJson = mapper.writeValueAsString(doc);
    System.out.println(deviceJson);
    UpdateRequest updateRequest = new UpdateRequest(INDEX, TYPE, String.valueOf(brandId))
        .doc(deviceJson);
    try {
      client.update(updateRequest).get();
    }
    catch (ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  public IDevice addDevice(int brandId, int productId, String deviceName)
  {
    IBrand brand = getBrand(brandId);
    Map<Integer, IProduct> products = brand.getProducts();
    IProduct product = products.get(productId);
    Map<Integer, IDevice> devices = product.getDevices();
    int deviceId = devices.size();
    Device device = new Device(deviceId, deviceName);
    devices.put(deviceId, device);
    try {
      updateDeviceInElastic(brandId, product.getId(), device);
      return device;
    }
    catch (JsonProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }
  
  public IDevice getDevice(int brandId, int productId, int deviceId)
  {
    IBrand brand = getBrand(brandId);
    Map<Integer, IProduct> products = brand.getProducts();
    IProduct product = products.get(productId);
    Map<Integer, IDevice> devices = product.getDevices();
    IDevice device = devices.get(deviceId);
    return device;
  }
}
