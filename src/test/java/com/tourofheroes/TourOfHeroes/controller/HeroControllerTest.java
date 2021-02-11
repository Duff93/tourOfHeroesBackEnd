package com.tourofheroes.TourOfHeroes.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourofheroes.TourOfHeroes.entity.Hero;
import com.tourofheroes.TourOfHeroes.repository.HeroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HeroController.class)
class HeroControllerTest {

    @Autowired
    private MockMvc mvc;

    private List<Hero> heroes = new ArrayList<>() {};

    private Hero hero = new Hero();

    @MockBean
    private HeroRepository heroesRepository;

    @BeforeEach
    public void setup() {
        this.heroes = Arrays.asList(new Hero(), new Hero());
    }

    @Test
    void getAllHeroes() throws Exception {
        when(heroesRepository.findAll()).thenReturn(heroes);
        RequestBuilder request = MockMvcRequestBuilders.get("/api/heroes");
        MvcResult result = mvc.perform(request).andReturn();
        final var resultString = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        var h = mapper.readValue(resultString, Hero[].class);
        Hero[] heroesArray = heroes.toArray(new Hero[2]);
        Assertions.assertArrayEquals(heroesArray, h);
    }

    @Test
    void getHeroById() throws Exception {
        hero.setId("600aa80886ca071a2051d256");
        when(heroesRepository.findById("600aa80886ca071a2051d256")).thenReturn(java.util.Optional.ofNullable(hero));
        RequestBuilder request = MockMvcRequestBuilders.get("/api/heroes/600aa80886ca071a2051d256");
        MvcResult result = mvc.perform(request).andReturn();
        final var resultAsString = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        var h = mapper.readValue(resultAsString, Hero.class);
        Assertions.assertEquals(hero, h);
    }

    @Test
    void createHero() throws Exception {
        when(heroesRepository.save(hero)).thenReturn(hero);
        mvc.perform(MockMvcRequestBuilders
            .post("/api/heroes")
            .contentType("application/json")
            .content(new ObjectMapper().writeValueAsString(hero)))
            .andExpect(status().isOk());
    }

    @Test
    void updateHero() throws Exception {
        hero.setId("600aa80886ca071a2051d256");
        when(heroesRepository.save(hero)).thenReturn(hero);
        mvc.perform(MockMvcRequestBuilders
                .post("/api/heroes")
                .param("id","600aa80886ca071a2051d256")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(hero)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteHero() throws Exception {
        hero.setId("600aa80886ca071a2051d256");
        doNothing().when(heroesRepository).deleteById("600aa80886ca071a2051d256");
        mvc.perform(MockMvcRequestBuilders
                .delete("/api/heroes")
                .param("id","600aa80886ca071a2051d256")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(hero)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAllHeroes() throws Exception {
        doNothing().when(heroesRepository).deleteAll();
        mvc.perform(MockMvcRequestBuilders
                .delete("/api/heroes")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(hero)))
                .andExpect(status().isOk());
    }
}