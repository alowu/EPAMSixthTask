package com.epam.task.sixth.logic;

import com.epam.task.sixth.entity.Player;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PlayerLogic {
    private final Player player;

    private static final Logger LOG = Logger.getLogger(PlayerLogic.class);
    private static final Lock LOCK = new ReentrantLock();

    public PlayerLogic(Player player) {
        this.player = player;
    }

    public void sellByn(int course){
        try {
            LOCK.lock();
            int currentByn = player.getByn();
            int bynToSell = currentByn / 2;
            currentByn -= bynToSell;

            int currentUsd = player.getUsd();
            currentUsd += bynToSell / course;

            player.setUsd(currentUsd);
            player.setByn(currentByn);
            LOG.info("player " + player.getId() + " sell byn " + bynToSell
                    + " and now have " + currentUsd + " usd & " + currentByn + " byn" +
                    " course " + course);
        } finally {
            LOCK.unlock();
        }
    }

    public void sellUsd(int course){
        try {
            LOCK.lock();
            int currentUsd = player.getUsd();
            int usdToSell = currentUsd / 2;
            currentUsd -= usdToSell;

            int currentByn = player.getByn();
            currentByn += usdToSell * course;

            player.setUsd(currentUsd);
            player.setByn(currentByn);
            LOG.info("player " + player.getId() + " sell usd " + usdToSell
                    + " and now have " + currentUsd + " usd & " + currentByn + " byn" +
                    " course " + course);
        } finally {
            LOCK.unlock();
        }
    }
}
