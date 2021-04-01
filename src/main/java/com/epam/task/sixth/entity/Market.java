package com.epam.task.sixth.entity;

import com.epam.task.sixth.logic.PlayerLogic;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Market {
    private final static Random random = new Random();
    private final static int MAX_BYN_TO_USD = 10;

    private static final Lock LOCK = new ReentrantLock();
    private static final AtomicReference<Market> INSTANCE = new AtomicReference<>();

    private static final Logger LOG = Logger.getLogger(Market.class);
    private static final int TOTAL_OPERATIONS = 2;
    private static final Semaphore SEMAPHORE = new Semaphore(TOTAL_OPERATIONS);

    private int getCourse() {
        return random.nextInt(MAX_BYN_TO_USD) + 1;
    }

    private Market(){}

    public static Market getInstance(){
        if (INSTANCE.get() == null) {
            create();
        }
        return INSTANCE.get();
    }

    private static void create() {
        try {
            LOCK.lock();
            if (INSTANCE.get() == null) {
                INSTANCE.getAndSet(new Market());
            }
        } finally {
            LOCK.unlock();
        }
    }

    public void process(Player player) {
        try{
            SEMAPHORE.acquire();

            int course = getCourse();
            PlayerLogic logic = new PlayerLogic(player);
            double operation = Math.random();
            if (operation > 0.555) {
                logic.sellByn(course);
            } else {
                logic.sellUsd(course);
            }
        } catch (InterruptedException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            SEMAPHORE.release();
        }
    }
}
