package com.tourofheroes.TourOfHeroes.controller;

import com.tourofheroes.TourOfHeroes.entity.Hero;
import com.tourofheroes.TourOfHeroes.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class HeroController {
    @Autowired
    HeroRepository heroesRepository;

    @GetMapping("/heroes")
    @ResponseStatus(HttpStatus.OK)
    public List<Hero> getAllHeroes() {
        return this.heroesRepository.findAll();
    }

    @GetMapping("/heroes/{id}")
    public Hero getHeroById(@PathVariable("id") String id) {
        return this.heroesRepository.findById(id).orElseThrow();
    }

    @PostMapping("/heroes")
    public Hero createHero(@RequestBody Hero newHero) {
        return this.heroesRepository.save(newHero);
    }

    @PutMapping("/heroes/{id}")
    public Hero updateHero(@PathVariable("id") String id, @RequestBody Hero newHero) {
        return this.heroesRepository.save(newHero);
    }

    @DeleteMapping("/heroes/{id}")
    public void deleteHero(@PathVariable("id") String id) {
        heroesRepository.deleteById(id);
    }

    @DeleteMapping("/heroes")
    public void deleteAllHeroes() {
        heroesRepository.deleteAll();
    }
}
