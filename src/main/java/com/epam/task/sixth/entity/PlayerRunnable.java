package com.epam.task.sixth.entity;

public class PlayerRunnable implements Runnable{
    private final Player player;

    public PlayerRunnable(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        Market market = Market.getInstance();
        market.process(player);
    }
}
