package com.arm.api.hero.junit;

import com.arm.api.hero.junit.controller.rest.HeroControllerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        HeroControllerTest.class
})
public class SuiteTest {
}
