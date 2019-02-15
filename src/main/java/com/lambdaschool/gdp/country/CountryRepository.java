package com.lambdaschool.gdp.country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * The CountryRepository is an interface for interacting with the database.
 */
public interface CountryRepository extends JpaRepository<Country, Long> {
  @Query("select sum(gdp) from Country")
  Long getGDPSum();

  Country findByCountry(String country);
}
