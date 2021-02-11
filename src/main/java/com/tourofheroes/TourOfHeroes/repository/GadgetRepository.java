package com.tourofheroes.TourOfHeroes.repository;

import com.tourofheroes.TourOfHeroes.entity.Gadget;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GadgetRepository extends MongoRepository<Gadget,String> {
}
