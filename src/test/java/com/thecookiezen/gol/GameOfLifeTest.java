package com.thecookiezen.gol;

import core.Grid;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author nikom
 * @author cartesinus
 */
public class GameOfLifeTest {

    private int[][] testGrid = new int[][]{
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0}
    };

    @Test
    public void shouldDiesWhenNoNeighboursAndInTheMiddle() {
        // given
        testGrid[3][3] = 1;

        // when
        Grid grid = new Grid(testGrid);
        grid.nextGeneration();

        //then
        assertThat(grid.isCellAliveAt(3, 3), is(false));
    }

    @Test
    public void shouldLiveWhenTwoNeighboursAreLivingCreatures() {
        // given
        testGrid[0][0] = 1;
        testGrid[1][0] = 1;
        testGrid[1][1] = 1;

        // when
        Grid grid = new Grid(testGrid);
        grid.nextGeneration();

        //then
        assertThat(grid.isCellAliveAt(0, 0), is(true));
        assertThat(grid.isCellAliveAt(1, 0), is(true));
        assertThat(grid.isCellAliveAt(1, 1), is(true));
    }

    @Test
    public void shouldDieWhenMoreThanThreeNeighbours() {
        // given
        testGrid[1][1] = 1;

        testGrid[0][0] = 1;
        testGrid[0][1] = 1;
        testGrid[0][2] = 1;
        testGrid[1][0] = 1;

        // when
        Grid grid = new Grid(testGrid);
        grid.nextGeneration();

        //then
        assertThat(grid.isCellAliveAt(1, 1), is(false));
    }

    @Test
    public void shouldLiveWhenTwoNeighbours() {
        //given
        testGrid[2][2] = 1;

        testGrid[3][2] = 1;
        testGrid[2][1] = 1;

        //when
        Grid grid = new Grid(testGrid);
        grid.nextGeneration();

        //then
        assertThat(grid.isCellAliveAt(2, 2), is(true));
    }

    @Test
    public void shouldLiveWhenThreeNeighbours() {
        //given
        testGrid[2][2] = 1;

        testGrid[3][2] = 1;
        testGrid[2][1] = 1;
        testGrid[1][1] = 1;

        //when
        Grid grid = new Grid(testGrid);
        grid.nextGeneration();

        //then
        assertThat(grid.isCellAliveAt(2, 2), is(true));
    }

    @Test
    public void shouldBornWhenThreeNeighbours() {
        //given

        testGrid[3][2] = 1;
        testGrid[2][1] = 1;
        testGrid[1][1] = 1;

        //when
        Grid grid = new Grid(testGrid);
        grid.nextGeneration();

        //then
        assertThat(grid.isCellAliveAt(2, 2), is(true));
    }
}
