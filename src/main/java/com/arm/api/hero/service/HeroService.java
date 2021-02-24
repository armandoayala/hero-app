package com.arm.api.hero.service;

import com.arm.api.hero.component.ValidationComponent;
import com.arm.api.hero.exception.DataValidationException;
import com.arm.api.hero.exception.NotFoundException;
import com.arm.api.hero.model.bo.Hero;
import com.arm.api.hero.model.dto.request.CreateHeroReq;
import com.arm.api.hero.model.dto.request.UpdateHeroReq;
import com.arm.api.hero.model.dto.response.CreateHeroResp;
import com.arm.api.hero.model.dto.response.GetAllHeroesResp;
import com.arm.api.hero.model.dto.response.GetHeroeByIdResp;
import com.arm.api.hero.model.dto.response.UpdateHeroResp;
import com.arm.api.hero.model.support.ValidationErrorSet;
import com.arm.api.hero.repository.HeroRepository;
import com.arm.api.hero.util.Constant;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroService {

    @Autowired
    private HeroRepository repository;

    @Qualifier(Constant.BEAN_ID_DOZER_MAPPER)
    @Autowired
    private DozerBeanMapper mapper;

    @Autowired
    private ValidationComponent validationComponent;

    public CreateHeroResp create(CreateHeroReq req) {

        ValidationErrorSet validationErrorSet = validationComponent.validate(req);
        if (validationErrorSet.hasError()) {
            throw new DataValidationException(validationErrorSet.toString());
        }

        Hero hero = mapper.map(req, Hero.class);
        hero.setName(hero.getName().toUpperCase());

        List<Hero> heroFound = repository.findByName(hero.getName());
        if (heroFound != null && !heroFound.isEmpty()) {
            throw new DataValidationException("A hero already exists with the name " + hero.getName());
        }

        hero = repository.save(hero);
        return mapper.map(hero, CreateHeroResp.class);
    }

    public UpdateHeroResp update(Long id, UpdateHeroReq req) {
        ValidationErrorSet validationErrorSet = validationComponent.validate(req);
        if (validationErrorSet.hasError()) {
            throw new DataValidationException(validationErrorSet.toString());
        }
        if (id == null || id < 1) {
            throw new DataValidationException("Id cannot be less than 1");
        }

        Optional<Hero> heroOptional = repository.findById(id);
        if (!heroOptional.isPresent()) {
            throw new NotFoundException();
        }

        Hero hero = heroOptional.get();

        List<Hero> heroFound = repository.findByName(hero.getName());
        if (heroFound != null && !heroFound.isEmpty() && heroFound.get(0).getId() != id) {
            throw new DataValidationException("A hero already exists with the name " + hero.getName());
        }

        hero.setName(req.getName());
        hero.setDescription(req.getDescription());

        hero = repository.save(hero);
        return mapper.map(hero, UpdateHeroResp.class);

    }

    public void deleteById(Long id) {

        if (id == null || id < 1) {
            throw new DataValidationException("Id cannot be less than 1");
        }

        repository.deleteById(id);
    }

    public GetAllHeroesResp getAll() {
        GetAllHeroesResp resp = new GetAllHeroesResp();
        List<Hero> result = repository.findAll();
        resp.setHeroes(result);
        return resp;
    }

    public GetHeroeByIdResp getById(Long id) {

        if (id == null || id < 1) {
            throw new DataValidationException("Id cannot be less than 1");
        }

        Hero hero = repository.findById(id).orElse(null);
        if (hero != null) {
            return mapper.map(hero, GetHeroeByIdResp.class);
        }

        return null;
    }

    public GetAllHeroesResp getByName(String name) {

        if (name == null || name.isEmpty()) {
            throw new DataValidationException("Name cannot be null or empty");
        }

        GetAllHeroesResp resp = new GetAllHeroesResp();
        List<Hero> result = repository.findByNameContaining(name.toUpperCase());
        resp.setHeroes(result);
        return resp;
    }

}
