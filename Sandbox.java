public class Sandbox {

    static int WIDTH = 800;
    static int HEIGHT = 600;
    static int CELL_SIZE = 4;

    static int COLS = WIDTH / CELL_SIZE;
    static int ROWS = HEIGHT / CELL_SIZE;

    Elements[][] grid;

    public Sandbox() {
        grid = new Elements[ROWS][COLS];
        grid[20][40] = new Sand();
    }

    int frameCounter = 0;

    public void step() {
        frameCounter++;

        // Normal gravity
        // Normal gravity
        for (int row = ROWS - 2; row >= 0; row--) {
            for (int col = 0; col < COLS; col++) {

                if (grid[row][col] != null && !grid[row][col].reverseGravity) {
                    // Fall straight down
                    if (grid[row + 1][col] == null) {
                        grid[row + 1][col] = grid[row][col];
                    grid[row][col] = null;
                }

                    // If blocked, randomly choose left or right
                    else if (Math.random() < 0.5) {
                        // Left
                        if (col > 0 && grid[row + 1][col - 1] == null) {
                        grid[row + 1][col - 1] = grid[row][col];
                        grid[row][col] = null;
                        }

                        // Right if left is blocked
                        else if (col < COLS - 1 && grid[row + 1][col + 1] == null) {
                            grid[row + 1][col + 1] = grid[row][col];
                            grid[row][col] = null;
                        }
                    }
                    else {
                        // Right
                        if (col < COLS - 1 &&
                            grid[row + 1][col + 1] == null) {
                            grid[row + 1][col + 1] = grid[row][col];
                            grid[row][col] = null;
                        }

                        // Left if right is blocked
                        else if (col > 0 && grid[row + 1][col - 1] == null) {
                            grid[row + 1][col - 1] = grid[row][col];
                            grid[row][col] = null;
                        }
                    }
                }
            }
        }
        
        // Reverse gravity - only move every 5 frames as the gas would teleport to the top overwise
        if (frameCounter % 5 == 0) {

            for (int row = 1; row < ROWS; row++) {
                for (int col = 0; col < COLS; col++) {

                    if (grid[row][col] != null &&
                        grid[row][col].reverseGravity) {

                        if (grid[row - 1][col] == null) {
                            grid[row - 1][col] = grid[row][col];
                            grid[row][col] = null;
                        }
                    }
                }
            }
        }
    }
}
