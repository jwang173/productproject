package com.example.productproject.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Product implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "useType")
    private String useType;

    @Column(name = "application")
    private String application;

    @Column(name = "location")
    private String location;

    @Column(name = "modelYear")
    private int modelYear;

    @Column(name = "maxPower")
    private int maxPower;

    @Column(name = "soundAtMaxSpeed")
    private int soundAtMaxSpeed;

    @Column(name = "brand")
    private String brand;

    public Product() {

    }

    public Product(String name, String useType, String application, String location,
                    int modelYear, int maxPower, int soundAtMaxSpeed, String brand ) {
        this.setName(name);
        this.setUseType(useType);
        this.setApplication(application);
        this.setLocation(location);
        this.setModelYear(modelYear);
        this.setMaxPower(maxPower);
        this.setSoundAtMaxSpeed(soundAtMaxSpeed);
        this.setBrand(brand);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public int getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(int maxPower) {
        this.maxPower = maxPower;
    }

    public int getSoundAtMaxSpeed() {
        return soundAtMaxSpeed;
    }

    public void setSoundAtMaxSpeed(int soundAtMaxSpeed) {
        this.soundAtMaxSpeed = soundAtMaxSpeed;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
