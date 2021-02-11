package com.tourofheroes.TourOfHeroes.service;

import com.tourofheroes.TourOfHeroes.entity.Battle;
import com.tourofheroes.TourOfHeroes.entity.Hero;
import com.tourofheroes.TourOfHeroes.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class BattleService {

    @Autowired
    HeroRepository heroesRepository;

    //service to calculate the starter
    public int getStarter() {
        Random starter = new Random();
        return starter.nextInt(2);
    }

    //service to do the single fight
    public String doFight(Hero hero, Hero enemy, int participant) {
        hero.setRemainingHealth(hero.getHealth());
        enemy.setRemainingHealth(enemy.getHealth());
        //rule => if partecipant == 0 start hero, else start enemy
        while(hero.getRemainingHealth() > 0 && enemy.getRemainingHealth() > 0) {
            if(participant == 0) {
                enemy.setRemainingHealth(enemy.getRemainingHealth() - hero.getAttackTot());
                participant = 1;
            } else {
                hero.setRemainingHealth(hero.getRemainingHealth() - enemy.getAttackTot());
                participant = 0;
            }
        }

        if(hero.getRemainingHealth() > 0) return "hero";
        else return "enemy";
    }

    //service to save win/lost to db
    public void saveWinLostToDb(Hero hero) {
        this.heroesRepository.save(hero);
    }

    //handle the result of battle
    public void handleBattleResult(Battle battles, String winner, Hero enemy) {
        if (winner.equals("hero")) {
            enemy.setLost();
            battles.getSelectedHero().setWin();
        } else {
            enemy.setWin();
            battles.getSelectedHero().setLost();
        }
    }
}
