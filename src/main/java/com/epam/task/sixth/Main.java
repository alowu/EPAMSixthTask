package com.epam.task.sixth;

import com.epam.task.sixth.entity.Player;
import com.epam.task.sixth.entity.PlayerRunnable;
import com.epam.task.sixth.entity.Players;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    private static final String INPUT_FILE = "src/main/resources/data.json";
    private static final Logger LOG = Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        List<Player> players = readData(INPUT_FILE);
        players.forEach(System.out::println);
        System.out.println("-----------------------");

        final int amountOfPlayers = players.size();
        ExecutorService executorService = Executors.newFixedThreadPool(amountOfPlayers);

        List<Future<?>> futures = new ArrayList<>();


        players.stream().forEach(player -> {
            Runnable r = new PlayerRunnable(player);
            Future<?> future = executorService.submit(r);
            futures.add(future);
        });

        for (Future<?> future : futures) {
            future.get();
        }

        players.forEach(System.out::println);

        executorService.shutdown();
    }

    private static List<Player> readData(String inputFile) throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Players playersWrapper = objectMapper.readValue(new File(inputFile), Players.class);
            return playersWrapper.getPlayers();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw e;
        }
    }
}
