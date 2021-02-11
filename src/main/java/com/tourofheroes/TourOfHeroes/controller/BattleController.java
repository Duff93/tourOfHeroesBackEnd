package com.tourofheroes.TourOfHeroes.controller;

import com.tourofheroes.TourOfHeroes.entity.Battle;
import com.tourofheroes.TourOfHeroes.entity.Hero;
import com.tourofheroes.TourOfHeroes.service.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class BattleController {

    @Autowired
    BattleService battleService;

    @PostMapping("/battle")
    public Battle createBattle(@RequestBody Battle newBattle) {
        for (Hero enemy : newBattle.getEnemyHero()) {
            int starter = this.battleService.getStarter();
            String winner = this.battleService.doFight(newBattle.getSelectedHero(), enemy, starter);
            newBattle.getBattleLog().add(winner);
            battleService.handleBattleResult(newBattle, winner, enemy);
            this.battleService.saveWinLostToDb(enemy);
            this.battleService.saveWinLostToDb(newBattle.getSelectedHero());
        }
        return newBattle;
    }
}
