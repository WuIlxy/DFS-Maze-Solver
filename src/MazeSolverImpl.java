public class MazeSolverImpl {

    /**
     * You should write your code within this method. A good rule of thumb, especially with
     * recursive problems like this, is to write your input exception handling within this
     * method and then use helper methods to carry out the actual recursion.
     * <p>
     * How you set up the recursive methods is up to you, but note that since this is a *static*
     * method, all helper methods that it calls must *also* be static. Make them package-private
     * (i.e. without private or public modifiers) so you can test them individually.
     *
     * @param maze See the writeup for more details about the format of the input maze.
     * @param sourceCoord The source (starting) coordinate
     * @param goalCoord The goal (ending) coordinate
     * @return a matrix of the same dimension as the input maze containing the solution
     * path marked with 1's, or null if no path exists. See the writeup for more details.
     * @throws IllegalArgumentException in the following instances:
     * 1. If the maze is null
     * 2. If m * n <= 1 where m and n are the dimensions of the maze
     * 3. If either the source coordinate OR the goal coordinate are out of the matrix bounds.
     * 4. If your source or goal coordinate are on a blocked tile.
     */
    public static int[][] solveMaze(int[][] maze, Coordinate sourceCoord, Coordinate goalCoord) {
        int sx = sourceCoord.getX();
        int sy = sourceCoord.getY();
        int gx = goalCoord.getX();
        int gy = goalCoord.getY();
        if (maze == null) {
            throw new IllegalArgumentException();
        }
        // If the maze is empty
        if (maze.length == 0) {
            throw new IllegalArgumentException();
        }
        // If m * n <= 1 where m and n are the dimensions of the maze
        if (maze.length * maze[0].length <= 1) {
            throw new IllegalArgumentException();
        }
        // If either the source coordinate OR the goal coordinate are out of the matrix bounds.
        if (sx < 0 || sy < 0 || gx < 0 || gy < 0 || sx >= maze[0].length || sy >= maze.length
                || gx >= maze[0].length || gy >= maze.length) {
            throw new IllegalArgumentException();
        }
        // If your source or goal coordinate are on a blocked tile.
        if (maze[sy][sx] == 1 || maze[gy][gx] == 1) {
            throw new IllegalArgumentException();
        }
        int[][] solution = new int[maze.length][maze[0].length];
        if (pathFinder(maze, sx, sy, gx, gy, solution)) {
            return solution;
        } else {
            return null;
        }
    }

    // This is the helper method that traverses the maze
    static boolean pathFinder(int[][] maze, int sx, int sy, int gx, int gy, int[][] solution) {
        // check if current location out of bound of the maze, or at the block
        if (sx < 0 || sy < 0 || sx >= maze[0].length || sy >= maze.length ||
                maze[sy][sx] == 1) {
            return false;
        }
        // check if current location is the goal
        if (sx == gx && sy == gy) {
            solution[sy][sx] = 1;
            return true;
        }
        // check whether current location is visited
        if (solution[sy][sx] == 1) {
            return false;
        }
        // after all cases, mark this location
        solution[sy][sx] = 1;
        //move down
        if (pathFinder(maze, sx, sy + 1, gx, gy, solution)) {
            return true;
        } else if (pathFinder(maze, sx, sy - 1, gx, gy, solution)) { //move up
            return true;
        } else if (pathFinder(maze, sx - 1, sy, gx, gy, solution)) { //move left
            return true;
        } else if (pathFinder(maze, sx + 1, sy, gx, gy, solution)) { //move right
            return true;
        } else { // if no more moves, unmark this position, recurse back
            solution[sy][sx] = 0;
            return false;
        }
    }
}
