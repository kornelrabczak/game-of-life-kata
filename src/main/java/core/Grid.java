package core;

import com.google.common.primitives.Ints;

import java.util.Random;

/**
 * @author nikom
 */
public class Grid {

    private final static int DEFAULT_SIZE = 10;
    private final static int MAX_NEIGHBOURS = 3;
    private final static int MIN_NEIGHBOURS = 2;
    private int generationCount = 0;

    public int getSize() {
        return size;
    }

    private final int size;
    private int[][] grid;

    public Grid() {
        size = DEFAULT_SIZE;
        initialize();
    }

    public Grid(int size) {
        this.size = size;
        initialize();
    }

    public Grid(int[][] grid) {
        this.size = grid.length;
        this.grid = grid;
    }

    private void initialize() {
        grid = new int[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                grid[x][y] = (new Random().nextInt(5) % 5 == 0) ? 1 : 0;
            }
        }
    }

    public int getGenerationCount() {
        return generationCount;
    }

    public void nextGeneration() {
        int[][] nextGenerationGrid = new int[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                int livingNeighbours = countLiveNeighbours(x, y);
                if (isAlive(grid[x][y])) {
                    if (shouldDie(livingNeighbours)) {
                        nextGenerationGrid[x][y] = 0;
                    } else {
                        nextGenerationGrid[x][y] = 1;
                    }
                } else {
                    if (shouldBorn(livingNeighbours)) {
                        nextGenerationGrid[x][y] = 1;
                    } else {
                        nextGenerationGrid[x][y] = 0;
                    }
                }
            }
        }

        grid = nextGenerationGrid;
        generationCount++;
    }

    public boolean isCellAliveAt(int x, int y) {
        return isAlive(grid[x][y]);
    }

    private boolean shouldBorn(int livingNeighbours) {
        return livingNeighbours == MAX_NEIGHBOURS;
    }

    private boolean shouldDie(int livingNeighbours) {
        return livingNeighbours < MIN_NEIGHBOURS || livingNeighbours > MAX_NEIGHBOURS;
    }

    private int countLiveNeighbours(int x, int y) {
        int neighbours = 0;

        int minX = 0, minY = 0;
        int maxX = size - 1, maxY = size - 1;

        if (x > 0) {
            minX = x - 1;
        }

        if (x < size - 1) {
            maxX = x + 1;
        }

        if (y > 0) {
            minY = y - 1;
        }

        if (y < size - 1) {
            maxY = y + 1;
        }

        for (int lx = minX; lx <= maxX; lx++) {
            for (int ly = minY; ly <= maxY; ly++) {
                if (isAlive(grid[lx][ly]) && !(lx == x && ly == y)) {
                    neighbours++;
                }
            }
        }
        return neighbours;
    }

    private boolean isAlive(int i) {
        return i == 1;
    }

    @Override
    public String toString() {
        StringBuilder stringRepresentation = new StringBuilder();
        for (int x = 0; x < size; x++) {
            stringRepresentation.append("[");
            stringRepresentation.append(Ints.join(",", grid[x]));
            stringRepresentation.append("]\n");
        }
        return stringRepresentation.toString();
    }
}
