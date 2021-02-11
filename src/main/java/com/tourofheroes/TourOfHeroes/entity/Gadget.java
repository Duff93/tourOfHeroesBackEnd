package com.tourofheroes.TourOfHeroes.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "gadgets")
public class Gadget {
    @Id
    private String id;

    private String name;
    private int bonus;

    public Gadget() {

    }

    public Gadget(String name, int bonus) {
        this.name = name;
        this.bonus = bonus;
    }

    //getter methods
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBonus() {
        return bonus;
    }

    //setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}
