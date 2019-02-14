package com.lambdaschool.gdp.country;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The Country class represents a country with its Gross Domestic Product.
 */
@Data
@Entity
public class Country {
  private @Id @GeneratedValue Long id;
  private String country;
  private double gdp;

  /**
   * Default Constructor.
   */
  public Country() {}

  /**
   * Additional Constructor to create a country object.
   *
   * @param country The country's name
   * @param gdp     The country's gross domestic product
   */
  public Country(String country, double gdp) {
    this.country = country;
    this.gdp = gdp;
  }
}
