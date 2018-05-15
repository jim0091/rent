package com.jinhui.controller.result;


import com.jinhui.controller.base.WebResult;
import io.swagger.annotations.ApiModelProperty;


public class UploadAttachmentResult extends WebResult {

    @ApiModelProperty(value="文件ID", required = true)
    Long attachmentId;
    @ApiModelProperty(value="url", required = true)
    String url;
    @ApiModelProperty(value="文件名")
    String name;

    public Long getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
