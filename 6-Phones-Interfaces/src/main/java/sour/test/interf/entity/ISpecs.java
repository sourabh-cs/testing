package sour.test.interf.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import sour.test.interf.ientity.Specs;

@JsonDeserialize(as = Specs.class)
public interface ISpecs {

}