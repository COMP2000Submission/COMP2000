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

    // Checks that a position is inside the grid
    public void checkPosition(int row, int col) throws InvalidException {

        if (row < 0 || row >= ROWS ||
                col < 0 || col >= COLS) {

            throw new InvalidException(
                    "Invalid grid position: [" + row + "][" + col + "]");
        }
    }

    public void moveElement(int row, int col, int newRow, int newCol) throws InvalidException {
        // Check both positions
        checkPosition(row, col);
        checkPosition(newRow, newCol);
        // Check that there is actually an element to move
        if (grid[row][col] == null) {
            throw new InvalidException(
                    "Cannot move an empty cell.");
        }
        // Check that the destination is empty
        if (grid[newRow][newCol] != null) {
            throw new InvalidException(
                    "Cannot move element into an occupied cell.");
        }
        // Move element
        grid[newRow][newCol] = grid[row][col];
        grid[row][col] = null;
    }

    public void step() {
        frameCounter++;

        // Normal gravity
        for (int row = ROWS - 2; row >= 0; row--) {
            for (int col = 0; col < COLS; col++) {
                if (grid[row][col] != null &&
                        !grid[row][col].reverseGravity) {

                    // Fall straight down
                    if (grid[row + 1][col] == null) {
                        try {
                            moveElement(row, col, row + 1, col);
                        } catch (InvalidException e) {
                            System.out.println(
                                    "Gravity error: " + e.getMessage());
                        }
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
                        // 60% chance to move diagonally
                        if (Math.random() < 0.6) {
                            try {
                                reverse_side_gravity(row, col);
                            } catch (InvalidException e) {
                                System.out.println("Reverse side gravity error: " + e.getMessage());
                            }
                        }
                        // Otherwise move straight up
                        else if (grid[row - 1][col] == null) {
                            try {
                                moveElement(row, col, row - 1, col);
                            } catch (InvalidException e) {
                                System.out.println("Reverse gravity error: " + e.getMessage());
                            }
                        }
                    }
                }
            }
        }
    }

    public void side_gravity(int row, int col) {
        if (Math.random() < 0.5) {
            // Try down-left
            if (col > 0 && grid[row + 1][col - 1] == null) {
                grid[row + 1][col - 1] = grid[row][col];
                grid[row][col] = null;
            }
            // If left is blocked, try down-right
            else if (col < COLS - 1 && grid[row + 1][col + 1] == null) {
                grid[row + 1][col + 1] = grid[row][col];
                grid[row][col] = null;
            }
        } else {
            // Try down-right
            if (col < COLS - 1 && grid[row + 1][col + 1] == null) {
                grid[row + 1][col + 1] = grid[row][col];
                grid[row][col] = null;
            }
            // If right is blocked, try down-left
            else if (col > 0 && grid[row + 1][col - 1] == null) {

                grid[row + 1][col - 1] = grid[row][col];
                grid[row][col] = null;
            }
        }
    }

    // Reverse gravity:
    // 50% chance left, 50% chance right
    public void reverse_side_gravity(int row, int col) throws InvalidException {
        if (row - 1 < 0) {
            throw new InvalidException("Cannot move gas above the grid.");
        }
        if (Math.random() < 0.5) {
            if (col > 0 && grid[row - 1][col - 1] == null) {
                grid[row - 1][col - 1] = grid[row][col];
                grid[row][col] = null;
            }
            else if (col < COLS - 1 && grid[row - 1][col + 1] == null) {
                grid[row - 1][col + 1] = grid[row][col];
                grid[row][col] = null;
            }
        }
        else {
            if (col < COLS - 1 && grid[row - 1][col + 1] == null) {
                grid[row - 1][col + 1] = grid[row][col];
                grid[row][col] = null;
            }
            else if (col > 0 && grid[row - 1][col - 1] == null) {
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

// Custom exception for invalid grid positions
class InvalidException extends Exception {
    public InvalidException(String message) {
        super(message);
    }
}