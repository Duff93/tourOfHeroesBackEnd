package com.tourofheroes.TourOfHeroes.service;

import com.tourofheroes.TourOfHeroes.entity.Battle;
import com.tourofheroes.TourOfHeroes.entity.Gadget;
import com.tourofheroes.TourOfHeroes.entity.Hero;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BattleServiceTest {

    //start hero and attackTot hero > health enemy
    @Test
    void doFight_atkTotHeroGreaterHthEnemyStartHero_returnHero() {
        List<Gadget> gadgetsHero = new ArrayList<>();
        gadgetsHero.add(new Gadget("gadget1", 4));
        gadgetsHero.add(new Gadget("gadget2", 5));
        Hero hero = new Hero(5, gadgetsHero, 10);
        List<Gadget> gadgetsEnemy = new ArrayList<>();
        gadgetsEnemy.add(new Gadget("gadget1", 2));
        gadgetsEnemy.add(new Gadget("gadget2", 3));
        Hero enemy = new Hero(4, gadgetsEnemy, 9);
        BattleService battleService = new BattleService();
        String result = battleService.doFight(hero, enemy, 0);
        assertEquals("hero", result);
    }

    //start enemy and attackTot enemy > health hero
    @Test
    void doFight_atkTotEnemyGreaterHealthHeroStartEnemy_returnEnemy() {
        List<Gadget> gadgetsHero = new ArrayList<>();
        gadgetsHero.add(new Gadget("gadget1", 4));
        gadgetsHero.add(new Gadget("gadget2", 5));
        Hero hero = new Hero(5, gadgetsHero, 6);
        List<Gadget> gadgetsEnemy = new ArrayList<>();
        gadgetsEnemy.add(new Gadget("gadget1", 4));
        gadgetsEnemy.add(new Gadget("gadget2", 5));
        Hero enemy = new Hero(5, gadgetsEnemy, 8);
        BattleService battleService = new BattleService();
        String result = battleService.doFight(hero, enemy, 1);
        assertEquals("enemy", result);
    }

    //start enemy and attackTot enemy < health hero and attackTot hero > health enemy
    @Test
    void doFight_startEnemyAtkTotEnemyLessHealthHeroAndAtkTotHeroGreaterHthEnemy_returnHero() {
        List<Gadget> gadgetsHero = new ArrayList<>();
        gadgetsHero.add(new Gadget("gadget1", 4));
        gadgetsHero.add(new Gadget("gadget2", 5));
        Hero hero = new Hero(5, gadgetsHero, 6);
        List<Gadget> gadgetsEnemy = new ArrayList<>();
        gadgetsEnemy.add(new Gadget("gadget1", 1));
        gadgetsEnemy.add(new Gadget("gadget2", 1));
        Hero enemy = new Hero(1, gadgetsEnemy, 8);
        BattleService battleService = new BattleService();
        String result = battleService.doFight(hero, enemy, 1);
        assertEquals("hero", result);
    }

    //start hero and attackTot hero < health enemy and attackTot enemy > health hero
    @Test
    void doFight_startHeroAtkTotHeroLessHthEnemyAndAtkTotEnemyGreaterHealthHero_returnEnemy() {
        List<Gadget> gadgetsHero = new ArrayList<>();
        gadgetsHero.add(new Gadget("gadget1", 1));
        gadgetsHero.add(new Gadget("gadget2", 1));
        Hero hero = new Hero(2, gadgetsHero, 6);
        List<Gadget> gadgetsEnemy = new ArrayList<>();
        gadgetsEnemy.add(new Gadget("gadget1", 5));
        gadgetsEnemy.add(new Gadget("gadget2", 1));
        Hero enemy = new Hero(1, gadgetsEnemy, 8);
        BattleService battleService = new BattleService();
        String result = battleService.doFight(hero, enemy, 1);
        assertEquals("enemy", result);
    }

    // winner = hero => +1 lost in enemy, +1 win in hero
    @Test
    void handleBattleResult_winnerHero_plusLostInEnemyPlusWinInHero() {
        Battle newBattle = new Battle();
        Hero hero = new Hero();
        Hero enemy = new Hero();
        newBattle.setSelectedHero(hero);
        BattleService battleService = new BattleService();
        battleService.handleBattleResult(newBattle, "hero", enemy);
        assertEquals(1, newBattle.getSelectedHero().getWin());
        assertEquals(1, enemy.getLost());
    }

    // winner = enemy => +1 lost in hero, +1 win in enemy
    @Test
    void handleBattleResult_winnerEnemy_plusLostInHeroPlusWinInEnemy() {
        Battle newBattle = new Battle();
        Hero hero = new Hero();
        Hero enemy = new Hero();
        newBattle.setSelectedHero(hero);
        BattleService battleService = new BattleService();
        battleService.handleBattleResult(newBattle, "enemy", enemy);
        assertEquals(1, enemy.getWin());
        assertEquals(1, newBattle.getSelectedHero().getLost());
    }
}