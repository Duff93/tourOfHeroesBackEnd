package com.tourofheroes.TourOfHeroes.repository;

import com.tourofheroes.TourOfHeroes.entity.Hero;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRepository extends MongoRepository<Hero,String> {
}
