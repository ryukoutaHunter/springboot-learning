package com.mongo.example.repository;

import com.mongo.example.entity.City;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CityRepository extends ReactiveMongoRepository<City,Long> {

}
