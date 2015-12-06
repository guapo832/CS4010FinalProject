/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import Utilities.Encoding;

/**
 *
 * @author gyerby
 */
public class Recipe {
    
    private String title;
    private int id;
    private String instructions;
    private String author;
    private String shortDescription;
    private String imageFilepath;
    private int prepTime=0; //in minutes
    private int cookTime=0; //in minutes
    private String skillLevel;
    private String yield;
    private int userid;    
    
    /**
     *
     * @param id
     */
    public Recipe(int id){
       this.id = id;
    }
    
    /**
     *
     */
    public Recipe(){}

    /**
     *
     * @return
     */
    public int getId(){
        return id;
    }
    
    /**
     *
     * @return
     */
    public String getAuthor(){
        return author;
        
    }

    /**
     *
     * @return
     */
    public String getInstructions(){
        return instructions;
    }

    /**
     *
     * @return
     */
    public int getPrepTime() {
        return prepTime;
    }

    /**
     *
     * @return
     */
    public int getCookTime(){
        return cookTime;
    }
    
    /**
     *
     * @return
     */
    public int getTotalTime(){
        return prepTime + cookTime;
    }
    
    /**
     *
     * @return
     */
    public String getSkillLevel(){
        return skillLevel;
    }
    
    /**
     *
     * @return
     */
    public String getYield(){
        return yield;
    }

    /**
     *
     * @return
     */
    public String getTitle(){
        return title;
    }

    /**
     *
     * @return
     */
    public String getShortDescription(){
        return shortDescription;
    }

    /**
     *
     * @return
     */
    public String getImageFilePath(){
        
        return imageFilepath;
    }

    /**
     *
     * @return
     */
    public int getUserid(){
        return this.userid;
    }

    /**
     *
     * @param value
     */
    public void setUserid(int value){
        this.userid = value;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title){this.title = title;}

    /**
     *
     * @param author
     */
    public void setAuthor(String author){this.author = author;}

    /**
     *
     * @param instructions
     */
    public void setInstructions(String instructions){this.instructions = instructions;}

    /**
     *
     * @param id
     */
    public void setId(int id){this.id = id;}

    /**
     *
     * @param shortDescription
     */
    public void setShortDescription(String shortDescription){
        this.shortDescription = shortDescription;
    }

    /**
     *
     * @param filepath
     */
    public void setImageFilePath(String filepath){
        this.imageFilepath = filepath;
    }

    /**
     *
     * @param value
     */
    public void setYield(String value){yield = value;}

    /**
     *
     * @param value
     */
    public void setSkillLevel(String value){skillLevel = value;}

    /**
     *
     * @param value
     */
    public void setPrepTime(int value){prepTime = value;}

    /**
     *
     * @param value
     */
    public void setCookTime(int value) {cookTime = value;}
    
    }
    

