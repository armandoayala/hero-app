package com.arm.api.hero.junit.controller.rest;

import com.arm.api.hero.junit.common.WebApp;
import com.arm.api.hero.model.bo.Hero;
import com.arm.api.hero.model.dto.request.CreateHeroReq;
import com.arm.api.hero.model.dto.response.GenericResp;
import com.arm.api.hero.repository.HeroRepository;
import com.arm.api.hero.service.HeroService;
import com.arm.api.hero.util.Constant;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.doReturn;

public class HeroControllerTest extends WebApp {

    @MockBean
    private HeroRepository repository;

    @SpyBean
    private HeroService service;

    @Override
    @Before
    public void setUp() {
        try {
            super.setUp();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @After
    public void afterEachTest() {

    }

    @WithMockUser(value = "admin", username = "admin", authorities = {"ROLE_OPERADOR", "ROLE_ADMIN"})
    @Test
    public void create_OK() {

        try {

            Hero hero = new Hero();
            hero.setName("SUPERMAN");
            hero.setDescription("Hero");

            CreateHeroReq request = new CreateHeroReq();
            request.setName("Superman");
            request.setDescription("Hero");

            doReturn(new ArrayList<Hero>()).when(repository).findByName(Mockito.anyString());
            doReturn(hero).when(repository).save(Mockito.any());

            String uri = "/hero";

            MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                    .content(mapToJson(request))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            ).andReturn();

            GenericResp genericResp = mapFromJson(mvcResult.getResponse().getContentAsString(), GenericResp.class);
            LinkedHashMap createHeroResp = (LinkedHashMap) genericResp.getResult();

            org.assertj.core.api.Assertions.assertThat(HttpStatus.CREATED.value()).isEqualTo(mvcResult.getResponse().getStatus());
            org.assertj.core.api.Assertions.assertThat(genericResp.getMessage() == Constant.AppEnum.MSG_SUCCESS.getValue());
            org.assertj.core.api.Assertions.assertThat(genericResp.getException() == null);
            org.assertj.core.api.Assertions.assertThat(createHeroResp.get("name") == hero.getName());
            org.assertj.core.api.Assertions.assertThat(createHeroResp.get("description") == hero.getDescription());

        } catch (Exception ex) {
            fail("Error at create_OK: " + ex.getMessage());
            ex.printStackTrace();
        }


    }

}
