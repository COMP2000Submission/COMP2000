public class Sandbox {

    static int WIDTH = 800; // Width and Height are changeable, not sure if this is too big.
    static int HEIGHT = 600;
    static int CELL_SIZE = 4;

    static int COLS = WIDTH / CELL_SIZE;
    static int ROWS = HEIGHT / CELL_SIZE;

    Elements[][] grid;

public Sandbox() {
        grid = new Elements[ROWS][COLS];
        grid[20][40] = new Sand();
    }

    public void step() {
        for (int row = ROWS - 2; row >= 0; row--) {
            for (int col = 0; col < COLS; col++) {

                // Check if  cell contains an element
                if (grid[row][col] != null) {

                    // Check if  cell below is empty
                    if (grid[row + 1][col] == null) {

                        // Gravity
                        grid[row + 1][col] = grid[row][col];
                        grid[row][col] = null;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Sandbox sandbox = new Sandbox();
        UI frame = new UI(WIDTH, HEIGHT, sandbox);
    }
}

