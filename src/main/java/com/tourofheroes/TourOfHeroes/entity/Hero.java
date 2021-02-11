package com.tourofheroes.TourOfHeroes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "heroes")
public class Hero {
    @Id
    private String id;

    private String name;
    private String img;
    private int attack;
    private int health;
    private int win;
    private int lost;
    @DBRef
    private List<Gadget> gadgets = new ArrayList<>();
    @Transient
    @JsonIgnore
    private int remainingHealth;


    public Hero() {

    }

    public Hero(int attack, List<Gadget> gadgets, int health) {
        this.attack = attack;
        this.gadgets = gadgets;
        this.health = health;
    }

    //getter methods
    public String getId() {
        return id;
    }

    public String getImg() { return img; }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }

    public int getWin() { return win; }

    public int getLost() { return lost; }

    public List<Gadget> getGadgets() {
        return gadgets;
    }

    public int getRemainingHealth() {
        return remainingHealth;
    }

    //setter methods
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setHealth(int health) {
        this.health = health;
        this.remainingHealth = health;
    }

    public void setWin() { this.win = this.win + 1; }

    public void setLost() { this.lost = this.lost + 1; }

    public void setGadgets(List<Gadget> gadgets) {
        this.gadgets = gadgets;
    }

    public int getAttackTot() {
        int attackTot = this.getAttack();

        if(!(gadgets.isEmpty())) {
            for(Gadget g : gadgets) {
                attackTot = attackTot + g.getBonus();
            }
        }

        return attackTot;
    }

    public void setRemainingHealth(int remainingHealth) {
        this.remainingHealth = remainingHealth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return Objects.equals(id, hero.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", attack=" + attack +
                ", health=" + health +
                ", win=" + win +
                ", lost=" + lost +
                ", gadgets=" + gadgets +
                ", remainingHealth=" + remainingHealth +
                '}';
    }
}
