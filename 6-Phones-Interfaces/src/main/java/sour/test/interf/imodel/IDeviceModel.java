package sour.test.interf.imodel;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import sour.test.interf.entity.IDevice;
import sour.test.interf.model.DeviceModel;

@JsonDeserialize(as = DeviceModel.class)
public interface IDeviceModel extends IDevice {

}
