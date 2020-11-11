package com.sm.page;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hook extends PageInit {

    @Before
    public void init(Scenario scenario) {
        System.out.println(scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println(scenario.getName());
    }
}
