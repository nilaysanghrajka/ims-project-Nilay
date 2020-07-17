package com.qa.ims.controller;


import static org.junit.Assert.assertTrue;


import org.junit.Test;


public class ActionTest {


    @Test
    public void returnTest() {
        Action action = Action.RETURN;
        assertTrue(action.getDescription().toLowerCase().contains("return to domain"));
    }


    @Test
    public void createTest() {
        Action action = Action.CREATE;
        assertTrue(action.getDescription().toLowerCase().contains("save a new item"));
    }


    @Test
    public void readTest() {
        Action action = Action.READ;
        assertTrue(action.getDescription().toLowerCase().contains("read an item"));
    }


    @Test
    public void updateTest() {
        Action action = Action.UPDATE;
        assertTrue(action.getDescription().toLowerCase().contains("change an item"));
    }


    @Test
    public void deleteTest() {
        Action action = Action.DELETE;
        assertTrue(action.getDescription().toLowerCase().contains("remove an item"));
    }


}
 