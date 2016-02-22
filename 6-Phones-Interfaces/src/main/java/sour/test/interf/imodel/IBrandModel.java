package sour.test.interf.imodel;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import sour.test.interf.entity.IBrand;
import sour.test.interf.model.BrandModel;

@JsonDeserialize(as = BrandModel.class)
public interface IBrandModel extends IBrand {
  
  String getDate();
  
  void setDate(String date);
}
