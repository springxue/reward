package com.weixin.reward.bean;

public class Setting {
    private int id;
    private Double box0;
    private Double box1;
    private Double box2;
    private Double box3;
    private Double box4;
    private Double box5;
    private Double box6;
    private Double box7;
    private String serial;
    private int reward_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getBox0() {
        return box0;
    }

    public void setBox0(Double box0) {
        this.box0 = box0;
    }

    public Double getBox1() {
        return box1;
    }

    public void setBox1(Double box1) {
        this.box1 = box1;
    }

    public Double getBox2() {
        return box2;
    }

    public void setBox2(Double box2) {
        this.box2 = box2;
    }

    public Double getBox3() {
        return box3;
    }

    public void setBox3(Double box3) {
        this.box3 = box3;
    }

    public Double getBox4() {
        return box4;
    }

    public void setBox4(Double box4) {
        this.box4 = box4;
    }

    public Double getBox5() {
        return box5;
    }

    public void setBox5(Double box5) {
        this.box5 = box5;
    }

    public Double getBox6() {
        return box6;
    }

    public void setBox6(Double box6) {
        this.box6 = box6;
    }

    public Double getBox7() {
        return box7;
    }

    public void setBox7(Double box7) {
        this.box7 = box7;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public int getReward_id() {
        return reward_id;
    }

    public void setReward_id(int reward_id) {
        this.reward_id = reward_id;
    }

    @Override
    public String toString() {
        return "Setting{" +
                "id=" + id +
                ", box0=" + box0 +
                ", box1=" + box1 +
                ", box2=" + box2 +
                ", box3=" + box3 +
                ", box4=" + box4 +
                ", box5=" + box5 +
                ", box6=" + box6 +
                ", box7=" + box7 +
                ", serial='" + serial + '\'' +
                ", reward_id=" + reward_id +
                '}';
    }
}
