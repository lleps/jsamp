package com.lleps.jsamp.worldindexing;

import com.lleps.jsamp.player.Player;
import com.lleps.jsamp.data.Vector3D;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CellStreamer {
    private Cell previousCell;

    public CellStreamer(Player player) {

    }

    public void update(Player player, Vector3D position) {

    }

    public Collection<Cell> getNearestCellsTo(int cellX, int cellY) {
        Set<Cell> result = new HashSet<>(9);
        /*result.add(world.getCellAt(playerX    , playerY));
        result.add(world.getCellAt(playerX - 1, playerY));
        result.add(world.getCellAt(playerX - 1, playerY - 1));
        result.add(world.getCellAt(playerX    , playerY - 1));
        result.add(world.getCellAt(playerX + 1, playerY - 1));
        result.add(world.getCellAt(playerX + 1, playerY));
        result.add(world.getCellAt(playerX + 1, playerY + 1));
        result.add(world.getCellAt(playerX    , playerY + 1));
        result.add(world.getCellAt(playerX + 1, playerY + 1));*/
        return result;
    }
}