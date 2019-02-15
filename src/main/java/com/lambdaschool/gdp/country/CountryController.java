package com.lambdaschool.gdp.country;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lambdaschool.gdp.GdpApplication;
import com.lambdaschool.gdp.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CountryController {
  private final CountryRepository COUNTRY_REPO;
  private final RabbitTemplate RBMQ_TEMPLATE;

  public CountryController(CountryRepository countryRepo, RabbitTemplate rabbitTemplate) {
    this.COUNTRY_REPO = countryRepo;
    this.RBMQ_TEMPLATE = rabbitTemplate;
  }

  /**
   * In JSON format return all of the countries alphabetized by country name.
   *
   * @return  A JSON list of all countries sorted alphabetically
   */
  @GetMapping("/names")
  public List<Country> allByName() {
    return COUNTRY_REPO.findAll(new Sort(Sort.DEFAULT_DIRECTION, "country"));
  }

  /**
   * In JSON format return all of the countries sorted from most to least GDP.
   *
   * @return  A JSON list of all countries sorted in descending order by GDP
   */
  @GetMapping("/economy")
  public List<Country> allByGDP() {
    return COUNTRY_REPO.findAll(new Sort(Sort.Direction.DESC, "gdp"));
  }

  /**
   * In JSON format return the sum of all GDPs with country name being returned as Total.
   *
   * @return  A JSON object containing the sum of all GDPs
   */
  @GetMapping("/total")
  public ObjectNode sumGDP() {
    Long total = COUNTRY_REPO.getGDPSum();
    ObjectMapper mapper = new ObjectMapper();
    ObjectNode totalGDP = mapper.createObjectNode();

    totalGDP.put("id", 0);
    totalGDP.put("country", "total");
    totalGDP.put("gdp", total);

    return totalGDP;
  }

  /**
   * In JSON format return the record for that country. Log that someone looked up this country.
   *
   * @param country                  The name of the country
   * @return                          A JSON object containing the country matching the given country name.
   * @throws CountryNotFoundException If invalid country name is given
   */
  @GetMapping("/gdp/{country}")
  public Country oneByCountry(@PathVariable String country) {
    Country c = COUNTRY_REPO.findByCountry(country);

    if(c == null) {
      throw new CountryNotFoundException(country);
    }

    Log msg = new Log("Queried country");
    RBMQ_TEMPLATE.convertAndSend(GdpApplication.QUEUE, msg.toString());
    log.info("Message sent");

    return c;
  }

  /**
   * Create country records with provided JSON data. Supports loading with a JSON file.
   *
   * @param countries A JSON list of countries
   * @return          A JSON list with the newly created countries
   */
  @PostMapping("/gdp")
  public List<Country> create(@RequestBody List<Country> countries) {
    return COUNTRY_REPO.saveAll(countries);
  }
}
