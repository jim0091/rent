package com.jinhui.domain;


import org.springframework.util.StringUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Attachment {

    private Long userId;
    private Long id;
    private String name;
    private String location;
    private Type type;

    public Attachment(Long userId, String name, Type type) {
        this.userId = userId;
        this.name = name;
        this.type = type;
    }

    public Attachment(Long id) {
        this.id = id;
    }

    public enum Type {
        House("房屋证明"), Contract("合同证明"), Trusteeship("托管证明"), Image("房源图片"),
        IdentityFront("身份证正面"), IdentityBack("身份证反面"), BatchImport("批量导入数据文件"), Others("其它");
        private String text;
        Type(String text) {
            this.text = text;
        }
        public String text() {
            return text;
        }

        public String lowcaseName() {
            return this.toString().toLowerCase();
        }
        public static Type typeOf(String type) {
            if(StringUtils.isEmpty(type)) {
                throw new IllegalArgumentException("type不能为空");
            }
            for(Type t: Type.values()) {
                if(t.lowcaseName().equals(type.toLowerCase()))
                    return t;
            }
            throw new IllegalArgumentException("未知附件类型");
        }
    }

    public String fileLocation(String parentDir) {
        File dir = new File(parentDir + File.separator + this.type.lowcaseName());
        if(!dir.exists()) {
            dir.mkdir();
        }
        return dir.getAbsolutePath() + File.separator + this.name;
    }

    public String toUrl() {
        return this.type.lowcaseName()+"/"+ name;
    }

    public static String toStoreFilename(String srcFilename) {
        String fileType = srcFilename.substring(srcFilename.lastIndexOf("."));
        return new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date()) + fileType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    Attachment() {
    }
}
