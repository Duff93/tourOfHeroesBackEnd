package com.tourofheroes.TourOfHeroes.controller;

import com.tourofheroes.TourOfHeroes.entity.Gadget;
import com.tourofheroes.TourOfHeroes.entity.Hero;
import com.tourofheroes.TourOfHeroes.repository.GadgetRepository;
import com.tourofheroes.TourOfHeroes.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class GadgetController {
    @Autowired
    GadgetRepository gadgetRepository;

    @GetMapping("/gadgets")
    @ResponseStatus(HttpStatus.OK)
    public List<Gadget> getAllGadgets() {
        return this.gadgetRepository.findAll();
    }

    @GetMapping("/gadgets/{id}")
    public Gadget getGadgetById(@PathVariable("id") String id) {
        return this.gadgetRepository.findById(id).orElseThrow();
    }

    @PostMapping("/gadgets")
    public Gadget createGadget(@RequestBody Gadget newGadget) {
        return this.gadgetRepository.save(newGadget);
    }

    @PutMapping("/gadgets/{id}")
    public Gadget updateGadget(@PathVariable("id") String id, @RequestBody Gadget newGadget) {
        return this.gadgetRepository.save(newGadget);
    }

    @DeleteMapping("/gadgets/{id}")
    public void deleteGadget(@PathVariable("id") String id) {
        gadgetRepository.deleteById(id);
    }

    @DeleteMapping("/gadgets")
    public void deleteAllGadgets() {
        gadgetRepository.deleteAll();
    }
}
