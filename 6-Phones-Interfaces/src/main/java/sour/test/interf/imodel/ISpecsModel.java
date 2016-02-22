package sour.test.interf.imodel;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import sour.test.interf.entity.ISpecs;
import sour.test.interf.model.SpecsModel;

@JsonDeserialize(as = SpecsModel.class)
public interface ISpecsModel extends ISpecs {

}
