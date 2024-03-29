package com.mongo.example.handler;

import com.mongo.example.entity.City;
import com.mongo.example.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CityHandler {

  private final CityRepository cityRepository;

  @Autowired
  public CityHandler(CityRepository cityRepository) {
    this.cityRepository = cityRepository;
  }

  public Mono<City> save(City city) {
    return cityRepository.save(city);
  }

  public Mono<City> findCityById(Long id) {
    return cityRepository.findById(id);
  }

  public Flux<City> findAllCity() {
    return cityRepository.findAll();
  }

  public Mono<City> modify(City city) {
    return cityRepository.save(city);
  }


  public Mono<Long> deleteCity(Long id) {
    cityRepository.deleteById(id);
    return Mono.create(cityMonoSink -> cityMonoSink.success(id));
  }
}
