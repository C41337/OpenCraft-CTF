/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.EMPCTF.task.impl;

import java.util.Random;
import org.EMPCTF.model.World;
import org.EMPCTF.task.ScheduledTask;

/**
 *
 * @author Jacob Morgan
 */
public class MessageTask extends ScheduledTask{

    private static final long DELAY = 3 * 60 * 1000;

    private Random r = new Random();

    public MessageTask() {
            super(0);
    }

    public void execute() {
        if (this.getDelay() == 0) {
                this.setDelay(DELAY);
        }
        int id = r.nextInt(4);
        String msg = "_";
        switch(id)
        {
            case 0:
                msg = "Join the site! &cEmpyregaming.com";
            break;
            case 1:
                msg = "I'm a scaly man fish!";
            break;
            case 2:
                msg = "Have you read the &8/rules &eyet?";
            break;
            case 3:
                msg = "Visit &aleaderboards.empyregaming.com &eto view your scores!";
            break;
            case 4:
                msg = "Check out &cEmpyre Realms &eand claim your realm today!";
            break;
            case 5:
                msg = "I'm Old Greg!";
            break;
            case 6:
                msg= "Server powered by &13 Monkey's on bikes&e and &aThe Funk&c!";
            break;
            case 7:
                msg= "You ever drink Bailey's from a shoe?";
            break;
        }
        World.getWorld().broadcast("&e[&5Old Greg&e]&f -&e "+msg);
    }

}