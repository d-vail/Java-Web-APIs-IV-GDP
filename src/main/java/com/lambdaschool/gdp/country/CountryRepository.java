package com.lambdaschool.gdp.country;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The CountryRepository is an interface for interacting with the database.
 */
public interface CountryRepository extends JpaRepository<Country, Long> {
}
