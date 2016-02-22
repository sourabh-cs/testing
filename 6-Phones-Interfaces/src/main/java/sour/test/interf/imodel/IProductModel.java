package sour.test.interf.imodel;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import sour.test.interf.entity.IProduct;
import sour.test.interf.model.ProductModel;

@JsonDeserialize(as = ProductModel.class)
public interface IProductModel extends IProduct {

}
