package com.example.demoLina.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name= "product")
public class Product {


    public Product() {
        super();
    }

    public Product(int id, String name, int quantity, double price) {
 super();
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;

    }

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int quantity;
    private double price;

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setId(int id) {
       this.id = id;
    }

}
