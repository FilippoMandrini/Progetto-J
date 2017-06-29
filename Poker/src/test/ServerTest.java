/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import server.Server;

/**
 *
 * @author Nickelsilver
 */

public class ServerTest {
    
    public static void main(String[] args) throws IOException {
        Thread server = new Thread(new Server());
        server.start();
    }
}
