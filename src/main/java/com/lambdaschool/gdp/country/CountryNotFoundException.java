package com.lambdaschool.gdp.country;

/**
 * CountryNotFoundException is a custom runtime exception to be thrown when an invalid country id is
 * provided.
 */
public class CountryNotFoundException extends RuntimeException {
  /**
   * Constructor
   *
   * Pass custom message to RuntimeException class.
   *
   * @param id  A country id
   */
  public CountryNotFoundException(Long id) {
    super("Could not find country");
  }

  public CountryNotFoundException(String country) {
    super("Could not find country");
  }
}
