package com.github.matiasvergaras;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.normal.Thief;
import com.github.matiasvergaras.finalreality.model.weapon.normal.Knife;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Ignacio Slater Muñoz.
 */
public class TimerExample {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<ICharacter> queue = new LinkedBlockingQueue<>();
        Random rng = new Random();
        for (int i = 0; i < 10; i++) {
            // Gives a random speed to each character to generate different waiting times
            var weapon = new Knife("", 0, rng.nextInt(50));
            var character = new Thief(queue, Integer.toString(i), 200, 200);
            character.equip(weapon);
            character.waitTurn();
        }
        // Waits for 6 seconds to ensure that all characters have finished waiting
        Thread.sleep(6000);
        while (!queue.isEmpty()) {
            // Pops and prints the names of the characters of the queue to illustrate the turns
            // order
            System.out.println(queue.poll().getName());
        }
    }
}
