package com.sdau.personal.pojo;

public class Employee {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private Integer basic;
    private Integer jiangjin;
    private  Integer baoxiao;

    private Integer total;

    public Employee() {
    }

    public Employee(Integer id, String name, Integer age, String gender, Integer basic, Integer jiangjin, Integer baoxiao, Integer total) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.basic = basic;
        this.jiangjin = jiangjin;
        this.baoxiao = baoxiao;
        this.total = total;
    }

    public Employee(Integer id, String name, Integer age, String gender, Integer basic, Integer jiangjin, Integer baoxiao) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.basic = basic;
        this.jiangjin = jiangjin;
        this.baoxiao = baoxiao;
    }

    public Employee(String name, Integer age, String gender, Integer basic, Integer jiangjin, Integer baoxiao) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.basic = basic;
        this.jiangjin = jiangjin;
        this.baoxiao = baoxiao;
    }

    public Employee(String name, Integer age, String gender, Integer basic, Integer jiangjin, Integer baoxiao, Integer total) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.basic = basic;
        this.jiangjin = jiangjin;
        this.baoxiao = baoxiao;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getBasic() {
        return basic;
    }

    public void setBasic(Integer basic) {
        this.basic = basic;
    }

    public Integer getJiangjin() {
        return jiangjin;
    }

    public void setJiangjin(Integer jiangjin) {
        this.jiangjin = jiangjin;
    }

    public Integer getBaoxiao() {
        return baoxiao;
    }

    public void setBaoxiao(Integer baoxiao) {
        this.baoxiao = baoxiao;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
