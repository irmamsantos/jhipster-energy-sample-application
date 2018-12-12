package io.github.jhipster.energy.cucumber.stepdefs;

import io.github.jhipster.energy.JhipsterEnergySampleApplicationApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = JhipsterEnergySampleApplicationApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
