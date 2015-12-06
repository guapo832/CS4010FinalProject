/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import controllers.Index;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Ingredient;
import models.Recipe;
import models.User;

/**
 *
 * @author gyerby
 */
public final class DBHelper {

    /**
     *
     * @param dbcon
     * @param recipe
     * @return
     * @throws SQLException
     */
    public static int createRecipe(Connection dbcon, Recipe recipe) throws SQLException {
        String query = "INSERT INTO gyerby_recipes(Title,Author,Instructions,ShortDescription, picture_filepath, PrepTime, CookTime, SkillLevel, Yield, gyerby_userID) VALUES(?,?,?,?,?,?,?,?,?,?)";
        
        try {
           PreparedStatement ps = dbcon.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
           ps.setString(1, recipe.getTitle());
           ps.setString(2, recipe.getAuthor());
           ps.setString(3, recipe.getInstructions());
           ps.setString(4, recipe.getShortDescription());
           ps.setString(5, recipe.getImageFilePath());
           ps.setInt(6, recipe.getPrepTime());
           ps.setInt(7, recipe.getCookTime());
           ps.setString(8,recipe.getSkillLevel());
           ps.setString(9, recipe.getYield());
           ps.setInt(10, recipe.getUserid());
           ps.executeUpdate();
           ResultSet rs = ps.getGeneratedKeys();
           if(rs.next())
               return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
          
        }
        return 0;
    }

    /**
     *
     * @param dbcon
     * @param ingredient
     * @return
     * @throws SQLException
     */
    public static int createIngredient(Connection dbcon, Ingredient ingredient) throws SQLException {
        String query = "INSERT INTO gyerby_ingredients(Recipe_ID,Description) VALUES(?,?)";
        
        try {
           PreparedStatement ps = dbcon.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
           ps.setInt(1, ingredient.getRecipeId());
           ps.setString(2, ingredient.getDescription());
          
           ps.executeUpdate();
           ResultSet rs = ps.getGeneratedKeys();
           if(rs.next())
               return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return 0;
    }

    /**
     *
     * @param dbcon
     * @param recipeID
     * @param encodeValues
     * @return
     * @throws SQLException
     */
    public static Recipe readRecipe(Connection dbcon, int recipeID, Boolean encodeValues) throws SQLException{
          String query = "Select * From gyerby_recipes WHERE idRecipe = ?";
          
       Recipe recipe = null; 
        
        try {
            PreparedStatement ps = dbcon.prepareStatement(query);
            ps.setInt(1, recipeID);
            ResultSet rs = ps.executeQuery();
                       
            try {
            if(rs.next()){
                
                int id = rs.getInt("idRecipe");
                recipe = new Recipe(id);
                String author = rs.getString("Author");
                String instructions = rs.getString("Instructions");
                String shortdesc = rs.getString("ShortDescription");
                String imgfilepath = rs.getString("picture_filepath");
                String title = rs.getString("Title");
                int preptime = rs.getInt("PrepTime");
                int cooktime = rs.getInt("CookTime");
                String skilllevel = rs.getString("SkillLevel");
                String yield = rs.getString("Yield");
                if(encodeValues){
                    author = Encoding.encodeHTML(author);
                    instructions = Encoding.encodeHTML(instructions).replace("\n","<br />");
                    shortdesc = Encoding.encodeHTML(shortdesc);
                    title = Encoding.encodeHTML(title);
                    yield = Encoding.encodeHTML(yield);
                    skilllevel = Encoding.encodeHTML(skilllevel);
                }
                
                recipe.setInstructions(instructions);
                recipe.setAuthor(author);
                recipe.setTitle(title);
                recipe.setImageFilePath(rs.getString("picture_filepath"));
                recipe.setShortDescription(shortdesc);
                recipe.setCookTime(cooktime);
                recipe.setPrepTime(preptime);
                recipe.setSkillLevel(skilllevel);
                recipe.setYield(yield);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return recipe;
    }

    /**
     *
     * @param dbcon
     * @param ID
     * @return
     * @throws SQLException
     */
    public static Ingredient readIngredient(Connection dbcon, int ID) throws SQLException{
          String query = "Select * From gyerby_ingredients WHERE idIngredient = ?";
          
       Ingredient ingredient = null; 
        
        try {
            PreparedStatement ps = dbcon.prepareStatement(query);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
                       
            try {
            if(rs.next()){
                ingredient = new Ingredient(ID);
                ingredient.setDescription(rs.getString("Description"));
                ingredient.setRecipeId(rs.getInt("Recipe_ID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return ingredient;
    }

    /**
     *
     * @param dbcon
     * @param ID
     * @return
     */
    public static ArrayList<Ingredient> readIngredients(Connection dbcon, int ID){
          String query = "Select * From gyerby_ingredients WHERE Recipe_ID = ?";
          Ingredient ingredient = null;
          ArrayList<Ingredient> ingredients = new ArrayList<>();
        
        try {
            PreparedStatement ps = dbcon.prepareStatement(query);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
                       
            try {
            while(rs.next()){
                
                int id = rs.getInt("idIngredient");
                
                ingredient = new Ingredient(id);
                ingredient.setRecipeId(ID);
                ingredient.setDescription(rs.getString("Description"));
                ingredients.add(ingredient);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ingredients;
    }

    /**
     *
     * @param dbcon
     * @param recipe
     * @throws SQLException
     */
    public static void updateRecipe(Connection dbcon, Recipe recipe) throws SQLException{
         String query = "Update gyerby_recipes SET Title =?, Author=?, Instructions=?, ShortDescription=?, picture_filepath=?, PrepTime=?, CookTime=?, Yield=?, SkillLevel=? WHERE idRecipe=?";
       try {
            PreparedStatement ps = dbcon.prepareStatement(query);
            ps.setString(1, recipe.getTitle());
            ps.setString(2, recipe.getAuthor());
            ps.setString(3, recipe.getInstructions());
            ps.setString(4, recipe.getShortDescription());
            
            String Filepath = recipe.getImageFilePath();
            ps.setString(5, Filepath);
            ps.setInt(6,recipe.getPrepTime());
            ps.setInt(7, recipe.getCookTime());
            ps.setString(8, recipe.getYield());
            ps.setString(9, recipe.getSkillLevel());
            ps.setInt(10, recipe.getId());
            performUpdate(ps);
                       
            
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        
    }

    /**
     *
     * @param dbcon
     * @param ingredient
     * @throws SQLException
     */
    public static void updateIngredient(Connection dbcon, Ingredient ingredient) throws SQLException{
         String query = "Update gyerby_ingredients SET Description=? WHERE idIngredient=?";
       try {
            PreparedStatement ps = dbcon.prepareStatement(query);
            ps.setString(1, ingredient.getDescription());
            ps.setInt(2, ingredient.getId());
            //ps.executeUpdate();
            performUpdate(ps);
                       
            
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        
    }

    /**
     *
     * @param dbcon
     * @param ID
     * @throws SQLException
     */
    public static void deleteRecipe(Connection dbcon, int ID) throws SQLException{
         String query = "DELETE FROM gyerby_ingredients WHERE Recipe_ID=?";
         String querydeleteRecipe = "DELETE FROM gyerby_recipes WHERE idRecipe=?";
       try {
            PreparedStatement ps = dbcon.prepareStatement(query);
            PreparedStatement ps2 = dbcon.prepareStatement(querydeleteRecipe);
            ps.setInt(1, ID);
            ps2.setInt(1, ID);
            ps.executeUpdate();
            performUpdate(ps);
           performUpdate(ps2);
                       
            
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    /**
     *
     * @param dbcon
     * @param ID
     */
    public static void deleteIngredient(Connection dbcon, int ID){
         String query = "DELETE FROM gyerby_ingredients WHERE idIngredient=?";
       try {
            PreparedStatement ps = dbcon.prepareStatement(query);
            ps.setInt(1, ID);
            performUpdate(ps);
                       
            
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param dbcon
     * @param userid
     * @return
     * @throws SQLException
     */
    public static ArrayList<Recipe> readRecipeList(Connection dbcon, int userid) throws SQLException{
        String query = "Select * From gyerby_recipes WHERE gyerby_userID = ?";
       
        ArrayList<Recipe> recipeList = new ArrayList<>();
        try {
            PreparedStatement ps = dbcon.prepareStatement(query);
            ps.setInt(1, userid);
            ResultSet rs = ps.executeQuery();
            
            try {
            while(rs.next()){
                int id = rs.getInt("idRecipe");
                Recipe recipe = new Recipe(id);
                recipe.setAuthor(rs.getString("Author"));
                recipe.setInstructions(rs.getString("Instructions"));
                recipe.setTitle(rs.getString("Title"));
                recipe.setShortDescription(rs.getString("ShortDescription"));
                recipe.setImageFilePath(rs.getString("picture_filepath"));
                recipe.setCookTime(rs.getInt("CookTime"));
                recipe.setPrepTime(rs.getInt("PrepTime"));
                recipe.setSkillLevel(rs.getString("SkillLevel"));
                recipe.setYield(rs.getString("Yield"));
                recipeList.add(recipe);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return recipeList;
    }
    
    /**
     *
     * @param dbcon
     * @return
     * @throws SQLException
     */
    public static ArrayList<Recipe> readRecipeList(Connection dbcon) throws SQLException{
        String query = "Select * From gyerby_recipes";
       
        ArrayList<Recipe> recipeList = new ArrayList<>();
        try {
            PreparedStatement ps = dbcon.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            try {
            while(rs.next()){
                int id = rs.getInt("idRecipe");
                Recipe recipe = new Recipe(id);
                recipe.setAuthor(rs.getString("Author"));
                recipe.setInstructions(rs.getString("Instructions"));
                recipe.setTitle(rs.getString("Title"));
                recipe.setShortDescription(rs.getString("ShortDescription"));
                recipe.setImageFilePath(rs.getString("picture_filepath"));
                recipe.setCookTime(rs.getInt("CookTime"));
                recipe.setPrepTime(rs.getInt("PrepTime"));
                recipe.setSkillLevel(rs.getString("SkillLevel"));
                recipe.setYield(rs.getString("Yield"));
                recipeList.add(recipe);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return recipeList;
    }

    /**
     *
     * @param dbcon
     * @param username
     * @param password
     * @return
     */
    public static User getUser(Connection dbcon, String username, String password){
         String query = "Select * From gyerby_users WHERE Username = ? and Password=?";
          
       User user = null; 
        
        try {
            PreparedStatement ps = dbcon.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
                       
            try {
            if(rs.next()){
                user = new User();
                user.setUsername(rs.getString("Username"));
                user.setUserid((rs.getInt("idgyerby_Users")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    /**
     *
     * @param dbcon
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    public static User createUser(Connection dbcon, String username, String password) throws SQLException {
        String query = "INSERT INTO gyerby_users(Username,Password) VALUES(?,?)";
        User user = null;
        try {
           PreparedStatement ps = dbcon.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
           ps.setString(1, username);
           ps.setString(2, password);
           ps.executeUpdate();
           ResultSet rs = ps.getGeneratedKeys();
           if(rs.next())
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setUserid(rs.getInt(1));
            
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
          throw ex;
        }
        return null;
    }

    
    private static synchronized void performUpdate(PreparedStatement ps) throws SQLException{
        ps.executeUpdate();
    }
}
