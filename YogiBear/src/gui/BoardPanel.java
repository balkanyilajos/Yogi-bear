package gui;

import model.Figure;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import model.*;
import util.Direction;

/**
 * Container of the game
 *
 * @author Balkányi Lajos
 */
public class BoardPanel extends javax.swing.JPanel {

    private int width;
    private int height;

    private int points;
    private boolean wasPlayerInside;
    private Direction playerDirection;

    private Player player;
    private Grass grass;
    private ArrayList<Figure> staticFigures;
    private ArrayList<Figure> supplies;
    private ArrayList<Ranger> rangers;

    private long lastTime;
    private double deltaTime;

    public BoardPanel() {
    }

    public void initVariables(String path, int width, int height, Direction playerDirection) {
        this.width = width;
        this.height = height;
        wasPlayerInside = false;
        staticFigures = new ArrayList<>();
        supplies = new ArrayList<>();
        rangers = new ArrayList<>();
        grass = new Grass(width, height);
        points = 0;
        this.lastTime = System.nanoTime();
        this.deltaTime = 0;
        this.playerDirection = playerDirection;
        fileReader(path);
    }

    public int getPoints() {
        return points;
    }

    public Direction getPlayerDirection() {
        return playerDirection;
    }

    public boolean wasPlayerInside() {
        return wasPlayerInside;
    }

    private void movePlayer() {
        double makeSlower = (playerDirection.isCrossing()) ? sqrt(2) : 1;
        if (playerDirection.getUp()) {
            player.moveUp(deltaTime / makeSlower);
        }
        if (playerDirection.getDown()) {
            player.moveDown(deltaTime / makeSlower);
        }
        if (playerDirection.getLeft()) {
            player.moveLeft(deltaTime / makeSlower);
        }
        if (playerDirection.getRight()) {
            player.moveRight(deltaTime / makeSlower);
        }
    }

    public ArrayList<Figure> getStaticFigures() {
        return staticFigures;
    }

    public ArrayList<Figure> getSupplies() {
        return supplies;
    }

    public boolean isEndOfMap() {
        return supplies.isEmpty();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void increasePoints() {
        points += 1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 603, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 597, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics2D g = (Graphics2D) grphcs;
        deltaTime = (System.nanoTime() - lastTime) / 1_000_000_000.0;
        lastTime = System.nanoTime();

        if (player != null) {
            grass.draw(g);
            for (Figure staticFigure : staticFigures) {
                staticFigure.draw(g);
            }
            for (Figure supply : supplies) {
                supply.draw(g);
            }
            for (Ranger ranger : rangers) {
                ranger.move(deltaTime);
                wasPlayerInside =wasPlayerInside || ranger.isPlayerInside();
                ranger.draw(g);
            }
            movePlayer();
            player.draw(g);
        }
    }

    /**
     * Read objects from a file
     *
     * @param path
     */
    private void fileReader(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = br.readLine();
            String[] playerTokens = new String[5];
            while (line != null) {
                String[] tokens = line.split(" ");
                switch (tokens[0]) {
                    case "Player" ->
                        playerTokens = tokens;
                    case "Tree" ->
                        staticFigures.add(new Tree(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[3])));
                    case "Basket" ->
                        supplies.add(new Basket(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4])));
                    case "Ranger" -> {
                        Direction d = new Direction();
                        switch (tokens[1]) {
                            case "Left" ->
                                d.setLeft(true);
                            case "Right" ->
                                d.setRight(true);
                            case "Up" ->
                                d.setUp(true);
                            case "Down" ->
                                d.setDown(true);
                        }
                        rangers.add(new Ranger(this, Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]), d));
                    }
                }
                line = br.readLine();
            }

            player = new Player(this, Integer.parseInt(playerTokens[1]), Integer.parseInt(playerTokens[2]), Integer.parseInt(playerTokens[3]), Integer.parseInt(playerTokens[4]), Integer.parseInt(playerTokens[5]));
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
