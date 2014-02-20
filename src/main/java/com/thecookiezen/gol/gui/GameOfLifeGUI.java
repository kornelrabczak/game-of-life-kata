package com.thecookiezen.gol.gui;

import core.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author nikom
 * @author cartesinus
 */
public class GameOfLifeGUI extends JPanel {

    private Grid grid;

    public GameOfLifeGUI(int width, int height) {
        grid = new Grid(width/4);
    }

    public void updateGrid() {
        grid.nextGeneration();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(grid.getSize() * 4, grid.getSize()* 4);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color gColor = g.getColor();

        g.drawString("Generation: " + grid.getGenerationCount(), 0, 10);
        for (int i = 0; i < grid.getSize(); i++) {
            for (int j = 0; j < grid.getSize(); j++) {
                if (grid.isCellAliveAt(i, j)) {
                    g.setColor(Color.red);
                    g.fillRect(j * 4, i * 4, 4, 4);
                }
            }
        }

        g.setColor(gColor);
    }

    public static void main(String[] args) {
        final GameOfLifeGUI c = new GameOfLifeGUI(800, 800);
        JFrame frame = new JFrame();
        frame.getContentPane().add(c);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        new Timer(500, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                c.updateGrid();
                c.repaint();
            }
        }).start();
    }

}
