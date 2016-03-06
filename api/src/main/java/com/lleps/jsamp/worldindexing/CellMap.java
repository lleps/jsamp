package com.lleps.jsamp.worldindexing;

import com.lleps.jsamp.player.Player;
import com.lleps.jsamp.data.Vector;
import com.lleps.jsamp.world.World;
import com.lleps.jsamp.world.entity.WorldEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CellMap {
    // map goes from -4000, 4000 on x and y
    int cellSize = 10;
    int mapExtensionFromCenter = 4000; // goes on x from (-4000,4000) and y (-4000, 4000)
    int mapExtension = mapExtensionFromCenter * 2;
    int cellsInAxis = mapExtension / cellSize;
    int totalCells = cellsInAxis * cellsInAxis;

    World world;

    Cell[][] cells = new Cell[cellsInAxis + 1][cellsInAxis + 1];

    public CellMap(World world) {
        this.world = world;
        for (int x = 0; x < cellsInAxis+1; x++) {
            for (int y = 0; y < cellsInAxis+1; y++) {
                cells[x][y] = new Cell(world);
            }
        }
    }

    void addEntity(WorldEntity entity) {
        Vector entityPos = entity.getPosition();
        int cellX = worldXToCellX(entityPos.getX());
        int cellY = worldXToCellX(entityPos.getY());
        cells[cellX][cellY].addEntity(entity);
    }

    void removeEntity(WorldEntity entity) {
        Vector entityPos = entity.getPosition();
        int cellX = worldXToCellX(entityPos.getX());
        int cellY = worldXToCellX(entityPos.getY());
        cells[cellX][cellY].removeEntity(entity);
    }

    void updatePlayer(Player player) {
        List<Cell> loadedCells = getPlayerLoadedCells(player);
        List<Cell> newCellsToLoad = new ArrayList<>();
        Vector playerPosition = player.getPosition();
        int playerX = worldXToCellX(playerPosition.getX()), playerY = worldYToCellY(playerPosition.getY());
        newCellsToLoad.add(getCellAt(playerX, playerY));
        newCellsToLoad.add(getCellAt(playerX-1, playerY));
        newCellsToLoad.add(getCellAt(playerX-1, playerY-1));
        newCellsToLoad.add(getCellAt(playerX, playerY-1));
        newCellsToLoad.add(getCellAt(playerX+1, playerY-1));
        newCellsToLoad.add(getCellAt(playerX+1, playerY));
        newCellsToLoad.add(getCellAt(playerX+1, playerY+1));
        newCellsToLoad.add(getCellAt(playerX, playerY+1));
        newCellsToLoad.add(getCellAt(playerX+1, playerY+1));

        for (Cell cell : loadedCells) {
            if (!newCellsToLoad.contains(cell)) cell.unloadTo(player);
        }

        for (Cell cell : newCellsToLoad) {
            if (!loadedCells.contains(cell)) cell.loadTo(player);
        }

        setPlayerLoadedCells(player, newCellsToLoad);
    }

    Map<Player, List<Cell>> playerLoadedCells = new HashMap<>();

    Cell getCellAt(int cellX, int cellY) {
        if (cellX < 0) cellX = 0;
        if (cellX > cellsInAxis) cellX = cellsInAxis;
        if (cellY < 0) cellY = 0;
        if (cellY > cellsInAxis) cellY = cellsInAxis;

        return cells[cellX][cellY];
    }

    List<Cell> getPlayerLoadedCells(Player player) {
        return playerLoadedCells.getOrDefault(player, new ArrayList<>());
    }

    void setPlayerLoadedCells(Player player, List<Cell> cells) {
        playerLoadedCells.put(player, cells);
    }

    int worldXToCellX(float x) {
        // limit x to bounds. without it, a out-of-bounds x will throw a NPE.
        if (x < -mapExtensionFromCenter) x = -mapExtensionFromCenter;
        if (x > mapExtensionFromCenter) x = mapExtensionFromCenter;
        // no negative numbers. If x is within bounds, add mapExtensionFromCenter to make it ok?
        x += mapExtensionFromCenter;
        // now we're ok. convert it to cell cords.
        int result = (int)x / cellSize;
        return result;
    }

    int worldYToCellY(float y) {
        if (y < -mapExtensionFromCenter) y = -mapExtensionFromCenter;
        if (y > mapExtensionFromCenter) y = mapExtensionFromCenter;
        y += mapExtensionFromCenter;
        int result = (int)y / cellSize;
        return result;
    }
}
