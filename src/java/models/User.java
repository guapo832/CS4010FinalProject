/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author gyerby
 */
public class User implements java.io.Serializable  {
    
    private String username;
    private String password;
    private int userid;
    
    /**
     *
     * @return
     */
    public String getUsername(){
        return username;
    }
    
    /**
     *
     * @return
     */
    public int getUserid(){
        return userid;
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
     * @param value
     */
    public void setUsername(String value){
        username = value;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return this.password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
