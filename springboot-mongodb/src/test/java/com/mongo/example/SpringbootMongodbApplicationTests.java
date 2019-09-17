package com.mongo.example;

import static org.junit.Assert.assertEquals;

import com.mongo.example.entity.City;
import com.mongo.example.repository.CityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMongodbApplicationTests {

  @Autowired
  private CityRepository repository;

  @Test
  public void contextLoads() {
    repository.save(new City(111L, 322L, "BEIJING1", "capital1")).block();
    Mono<City> cityMono = repository.findById(111L);

    StepVerifier.create(cityMono)
        .assertNext(city -> {
          assertEquals("BEIJING1", city.getCityName());
          assertEquals("capital1", city.getDescription());
        })
        .expectComplete()
        .verify();
  }

}
