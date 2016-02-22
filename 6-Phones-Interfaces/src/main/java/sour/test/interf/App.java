package sour.test.interf;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sour.test.interf.entity.IBrand;
import sour.test.interf.entity.IDevice;
import sour.test.interf.entity.IProduct;
import sour.test.interf.imodel.IBrandModel;
import sour.test.interf.imodel.IDeviceModel;
import sour.test.interf.imodel.IProductModel;
import sour.test.interf.phones.PhoneRoot;

@RestController
public class App {
  
  PhoneRoot root = new PhoneRoot();
  
  @RequestMapping(value = "test/new", method = RequestMethod.POST)
  public @ResponseBody IBrandModel testPost(@RequestBody IBrandModel brand)
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
  public @ResponseBody Map<Integer, IBrand> getAllBrands()
  {
    return root.getBrands();
    // return root.getBrandNames();
  }
  
  @RequestMapping(value = "phones/new", method = RequestMethod.POST)
  public @ResponseBody IBrand addBrand(@RequestBody IBrandModel brand)
  {
    return root.addBrand(brand);
  }
  
  @RequestMapping(value = "phones/{brandId}", method = RequestMethod.GET)
  public @ResponseBody Map<Integer, String> getProductNames(@PathVariable("brandId") int brandId)
  {
    return root.getProductNames(brandId);
  }
  
  @RequestMapping(value = "phones/{brandId}/new", method = RequestMethod.POST)
  public @ResponseBody IProduct addProduct(@RequestBody IProductModel product,
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
  public @ResponseBody IDevice addDevice(@RequestBody IDeviceModel device,
      @PathVariable("brandId") int brandId, @PathVariable("productId") int productId)
  {
    return root.addDevice(brandId, productId, device.getName());
  }
}