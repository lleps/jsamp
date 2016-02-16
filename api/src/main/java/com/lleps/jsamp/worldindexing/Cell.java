package com.lleps.jsamp.worldindexing;

import com.lleps.jsamp.player.Player;
import com.lleps.jsamp.FunctionAccess;
import com.lleps.jsamp.world.World;
import com.lleps.jsamp.world.WorldEntity;

import java.util.ArrayList;
import java.util.List;


public class Cell {
    private final List<WorldEntity> entities = new ArrayList<>();
    private final List<Player> players = new ArrayList<>();

    private final World world;

    public Cell(World world) {
        this.world = world;
    }

    public void addEntity(WorldEntity entity) {
        entities.add(entity);
        for (Player player : players) {
            //if (!entity.isCreated(player.getId())) {
            //    entity.create(player.getId(), world.getId());
            //}
        }
    }

    public void removeEntity(WorldEntity entity) {
        entities.add(entity);
        for (Player player : players) {
         //   entity.destroy(player.getId());
        }
    }

    public void loadTo(Player player) {
        FunctionAccess.SendClientMessage(player.getId(), -1, "célula cargada: " + entities);
        for (WorldEntity entity : entities) {
           // if (!entity.isCreated(player.getId())) {
         //       entity.create(player.getId(), world.getId());
            //}
        }
        players.add(player);
    }

    public void unloadTo(Player player) {
        FunctionAccess.SendClientMessage(player.getId(), -1, "célula descargada: " + entities);
        //for (WorldEntity entity : entities) entity.destroy(player.getId());
        players.remove(player);
    }
}
