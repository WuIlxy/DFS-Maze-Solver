import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MazeSolverImplTest {

    private int[][] smallWriteupMaze;
    private int[][] bigWriteupMaze;

    @Before
    public void setupTestMazes() {
        smallWriteupMaze = new int[][]{
                {0, 0, 0, 0},
                {1, 1, 0, 0},
                {0, 0, 0, 1},
                {0, 0, 1, 0}
        };

        bigWriteupMaze = new int[][]{
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 0, 1, 1, 0},
                {0, 0, 0, 1, 0, 1, 0, 1, 1, 0},
                {1, 1, 0, 1, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 1, 1, 0, 1, 0},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

    }

    @Test
    public void testReturnsSmallMazeSolutionPathInWriteup() {
        int[][] solutionPath = {
                {1, 1, 1, 0},
                {0, 0, 1, 0},
                {1, 1, 1, 0},
                {1, 1, 0, 0}
        };
        assertArrayEquals(solutionPath, MazeSolverImpl.solveMaze(smallWriteupMaze,
                new Coordinate(0, 0), new Coordinate(0, 2)));
    }

    @Test
    public void testReturnsBigMazeSolutionPathInWriteup() {
        int[][] bigWriteupSolution = new int[][]{
                {1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int[][] returnedPath = MazeSolverImpl.solveMaze(bigWriteupMaze, new Coordinate(2, 0),
                new Coordinate(4, 0));
        assertArrayEquals(bigWriteupSolution, returnedPath);
    }

    /*
      Note: the above tests are the ones included in the writeup and NOT exhaustive. The autograder
      uses other test cases not listed above. Please thoroughly read all stub files, including
      CoordinateTest.java!

      For help with creating test cases, please see this link:
      https://www.seas.upenn.edu/~cis121/current/testing_guide.html
     */


    @Test (expected = IllegalArgumentException.class)
    public void testNullArray() {
        int[][] nullArray;
        nullArray = null;
        MazeSolverImpl.solveMaze(nullArray, new Coordinate(0,0), new Coordinate(5,5));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testInvalidDimension() {
        int[][] maze = new int[1][1];
        MazeSolverImpl.solveMaze(maze, new Coordinate(0,0), new Coordinate(0,0));
    }
    //the following 8 tests check either the source coordinate or the goal coordinate are
    // out of the matrix bounds one by one
    @Test (expected = IllegalArgumentException.class)
    public void testSourceSmallX() {
        int[][] maze = new int[2][2];
        MazeSolverImpl.solveMaze(maze, new Coordinate(-1,1), new Coordinate(0,0));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSourceLargeX() {
        int[][] maze = new int[2][2];
        MazeSolverImpl.solveMaze(maze, new Coordinate(3,1), new Coordinate(0,0));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGoalSmallX() {
        int[][] maze = new int[2][2];
        MazeSolverImpl.solveMaze(maze, new Coordinate(0,0), new Coordinate(-1,1));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGoalLargeX() {
        int[][] maze = new int[2][2];
        MazeSolverImpl.solveMaze(maze, new Coordinate(0,0), new Coordinate(3,1));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSourceSmallY() {
        int[][] maze = new int[2][2];
        MazeSolverImpl.solveMaze(maze, new Coordinate(1,-1), new Coordinate(0,0));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSourceLargeY() {
        int[][] maze = new int[2][3];
        MazeSolverImpl.solveMaze(maze, new Coordinate(1,3), new Coordinate(0,0));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGoalSmallY() {
        int[][] maze = new int[2][3];
        MazeSolverImpl.solveMaze(maze, new Coordinate(0,0), new Coordinate(1,-1));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGoalLargeY() {
        int[][] maze = new int[2][2];
        MazeSolverImpl.solveMaze(maze, new Coordinate(0,0), new Coordinate(1,3));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSourceBlocked() {
        int[][] maze = {
                {1, 0},
                {0, 1}
        };
        MazeSolverImpl.solveMaze(maze, new Coordinate(0,0), new Coordinate(0,1));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGoalBlocked() {
        int[][] maze = {
                {1, 0},
                {0, 1}
        };
        MazeSolverImpl.solveMaze(maze, new Coordinate(1, 0), new Coordinate(1,1));
    }

    @Test
    public void testNoSolutionMaze() {
        int[][] maze = {
                {0, 1, 0},
                {1, 0, 1},
                {1, 1, 0}
        }; //should return null since it cannot go diagonally
        assertNull(MazeSolverImpl.solveMaze(maze, new Coordinate(0,0), new Coordinate(2,2)));
    }

    @Test
    public void testNoSolutionMaze2() {
        int[][] maze = {
                {0, 1, 1},
                {1, 1, 1},
                {1, 1, 0}
        };
        assertNull(MazeSolverImpl.solveMaze(maze, new Coordinate(2,2), new Coordinate(0,0)));
    }

    @Test
    public void testSourceEqualGoal() {
        int[][] maze = {
                {0, 1, 0},
                {1, 0, 1},
                {1, 0, 1}
        };
        int[][] solution = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        }; //only the start should be marked
        assertArrayEquals(solution, MazeSolverImpl.solveMaze(maze, new Coordinate(1,1),
                new Coordinate(1,1)));
    }

    @Test
    public void testAllZeros() {
        int[][] maze = new int[3][3];
        int[][] solution = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };  //it should traverse all possible paths
        assertArrayEquals(solution, MazeSolverImpl.solveMaze(maze, new Coordinate(0,0),
                new Coordinate(2,2)));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyMaze() {
        int[][] maze = new int[0][0];
        MazeSolverImpl.solveMaze(maze, new Coordinate(0,0), new Coordinate(0,0));
    }

    @Test
    public void testAllZerosRectangle() {
        int[][] maze = new int[3][5];
        int[][] solution = {
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };  //it should traverse all possible paths
        assertArrayEquals(solution, MazeSolverImpl.solveMaze(maze, new Coordinate(0,0),
                new Coordinate(4,2)));
    }

    @Test
    public void testUnmarkPosition() {
        int[][] maze = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 1, 0}
        };
        int[][] solution = {
                {1, 1, 1},
                {0, 0, 1},
                {0, 0, 1}
        }; //since it first tries to go down, it will go to the end of the first column then unmark
        // back to the source and go right.
        assertArrayEquals(solution, MazeSolverImpl.solveMaze(maze, new Coordinate(0,0),
                new Coordinate(2,2)));
    }

    @Test
    public void testRectangle() {
        int[][] maze =  {
                {1, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 1}
        };
        int[][] solution = {
                {0, 1, 0, 0, 1, 0},
                {0, 1, 1, 1, 1, 0}
        };
        assertArrayEquals(solution, MazeSolverImpl.solveMaze(maze, new Coordinate(1, 0),
                new Coordinate(4, 0)));
    }

    @Test
    public void testRectangle2() {
        int[][] maze = {
                {0, 0, 0, 1},
                {1, 1, 0, 1},
                {0, 0, 0, 0},
                {1, 0, 1, 0},
                {1, 0, 0, 0}
        };
        int[][] solution = {
                {1, 1, 1, 0},
                {0, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 1, 0, 0},
                {0, 1, 1, 1}
        };
        assertArrayEquals(solution, MazeSolverImpl.solveMaze(maze, new Coordinate(0,0),
                new Coordinate(3, 4)));
    }

    @Test
    public void testLoopInRectangle() {
        int[][] maze = {
                {0, 1, 1, 0},
                {0, 0, 0, 0},
                {0, 1, 1, 1},
                {0, 0, 0, 1},
                {0, 1, 0, 1},
                {0, 0, 0, 1}
        };
        int[][] solution = {
                {1, 0, 0, 1},
                {1, 1, 1, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        }; //maze idea by ed discussion post
        assertArrayEquals(solution, MazeSolverImpl.solveMaze(maze, new Coordinate(0,0),
                new Coordinate(3, 0)));
    }

    @Test
    public void testSingleRow() {
        int[][] maze = {{0, 0, 0, 0, 0}};
        int[][] solution = {{0, 1, 1, 1, 1}};
        assertArrayEquals(solution, MazeSolverImpl.solveMaze(maze, new Coordinate(1,0),
                new Coordinate(4,0)));
    }

    @Test
    public void testSingleColumn() {
        int[][] maze = {
                {0},
                {0},
                {0},
                {0},
                {0}
        };
        int[][] solution = {
                {0},
                {1},
                {1},
                {1},
                {1}
        };
        assertArrayEquals(solution, MazeSolverImpl.solveMaze(maze, new Coordinate(0,1),
                new Coordinate(0,4)));
    }
}
