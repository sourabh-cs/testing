package sour.test.jetty.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sour.test.jetty.phones.Brand;
import sour.test.jetty.phones.Device;
import sour.test.jetty.phones.Product;
import sour.test.jetty.planet.Planet;

@RestController
public class PlanetController {
  
  Map<String, Brand> phones = new HashMap<String, Brand>();
  
  @RequestMapping(value = "phones")
  public @ResponseBody Map<String, Brand> getAllBrands()
  {
    return phones;
  }
  
  @RequestMapping(value = "phones/new", method = RequestMethod.POST)
  public @ResponseBody Brand createBrand(@RequestBody String brandName)
  {
    if (brandName.endsWith("="))
      brandName = brandName.substring(0, brandName.length() - 1);
    Brand brand = new Brand(brandName);
    phones.put(brandName, brand);
    return brand;
  }
  
  @RequestMapping(value = "phones/{brand}/new", method = RequestMethod.POST)
  public @ResponseBody Product createProduct(@PathVariable("brand") String brandName,
      @RequestBody String productName)
  {
    
    System.out.println(brandName + productName);
    if (!phones.containsKey(brandName))
      return null;
    if (productName.endsWith("="))
      productName = productName.substring(0, productName.length() - 1);
    Product product = new Product(productName);
    Brand brand = phones.get(brandName);
    System.out.println(brand.getMap().size());
    brand.getMap().put(productName, product);
    System.out.println(brand.getMap().size());
    return product;
  }
  
  @RequestMapping(value = "phones/{brand}/{product}/new", method = RequestMethod.POST)
  public @ResponseBody Device createDevice(@PathVariable("brand") String brandName,
      @PathVariable("product") String productName, @RequestBody String deviceName)
  {
    
    System.out.println(brandName + productName + deviceName);
    if (!phones.containsKey(brandName))
      return null;
    Brand brand = phones.get(brandName);
    Map<String, Product> productMap = brand.getMap();
    if (!productMap.containsKey(productName)) {
      return null;
    }
    Product product = productMap.get(productName);
    if (deviceName.endsWith("="))
      deviceName = deviceName.substring(0, deviceName.length() - 1);
    Device device = new Device(deviceName);
    System.out.println(brand.getMap().size());
    product.getMap().put(deviceName, device);
    System.out.println(brand.getMap().size());
    return device;
  }
  
  @RequestMapping(value = "phones/{brand}/{product}/{device}")
  public @ResponseBody Device getDevice(@PathVariable("brand") String brandName,
      @PathVariable("product") String productName, @PathVariable("device") String deviceName)
  {
    if (!phones.containsKey(brandName)) {
      return null;
    }
    Brand brand = phones.get(brandName);
    Map<String, Product> m = brand.getMap();
    if (!m.containsKey(productName)) {
      return null;
    }
    Product product = m.get(productName);
    Map<String, Device> deviceMap = product.getMap();
    if (!deviceMap.containsKey(deviceName)) {
      return null;
    }
    return deviceMap.get(deviceName);
  }
  
  @RequestMapping(value = "phones/{brand}/{product}")
  public @ResponseBody Product getProduct(@PathVariable("brand") String brandName,
      @PathVariable("product") String productName)
  {
    if (!phones.containsKey(brandName)) {
      return null;
    }
    Brand brand = phones.get(brandName);
    Map<String, Product> m = brand.getMap();
    if (!m.containsKey(productName)) {
      return null;
    }
    return m.get(productName);
  }
  
  @RequestMapping(value = "phones/{name}")
  public @ResponseBody Brand getBrand(@PathVariable("name") String name)
  {
    if (!phones.containsKey(name)) {
    
    }
    Brand brand = phones.get(name);
    return brand;
  }
  
  @RequestMapping("home")
  public String loadHomePage(Model m)
  {
    m.addAttribute("name", "Sourabh");
    return "home";
  }
  
  @RequestMapping("planet")
  public Planet loadPlanetPage(@RequestParam(value = "name", defaultValue = "earth") String name,
      Model m)
  {
    AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    Planet p;
    for (String s : context.getBeanDefinitionNames()) {
      System.out.println(s);
    }
    if (!context.containsBean(name))
      p = (Planet) context.getBean("unknown");
    else
      p = (Planet) context.getBean(name);
    m.addAttribute("name", p.getName());
    context.close();
    return p;
  }
}
