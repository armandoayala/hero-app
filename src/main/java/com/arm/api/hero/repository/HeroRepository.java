package com.arm.api.hero.repository;

import com.arm.api.hero.model.bo.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeroRepository extends JpaRepository<Hero, Long> {

    List<Hero> findByNameContaining(String name);

    List<Hero> findByName(String name);

}
