/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dzikizachod;

import java.util.Random;

/**
 *
 * @author joald_000
 */
public class Kostka {
    public static int rzuć() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }
}
