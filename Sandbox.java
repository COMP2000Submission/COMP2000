import javax.swing.Box;

public class Sandbox {

    static int WIDTH = 800;
    static int HEIGHT = 600;
    static int CELL_SIZE = 4;

    static int COLS = WIDTH / CELL_SIZE;
    static int ROWS = HEIGHT / CELL_SIZE;

    Elements[][] grid;

    Tester<Elements> Tester = new Tester<Elements>();

    public Sandbox() {
        Tester.set(new Sand());
        grid = new Elements[ROWS][COLS];
        grid[20][40] = Tester.get();
    }

    int frameCounter = 0;

    public void step() {
        frameCounter++;

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
                    else {
                        side_gravity(row, col);
                    }
                }
            }
        }
        
        // Reverse gravity - only move every 5 frames as the gas would teleport to the top overwise
            if (frameCounter % 2 == 0) {
            for (int row = 1; row < ROWS; row++) {
                for (int col = 0; col < COLS; col++) {
                    if (grid[row][col] != null && grid[row][col].reverseGravity) {
                        if (grid[row - 1][col] == null) {
                            grid[row - 1][col] = grid[row][col];
                            grid[row][col] = null;
                        } else {
                            side_gravity(row, col);
                        }
                    }
                }
            }
        }
    }

        public void side_gravity(int row, int col) {
    if (Math.random() < 0.5) {
        if (col > 0 && grid[row + 1][col - 1] == null) {
            grid[row + 1][col - 1] = grid[row][col];
            grid[row][col] = null;
        }
        else if (col < COLS - 1 && grid[row + 1][col + 1] == null) {
                grid[row + 1][col + 1] = grid[row][col];
                grid[row][col] = null;
            }
        }
        else {
            if (col < COLS - 1 && grid[row + 1][col + 1] == null) {
                grid[row + 1][col + 1] = grid[row][col];
                grid[row][col] = null;
            }
            else if (col > 0 && grid[row + 1][col - 1] == null) {
                grid[row + 1][col - 1] = grid[row][col];
                grid[row][col] = null;
            }
        }
    }

    class Tester<T> {
        T value;
        void set(T value) {
            this.value = value;
        }
        T get() {
            return value;
        }
    }
}
