package com.jinhui.controller.result;

import com.jinhui.controller.base.WebResult;
import com.jinhui.controller.vo.SignatureVo;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by jinhui on 2018/1/27.
 */
public class SignatureResult extends WebResult{

    @ApiModelProperty(value="告白结果", dataType = "com.jinhui.controller.vo.SignatureVo")
    private SignatureVo signatureVo;

    public SignatureVo getSignatureVo() {
        return signatureVo;
    }

    public void setSignatureVo(SignatureVo signatureVo) {
        this.signatureVo = signatureVo;
    }

    @Override
    public String toString() {
        return "SignatureResult{" +
                "signatureVo=" + signatureVo +
                '}';
    }
}
