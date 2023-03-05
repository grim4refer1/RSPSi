package com.rspsi.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.major.map.RenderFlags;

import com.jagex.Client;
import com.jagex.Client.LoadState;
import com.jagex.map.object.GameObject;
import com.jagex.map.object.ObjectGroup;
import com.jagex.map.object.ObjectType;
import com.jagex.map.tile.SceneTile;
import com.jagex.util.BitFlag;
import com.rspsi.options.Options;

public class StepsBuilder {

    private static final int STEP_SIZE = 2; // The size of each step in tiles
    private static final int STEP_HEIGHT_DIFFERENCE = 1; // The difference in height between each step
    private static final int STAIRCASE_ID = 117; // The ID of the staircase object to spawn

    public static void buildStairs() throws Exception {
        Client client = Client.getSingleton();
        if (client.loadState == LoadState.ACTIVE) {
            if (Options.currentHeight.get() == 3) {
                throw new Exception("Invalid height");
            }
            int oldHeight = Options.tileHeightLevel.get();
            BitFlag oldFlag = Options.tileFlags.get();

            int lowerZ = Options.currentHeight.get();
            List<SceneTile> selectedTiles = client.sceneGraph.getSelectedTiles();
            List<SceneTile> tilesAround = selectedTiles.stream()
                    .flatMap(tile -> IntStream
                            .rangeClosed(-1, 1)
                            .boxed()
                            .flatMap(x -> IntStream.rangeClosed(-1, 1)
                                    .mapToObj(y -> client.sceneGraph.tiles[tile.plane][tile.positionX + x][tile.positionY + y])))
                    .filter(tile -> !selectedTiles.contains(tile))
                    .collect(Collectors.toList());
            List<List<SceneTile>> steps = splitTilesByDirection(selectedTiles);
            int highestHeight = selectedTiles.stream()
                    .mapToInt(tile -> -client.mapRegion.tileHeights[lowerZ][tile.positionX][tile.positionY])
                    .max()
                    .orElse(0);

            Options.tileHeightLevel.set(highestHeight);
            Options.tileFlags.set(new BitFlag(RenderFlags.WALL_TILE));
            Options.overlayPaintShapeId.set(1);
            Options.overlayPaintId.set(1);

            for (int i = steps.size() - 1; i >= 0; i--) {
                List<SceneTile> step = steps.get(i);
                for (SceneTile tile : step) {
                    tile.height = Options.tileHeightLevel.get();
                }
                spawnStaircaseObject(client, step);
                Options.tileHeightLevel.set(Options.tileHeightLevel.get() - STEP_HEIGHT_DIFFERENCE);
            }

            client.sceneGraph.setTileHeights(tilesAround, true);

            Options.tileHeightLevel.set(oldHeight);
            Options.tileFlags.set(oldFlag);

            client.sceneGraph.tileQueue.clear();
        }
    }

    private static List<List<SceneTile>> splitTilesByDirection(List<SceneTile> tiles) {
        List<List<SceneTile>> steps = new ArrayList<>();
        boolean isXDirection = tiles.get(0).positionX == tiles.get(1).positionX;
        List<SceneTile> step = new ArrayList<>();
        int lastPosition = isXDirection ? tiles.get(0).positionY : tiles.get(0).positionX;
        for (SceneTile tile : tiles) {
            int currentPosition = isXDirection ? tile.positionY : tile