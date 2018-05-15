package com.jinhui.controller.result;

import com.jinhui.controller.base.WebResult;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by jinhui on 2018/1/20.
 */
public class HouseIdResult extends WebResult {

    @ApiModelProperty(value="房子ID")
    private String houseId;

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }
}
