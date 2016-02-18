package sour.test.jetty.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sour.test.jetty.phones.Brand;
import sour.test.jetty.phones.Device;
import sour.test.jetty.phones.PhoneRoot;
import sour.test.jetty.phones.Product;

@RestController
public class PhoneController {
  
  PhoneRoot root = new PhoneRoot();
  
  @RequestMapping(value = "test/new", method = RequestMethod.POST)
  public @ResponseBody Brand testPost(@RequestBody Brand brand)
  {
    return brand;
  }
  
  @RequestMapping(value = "phones")
  public @ResponseBody Map<Integer, String> getBrandNames()
  {
    return root.getBrandNames();
    // return root.getBrandNames();
  }
  
  @RequestMapping(value = "phones/all")
  public @ResponseBody Map<Integer, Brand> getAllBrands()
  {
    return root.getBrands();
    // return root.getBrandNames();
  }
  
  @RequestMapping(value = "phones/new", method = RequestMethod.POST)
  public @ResponseBody Brand addBrand(@RequestBody Brand brand)
  {
    return root.addBrand(brand);
  }
  
  @RequestMapping(value = "phones/{brandId}", method = RequestMethod.GET)
  public @ResponseBody Map<Integer, String> getProductNames(@PathVariable("brandId") int brandId)
  {
    return root.getProductNames(brandId);
  }
  
  @RequestMapping(value = "phones/{brandId}/new", method = RequestMethod.POST)
  public @ResponseBody Product addProduct(@RequestBody Product product,
      @PathVariable("brandId") int brandId)
  {
    return root.addProduct(brandId, product.getName());
  }
  
  @RequestMapping(value = "phones/{brandId}/{productId}", method = RequestMethod.GET)
  public @ResponseBody Map<Integer, String> getDeviceNames(@PathVariable("brandId") int brandId,
      @PathVariable("productId") int productId)
  {
    return root.getDeviceNames(brandId, productId);
  }
  
  @RequestMapping(value = "phones/{brandId}/{productId}/new", method = RequestMethod.POST)
  public @ResponseBody Device addDevice(@RequestBody Device device,
      @PathVariable("brandId") int brandId, @PathVariable("productId") int productId)
  {
    return root.addDevice(brandId, productId, device.getName());
  }
}