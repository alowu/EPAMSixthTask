package com.epam.task.sixth.logic;

import com.epam.task.sixth.entity.Player;
import org.apache.log4j.Logger;

public class PlayerLogic {
    private final Player player;

    private static final Logger LOG = Logger.getLogger(PlayerLogic.class);

    public PlayerLogic(Player player) {
        this.player = player;
    }

    public void sellByn(int course) {

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

    }

    public void sellUsd(int course) {

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

    }
}
