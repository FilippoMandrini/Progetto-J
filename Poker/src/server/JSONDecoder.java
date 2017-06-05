/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author Nickelsilver
 */
public class JSONDecoder {
     
    private static JSONDecoder instance;
   
    private JSONDecoder(){
        
    }
    
    public static synchronized JSONDecoder getInstance()
    {
        if (instance == null)
        {
            instance = new JSONDecoder();
        }
        return instance;
    }
    
}
