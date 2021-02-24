package com.arm.api.hero.model.dto.response;

import com.arm.api.hero.model.bo.Hero;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetAllHeroesResp {

    private List<Hero> heroes;

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }

    @JsonProperty("size")
    public int count() {
        return heroes == null ? 0 : heroes.size();
    }
}
