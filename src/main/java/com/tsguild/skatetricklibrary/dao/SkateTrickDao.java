/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.skatetricklibrary.dao;

import com.tsguild.skatetricklibrary.model.Trick;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 *
 * @author Jimmy Cook
 */
public interface SkateTrickDao {

    // CREATE
    public Trick addTrick(Trick trick);

    // READ
    public Trick getTrickById(int trickId);

    public List<Trick> getAllTricks();

    // UPDATE
    public void updateTrick(Trick trick);

    // DELETE
    public void removeTrick(int trickId);

    // SEARCH
    public List<Trick> searchTricks(Map<SearchTerm, String> criteria);
    public List<Trick> searchTricks(Predicate<Trick> filter);
    
}
