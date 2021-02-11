package com.tourofheroes.TourOfHeroes.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {

    @Test
    void getAttackTot_heroWithAtk5And2Gad_totAtkEqual14() {
        Hero hero = new Hero();
        hero.setAttack(5);
        List<Gadget> gadgets = new ArrayList<>();
        gadgets.add(new Gadget("gadget1", 4));
        gadgets.add(new Gadget("gadget2", 5));
        hero.setGadgets(gadgets);
        int response = hero.getAttackTot();
        assertEquals(14,response);
    }

    @Test
    void getAttackTot_heroWithAtk5AndNoGad_totAtkEqualTo5() {
        Hero hero = new Hero();
        hero.setAttack(5);
        int response = hero.getAttackTot();
        assertEquals(5,response);
    }

    @Test
    void getAttackTot_heroWithNoAtkAnd2Gad_totAtkEqualTo9() {
        Hero hero = new Hero();
        List<Gadget> gadgets = new ArrayList<>();
        gadgets.add(new Gadget("gadget1", 4));
        gadgets.add(new Gadget("gadget2", 5));
        hero.setGadgets(gadgets);
        int response = hero.getAttackTot();
        assertEquals(9,response);
    }
}