package com.tourofheroes.TourOfHeroes.controller;

import com.tourofheroes.TourOfHeroes.entity.Battle;
import com.tourofheroes.TourOfHeroes.entity.Hero;
import com.tourofheroes.TourOfHeroes.service.BattleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BattleController.class)
class BattleControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BattleService battleServiceMock;

    @Test
    void createBattle() throws Exception {
        Battle newBattle = new Battle();
        Hero hero = new Hero();
        Hero enemy = new Hero();
        newBattle.setSelectedHero(hero);
        newBattle.setEnemyHero(List.of(enemy));
        when(battleServiceMock.getStarter()).thenReturn(0);
        when(battleServiceMock.doFight(hero, enemy, 0)).thenReturn("hero");
        doNothing().when(battleServiceMock).handleBattleResult(newBattle, "hero", enemy);
        RequestBuilder request = post("/battle");
        MvcResult result = mvc.perform(request).andReturn();
        assertNotEquals("Hello", result.getResponse().getContentAsString());
    }
}