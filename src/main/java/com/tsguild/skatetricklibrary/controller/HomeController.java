/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.skatetricklibrary.controller;

import com.tsguild.skatetricklibrary.dao.SkateTrickDao;
import com.tsguild.skatetricklibrary.model.Trick;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Jimmy Cook
 */
@Controller
public class HomeController {

    private SkateTrickDao dao;

    // annotation-driven constructor injection
    @Inject
    public HomeController(SkateTrickDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String displayHomePage() {
        return "home";
    }

    // Retrive Trick
    @RequestMapping(value = "/trick/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Trick getTrick(@PathVariable int id) {
        return dao.getTrickById(id);
    }

    // Create a Trick
    @RequestMapping(value = "/trick", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Trick createTrick(@Valid @RequestBody Trick trick) {

        dao.addTrick(trick);

        return trick;
    }

    // Delete a Trick
    @RequestMapping(value = "/trick/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrick(@PathVariable int id) {
        dao.removeTrick(id);
    }

    // Update a Trick
    @RequestMapping(value = "/trick/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putTrick(@PathVariable int id, @RequestBody Trick trick) {

        trick.setId(id);

        dao.updateTrick(trick);
    }

    // Retrieve all Tricks
    @RequestMapping(value = "/tricks", method = RequestMethod.GET)
    @ResponseBody
    public List<Trick> getAllTricks() {
        return dao.getAllTricks();
    }

}
