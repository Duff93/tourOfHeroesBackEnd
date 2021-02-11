package com.tourofheroes.TourOfHeroes.entity;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Battle {
    @DBRef
    private Hero selectedHero;
    @DBRef
    private Collection<Hero> enemyHero;
    private List<String> battleLog = new ArrayList<>();

    public Battle() {}

    //getter methods
    public Hero getSelectedHero() { return selectedHero; }

    public Collection<Hero> getEnemyHero() { return enemyHero; }

    public List<String> getBattleLog() { return battleLog; }

    //setter methods
    public void setSelectedHero(Hero selectedHero) { this.selectedHero = selectedHero; }

    public void setEnemyHero(Collection<Hero> enemyHero) { this.enemyHero = enemyHero; }

    public void setBattleLog(List<String> battleLog) { this.battleLog = battleLog;}
}
