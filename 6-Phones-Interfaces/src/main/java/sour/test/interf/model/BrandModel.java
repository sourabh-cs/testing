package sour.test.interf.model;

import sour.test.interf.ientity.Brand;
import sour.test.interf.imodel.IBrandModel;

public class BrandModel extends Brand implements IBrandModel {
  
  private String dateAdded;
  
  @Override
  public String getDate()
  {
    return dateAdded;
  }
  
  @Override
  public void setDate(String date)
  {
    dateAdded = date;
  }
  
}
