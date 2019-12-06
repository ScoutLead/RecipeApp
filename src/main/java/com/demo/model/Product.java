package com.demo.model;

public class Product {
  private String id;
  private String name;
  private ProductType type;


//
//  public Product(String id, String name, ProductType type) {
//    this.id = id;
//    this.name = name;
//    this.type = type;
//  }

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

  public ProductType getType() {
    return type;
  }

  public void setType(ProductType type) {
    this.type = type;
  }
}
