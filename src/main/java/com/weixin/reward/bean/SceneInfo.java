package com.weixin.reward.bean;

public class SceneInfo {
    private String id;
    private String name;
    private String area_code;
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "sceneInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", area_code='" + area_code + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
