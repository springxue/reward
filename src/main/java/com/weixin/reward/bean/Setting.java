package com.weixin.reward.bean;

public class Setting {
    private int id;
    private double box0;
    private double box1;
    private double box2;
    private double box3;
    private double box4;
    private double box5;
    private double box6;
    private double box7;
    private String serial;
    private int reward_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBox0() {
        return box0;
    }

    public void setBox0(double box0) {
        this.box0 = box0;
    }

    public double getBox1() {
        return box1;
    }

    public void setBox1(double box1) {
        this.box1 = box1;
    }

    public double getBox2() {
        return box2;
    }

    public void setBox2(double box2) {
        this.box2 = box2;
    }

    public double getBox3() {
        return box3;
    }

    public void setBox3(double box3) {
        this.box3 = box3;
    }

    public double getBox4() {
        return box4;
    }

    public void setBox4(double box4) {
        this.box4 = box4;
    }

    public double getBox5() {
        return box5;
    }

    public void setBox5(double box5) {
        this.box5 = box5;
    }

    public double getBox6() {
        return box6;
    }

    public void setBox6(double box6) {
        this.box6 = box6;
    }

    public double getBox7() {
        return box7;
    }

    public void setBox7(double box7) {
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
