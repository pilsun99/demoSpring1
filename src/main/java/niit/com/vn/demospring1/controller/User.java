package niit.com.vn.demospring1.controller;

import java.io.Serializable;

public class User implements Serializable {
    public String name;
    public String address;
    public int age;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
