/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import javax.servlet.http.HttpSession;
import models.User;

/**
 *
 * @author gyerby
 */
public class Membership {

    /**
     *
     * @param session
     * @return
     */
    public static User getUserBean(HttpSession session) {
        User currentUser = (User)session.getAttribute("CurrentUser");
        return currentUser;
    }
    
    
    
}
