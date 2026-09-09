public class Sandbox {

    static int WIDTH = 800;
    static int HEIGHT = 600;
    static int CELL_SIZE = 4;

    static int COLS = WIDTH / CELL_SIZE;
    static int ROWS = HEIGHT / CELL_SIZE;

    Elements[][] grid;

    Tester<Elements> tester = new Tester<>();

    public Sandbox() {
        tester.set(new Gas());
        grid = new Elements[ROWS][COLS];
        grid[20][40] = tester.get();
    }

    int frameCounter = 0;

    public void step() {
        frameCounter++;

        // Normal gravity
        for (int row = ROWS - 2; row >= 0; row--) {
            for (int col = 0; col < COLS; col++) {

                if (grid[row][col] != null &&
                    !grid[row][col].reverseGravity) {

                    // Fall straight down
                    if (grid[row + 1][col] == null) {

                        grid[row + 1][col] = grid[row][col];
                        grid[row][col] = null;

                    }

                    // If blocked, move diagonally
                    else {
                        side_gravity(row, col);
                    }
                }
            }
        }


        // Reverse gravity
        if (frameCounter % 2 == 0) {
            for (int row = 1; row < ROWS; row++) {
                for (int col = 0; col < COLS; col++) {
                    if (grid[row][col] != null && grid[row][col].reverseGravity) {
                        if (Math.random() < 0.6) {
                            reverse_side_gravity(row, col);
                        }
                        else if (grid[row - 1][col] == null) {
                            grid[row - 1][col] = grid[row][col];
                            grid[row][col] = null;
                        }
                    }
                }
            }
        }       
    }

    public void side_gravity(int row, int col) {
        if (Math.random() < 0.5) {
            // Try down-left
            if (col > 0 &&
                grid[row + 1][col - 1] == null) {

                grid[row + 1][col - 1] = grid[row][col];
                grid[row][col] = null;
            }
            // If left is blocked, try down-right
            else if (col < COLS - 1 &&
                     grid[row + 1][col + 1] == null) {

                grid[row + 1][col + 1] = grid[row][col];
                grid[row][col] = null;
            }
        } else {
            // Try down-right
            if (col < COLS - 1 &&
                grid[row + 1][col + 1] == null) {

                grid[row + 1][col + 1] = grid[row][col];
                grid[row][col] = null;
            }
            // If right is blocked, try down-left
            else if (col > 0 &&
                     grid[row + 1][col - 1] == null) {

                grid[row + 1][col - 1] = grid[row][col];
                grid[row][col] = null;
            }
        }
    }


    // Reverse gravity:
    // 50% chance left, 50% chance right
    public void reverse_side_gravity(int row, int col) {

        if (Math.random() < 0.5) {

            // Try up-left
            if (col > 0 &&
                grid[row - 1][col - 1] == null) {

                grid[row - 1][col - 1] = grid[row][col];
                grid[row][col] = null;
            }

            // If left is blocked, try up-right
            else if (col < COLS - 1 &&
                     grid[row - 1][col + 1] == null) {

                grid[row - 1][col + 1] = grid[row][col];
                grid[row][col] = null;
            }

        } else {

            // Try up-right
            if (col < COLS - 1 &&
                grid[row - 1][col + 1] == null) {

                grid[row - 1][col + 1] = grid[row][col];
                grid[row][col] = null;
            }

            // If right is blocked, try up-left
            else if (col > 0 &&
                     grid[row - 1][col - 1] == null) {

                grid[row - 1][col - 1] = grid[row][col];
                grid[row][col] = null;
            }
        }
    }


    // Generic tester class
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