/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.skatetricklibrary.model;

import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author Jimmy Cook
 */
public class Trick {
    
    private int id;
    @NotEmpty(message="You must supply a value for name.")
    @Length(max=50, message="Trick name must be no more than 50 characters in length.")
    private String name;
    @NotEmpty(message="You must supply a value for type.")
    @Length(max=50, message="Trick type must be no more than 50 characters in length.")
    private String type;
    @NotEmpty(message="You must supply a value for category.")
    @Length(max=50, message="Trick category must be no more than 50 characters in length.")
    private String category;
    @Range(min=1, max=5, message="Rating must be between 1 and 5.")
    private int rating;

      
    
    public Trick(int id, String name, String type, String category, int rating) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.category = category;
        this.rating = rating;
    }
    
    public Trick(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.id;
        hash = 17 * hash + Objects.hashCode(this.name);
        hash = 17 * hash + Objects.hashCode(this.type);
        hash = 17 * hash + Objects.hashCode(this.category);
        hash = 17 * hash + this.rating;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Trick other = (Trick) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.rating != other.rating) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        return true;
    }
    
    
    
}
