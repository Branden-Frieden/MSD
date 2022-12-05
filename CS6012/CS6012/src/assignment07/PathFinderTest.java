package assignment07;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PathFinderTest {

    PathFinder pathFind;
    @BeforeEach
    void setUp() {
        pathFind = new PathFinder();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void SolveMaze() throws IOException {
        PathFinder.solveMaze("src/assignment07/mazes/classic.txt", "src/assignment07/solution");
        byte[] mySol = new FileInputStream("src/assignment07/solution").readAllBytes();
        byte[] Sol =  new FileInputStream("src/assignment07/mazes/classicSol.txt").readAllBytes();
        for(int i = 0; i < mySol.length; i++) {
            assertEquals(mySol[i], Sol[i]);
        }

        PathFinder.solveMaze("src/assignment07/mazes/bigMaze.txt", "src/assignment07/solution");
        mySol = new FileInputStream("src/assignment07/solution").readAllBytes();
        Sol =  new FileInputStream("src/assignment07/mazes/bigMazeSol.txt").readAllBytes();
        for(int i = 0; i < mySol.length; i++) {
            assertEquals(mySol[i], Sol[i]);
        }

        PathFinder.solveMaze("src/assignment07/mazes/unsolvable.txt", "src/assignment07/solution");
        mySol = new FileInputStream("src/assignment07/solution").readAllBytes();
        Sol =  new FileInputStream("src/assignment07/mazes/unsolvableSol.txt").readAllBytes();
        for(int i = 0; i < mySol.length; i++) {
            assertEquals(mySol[i], Sol[i]);
        }

    }
}