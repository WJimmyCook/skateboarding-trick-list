/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.skatetricklibrary.dao;

import com.tsguild.skatetricklibrary.model.Trick;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author Jimmy Cook
 */
public class SkateTrickDaoImpl implements SkateTrickDao {

    private Map<Integer, Trick> trickMap = new HashMap<>();
    
    private static int trickIdCounter = 0;
    
    @Override
    public Trick addTrick(Trick trick) {
        trick.setId(trickIdCounter);
        trickMap.put(trickIdCounter, trick);
        trickIdCounter++;
        return trick;
    }

    @Override
    public Trick getTrickById(int trickId) {
        return trickMap.get(trickId);
    }

    @Override
    public List<Trick> getAllTricks() {
        return trickMap.values().stream()
                .collect(Collectors.toList());
    }

    @Override
    public void updateTrick(Trick trick) {
        trickMap.put(trick.getId(), trick);
    }

    @Override
    public void removeTrick(int trickId) {
        trickMap.remove(trickId);
    }
    
    @Override
    public List<Trick> searchTricks(Map<SearchTerm, String> criteria) {
        
        String nameCriteria = criteria.get(SearchTerm.NAME);
        String categoryCriteria = criteria.get(SearchTerm.CATEGORY);
        String typeCriteria = criteria.get(SearchTerm.TYPE);
        String ratingCriteria = criteria.get(SearchTerm.RATING);
        
        Predicate<Trick> nameMatches;
        Predicate<Trick> categoryMatches;
        Predicate<Trick> typeMatches;
        Predicate<Trick> ratingMatches;
        
        Predicate<Trick> truePredicate = (t) -> {return true;};
        
        nameMatches = (nameCriteria == null || nameCriteria.isEmpty())
                ? truePredicate : (t) -> t.getName().toLowerCase()
                        .equals(nameCriteria.toLowerCase());
        
        categoryMatches = (categoryCriteria == null || categoryCriteria.isEmpty())
                ? truePredicate : (t) -> t.getCategory().toLowerCase()
                        .equals(categoryCriteria.toLowerCase());
        
        typeMatches = (typeCriteria == null || typeCriteria.isEmpty())
                ? truePredicate : (t) -> t.getType().toLowerCase()
                        .equals(typeCriteria.toLowerCase());
        
        ratingMatches = (ratingCriteria == null || ratingCriteria.isEmpty())
                ? truePredicate : (t) -> Integer.toString(t.getRating()).equals(ratingCriteria);
        
        return trickMap.values().stream()
                .filter(nameMatches
                    .and(categoryMatches)
                    .and(typeMatches)
                    .and(ratingMatches))
                .collect(Collectors.toList());
    }

    @Override
    public List<Trick> searchTricks(Predicate<Trick> filter) {
        return trickMap.values().stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

}
