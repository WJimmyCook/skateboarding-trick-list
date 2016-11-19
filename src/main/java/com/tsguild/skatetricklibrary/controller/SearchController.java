/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.skatetricklibrary.controller;

import com.tsguild.skatetricklibrary.dao.SearchTerm;
import com.tsguild.skatetricklibrary.dao.SkateTrickDao;
import com.tsguild.skatetricklibrary.model.Trick;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Jimmy Cook
 */
@Controller
public class SearchController {
    
    private SkateTrickDao dao;
    
    @Inject
    public SearchController(SkateTrickDao dao){
        this.dao = dao;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String displaySearchPage() {

        return "search";
    }

    @ResponseBody
    @RequestMapping(value="search/tricks", method = RequestMethod.POST)
    public List<Trick> searchTricks(@RequestBody Map<String, String> searchMap){
        Map<SearchTerm, String> criteriaMap = new HashMap<>();
        
        String currentTerm = searchMap.get("name");
        if(currentTerm != null && !currentTerm.isEmpty()){
            criteriaMap.put(SearchTerm.NAME, currentTerm);
        }
        
        currentTerm = searchMap.get("category");
        if(currentTerm != null && !currentTerm.isEmpty()){
            criteriaMap.put(SearchTerm.CATEGORY, currentTerm);
        }
        
        currentTerm = searchMap.get("type");
        if(currentTerm != null && !currentTerm.isEmpty()){
            criteriaMap.put(SearchTerm.TYPE, currentTerm);
        }
        
        currentTerm = searchMap.get("rating");
        if(currentTerm != null && !currentTerm.isEmpty()){
            criteriaMap.put(SearchTerm.RATING, currentTerm);
        }
        
        return dao.searchTricks(criteriaMap);
    }
}
