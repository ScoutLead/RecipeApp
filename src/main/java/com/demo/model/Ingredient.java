package com.demo.model;

public class Ingredient {
  private Product product;
  private String measurementType;
  private int count;
//
//  public Ingredient(Product product, String measurementType, int count) {
//    this.product = product;
//    this.measurementType = measurementType;
//    this.count = count;
//  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public String getMeasurementType() {
    return measurementType;
  }

  public void setMeasurementType(String measurementType) {
    this.measurementType = measurementType;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }
}
