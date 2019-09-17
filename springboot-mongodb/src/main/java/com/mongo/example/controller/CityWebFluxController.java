package com.mongo.example.controller;

import com.mongo.example.entity.City;
import com.mongo.example.handler.CityHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/city")
public class CityWebFluxController {

  @Autowired
  private CityHandler cityHandler;

  @GetMapping(value = "/{id}")
  public Mono<City> findCityById(@PathVariable("id") Long id) {
    return cityHandler.findCityById(id);
  }

  @GetMapping
  public Flux<City> findAll() {
    return cityHandler.findAllCity();
  }

  @PostMapping
  public Mono<City> saveCity(@RequestBody City city) {
    return cityHandler.save(city);
  }

  @PutMapping
  public Mono<City> modifyCity(@RequestBody City city) {
    return cityHandler.modify(city);
  }

  @DeleteMapping(value = "/{id}")
  public Mono<Long> deleteCityById(@PathVariable("id") Long id) {
    return cityHandler.deleteCity(id);
  }

}
