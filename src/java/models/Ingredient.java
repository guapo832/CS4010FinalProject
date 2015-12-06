/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;

/**
 *
 * @author gyerby
 */
public class Ingredient {
    private String description;
    private int recipeid;
    private int id;

    /**
     *
     * @param id
     */
    public Ingredient(int id) {
       this.id = id;
    }

    /**
     *
     */
    public Ingredient(){}
    // get methods

    /**
     *
     * @return
     */
        public String getDescription(){return description;}

    /**
     *
     * @return
     */
    public int getRecipeId(){return recipeid;}

    /**
     *
     * @return
     */
    public int getId(){return id;}
    
    //set methods

    /**
     *
     * @param value
     */
        public void setDescription(String value){description = value;}

    /**
     *
     * @param value
     */
    public void setRecipeId(int value){recipeid = value;}

    /**
     *
     * @param value
     */
    public void setId(int value){id = value;}

   
    
}
