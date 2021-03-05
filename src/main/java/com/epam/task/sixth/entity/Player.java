package com.epam.task.sixth.entity;

public class Player{
    private int id;
    private int usd;
    private int byn;

    public Player(){}

    public int getId(){
        return id;
    }

    public int getUsd() {
        return usd;
    }

    public void setUsd(int usd) {
        this.usd = usd;
    }

    public int getByn() {
        return byn;
    }

    public void setByn(int byn) {
        this.byn = byn;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", usd=" + usd +
                ", byn=" + byn +
                '}';
    }
}
